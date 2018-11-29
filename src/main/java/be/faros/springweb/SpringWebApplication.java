package be.faros.springweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
	This project contains some samples & code for configuring spring web with mustache.
	I also included actuator and basic spring security (user/pwd)

	Resources:
    	https://spring.io/blog/2016/11/21/the-joy-of-mustache-server-side-templates-for-the-jvm
 */
@SpringBootApplication
public class SpringWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringWebApplication.class, args);
	}

}
