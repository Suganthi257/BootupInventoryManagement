
package com.cts.inventorysystem.service;

import java.util.List;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.cts.inventorysystem.model.Product;

@FeignClient(name="product-service", url = "http://localhost:8081")
@RibbonClient(name="product-proxy")
public interface ProductServiceProxy {
	
	@GetMapping(value = "/api/products/{id}")
	public Product findProductById(@PathVariable("id") long id);
	
	@GetMapping(value = "/api/products/")
	public List<Product> findAll();

	@PostMapping(value = "/api/products/")
	public void save(Product product);

	@DeleteMapping("/api/products/{id}")
	public void deleteById(@PathVariable("id") long id);

	@GetMapping("/api/products/ping")
	public Boolean ping();
}
