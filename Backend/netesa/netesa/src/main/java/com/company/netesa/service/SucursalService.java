package com.company.netesa.service;

import java.util.List;
import java.util.Optional;

import com.company.netesa.model.Sucursal;

public interface SucursalService {

	public List<Sucursal> consultarSucursal();
	public Optional<Sucursal> consultarSucursalId(Long id);
	public Sucursal guardarSucursal(Sucursal sucursal);
	void eliminarSucursal(Long id);

}
