
package com.cts.bootup.run.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.bootup.run.entity.Product;
import com.cts.bootup.run.exception.BannedProductException;
import com.cts.bootup.run.service.ProductService;

@RestController
@CrossOrigin
@RequestMapping("/api/products")
public class ProductRestController {
	@Autowired
	private ProductService productService;
	
	@GetMapping("/ping")
	public Boolean ping() {
		return true;
	}
	
	@GetMapping("/")
	public List<Product> getAllProducts() {
		return productService.getAllProducts();
	}
	
	@GetMapping("/{id}")
	public Product getProductById(@PathVariable("id") long id) {
		Optional<Product> optional = productService.getProductByProductId(id);
		return optional.orElse(null);
	}
	
	@GetMapping("/retail/{id}")
	public ResponseEntity<Product> getProductByIdForRetailPartner(@PathVariable("id") long id) {
		return productService.getProductByIdForRetailPartner(id);
	}
	
	@PostMapping("/")
	public ResponseEntity<Product> saveProduct(@RequestBody Product product) {
		try {
			Product savedProd = productService.save(product);
			return ResponseEntity.ok(savedProd);
		} catch (BannedProductException e) {
			return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).build();
		}
	}
	
	@PostMapping("/all")
	public List<Product> saveProduct(@RequestBody List<Product> products) {
		return productService.saveAll(products);
	}
	
	@DeleteMapping("/{id}")
	public void deleteProductById(@PathVariable("id") long id) {
		productService.deleteProductByProductId(id);
	}
	
	@GetMapping("/price/{id}")
	public Double getPriceByProductId(@PathVariable("id") long id) {
		return productService.getPriceByProductId(id);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Product> updateProduct(@RequestBody Product product) {
		try {
			Product savedProd = productService.save(product);
			return ResponseEntity.ok(savedProd);
		} catch (BannedProductException e) {
			return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).build();
		}
	}
	
	@PutMapping("/{id}/{price}")
	public Product updatePrice(@PathVariable("id") long id, @PathVariable("price") double price) {
		return productService.updatePrice(id, price);
	}

}
