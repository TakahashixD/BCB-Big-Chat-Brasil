package br.com.bcb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
@SpringBootApplication
@EnableEurekaServer
public class BcbNamingServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(BcbNamingServerApplication.class, args);
	}

}
