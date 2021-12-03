package com.nttdata.controllers;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nttdata.models.Producto;
import com.nttdata.models.ProductoUsuario;
import com.nttdata.models.Usuario;
import com.nttdata.models.Venta;
import com.nttdata.services.ProductoUsuarioService;
import com.nttdata.services.UsuarioService;
import com.nttdata.services.VentaService;

@Controller
@RequestMapping("/venta")
public class VentaController {
	
	@Autowired 
	VentaService ventaService;
	@Autowired 
	UsuarioService usuarioService;
	@Autowired
	ProductoUsuarioService prous;
	
	@RequestMapping("")
	public String venta(@ModelAttribute("venta") Venta venta,
			Model model) {
		model.addAttribute("listaVentas", ventaService.obtenerListaVenta());
		return "venta/venta.jsp";
	}
	
	@RequestMapping("/agregar")
	public String agregar(@Valid @ModelAttribute("venta") Venta venta, Principal principal,
			HttpSession session) {
		//--------------------------------------------------------------------

		        List<ProductoUsuario> productosComprar = (List<ProductoUsuario>) session.getAttribute("carro");
				String nombre = principal.getName();

				Usuario usuario = usuarioService.findByName(nombre);

		        if(usuario==null) {
		            System.out.println("No se puede procesar la compra si no inicia sesión!");
		            return "login.jsp";
		        }else if(productosComprar.size()==0) { 
		            System.err.println("No se puede procesar la compra porque el carrito está vacío!");
		            return "redirect:/inicio";
		        }else{
		        	System.out.println(usuario);
		            for(int i=0; i<productosComprar.size();i++) {
		                ProductoUsuario productoUsuario = new ProductoUsuario();
		                productoUsuario.setUsuario(usuario);
		                productoUsuario.setTotal_product(productosComprar.get(i).getTotal_product());
		                productoUsuario.setQuantity_product(productosComprar.get(i).getQuantity_product());
		                productoUsuario.setProducto(productosComprar.get(i).getProducto());
		                prous.insertarCompra(productoUsuario);
		            }
		            System.out.println("Compra realizada con éxito");
		            return "redirect:/producto";
		        }
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
