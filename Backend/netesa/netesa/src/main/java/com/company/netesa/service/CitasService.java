package com.company.netesa.service;

import java.util.List;
import java.util.Optional;

import com.company.netesa.model.Citas;

public interface CitasService {
	public List<Citas> consultarCitas();
	public Optional<Citas> consultarCitasId(Long id);
	public Citas guardarCitas(Citas citas);
	public void eliminarCitas(Long id);
}
