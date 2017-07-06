package com.carboni.billing.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.carboni.billing.model.Titulo;


/**
 * Para cadastrar no banco de dados é necessário sempre criar uma interface que extende jpa repository
 * Passar como parametro a Entidade que este repositorio vai trabalhar. Como está no model Neste caso (Titulo)
 * Passar o tipo de dado do ID desta entidade (Long)
 */
public interface Titulos extends JpaRepository<Titulo, Long>{

	//forma de pesquisa
	public List<Titulo> findByDescricaoContaining(String descricao); //Containing = like
	
}
// conexao com h2
//jdbc:h2:mem:testdb 