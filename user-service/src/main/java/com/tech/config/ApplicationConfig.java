package com.tech.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ApplicationConfig {
	@Bean
	@LoadBalanced//call by the application name and balance it
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
}
