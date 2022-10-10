package com.company.netesa.service;

import java.util.List;
import java.util.Optional;

import com.company.netesa.model.Especialidad;

public interface EspecialidadService {
	public List<Especialidad> consultarEspecialidad();
	public Optional<Especialidad> consultarEspecialidadId(Long id);
	public Especialidad guardarEspecialidad(Especialidad citas);
	public void eliminarEspecialidad(Long id);
}
