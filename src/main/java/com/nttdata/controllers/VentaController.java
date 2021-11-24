package com.nttdata.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nttdata.models.Venta;
import com.nttdata.services.VentaService;

@Controller
@RequestMapping("/venta")
public class VentaController {
	
	@Autowired 
	VentaService ventaService;
	
	@RequestMapping("")
	public String venta(@ModelAttribute("venta") Venta venta,
			Model model) {
		model.addAttribute("listaVentas", ventaService.obtenerListaVenta());
		return "venta/venta.jsp";
	}
	
	@RequestMapping("/agregar")
	public String agregar(@Valid @ModelAttribute("venta") Venta venta) {
		ventaService.insertarVenta(venta);
		return "redirect:/venta";
	}
	
	@RequestMapping("/eliminar")
	public String eliminarObj(@RequestParam("id") Long id) {
		
		System.out.println("Id: "+ id);
		Venta venta = ventaService.buscarVentaId(id);
		
		if(venta != null) {
			ventaService.eliminarVentaObj(venta);
		}

		return "redirect:/venta";
	}
	
	@RequestMapping("/editar")
	public String editar(@RequestParam("id") Long id, 
			Model model, 
			HttpSession session) {
		
		System.out.println("Id: "+ id);
		Venta venta = ventaService.buscarVentaId(id);
		
		if(venta != null) {
			session.setAttribute("ventaEditar", venta);
			model.addAttribute("ventaActualizar", venta);
		}

		return "venta/ventaEditar.jsp";
	}
	
	@RequestMapping("/actualizar")
	public String actualizar(@ModelAttribute("ventaActualizar") Venta venta, 
			Model model, 
			HttpSession session) {
		
		Venta ventaAntiguo = (Venta)session.getAttribute("ventaEditar");
		
		if(ventaAntiguo != null) {
			ventaAntiguo.setDate(venta.getDate());
			ventaAntiguo.setTotal(venta.getTotal());
			
			ventaService.actualizarVenta(ventaAntiguo);
			return "redirect:/venta";
		}
		
		return "Venta Incorrecta";
	}

}
