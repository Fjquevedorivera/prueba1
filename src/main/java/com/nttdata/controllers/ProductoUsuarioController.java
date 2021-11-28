package com.nttdata.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.nttdata.services.ProductoUsuarioService;

@Controller
public class ProductoUsuarioController {
	
	@Autowired
	ProductoUsuarioService puService;
	
}
