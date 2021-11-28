package com.nttdata.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nttdata.repositories.ProductoUsuarioRepository;

@Service
public class ProductoUsuarioService {
	
	@Autowired
	ProductoUsuarioRepository puRepository;
	
}
