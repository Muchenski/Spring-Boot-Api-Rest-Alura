package com.alura.repository;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import com.alura.model.domain.Curso;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@ActiveProfiles(value = "test") // Forçando o Spring a utilizar o profile "test" ao executar esta classe.
class CursoRepositoryTest {

	@Autowired
	private CursoRepository cursoRepository;
	
	@Autowired
	private TestEntityManager testEntityManager;
	
	@Test
	public void deveCarregarUmCursoAoBuscarPeloNome() {
		// Cenário
		testEntityManager.persist(new Curso(null, "HTML 5", null));
		String nomeCurso = "HTML 5";

		// Ação
		Curso curso = cursoRepository.findByNome(nomeCurso);
		
		// Validação
		assertNotNull(curso);
		assertEquals(nomeCurso, curso.getNome());
	}
	
	@Test
	public void deveDevolverNuloAoBuscarPorNomeDeCursoNaoCadastrado() {
		// Cenário
		String nomeCurso = "JPA";
		
		// Ação
		Curso curso = cursoRepository.findByNome(nomeCurso);
		
		// Validação
		assertNull(curso);
	}

}
