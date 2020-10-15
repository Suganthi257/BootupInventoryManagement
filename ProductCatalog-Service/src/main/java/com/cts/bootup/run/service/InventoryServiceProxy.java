
package com.cts.bootup.run.service;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "inventory-service", url = "http://localhost:8050")
@RibbonClient(name = "inventory-proxy")
public interface InventoryServiceProxy {

	@GetMapping("/api/inventory/ping")
	public Boolean ping();
}
