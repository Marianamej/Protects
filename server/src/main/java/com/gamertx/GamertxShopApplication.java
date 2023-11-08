package com.gamertx;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.context.annotation.PropertySource;


@SpringBootApplication
@PropertySource("classpath:application-secrets.properties")
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class GamertxShopApplication {
	public static void main(String[] args) {
		SpringApplication.run(GamertxShopApplication.class, args);
	}
	@Autowired
	private PasswordEncoder passwordEncoder;

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

//	@Bean
//	public CommandLineRunner createPasswordCommand(){
//		return args -> {
	//      $2a$10$Sh6jpK2FLFAY4F.YTKy9guMTwECUoOl6MhK4fNBVEL/MN7OZrsrE.
//			System.out.println(passwordEncoder.encode("user"));
	//      $2a$10$Tj//dIfg2lHxAXPSCP2zSu9gnBEffguPeewRUjNPbJVQq0JMKN0zO
//			System.out.println(passwordEncoder.encode("admin"));
//		};
//	}

	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/greeting-javaconfig").allowedOrigins("http://127.0.0.1:5500");
			}
		};
	}


}