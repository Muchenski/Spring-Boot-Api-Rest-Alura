package com.alura.dtos;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.alura.model.domain.StatusTopico;

public class TopicoDetalhadoDto {

	private Long id;

	private String titulo;

	private String mensagem;

	private LocalDateTime dataCriacao;

	private String nomeDoAutor;

	private StatusTopico status;

	private List<RespostaDto> respostaDtos = new ArrayList<RespostaDto>();

	public TopicoDetalhadoDto() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public LocalDateTime getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(LocalDateTime dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public String getNomeDoAutor() {
		return nomeDoAutor;
	}

	public void setNomeDoAutor(String nomeDoAutor) {
		this.nomeDoAutor = nomeDoAutor;
	}

	public StatusTopico getStatus() {
		return status;
	}

	public void setStatus(StatusTopico status) {
		this.status = status;
	}

	public List<RespostaDto> getRespostaListagemDtos() {
		return respostaDtos;
	}

	public void setRespostaListagemDtos(List<RespostaDto> respostaDtos) {
		this.respostaDtos = respostaDtos;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TopicoDetalhadoDto other = (TopicoDetalhadoDto) obj;
		return Objects.equals(id, other.id);
	}

}
