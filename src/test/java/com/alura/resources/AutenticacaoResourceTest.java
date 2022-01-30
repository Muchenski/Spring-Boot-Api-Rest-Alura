package com.alura.resources;

import java.net.URI;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles(value = "test") // Forçando o Spring a utilizar o profile "test" ao executar esta classe.
class AutenticacaoResourceTest {

	@Autowired
	private MockMvc mockMvc;
	
	@Test
	void deveRetornar400AoTentarAutenticarComDadosInvalidos() throws Exception {
		URI uri = new URI("/auth");
		String json = "{\"email\":\"invalido@gmail.com\",\"senha\":\"invalida\"}";
		mockMvc
			.perform(MockMvcRequestBuilders
						.post(uri)
						.content(json)
						.contentType(MediaType.APPLICATION_JSON)
					)
					.andExpect(MockMvcResultMatchers.status().is(400));
	}

}
