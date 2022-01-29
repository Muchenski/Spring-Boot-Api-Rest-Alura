package com.alura.dtos;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class TopicoAtualizaDto {

	@NotEmpty
	private String titulo;

	@Size(min = 10, max = 2000)
	private String mensagem;

	public TopicoAtualizaDto() {
		super();
	}

	public TopicoAtualizaDto(@NotEmpty String titulo, @Size(min = 10, max = 2000) String mensagem) {
		super();
		this.titulo = titulo;
		this.mensagem = mensagem;
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

}
