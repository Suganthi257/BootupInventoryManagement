
package com.cts.bootup.run.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;

import com.cts.bootup.run.entity.Product;
import com.cts.bootup.run.exception.BannedProductException;

public interface ProductService {
	
	List<Product> getAllProducts();

	Optional<Product> getProductByProductId(long id);

	List<Product> getProductByProductName(String name);

	Product save(Product product) throws BannedProductException;

	List<Product> saveAll(List<Product> products);

	void deleteProductByProductId(long id);

	Double getPriceByProductId(long id);

	Product updatePrice(long id, double price);

	ResponseEntity<Product> getProductByIdForRetailPartner(long id);

}