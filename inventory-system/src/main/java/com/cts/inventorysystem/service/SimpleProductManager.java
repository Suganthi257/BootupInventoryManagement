
package com.cts.inventorysystem.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.inventorysystem.interfaces.ProductManager;
import com.cts.inventorysystem.model.Product;



@Service
public class SimpleProductManager implements ProductManager
{
	
	
	
	@Autowired
	private	ProductServiceProxy productServiceProxy;

	
    public void increasePrice(int percentage) {
    	
    	
    	
    	System.out.println("percent"+percentage);
    	 List<Product> productList = new ArrayList<Product>();
    	 
    	              
    	 productList.addAll(productServiceProxy.findAll());
    	 
    	 
        if (productServiceProxy != null) {
            for (Product product : productList) 
            {
            	System.out.println(product.getDescription());
               	
               Double newPrice = product.getPrice() * (100 + percentage)/100;
                
                System.out.println("newPrice"+newPrice);
                
                product.setPrice(newPrice);
                
                product.setId(product.getId());
                
                product.setDescription(product.getDescription());
                
               
              
                System.out.println(product.getId()+""+product.getDescription());
            
               //calling the update save method in dao repos
                productServiceProxy.save(product);
            
            }
            
            
        }
       
    }
    
}
