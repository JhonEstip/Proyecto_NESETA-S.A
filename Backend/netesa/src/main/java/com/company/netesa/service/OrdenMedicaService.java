package com.company.netesa.service;

import java.util.List;
import java.util.Optional;

import com.company.netesa.model.OrdenMedica;

public interface OrdenMedicaService {
	public List<OrdenMedica> consultarOrdenes();
	public Optional<OrdenMedica> consultarOrdenId(Long id);
	public OrdenMedica guardarOrden(OrdenMedica orden);
	public void eliminarOrden(Long id);
}
