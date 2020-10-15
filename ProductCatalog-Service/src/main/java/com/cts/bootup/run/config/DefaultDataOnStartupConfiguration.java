
package com.cts.bootup.run.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cts.bootup.run.dao.ProductDao;
import com.cts.bootup.run.entity.Product;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@ConditionalOnClass({ String.class })
public class DefaultDataOnStartupConfiguration {

	@Autowired
	private ProductDao productDao;

	@Bean
	public String loadDataOnStartup() {
		log.info("Configure Defaults");

		Product product = new Product("P1", 10.0, "P1 desc");
		productDao.save(product);

		return new String("Populated");
	}
}
