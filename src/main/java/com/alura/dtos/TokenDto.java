package com.alura.dtos;

public class TokenDto {

	private String token;

	private String tipoAutenticacao;

	public TokenDto() {
		super();
	}

	public TokenDto(String token, String tipoAutenticacao) {
		super();
		this.token = token;
		this.tipoAutenticacao = tipoAutenticacao;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getTipoAutenticacao() {
		return tipoAutenticacao;
	}

	public void setTipoAutenticacao(String tipoAutenticacao) {
		this.tipoAutenticacao = tipoAutenticacao;
	}

}
