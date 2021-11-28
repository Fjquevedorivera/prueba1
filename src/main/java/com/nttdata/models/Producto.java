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

@Entity //representación de la entidad
@Table(name="productos") // nombre de la tabla en la BD
public class Producto {
	
	@Id //clave primaria o PK
	@GeneratedValue(strategy= GenerationType.IDENTITY) // auto incrementable	
	private Long id;
	
	private String name;
	private String brand;
	private Integer price;
	
	// Relación nan
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
		name = "categorias_productos", // Tabla intermedia
		joinColumns = @JoinColumn(name="producto_id"), // Nombre posicionado
		inverseJoinColumns = @JoinColumn(name="categoria_id") // Nombre refereciado
	)
	private List<Categoria> categorias;
	
	// Relación nan + Atributos
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
		name = "productos_usuarios", // Tabla intermedia
		joinColumns = @JoinColumn(name="producto_id"), // Nombre posicionado
		inverseJoinColumns = @JoinColumn(name="usuario_id") // Nombre refereciado
	)
	private List<Usuario> usuarios;
	
	public Producto() {
		super();
	}

	public Producto(String name, String brand, Integer price) {
		super();
		this.name = name;
		this.brand = brand;
		this.price = price;
	}

	public List<Categoria> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
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

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}
	
}
