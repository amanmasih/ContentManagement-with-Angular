package com.niit.usercontent;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
@OpenAPIDefinition(info= @Info(title="CONTENT SERVICE",version="${springdoc.version}",description="CONTENT SERVICE API V1.0"))
public class UsercontentApplication {

	public static void main(String[] args) {
		SpringApplication.run(UsercontentApplication.class, args);
	}

}
