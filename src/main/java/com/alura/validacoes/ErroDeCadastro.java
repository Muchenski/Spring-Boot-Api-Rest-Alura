package com.alura.validacoes;

public class ErroDeCadastro {

	private String campo;

	private String erro;

	public ErroDeCadastro() {
		super();
	}

	public ErroDeCadastro(String campo, String erro) {
		super();
		this.campo = campo;
		this.erro = erro;
	}

	public String getCampo() {
		return campo;
	}

	public void setCampo(String campo) {
		this.campo = campo;
	}

	public String getErro() {
		return erro;
	}

	public void setErro(String erro) {
		this.erro = erro;
	}

}
