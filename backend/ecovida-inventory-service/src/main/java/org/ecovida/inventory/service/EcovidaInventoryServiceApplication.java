package org.ecovida.inventory.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class EcovidaInventoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcovidaInventoryServiceApplication.class, args);
	}

}
