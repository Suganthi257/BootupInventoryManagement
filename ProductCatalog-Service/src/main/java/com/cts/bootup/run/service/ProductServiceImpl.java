
package com.cts.bootup.run.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.cts.bootup.run.config.ConfigProperties;
import com.cts.bootup.run.dao.ProductDao;
import com.cts.bootup.run.entity.Product;
import com.cts.bootup.run.exception.BannedProductException;

@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	private ProductDao productDao;

	@Autowired
	private ConfigProperties configProperties;

	@Override
	public List<Product> getAllProducts() {
		return productDao.findAll();
	}

	@Override
	public Optional<Product> getProductByProductId(long id) {
		try {
			Thread.sleep(100 * 5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return productDao.findById(id);
	}

	@Override
	public List<Product> getProductByProductName(String name) {
		return productDao.findByName(name);
	}

	@Override
	public Product save(Product product) throws BannedProductException {
		String bannedProd = configProperties.getBanned();
		if (product.getName().equalsIgnoreCase(bannedProd)) {
			throw new BannedProductException("Product category Grocery not allowed");
		}

		return productDao.save(product);
	}

	@Override
	public List<Product> saveAll(List<Product> products) {
		return productDao.saveAll(products);
	}

	@Override
	public void deleteProductByProductId(long id) {
		productDao.deleteById(id);
	}

	@Cacheable("productdid")
	@Override
	public Double getPriceByProductId(long id) {
		Optional<Product> product = productDao.findById(id);
		Product prod = product.orElse(null);
		if (prod != null) {
			return prod.getPrice();
		}
		return null;
	}

	@CacheEvict(value = "productdid", allEntries = true)
	@Scheduled(fixedDelay = 1800000)
	public void resetAllEntries() {
		System.out.println("Cache flush");
	}

	@Override
	public Product updatePrice(long id, double price) {
		Optional<Product> product = productDao.findById(id);
		Product prod = product.orElse(null);
		if (prod != null) {
			prod.setPrice(price);
			Product savedProd = productDao.save(prod);
			return savedProd;
		}
		return null;
	}

	@Override
	public ResponseEntity<Product> getProductByIdForRetailPartner(long id) {
		if (id == 1) {
			return ResponseEntity.status(HttpStatus.MOVED_TEMPORARILY).build();
		}
		Product product = productDao.findById(id).orElse(null);
		if (product == null) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
		return ResponseEntity.ok().body(product);
	}

}
