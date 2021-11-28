package com.nttdata.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nttdata.models.Usuario;
import com.nttdata.services.ProductoService;
import com.nttdata.services.UsuarioService;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {
	
	@Autowired 
	UsuarioService usuarioService;
	
	@Autowired 
	ProductoService productoService;
	
	@RequestMapping("")
	public String usuario(@ModelAttribute("usuario") Usuario usuario,
			Model model) {
		model.addAttribute("listaUsuarios", usuarioService.obtenerListaUsuarios());
		return "usuario/usuario.jsp";
	}
	
	@RequestMapping("/registro")
	public String registro(@ModelAttribute("usuario") Usuario usuario,
			Model model) {
		return "usuario/registro.jsp";
	}
	
	@RequestMapping("/agregar")
	public String agregar(@Valid @ModelAttribute("usuario") Usuario usuario) {
		usuarioService.insertarUsuario(usuario);
		return "redirect:/usuario";
	}
	
	@RequestMapping("/registrar")
	public String registrar(@Valid @ModelAttribute("usuario") Usuario usuario) {
		usuarioService.insertarUsuario(usuario);
		return "redirect:/usuario/login";
	}
	
	@RequestMapping("/login")
	public String login(@ModelAttribute("usuario") Usuario usuario) {
		System.out.println(usuario.getId());
		return "usuario/login.jsp";
	}
	
	@RequestMapping("/logout")
	public String logout(@ModelAttribute("usuario") Usuario usuario, HttpSession session) {
		System.out.println(usuario.getId());
		session.setAttribute("usuarioLogin", null);
		return "usuario/login.jsp";
	}
	
	@RequestMapping("/iniciar")
	public String iniciar(@ModelAttribute("usuario") Usuario usuario, Model model, HttpSession session) {
		Usuario usuarioLogin = usuarioService.getIdLoginUser(usuario.getEmail(), usuario.getPassword());
		
		if(usuarioLogin != null) {
			System.out.println(usuarioLogin.getId());
			session.setAttribute("usuarioLogin", usuarioLogin);
			model.addAttribute("usuarioLogin", usuarioLogin);
			model.addAttribute("listaProductos", productoService.obtenerListaProducto());
			model.addAttribute("error", null);
			return "redirect:/producto";
		} else {
			model.addAttribute("error", "Usuario y contraseña incorrectos");
			return "usuario/login.jsp";
		}
		
	}
	
	@RequestMapping("/eliminar")
	public String eliminarObj(@RequestParam("id") Long id) {
		
		System.out.println("Id: "+ id);
		Usuario usuario = usuarioService.buscarUsuarioId(id);
		
		if(usuario != null) {
			usuarioService.eliminarUsuarioObj(usuario);
		}

		return "redirect:/usuario";
	}
	
	@RequestMapping("/editar")
	public String editar(@RequestParam("id") Long id, 
			Model model, 
			HttpSession session) {
		
		System.out.println("Id: "+ id);
		Usuario usuario = usuarioService.buscarUsuarioId(id);
		
		if(usuario != null) {
			session.setAttribute("usuarioEditar", usuario);
			model.addAttribute("usuarioActualizar", usuario);
		}

		return "usuario/usuarioEditar.jsp";
	}
	
	@RequestMapping("/actualizar")
	public String actualizar(@ModelAttribute("usuarioActualizar") Usuario usuario, 
			Model model, 
			HttpSession session) {
		
		Usuario usuarioAntiguo = (Usuario)session.getAttribute("usuarioEditar");
		
		if(usuarioAntiguo != null) {
			usuarioAntiguo.setName(usuario.getName());
			usuarioAntiguo.setLast_name(usuario.getLast_name());
			usuarioAntiguo.setEmail(usuario.getEmail());
			usuarioAntiguo.setPassword(usuario.getPassword());
			
			usuarioService.actualizarUsuario(usuarioAntiguo);
			return "redirect:/usuario";
		}
		
		return "Usuario Incorrecto";
	}
	
}
