package com.company.netesa.service;

import java.util.List;
import java.util.Optional;

import com.company.netesa.model.Ciudad;

public interface CiudadService {

	public List<Ciudad> consultarCiudad();
	public Optional<Ciudad> consultarCiudadId(Long id);
	public Ciudad guardarCiudad(Ciudad ciudad);
	void eliminarCiudad(Long id);

}
