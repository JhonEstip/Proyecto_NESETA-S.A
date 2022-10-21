package com.company.netesa.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.netesa.model.Consultorio;
import com.company.netesa.repository.ConsultorioRepository;


@Service
public class ConsultorioServiceImpl implements ConsultorioService{
	@Autowired
	private ConsultorioRepository repositorioConsultorio;
	
	@Override
	@Transactional
	public Consultorio guardarConsultorio(Consultorio consultorio) {
		return repositorioConsultorio.save(consultorio);
	}

	@Override
	public Optional<Consultorio> consultarConsultorioId(Long id) {
		return repositorioConsultorio.findById(id);
	}

	@Override
	public void eliminarConsultorio(Long id) {
		repositorioConsultorio.deleteById(id);
	}

	@Override
	public List<Consultorio> consultarConsultorio() {
		return repositorioConsultorio.findAll();
	}
}
