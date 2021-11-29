package com.nttdata.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nttdata.models.ProductoUsuario;
import com.nttdata.models.Usuario;
import com.nttdata.services.ProductoUsuarioService;

@Controller
@RequestMapping("/productousuario")
public class ProductoUsuarioController {

	@Autowired
	ProductoUsuarioService puService;

	@Scope("usuarioLogin")

	@RequestMapping("")
	public String carrito(@Valid @ModelAttribute("producto_usuario") ProductoUsuario producto_usuario, Model model,
			HttpSession session) {
		Integer cantidad = producto_usuario.getQuantity_product();
		Integer precio = producto_usuario.getProducto().getPrice();
		producto_usuario.setTotal_product(cantidad * precio);
		List<ProductoUsuario> carro = (List<ProductoUsuario>) session.getAttribute("carro");

		if (carro == null) {
			List<ProductoUsuario> carro_inicial = new ArrayList<ProductoUsuario>();
			carro_inicial.add(producto_usuario);
			session.setAttribute("carro", carro_inicial);
		} else {
			int i_producto_existe = -1;
			for (int i = 0; i < carro.size(); i++) {
				if (carro.get(i).getProducto().getId() == producto_usuario.getProducto().getId()) {
					i_producto_existe = i;
				} 
			}
			
			if (i_producto_existe != -1) {
				ProductoUsuario recalcular_producto = carro.get(i_producto_existe);
				recalcular_producto.setQuantity_product(cantidad + recalcular_producto.getQuantity_product());
				recalcular_producto.setTotal_product(precio * recalcular_producto.getQuantity_product());
				carro.set(i_producto_existe, recalcular_producto);
			} else {
				carro.add(producto_usuario);
			}
			
			session.setAttribute("carro", carro);
		}
		
		if (carro != null) {
			for (int i = 0; i < carro.size(); i++) {
				System.out.println("Carro: "+ carro.get(i).getProducto().getId() 
						+ " Cantidad: " + carro.get(i).getQuantity_product()+ " Precio: " + carro.get(i).getTotal_product());
			}
		}
		return "redirect:/producto";
	}

}
