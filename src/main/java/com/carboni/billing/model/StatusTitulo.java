package com.carboni.billing.model;

public enum StatusTitulo {
	
	//passing a string to constructor to display correctly in combo box.
	PENDENTE("Pendente"),
	RECEBIDO("Recebido");
	
	private String descricao;
	
	StatusTitulo(String descricao) {
		this.descricao = descricao;
	}
	
	
	// get
	public String getDescricao() {
		return descricao;
	}

}
