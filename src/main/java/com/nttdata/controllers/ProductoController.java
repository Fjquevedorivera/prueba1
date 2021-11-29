package com.nttdata.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.nttdata.models.Categoria;
import com.nttdata.models.Producto;
import com.nttdata.models.Usuario;
import com.nttdata.services.CategoriaService;
import com.nttdata.services.ProductoService;

@Controller
@RequestMapping("/producto")
public class ProductoController {
	
	@Autowired 
	ProductoService productoService;
	
	@Autowired
	CategoriaService categoriaService;
	
	@Scope("usuarioLogin")
	
	@RequestMapping("")
	public String producto(@ModelAttribute("producto") Producto producto,
			@ModelAttribute("categoria") Categoria categoria,
			Model model, HttpSession session) {
		Usuario usuarioLogin = (Usuario)session.getAttribute("usuarioLogin");
		if(usuarioLogin != null) {
			model.addAttribute("listaCategorias", categoriaService.obtenerListaCategoria());
			model.addAttribute("listaProductos", productoService.obtenerListaProducto());
			return "producto/producto.jsp";
		} else {
			return "redirect:/";
		}

	}
	
	@RequestMapping("/producto_admin")
	public String productoAdmin(@ModelAttribute("producto") Producto producto,
			Model model) {
		model.addAttribute("listaProductos", productoService.obtenerListaProducto());
		model.addAttribute("listaCategorias", categoriaService.obtenerListaCategoria());
		return "producto/producto_admin.jsp";

	}
	
	@RequestMapping("/agregar")
	public String agregar(@Valid @ModelAttribute("producto") Producto producto) {
		productoService.insertarProducto(producto);
		return "redirect:/producto/producto_admin";
	}
	
	@RequestMapping("/eliminar")
	public String eliminarObj(@RequestParam("id") Long id) {
		
		System.out.println("Id: "+ id);
		Producto producto = productoService.buscarProductoId(id);
		
		if(producto != null) {
			productoService.eliminarProductoObj(producto);
		}

		return "redirect:/producto/producto_admin";
	}
	
	@RequestMapping("/editar")
	public String editar(@RequestParam("id") Long id, Model model) {
		
		Producto producto = productoService.buscarProductoId(id);
		if(producto != null) {
			model.addAttribute("producto", producto);
			model.addAttribute("listaCategorias", categoriaService.obtenerListaCategoria());
			model.addAttribute("listaProductos", productoService.obtenerListaProducto());
			return "producto/productoEditar.jsp";
		}

		return "redirect:/";
		
	}
	
	@RequestMapping(value="/actualizar", method = RequestMethod.POST)
	public String actualizar(@Valid @ModelAttribute("producto") Producto producto, Model model) {
		System.out.println("id " + producto.getId());
		productoService.actualizarProducto(producto);
		return "redirect:/producto/producto_admin";
	}
	
	@RequestMapping("/categoria")
	public String categoria(@RequestParam("id") Long id, Model model) {
		
		model.addAttribute("listaCategorias", categoriaService.obtenerListaCategoria());
		model.addAttribute("listaProductos", productoService.productosByCategoria(id));
		return "/producto/producto.jsp";
	}
	
}
