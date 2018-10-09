package com.aboieriu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author aboieriu
 */
@SpringBootApplication
@ComponentScan(basePackages = "com.aboieriu")
public class AuthenticatorStarter {
	public static void main(String[] args) {
		SpringApplication.run(AuthenticatorStarter.class, args);
	}
}
