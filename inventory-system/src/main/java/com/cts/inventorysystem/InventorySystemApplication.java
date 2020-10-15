/* 
 * All rights reserved
 * @version 1.0
 * @author Cognizant
 */
package com.cts.inventorysystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class InventorySystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventorySystemApplication.class, args);
	}

}
