package com.company.netesa.service;

import java.util.List;
import java.util.Optional;

import com.company.netesa.model.Ciudad;
import com.company.netesa.model.Consultorio;

public interface ConsultorioService {

	public List<Consultorio> consultarConsultorio();
	public Optional<Consultorio> consultarConsultorioId(Long id);
	public Consultorio guardarConsultorio(Consultorio consultorio);
	void eliminarConsultorio(Long id);

}
