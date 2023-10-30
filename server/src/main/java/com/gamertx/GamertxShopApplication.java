package com.gamertx;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GamertxShopApplication {
	public static void main(String[] args) {
		SpringApplication.run(GamertxShopApplication.class, args);
	}

	//localhost:8090/gamertx/doc/swagger-ui/index.html
	@Bean
	public OpenAPI customOpenApi() {
		return new OpenAPI()
				.info(new Info()
						.title("Gamer Tx API project")
						.version("1.0.0")
						.description("API del e-commerce Gamer TX para llevar a cabo peticiones CRUD desde el Backend hasta el aplicativo web")
						.termsOfService("http://swagger.io/terms/")
						.license(new License().name("Apache 2.0").url("http://springdoc.org"))
				);
	}
}