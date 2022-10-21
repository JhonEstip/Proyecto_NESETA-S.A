package com.company.netesa.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.netesa.model.OrdenMedica;
import com.company.netesa.repository.OrdenMedicaRepository;


@Service
public class OrdenMedicaServiceImpl implements OrdenMedicaService{
	@Autowired
	private OrdenMedicaRepository repositorioOrden;
	
	@Override
	@Transactional
	public List<OrdenMedica> consultarOrdenes(){
		return repositorioOrden.findAll();
	}
	
	@Override
	@Transactional
	public Optional<OrdenMedica> consultarOrdenId(Long id) {
		return repositorioOrden.findById(id);
	}
	
	@Override
	@Transactional
	public OrdenMedica guardarOrden(OrdenMedica orden) {
		return repositorioOrden.save(orden);
	}
	
	@Override
	@Transactional
	public void eliminarOrden(Long id) {
		repositorioOrden.deleteById(id);
	}

}
