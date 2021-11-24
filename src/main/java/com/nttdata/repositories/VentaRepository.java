package com.nttdata.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.nttdata.models.Venta;

public interface VentaRepository extends CrudRepository<Venta, Long>{
	List<Venta> findAll(); 
}
