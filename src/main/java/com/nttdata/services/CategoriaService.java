package com.nttdata.services;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nttdata.models.Categoria;
import com.nttdata.repositories.CategoriaRepository;

@Service
public class CategoriaService {
	
	@Autowired
	CategoriaRepository categoriaRepository;
	
	public List<Categoria> obtenerListaCategoria() {
		return categoriaRepository.findAll();
	}

	public void agregarCategoria(Categoria categoria) {
		categoriaRepository.save(categoria);
	}

	public Categoria buscarCategoriaId(Long id) {
		return categoriaRepository.findById(id).get();
	}
	
	public void eliminarCategoriaObj(Categoria categoria) {
		categoriaRepository.delete(categoria);
	}
	
	public void actualizarCategoria(@Valid Categoria categoria) {
		categoriaRepository.save(categoria);
	}
	
}
