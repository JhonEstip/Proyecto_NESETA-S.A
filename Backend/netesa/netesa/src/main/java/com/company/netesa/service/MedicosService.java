package com.company.netesa.service;

import java.util.List;
import java.util.Optional;

import com.company.netesa.model.Medicos;

public interface MedicosService {
	public List<Medicos> consultarMedicos();
	public Optional<Medicos> consultarMedicoId(Long id);
	public Medicos guardarMedico(Medicos citas);
	public void eliminarMedico(Long id);
}
