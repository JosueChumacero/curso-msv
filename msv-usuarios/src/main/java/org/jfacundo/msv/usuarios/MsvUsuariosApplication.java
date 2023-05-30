package org.jfacundo.msv.usuarios;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MsvUsuariosApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsvUsuariosApplication.class, args);
	}

}
