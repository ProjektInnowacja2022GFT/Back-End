package com.gft.gdesk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class GDeskApplication {

	public static void main(String[] args) {
		SpringApplication.run(GDeskApplication.class, args);
	}

}

