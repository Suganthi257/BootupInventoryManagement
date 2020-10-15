
package com.cts.bootup.run.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:banned-products.properties")
public class ConfigProperties {

	@Value("${products.banned}")
	private String banned;

	public String getBanned() {
		return banned;
	}

	public void setBanned(String banned) {
		this.banned = banned;
	}

}
