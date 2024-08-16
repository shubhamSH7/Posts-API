package com.sma.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**").allowedOrigins("http://localhost:3000") // Specify allowed origins
				.allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Specify allowed methods
				.allowedHeaders("*").allowCredentials(true);
	}
}