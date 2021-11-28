package com.nttdata.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.nttdata.models.Categoria;
import com.nttdata.services.CategoriaService;
import com.nttdata.services.ProductoService;

@Controller
@RequestMapping("/categoria")
public class CategoriaController {

	@Autowired
	CategoriaService categoriaService;
	
	@Autowired
	ProductoService productoService;
	
	@RequestMapping("/categoria_admin")
	public String categoriaAdmin(@ModelAttribute("categoria") Categoria categoria, 
			Model model) {
		model.addAttribute("listaCategorias", categoriaService.obtenerListaCategoria());
		model.addAttribute("listaProductos", productoService.obtenerListaProducto());
		return "categoria/categoria_admin.jsp";
	}
	
	@RequestMapping("/eliminar")
	public String eliminar(@RequestParam("id") Long id) {
		
		System.out.println("Id: "+ id);
		Categoria categoria = categoriaService.buscarCategoriaId(id);
		
		if(categoria != null) {
			categoriaService.eliminarCategoriaObj(categoria);
		}

		return "redirect:/categoria/categoria_admin";
	}
	
	@RequestMapping("/agregar")
	public String agregar(@ModelAttribute("categoria") Categoria categoria, 
			Model model) {
		categoriaService.agregarCategoria(categoria);
		return "redirect:/categoria/categoria_admin";
	}
	
	@RequestMapping("/editar")
	public String editar(@RequestParam("id") Long id, Model model) {
		
		Categoria categoria = categoriaService.buscarCategoriaId(id);
		
		if(categoria != null) {
			model.addAttribute("categoria", categoria);
			model.addAttribute("listaProductos", productoService.obtenerListaProducto());;
			return "categoria/categoriaEditar.jsp";
		}

		return "redirect:/categoria/categoria_admin";
		
	}
	
	@RequestMapping(value="/actualizar", method = RequestMethod.POST)
	public String actualizar(@Valid @ModelAttribute("categoria") Categoria categoria, Model model) {
		System.out.println("id " + categoria);
		categoriaService.actualizarCategoria(categoria);
		return "redirect:/categoria/categoria_admin";
	}
	
}
