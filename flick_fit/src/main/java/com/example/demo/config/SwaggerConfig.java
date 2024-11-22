package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class SwaggerConfig {
	@Bean
	public OpenAPI customOpenAPI() {
		return new OpenAPI()
				.info(new Info().title("Flick-FIT Backend API")
				.description("Flick-FIT 프로젝트의 백엔드 API입니다.")
				.version("v1.0.0")
				.license(new License().name("Flick-FIT License").url("https://www.flick-fit.com")));
	}
}
