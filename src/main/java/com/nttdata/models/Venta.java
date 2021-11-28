package com.nttdata.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity //representación de la entidad
@Table(name="ventas") // nombre de la tabla en la BD
public class Venta {
	
	@Id //clave primaria o PK
	@GeneratedValue(strategy= GenerationType.IDENTITY) // auto incrementable	
	private Long id;
	
	private String date;
	private String total;
	
	public Venta() {
		super();
	}

	public Venta(String date, String total) {
		super();
		this.date = date;
		this.total = total;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

}
