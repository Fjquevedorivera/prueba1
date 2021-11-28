package com.nttdata.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@Table(name = "productos_usuarios")
@SQLDelete(sql = "UPDATE productos_usuarios SET deleted = true WHERE id = ?")//trasforma eliminación JPA en actualización
@Where(clause = "deleted = false")
public class ProductoUsuario {
	
	@Id //clave primaria o PK
	@GeneratedValue(strategy= GenerationType.IDENTITY) // auto incrementable	
	private Long id;
	private Integer quantity_product;
	private Integer total_product;
	
	@Column(updatable = false)
	private Date createdAt;
	private Date updatedAt;
	private boolean deleted = Boolean.FALSE;
	
	// Relaciones
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "usuario_id")
	private Usuario usuario;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "producto_id")
	private Producto producto;

	@PrePersist
    protected void onCreate(){
        this.createdAt = new Date();
    }
	
    @PreUpdate
    protected void onUpdate(){
        this.updatedAt = new Date();
    }

	public ProductoUsuario() {
		super();
	}

	public ProductoUsuario(Integer quantity_product, Integer total_product, boolean deleted, Usuario usuario,
			Producto producto) {
		super();
		this.quantity_product = quantity_product;
		this.total_product = total_product;
		this.deleted = deleted;
		this.usuario = usuario;
		this.producto = producto;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getQuantity_product() {
		return quantity_product;
	}

	public void setQuantity_product(Integer quantity_product) {
		this.quantity_product = quantity_product;
	}

	public Integer getTotal_product() {
		return total_product;
	}

	public void setTotal_product(Integer total_product) {
		this.total_product = total_product;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}
    
    
}
