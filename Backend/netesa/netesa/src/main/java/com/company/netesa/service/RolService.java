package com.company.netesa.service;

import java.util.List;
import java.util.Optional;

import com.company.netesa.model.Rol;


public interface RolService {
	public List<Rol> consultarRol();
	public Optional<Rol> consultarRolId(Long id);
	public Rol guardarRol(Rol rol);
	public void eliminarRol(Long id);
}
