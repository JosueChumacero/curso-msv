package org.jfacundo.msv.cursos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class MsvCursosApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsvCursosApplication.class, args);
	}

}
