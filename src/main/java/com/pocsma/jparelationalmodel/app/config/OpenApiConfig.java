package com.pocsma.jparelationalmodel.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class OpenApiConfig {

	@Bean
	public OpenAPI relationalModelOpenApi() {
		return new OpenAPI()
				.info(new Info()
						.title("JPA Relational Model POC API")
						.description("Simple API to explore a relational model backed by Spring Boot, JPA and PostgreSQL.")
						.version("v1"));
	}
}
