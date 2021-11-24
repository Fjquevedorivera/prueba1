package com.nttdata.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nttdata.models.Producto;
import com.nttdata.services.ProductoService;

@Controller
@RequestMapping("/producto")
public class ProductoController {
	
	@Autowired 
	ProductoService productoService;
	
	@RequestMapping("")
	public String producto(@ModelAttribute("producto") Producto producto,
			Model model) {
		model.addAttribute("listaProductos", productoService.obtenerListaProducto());
		return "producto/producto.jsp";
	}
	
	@RequestMapping("/agregar")
	public String agregar(@Valid @ModelAttribute("producto") Producto producto) {
		productoService.insertarProducto(producto);
		return "redirect:/producto";
	}
	
	@RequestMapping("/eliminar")
	public String eliminarObj(@RequestParam("id") Long id) {
		
		System.out.println("Id: "+ id);
		Producto producto = productoService.buscarProductoId(id);
		
		if(producto != null) {
			productoService.eliminarProductoObj(producto);
		}

		return "redirect:/producto";
	}
	
	@RequestMapping("/editar")
	public String editar(@RequestParam("id") Long id, 
			Model model, 
			HttpSession session) {
		
		System.out.println("Id: "+ id);
		Producto producto = productoService.buscarProductoId(id);
		
		if(producto != null) {
			session.setAttribute("productoEditar", producto);
			model.addAttribute("productoActualizar", producto);
		}

		return "producto/productoEditar.jsp";
	}
	
	@RequestMapping("/actualizar")
	public String actualizar(@ModelAttribute("productoActualizar") Producto producto, 
			Model model, 
			HttpSession session) {
		
		Producto productoAntiguo = (Producto)session.getAttribute("productoEditar");
		
		if(productoAntiguo != null) {
			productoAntiguo.setBarcode(producto.getBarcode());
			productoAntiguo.setName(producto.getName());
			productoAntiguo.setBrand(producto.getBrand());
			productoAntiguo.setPrice(producto.getPrice());
			
			productoService.actualizarProducto(productoAntiguo);
			return "redirect:/producto";
		}
		
		return "Producto Incorrecto";
	}
	
	
}
