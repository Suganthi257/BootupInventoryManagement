
package com.cts.inventorysystem.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/api/inventory")
public class InventoryRestController {

	@GetMapping("/ping")
	public boolean ping() {
		return true;
	}

}
