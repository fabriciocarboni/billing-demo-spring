package com.carboni.billing.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.carboni.billing.model.StatusTitulo;
import com.carboni.billing.model.Titulo;
import com.carboni.billing.repository.Titulos;


@Controller
@RequestMapping("/titulos")
public class TituloController {

	@Autowired // injetando a instancia do repositório Titulos
	private Titulos titulos;
	
	@GetMapping("/novo")
	public ModelAndView novo() {
		
		//Ao acessar esse endpoint, é necessario deixar disponivel o combo do campo status com todos os Enums
		ModelAndView mv = new ModelAndView("CadastroTitulo");
		return mv;
		
	}

	//@RequestMapping(method = RequestMethod.POST)
	@PostMapping
	public ModelAndView salvar(Titulo titulo) {
		
		titulos.save(titulo); //salva no banco de dados
		
		ModelAndView mv = new ModelAndView("CadastroTitulo");
		mv.addObject("mensagem","Titulo salvo com sucesso!"); // Adiciona msg no modelAndView para passar para o html CadastroTitulo.html
		return mv;
	}
	
	@GetMapping //Nao é necessário colocar o endereço pois já esse controller deve retornar a pesquisa de titulos assim que o request chega em /titulos, que esta mapeado já na linha 20
	public String pesquisar() {
		return "PesquisaTitulos";
		
	}
	
	
	//Criado dentro de ModelAtribute para que o controller sempre tenha este objeto disponivel na view
	//StatusTitulo é o enum. retorna o array com os valores todosStatusTitulo
	// O parametro passado em @ModelAttribute é o nome que será iterado pelo thymeleaf na view
	@ModelAttribute("todosStatusTitulo")
	public List<StatusTitulo> todosStatusTitulo() {
		return Arrays.asList(StatusTitulo.values());
	}

}
