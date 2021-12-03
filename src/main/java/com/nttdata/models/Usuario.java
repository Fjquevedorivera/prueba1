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
import javax.persistence.Transient;

@Entity //representación de la entidad
@Table(name="usuarios") // nombre de la tabla en la BD
public class Usuario {
	
	@Id //clave primaria o PK
	@GeneratedValue(strategy= GenerationType.IDENTITY) // auto incrementable	
	private Long id;
	
	private String name;
	private String last_name;
	private String email;
	private String password;
	@Transient
	private String passwordConfirmation;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
			name = "roles_usuarios", // Tabla intermedia
			joinColumns = @JoinColumn(name="usuario_id"), // Nombre posicionado
			inverseJoinColumns = @JoinColumn(name="rol_id") // Nombre refereciado
			)
	private List<Role> roles;
	
	public List<Role> getRoles() {
		return roles;
	}
	
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	
	public String getPasswordConfirmation() {
		return passwordConfirmation;
	}

	public void setPasswordConfirmation(String passwordConfirmation) {
		this.passwordConfirmation = passwordConfirmation;
	}
	
	public Usuario() {
		super();
	}
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
		name = "productos_usuarios", // Tabla intermedia
		joinColumns = @JoinColumn(name="usuario_id"), // Nombre posicionado
		inverseJoinColumns = @JoinColumn(name="producto_id") // Nombre refereciado
	)
	private List<Producto> productos;

	public List<Producto> getProductos() {
		return productos;
	}

	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}

	public Usuario(String name, String last_name, String email, String password, String passwordConfirmation,
			List<Role> roles, List<Producto> productos) {
		super();
		this.name = name;
		this.last_name = last_name;
		this.email = email;
		this.password = password;
		this.passwordConfirmation = passwordConfirmation;
		this.roles = roles;
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

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
