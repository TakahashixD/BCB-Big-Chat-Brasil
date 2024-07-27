package br.com.bcb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class BcbApplication {

	public static void main(String[] args) {
		SpringApplication.run(BcbApplication.class, args);
	}

}
