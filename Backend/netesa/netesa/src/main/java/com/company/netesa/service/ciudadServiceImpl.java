package com.company.netesa.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.netesa.model.Ciudad;
import com.company.netesa.repository.CiudadRepository;


@Service
public class ciudadServiceImpl implements CiudadService{
	@Autowired
	private CiudadRepository repositorioCiudad;
	
	@Override
	@Transactional
	public Ciudad guardarCiudad(Ciudad ciudad) {
		return repositorioCiudad.save(ciudad);
	}

	@Override
	public Optional<Ciudad> consultarCiudadId(Long id) {
		return repositorioCiudad.findById(id);
	}

	@Override
	public void eliminarCiudad(Long id) {
		repositorioCiudad.deleteById(id);
	}

	@Override
	public List<Ciudad> consultarCiudad() {
		return repositorioCiudad.findAll();
	}
}
