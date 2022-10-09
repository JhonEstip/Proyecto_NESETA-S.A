package com.company.netesa.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.netesa.model.Citas;
import com.company.netesa.model.User;
import com.company.netesa.repository.CitasRepository;
import com.company.netesa.repository.UserRepository;

@Service
public class CitasServiceImpl implements CitasService{
	@Autowired
	private CitasRepository repositorioCitas;
	
	@Override
	@Transactional
	public List<Citas> consultarCitas(){
		return repositorioCitas.findAll();
	}
	
	@Override
	@Transactional
	public Optional<Citas> consultarCitasId(Long id) {
		return repositorioCitas.findById(id);
	}
	
	@Override
	@Transactional
	public Citas guardarCitas(Citas citas) {
		return repositorioCitas.save(citas);
	}
	
	@Override
	@Transactional
	public void eliminarCitas(Long id) {
		repositorioCitas.deleteById(id);
	}

}
