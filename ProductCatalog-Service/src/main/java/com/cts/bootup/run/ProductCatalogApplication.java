package com.cts.bootup.run;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.cts.bootup.run.config.ConfigProperties;

@EnableFeignClients
@EnableScheduling
@EnableCaching
@SpringBootApplication
public class ProductCatalogApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext ctx = SpringApplication.run(ProductCatalogApplication.class, args);
		ConfigProperties cp = ctx.getBean(ConfigProperties.class);
		System.out.println(cp.getBanned());
	}
	
//	@Bean
//    public PromptProvider myPromptProvider() {
//        return () -> new AttributedString("Smit_667182:>",
//                AttributedStyle.DEFAULT.foreground(AttributedStyle.RED));
//    }
	
}
