package com.cts.bootup.run;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.cts.bootup.run.config.ConfigProperties;
import com.cts.bootup.run.dao.ProductDao;
import com.cts.bootup.run.entity.Product;
import com.cts.bootup.run.service.ProductService;
import com.cts.bootup.run.service.ProductServiceImpl;

@ExtendWith(SpringExtension.class)
//@RunWith(SpringRunner.class)
@DataJpaTest
@TestPropertySource("classpath:banned-products.properties")
public class ProductServiceIntegrationTest {
	
	@TestConfiguration
    static class ProductServiceImplTestContextConfiguration {
        @Bean
        public ProductService productService() {
            return new ProductServiceImpl();
        }
    }

	@Autowired
    private ProductService productService;
	
	@MockBean
	private ConfigProperties configProperties;

    @MockBean
    private ProductDao productDao;

    @BeforeEach
    public void setUp() {
        Product john = new Product("john");
        john.setId(11L);

        Product bob = new Product("bob");
        Product alex = new Product("alex");

        List<Product> allEmployees = Arrays.asList(john, bob, alex);

        Mockito.when(productDao.findById(john.getId())).thenReturn(Optional.of(john));
        Mockito.when(productDao.findAll()).thenReturn(allEmployees);
        Mockito.when(productDao.findById(-99L)).thenReturn(Optional.empty());
    }

    @Test
    public void whenValidId_thenEmployeeShouldBeFound() {
        Optional<Product> fromDb = productService.getProductByProductId(11L);
        Product product = fromDb.orElse(null);
        assertThat(product.getName()).isEqualTo("john");
        verifyFindByIdIsCalledOnce();
    }

    @Test
    public void whenInValidId_thenEmployeeShouldNotBeFound() {
        Optional<Product> fromDb = productService.getProductByProductId(-99L);
        Product product = fromDb.orElse(null);
        verifyFindByIdIsCalledOnce();
        assertThat(product).isNull();
    }

    @Test
    public void given3Employees_whengetAll_thenReturn3Records() {
        Product alex = new Product("alex");
        Product john = new Product("john");
        Product bob = new Product("bob");

        List<Product> allEmployees = productService.getAllProducts();
        verifyFindAllEmployeesIsCalledOnce();
        assertThat(allEmployees).hasSize(3).extracting(Product::getName).contains(alex.getName(), john.getName(), bob.getName());
    }

    private void verifyFindByNameIsCalledOnce(String name) {
        Mockito.verify(productDao, VerificationModeFactory.times(1)).findByName(name);
        Mockito.reset(productDao);
    }

    private void verifyFindByIdIsCalledOnce() {
        Mockito.verify(productDao, VerificationModeFactory.times(1)).findById(Mockito.anyLong());
        Mockito.reset(productDao);
    }

    private void verifyFindAllEmployeesIsCalledOnce() {
        Mockito.verify(productDao, VerificationModeFactory.times(1)).findAll();
        Mockito.reset(productDao);
    }
}
