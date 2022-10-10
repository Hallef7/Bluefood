package br.com.softblue.bluefood.infrastructure.web.controller;
/*Classe responsavel pelo processamento da aplicação*/

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.softblue.bluefood.application.service.ClienteService;
import br.com.softblue.bluefood.application.service.RestauranteService;
import br.com.softblue.bluefood.application.service.ValidationException;
import br.com.softblue.bluefood.domain.cliente.Cliente;
import br.com.softblue.bluefood.domain.restaurante.CategoriaRestaurante;
import br.com.softblue.bluefood.domain.restaurante.CategoriaRestauranteRepository;
import br.com.softblue.bluefood.domain.restaurante.Restaurante;

@Controller
@RequestMapping(path = "/public")
public class PublicController {
	
	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private RestauranteService restauranteService;
	
	@Autowired /* pega uma injeção de depêndencia do spring*/
	private CategoriaRestauranteRepository categoriaRestauranteRepository;
	
	@GetMapping("/cliente/new")
	public String newCliente(Model model) {
		model.addAttribute("cliente", new Cliente());
		ControllerHelper.setEditMode(model, false);
		return "cliente-Cadastro";
	}
	
	@GetMapping("/restaurante/new")
	public String newRestaurante(Model model) {
		model.addAttribute("restaurante", new Restaurante());
		ControllerHelper.setEditMode(model, false);
		ControllerHelper.addCategoriasToRequest(categoriaRestauranteRepository, model);
		
		/*findAll(); metodo que retorna tudo que esta cadastrado na tabela em fomrma de lista. List <>*/
		List<CategoriaRestaurante> categorias = categoriaRestauranteRepository.findAll();
		model.addAttribute("categorias", categorias);/*model deixa a lista disponivel para renderização do html*/
		
		
		return "restaurante-Cadastro";
	}
	
	@PostMapping (path= "/cliente/save")
	public String saveCliente(@ModelAttribute("cliente") @Valid Cliente cliente, Errors errors, Model model) {
		
		if (!errors.hasErrors()) {
			try {
				clienteService.saveCliente(cliente);
				model.addAttribute("msg", "Cliente gravado com sucesso");
				
			}catch (ValidationException e) {
				errors.rejectValue("email", null, e.getMessage());
			
			}
		}
		ControllerHelper.setEditMode(model, false);
		return "cliente-Cadastro";
	}




@PostMapping (path= "/restaurante/save")
public String saveRestaurante(@ModelAttribute("restaurante") @Valid Restaurante restaurante, Errors errors, Model model) {
	
	if (!errors.hasErrors()) {
		try {
			restauranteService.saveRestaurante(restaurante);
			model.addAttribute("msg", "Restaurante gravado com sucesso");
			
		}catch (ValidationException e) {
			errors.rejectValue("email", null, e.getMessage());
		
		}
	}
	ControllerHelper.setEditMode(model, false);
	ControllerHelper.addCategoriasToRequest(categoriaRestauranteRepository, model);
	return "restaurante-Cadastro";
}

}
