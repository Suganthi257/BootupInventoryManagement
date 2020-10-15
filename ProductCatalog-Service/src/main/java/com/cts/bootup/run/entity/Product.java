
package com.cts.bootup.run.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "PRODUCT")
@DynamicUpdate
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	@Column(name = "product_id")
	private long id;
	private String name;
	private double price;
	private String description;

	public Product() {
		super();
	}
	
	public Product(String name) {
		super();
		this.name = name;
	}
	
	public Product(String name, double price, String description) {
		super();
		this.name = name;
		this.price = price;
		this.description = description;
	}

	public Product(long id, String name, double price, String description) {
		this();
		this.id = id;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
