
package com.cts.inventorysystem.interfaces;

import java.io.Serializable;

import org.springframework.stereotype.Component;


@Component
public interface ProductManager extends Serializable
{
	
	 public void increasePrice(int percentage);
	    
	    //public List<Product> getProducts();

}
