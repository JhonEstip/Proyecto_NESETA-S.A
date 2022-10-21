package com.company.netesa.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="sucursal")
public class Sucursal implements Serializable{

	private static final long serialVersionUID = -5403194857560723906L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_sucursal;
	
	@Column(name="descripcion", length = 50)
	private String descripcion;
	
	@OneToOne
	@JoinColumn(name="id_ciudad")
	private Ciudad ciudad;
	
	
	public Long getId() {
		return id_sucursal;
	}

	public void setId(Long id) {
		this.id_sucursal = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Ciudad getCiudad() {
		return ciudad;
	}

	public void setCiudad(Ciudad ciudad) {
		this.ciudad = ciudad;
	}
	
	
}
