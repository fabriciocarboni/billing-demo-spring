package com.carboni.billing.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.carboni.billing.model.StatusTitulo;
import com.carboni.billing.model.Titulo;
import com.carboni.billing.repository.Titulos;

@Service
public class CadastroTituloService {
	
	@Autowired
	private Titulos titulos;
	
	//salvar um titulo
	public void salvar(Titulo titulo) {
		try{
			titulos.save(titulo);
		} catch (DataIntegrityViolationException e) {
			throw new IllegalArgumentException("Formato da data inválido");
		}
	}
	
	public String receber(Long codigo){
		Titulo titulo = titulos.findOne(codigo); // recuperar o tidulo ue tem o codigo recebido
		titulo.setStatus(StatusTitulo.RECEBIDO); // atualiza o status no ENUM
		titulos.save(titulo);
		
		return StatusTitulo.RECEBIDO.getDescricao();
	}

	//excluir titulo
	public void excluir(Long codigo) {
		titulos.delete(codigo);
	}
}
