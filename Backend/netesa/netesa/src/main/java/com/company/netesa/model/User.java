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
@Table(name="usuario")
public class User implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1706391519147865294L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="numero_documento")
	private Long numeroDocumento;
	
	@Column(name="nombre", length = 50)
	private String nombre;
	
	@Column(name="apellido", length = 50)
	private String apellido;
	
	@Column(name="direccion", length = 50)
	private String direccion;
	
	@Column(name="celular")
	private Long celular;
	
	@Column(name="email", length = 50)
	private String email;
	
	@Column(name="password", length = 50)
	private String password;
	
}
