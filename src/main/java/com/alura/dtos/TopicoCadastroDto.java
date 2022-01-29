package com.alura.dtos;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class TopicoCadastroDto {

	@NotEmpty
	private String titulo;

	@Size(min = 10, max = 2000)
	private String mensagem;

	@NotEmpty
	private String nomeCurso;

	public TopicoCadastroDto() {
		super();
	}

	public TopicoCadastroDto(@NotEmpty String titulo, @Size(min = 10, max = 2000) String mensagem,
			@NotEmpty String nomeCurso) {
		super();
		this.titulo = titulo;
		this.mensagem = mensagem;
		this.nomeCurso = nomeCurso;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public String getNomeCurso() {
		return nomeCurso;
	}

	public void setNomeCurso(String nomeCurso) {
		this.nomeCurso = nomeCurso;
	}

}
