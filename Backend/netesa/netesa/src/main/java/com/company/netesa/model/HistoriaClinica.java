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

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

@Entity
@Table(name="historia_clinica")
public class HistoriaClinica implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8053538059442478159L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OneToOne
    @JoinColumn(name = "id_proxima_cita")
	private Citas id_proxima_cita;
	
	@OneToOne
    @JoinColumn(name = "id_cita")
	private Citas id_cita;
	
	@OneToOne
    @JoinColumn(name = "id_usuario")
	private User id_usuario;
	
	@Column(name="registro_medico", length = 5000)
	private String registro_medico;
	
	@Column(name="diagnostico", length = 5000)
	private String diagnostico;
	
	@Column(name="siguiente_cita", length = 50)
	private String siguiente_cita;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Citas getId_proxima_cita() {
		return id_proxima_cita;
	}

	public void setId_proxima_cita(Citas id_proxima_cita) {
		this.id_proxima_cita = id_proxima_cita;
	}

	public Citas getId_cita() {
		return id_cita;
	}

	public void setId_cita(Citas id_cita) {
		this.id_cita = id_cita;
	}

	public User getId_usuario() {
		return id_usuario;
	}

	public void setId_usuario(User id_usuario) {
		this.id_usuario = id_usuario;
	}

	public String getRegistro_medico() {
		return registro_medico;
	}

	public void setRegistro_medico(String registro_medico) {
		this.registro_medico = registro_medico;
	}

	public String getDiagnostico() {
		return diagnostico;
	}

	public void setDiagnostico(String diagnostico) {
		this.diagnostico = diagnostico;
	}

	public String getSiguiente_cita() {
		return siguiente_cita;
	}

	public void setSiguiente_cita(String siguiente_cita) {
		this.siguiente_cita = siguiente_cita;
	}

	
	
}
