package com.carboni.billing.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

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

	//excluir titulo
	public void excluir(Long codigo) {
		titulos.delete(codigo);
	}
}
