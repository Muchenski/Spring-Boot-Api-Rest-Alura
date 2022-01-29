package com.alura.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alura.model.domain.Topico;

@Repository
public interface TopicoRepository extends JpaRepository<Topico, Long> {

	// Caso utilizassemos findByCursoNomeContainingIgnoreCase, e houvesse um
	// atributo chamado cursoNome na entidade Topico, iria haver erro de
	// ambiguidade.
	// Utilizando o underline conseguimos definir que a propriedade após o underline
	// pertence a entidade que está antes do underline.
	Page<Topico> findByCurso_NomeContainingIgnoreCase(String nome, Pageable pageable);

}
