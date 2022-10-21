package com.company.netesa.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.netesa.model.Ciudad;
import com.company.netesa.model.Sucursal;
import com.company.netesa.repository.CiudadRepository;
import com.company.netesa.repository.SucursalRepository;


@Service
public class sucursalServiceImpl implements SucursalService{
	@Autowired
	private SucursalRepository repositorioSucursal;
	
	@Override
	@Transactional
	public Sucursal guardarSucursal(Sucursal sucursal) {
		return repositorioSucursal.save(sucursal);
	}

	@Override
	public Optional<Sucursal> consultarSucursalId(Long id) {
		return repositorioSucursal.findById(id);
	}

	@Override
	public void eliminarSucursal(Long id) {
		repositorioSucursal.deleteById(id);
	}

	@Override
	public List<Sucursal> consultarSucursal() {
		return repositorioSucursal.findAll();
	}
}
