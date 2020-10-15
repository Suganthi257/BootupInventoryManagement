
package com.cts.bootup.run.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cts.bootup.run.entity.Product;

public interface ProductDao extends JpaRepository<Product, Long>{

	@Query(value = "FROM Product u where lower(u.name) like %:name%")
	List<Product> findByName(String name);
	

}
