package com.company.netesa.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.netesa.model.Medicos;
import com.company.netesa.repository.MedicosRepository;

@Service
public class MedicosServiceImpl implements MedicosService{
	@Autowired
	private MedicosRepository repositorioMedico;
	
	@Override
	@Transactional
	public List<Medicos> consultarMedicos(){
		return repositorioMedico.findAll();
	}
	
	@Override
	@Transactional
	public Optional<Medicos> consultarMedicoId(Long id) {
		return repositorioMedico.findById(id);
	}
	
	@Override
	@Transactional
	public Medicos guardarMedico(Medicos medico) {
		return repositorioMedico.save(medico);
	}
	
	@Override
	@Transactional
	public void eliminarMedico(Long id) {
		repositorioMedico.deleteById(id);
	}

}
