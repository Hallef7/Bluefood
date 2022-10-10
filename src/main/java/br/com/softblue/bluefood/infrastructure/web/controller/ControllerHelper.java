package br.com.softblue.bluefood.infrastructure.web.controller;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.ui.Model;

import br.com.softblue.bluefood.domain.restaurante.CategoriaRestaurante;
import br.com.softblue.bluefood.domain.restaurante.CategoriaRestauranteRepository;

public class ControllerHelper {
		/*classe auxiliar que adiciona o atributo edit mode*/
	public static void setEditMode(Model model, boolean isEdit) {
		model.addAttribute("editMode", isEdit);
	}
	
	/*método que passa o repositorio e objeto model*/
	public static void addCategoriasToRequest(CategoriaRestauranteRepository repository, Model model) {
		
		/*findAll(); metodo que retorna tudo que esta cadastrado na tabela em fomrma de lista. List <> / Sort.by ordena alista de foma ascendente*/
		List<CategoriaRestaurante> categorias = repository.findAll(Sort.by("nome"));
		model.addAttribute("categorias", categorias);/*model deixa a lista disponivel para renderização do html*/
		
	}
}
