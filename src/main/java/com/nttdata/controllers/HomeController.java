package com.nttdata.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nttdata.models.Usuario;

@Controller
public class HomeController {
	
	@RequestMapping("")
	public String home(@ModelAttribute("usuario") Usuario usuario){
		return "usuario/login.jsp";
	}
}
