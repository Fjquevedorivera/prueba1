package com.nttdata.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.nttdata.models.Producto;

@Repository
public interface ProductoRepository extends CrudRepository<Producto, Long>{
	List<Producto> findAll();
	
	@Query(value = "SELECT DISTINCT p.* FROM productos p, categorias c \r\n"
			+ "LEFT JOIN categorias_productos cp \r\n"
			+ "ON cp.categoria_id = ?1 \r\n"
			+ "WHERE cp.producto_id = p.id", nativeQuery = true)
	List<Producto> productosByCategoria(Long id); 
}
