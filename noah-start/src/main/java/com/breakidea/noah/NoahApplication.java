package com.breakidea.noah;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@SpringBootApplication
@ImportResource({ "classpath:applicationContext.xml" })
public class NoahApplication {

	public static void main(String[] args) {
		SpringApplication.run(NoahApplication.class, args);
	}

}
