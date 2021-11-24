package com.nttdata.services;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nttdata.models.Producto;
import com.nttdata.repositories.ProductoRepository;

@Service
public class ProductoService {
	
	@Autowired
	ProductoRepository productoRepository;
	
	public void insertarProducto(@Valid Producto usuario) {
		productoRepository.save(usuario);	
	}
	
	public void actualizarProducto(@Valid Producto usuario) {
		productoRepository.save(usuario);
	}

	public List<Producto> obtenerListaProducto() {
		return productoRepository.findAll();
	}
	
	public Producto buscarProductoId(Long id) {
		return productoRepository.findById(id).get();
	}

	public void eliminarProducto(Long id) {
		productoRepository.deleteById(id);
	}

	public void eliminarProductoObj(Producto usuario) {
		productoRepository.delete(usuario);
	}
	
}
