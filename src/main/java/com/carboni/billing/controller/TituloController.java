package com.carboni.billing.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.carboni.billing.model.Titulo;
import com.carboni.billing.repository.Titulos;


@Controller
@RequestMapping("/titulos")
public class TituloController {

	@Autowired // injetando a instancia do repositório Titulos
	private Titulos titulos;
	
	@GetMapping("/novo")
	public String novo() {
		return "CadastroTitulo";
	}
	

	//@RequestMapping(method = RequestMethod.POST)
	@PostMapping
	public ModelAndView salvar(Titulo titulo) {
		
		titulos.save(titulo); //salva no banco de dados
		
		ModelAndView mv = new ModelAndView("CadastroTitulo");
		mv.addObject("mensagem","Titulo salvo com sucesso!"); // Adciona msg no modelAndView para passar para o html CadastroTitulo.html
		return mv;
	}

}
