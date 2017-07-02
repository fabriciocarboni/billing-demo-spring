package com.carboni.billing.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.carboni.billing.model.StatusTitulo;
import com.carboni.billing.model.Titulo;
import com.carboni.billing.repository.Titulos;


@Controller
@RequestMapping("/titulos")
public class TituloController {
	
	private static final String CADASTRO_VIEW = "CadastroTitulo";

	@Autowired // injetando a instancia do repositório Titulos
	private Titulos titulos;
	
	@GetMapping("/novo")
	public ModelAndView novo() {
		
		//Ao acessar esse endpoint, é necessario deixar disponivel o combo do campo status com todos os Enums
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
		
		//Adiciona neste controller o objeto titulo para ser acessado na pagina CadastroTitulo.
		//Esse objeto será acessado através desta tag html <form class="form-horizontal" method="POST" action="/titulos" th:object="${titulo}">
		mv.addObject(new Titulo());
		return mv;
		
	}

	//@RequestMapping(method = RequestMethod.POST)
	@PostMapping
	public String salvar(@Validated Titulo titulo, Errors errors, RedirectAttributes attributes) { //Indica ao spring para validar o classe Titulo, de acordo com as regras onde tiver anotacoes de validation como @NotNull por exemplo
		if (errors.hasErrors()) {
			return CADASTRO_VIEW; //Aqui retorna a pagina html CadastroTitulo.html
		}
		
		titulos.save(titulo); //salva no banco de dados
		attributes.addFlashAttribute("mensagem","Titulo salvo com sucesso!"); // Adiciona a msg de cadastro com sucesso ao redirecionar para /titulos/novo após clicar no botao salvar
		return "redirect:/titulos/novo"; //Aqui retorna uma url redirecinando para titulos/novo
	}
	
	@GetMapping //Nao é necessário colocar o endereço pois já esse controller deve retornar a pesquisa de titulos assim que o request chega em /titulos, que esta mapeado já na linha 20
	public ModelAndView pesquisar() {
		List<Titulo> todosTitulos = titulos.findAll(); //procura no bd todos os titulos. Esse repositorio de titulos só esta disponivel pois lá em cima teve um @Autowired injetando Titulos
		ModelAndView mv = new ModelAndView("PesquisaTitulos");
		mv.addObject("titulos",todosTitulos);
		return mv;
	}
	
	@GetMapping("{codigo}")
	//public ModelAndView edicao(@PathVariable Long codigo) {
	public ModelAndView edicao(@PathVariable("codigo") Titulo titulo) { //ao inves de usar a linha abaixo para procurar o codigo, o spring ja faz isso automaticamente se passarmos o codigo para o objeto Titulo
		//Titulo titulo = titulos.findOne(codigo); //recupera do banco de dados o codigo informado no link
		
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
		mv.addObject(titulo); //passa para view CadastroTitulo.html o codigo para edicao.
		return mv;
	}
	
	//implementa exclusao
	//@PostMapping("{codigo}")
	@RequestMapping(value="{codigo}", method = RequestMethod.DELETE)
	public String excluir(@PathVariable Long codigo, RedirectAttributes attributes){
		titulos.delete(codigo);
		
		attributes.addFlashAttribute("mensagem","Titulo excluido com sucesso!"); // Adiciona a msg de cadastro com sucesso ao redirecionar para /titulos/novo após clicar no botao salvar
		return "redirect:/titulos";
	}
	
	
	
	//Criado dentro de ModelAtribute para que o controller sempre tenha este objeto disponivel na view
	//StatusTitulo é o enum. retorna o array com os valores todosStatusTitulo
	// O parametro passado em @ModelAttribute é o nome que será iterado pelo thymeleaf na view
	@ModelAttribute("todosStatusTitulo")
	public List<StatusTitulo> todosStatusTitulo() {
		return Arrays.asList(StatusTitulo.values());
	}

}
