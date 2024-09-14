package com.suraj.blog_api.surajblogapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.suraj.blog_api.surajblogapi")
public class SurajblogapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SurajblogapiApplication.class, args);
	}

}
