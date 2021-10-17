package com.ncs.linkstation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages ={"com.ncs.linkstation"})
public class LinkstationApplication {

	public static void main(String[] args) {
		SpringApplication.run(LinkstationApplication.class, args);
	}

}
