package com.alura;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

@SpringBootApplication
// Anotação para conseguirmos repassar dados da requisição para o Spring Data JPA.
// Ex: Pegar o Pageable no parâmetro de um controlador, preenchido com os dados da requisição, 
// e repassar para o JpaRepository como critério de consulta.
@EnableSpringDataWebSupport
@EnableCaching
public class AluraRestApplication {

	public static void main(String[] args) {
		SpringApplication.run(AluraRestApplication.class, args);
	}

}
