package com.nttdata.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nttdata.models.ProductoUsuario;
import com.nttdata.repositories.ProductoUsuarioRepository;

@Service
public class ProductoUsuarioService {
	
	@Autowired
	ProductoUsuarioRepository puRepository;

	public void insertarCompra(ProductoUsuario productoUsuario) {
		puRepository.save(productoUsuario);		
	}
	
}
