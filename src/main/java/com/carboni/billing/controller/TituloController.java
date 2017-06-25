package com.carboni.billing.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
		
		//Adiciona neste controller o objeto titulo para ser acessado na pagina CadastroTitulo.
		//Esse objeto será acessado através desta tag html <form class="form-horizontal" method="POST" action="/titulos" th:object="${titulo}">
		mv.addObject(new Titulo());
		return mv;
		
	}

	//@RequestMapping(method = RequestMethod.POST)
	@PostMapping
	public String salvar(@Validated Titulo titulo, Errors errors, RedirectAttributes attributes) { //Indica ao spring para validar o classe Titulo, de acordo com as regras onde tiver anotacoes de validation como @NotNull por exemplo
		if (errors.hasErrors()) {
			return "CadastroTitulo"; //Aqui retorna a pagina html CadastroTitulo.html
		}
		
		titulos.save(titulo); //salva no banco de dados
		attributes.addFlashAttribute("mensagem","Titulo salvo com sucesso!"); // Adiciona a msg de cadastro com sucesso ao redirecionar para /titulos/novo após clicar no botao salvar
		return "redirect:/titulos/novo"; //Aqui retorna uma url
	}
	
	@GetMapping //Nao é necessário colocar o endereço pois já esse controller deve retornar a pesquisa de titulos assim que o request chega em /titulos, que esta mapeado já na linha 20
	public ModelAndView pesquisar() {
		List<Titulo> todosTitulos = titulos.findAll(); //procura no bd todos os titulos. Esse repositorio de titulos só esta disponivel pois lá em cima teve um @Autowired injetando Titulos
		ModelAndView mv = new ModelAndView("PesquisaTitulos");
		mv.addObject("titulos",todosTitulos);
		return mv;
		
	}
	
	
	//Criado dentro de ModelAtribute para que o controller sempre tenha este objeto disponivel na view
	//StatusTitulo é o enum. retorna o array com os valores todosStatusTitulo
	// O parametro passado em @ModelAttribute é o nome que será iterado pelo thymeleaf na view
	@ModelAttribute("todosStatusTitulo")
	public List<StatusTitulo> todosStatusTitulo() {
		return Arrays.asList(StatusTitulo.values());
	}

}
