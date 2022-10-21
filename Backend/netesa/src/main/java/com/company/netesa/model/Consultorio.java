package com.company.netesa.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="consultorio")
public class Consultorio implements Serializable{

	private static final long serialVersionUID = -2677807576655241761L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_consultorio;
	
	@ManyToOne
	@JoinColumn(name="id_sucursal")
	private Sucursal id_sucursal;
	
	@Column(name="piso")
	private Long piso;
	
	@Column(name="descr_consultorio", length = 50)
	private String descr_consultorio;

	public Long getId_consultorio() {
		return id_consultorio;
	}

	public void setId_consultorio(Long id_consultorio) {
		this.id_consultorio = id_consultorio;
	}

	public Sucursal getId_sucursal() {
		return id_sucursal;
	}

	public void setId_sucursal(Sucursal id_sucursal) {
		this.id_sucursal = id_sucursal;
	}

	public Long getPiso() {
		return piso;
	}

	public void setPiso(Long piso) {
		this.piso = piso;
	}

	public String getDescr_consultorio() {
		return descr_consultorio;
	}

	public void setDescr_consultorio(String descr_consultorio) {
		this.descr_consultorio = descr_consultorio;
	}
	
}
