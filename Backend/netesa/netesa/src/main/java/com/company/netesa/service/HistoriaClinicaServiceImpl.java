package com.company.netesa.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.netesa.model.HistoriaClinica;
import com.company.netesa.repository.HistoriaClinicaRepository;

@Service
public class HistoriaClinicaServiceImpl implements HistoriaClinicaService{
	@Autowired
	private HistoriaClinicaRepository repositorioHistoria;
	
	@Override
	@Transactional
	public List<HistoriaClinica> consultarHistorias(){
		return repositorioHistoria.findAll();
	}
	
	@Override
	@Transactional
	public Optional<HistoriaClinica> consultarHistoriaId(Long id) {
		return repositorioHistoria.findById(id);
	}
	
	@Override
	@Transactional
	public HistoriaClinica guardarHistoria(HistoriaClinica historia) {
		return repositorioHistoria.save(historia);
	}
	
	@Override
	@Transactional
	public void eliminarHistoria(Long id) {
		repositorioHistoria.deleteById(id);
	}

}
