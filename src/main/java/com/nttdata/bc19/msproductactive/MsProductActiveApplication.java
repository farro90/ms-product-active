package com.nttdata.bc19.msproductactive;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class MsProductActiveApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsProductActiveApplication.class, args);
	}

}
