package com.company.netesa.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tipo_documento")
public class TipoDocumento implements Serializable{

	private static final long serialVersionUID = -6505371535523099832L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_tipo_documento;
	
	@Column(name="tipo_documento", length = 3)
	private String tipoDocumento;

	@Column(name="descr_documento", length = 50)
	private String descrDocumento;

	public Long getId_tipo_documento() {
		return id_tipo_documento;
	}

	public void setId_tipo_documento(Long id_tipo_documento) {
		this.id_tipo_documento = id_tipo_documento;
	}

	public String getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public String getDescrDocumento() {
		return descrDocumento;
	}

	public void setDescrDocumento(String descrDocumento) {
		this.descrDocumento = descrDocumento;
	}
	
}
