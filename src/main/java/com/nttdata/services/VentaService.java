package com.nttdata.services;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nttdata.models.Venta;
import com.nttdata.repositories.VentaRepository;

@Service
public class VentaService {
	@Autowired
	VentaRepository ventaRepository;

	public void insertarVenta(@Valid Venta venta) {
		ventaRepository.save(venta);
	}
	
	public void actualizarVenta(@Valid Venta venta) {
		ventaRepository.save(venta);
	}
	
	public List<Venta> obtenerListaVenta() {
		return ventaRepository.findAll();
	}
	
	public Venta buscarVentaId(Long id) {
		return ventaRepository.findById(id).get();
	}

	public void eliminarVenta(Long id) {
		ventaRepository.deleteById(id);
	}

	public void eliminarVentaObj(Venta venta) {
		ventaRepository.delete(venta);
	}
}
