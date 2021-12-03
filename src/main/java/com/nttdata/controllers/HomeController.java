package com.nttdata.controllers;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nttdata.models.Usuario;
import com.nttdata.services.UsuarioService;

@Controller
public class HomeController {
	
	UsuarioService usuarioService;
	
	@RequestMapping("/home")
	public String home(Principal principal, Model model){
		return "home.jsp";
	}
	
	@RequestMapping("/login")
	public String login() {
		return "usuario/login.jsp";
	}
	
	@RequestMapping("/registro")
	public String registro(@ModelAttribute("usuario") Usuario usuario) {
		return "usuario/registro.jsp";
	}
}
