package com.company.netesa.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.netesa.model.Especialidad;
import com.company.netesa.repository.EspecialidadRepository;

@Service
public class EspecialidadServiceImpl implements EspecialidadService{
	@Autowired
	private EspecialidadRepository repositorioEspecialidad;
	
	@Override
	@Transactional
	public List<Especialidad> consultarEspecialidad(){
		return repositorioEspecialidad.findAll();
	}
	
	@Override
	@Transactional
	public Optional<Especialidad> consultarEspecialidadId(Long id) {
		return repositorioEspecialidad.findById(id);
	}
	
	@Override
	@Transactional
	public Especialidad guardarEspecialidad(Especialidad especialidad) {
		return repositorioEspecialidad.save(especialidad);
	}
	
	@Override
	@Transactional
	public void eliminarEspecialidad(Long id) {
		repositorioEspecialidad.deleteById(id);
	}

}
