package com.nttdata.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nttdata.models.Usuario;
import com.nttdata.services.UsuarioService;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {
	
	@Autowired 
	UsuarioService usuarioService;
	
	@RequestMapping("")
	public String usuario(@ModelAttribute("usuario") Usuario usuario,
			Model model) {
		model.addAttribute("listaUsuarios", usuarioService.obtenerListaUsuarios());
		return "usuario/usuario.jsp";
	}
	
	@RequestMapping("/agregar")
	public String login(@Valid @ModelAttribute("usuario") Usuario usuario) {
		usuarioService.insertarUsuario(usuario);
		return "redirect:/usuario";
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
