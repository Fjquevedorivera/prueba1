package com.nttdata.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "categorias")
public class Categoria {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;
	private String description;
	
	// Relación nan
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
		name = "categorias_productos", // Tabla intermedia
		joinColumns = @JoinColumn(name="categoria_id"), // Nombre posicionado
		inverseJoinColumns = @JoinColumn(name="producto_id") // Nombre refereciado
	)
	
	private List<Producto> productos;

	public Categoria() {
		super();
	}

	public Categoria(String name, String description, List<Producto> productos) {
		super();
		this.name = name;
		this.description = description;
		this.productos = productos;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Producto> getProductos() {
		return productos;
	}

	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}

}
