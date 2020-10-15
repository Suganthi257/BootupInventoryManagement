package com.cts.bootup.run;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParseException;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.cts.bootup.run.controller.ProductRestController;
import com.cts.bootup.run.entity.Product;
import com.cts.bootup.run.service.ProductService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

//@SpringBootTest
@AutoConfigureMockMvc
@WebMvcTest(value = ProductRestController.class)
public class ProductRestControllerTest {

	@Autowired
	protected MockMvc mvc;
	@Autowired
	WebApplicationContext webApplicationContext;
	@MockBean
	private ProductService productService;

	protected void setUp() {
		mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	protected String mapToJson(Object obj) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(obj);
	}

	protected <T> T mapFromJson(String json, Class<T> clazz)
			throws JsonParseException, JsonMappingException, IOException {

		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.readValue(json, clazz);
	}
	
	@Test
    public void testSaveProduct() throws Exception {
        Product prod = new Product(1, "IPad", 1000, "Tablet");
        given(productService.save(Mockito.any())).willReturn(prod);
        mvc.perform(post("/api/products/").contentType(MediaType.APPLICATION_JSON).content(mapToJson(prod))).andExpect(status().isOk()).andExpect(jsonPath("$.name", is("IPad")));
        verify(productService, VerificationModeFactory.times(1)).save(Mockito.any());
        reset(productService);
    }
	
	@Test
    public void testGetAllProduct() throws Exception {
        Product prod1 = new Product(1, "IPad", 1000, "Tablet");
        Product prod2 = new Product(2, "Thinkpad", 1000, "Laptop");
        List<Product> list = new ArrayList<>();
        list.add(prod1);
        list.add(prod2);
        given(productService.getAllProducts()).willReturn(list);
        mvc.perform(get("/api/products/").contentType(MediaType.APPLICATION_JSON)).andExpect(jsonPath("$", hasSize(2))).andExpect(jsonPath("$[0].name", is(prod1.getName()))).andExpect(jsonPath("$[1].name", is(prod2.getName())));
        verify(productService, VerificationModeFactory.times(1)).getAllProducts();
        reset(productService);
    }
}
