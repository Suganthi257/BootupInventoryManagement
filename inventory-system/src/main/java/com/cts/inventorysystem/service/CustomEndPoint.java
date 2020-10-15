
package com.cts.inventorysystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class CustomEndPoint implements HealthIndicator {

	@Autowired
	private ProductServiceProxy productServiceProxy;

	private final String message_key = "ProductCatalog-Service";

	@Override
	public Health health() {
		if (!isInventoryServiceRunning()) {
			return Health.down().withDetail(message_key, "Not Available").build();
		}
		return Health.up().withDetail(message_key, "Available").build();
	}

	private Boolean isInventoryServiceRunning() {
		return productServiceProxy.ping();
	}
}