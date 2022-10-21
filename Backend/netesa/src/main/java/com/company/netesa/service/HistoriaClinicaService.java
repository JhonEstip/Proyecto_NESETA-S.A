package com.company.netesa.service;

import java.util.List;
import java.util.Optional;

import com.company.netesa.model.HistoriaClinica;

public interface HistoriaClinicaService {
	public List<HistoriaClinica> consultarHistorias();
	public Optional<HistoriaClinica> consultarHistoriaId(Long id);
	public HistoriaClinica guardarHistoria(HistoriaClinica historia);
	public void eliminarHistoria(Long id);
}
