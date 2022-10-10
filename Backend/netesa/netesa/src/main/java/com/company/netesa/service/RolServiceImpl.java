package com.company.netesa.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.netesa.model.Rol;
import com.company.netesa.model.User;
import com.company.netesa.repository.RolRepository;
import com.company.netesa.repository.UserRepository;

@Service
public class RolServiceImpl implements RolService{
	
	@Autowired
	private RolRepository repositorioRol;
	
	@Override
	@Transactional
	public List<Rol> consultarRol(){
		return repositorioRol.findAll();
	}
	
	@Override
	@Transactional
	public Optional<Rol> consultarRolId(Long id) {
		return repositorioRol.findById(id);
	}
	
	@Override
	@Transactional
	public Rol guardarRol(Rol rol) {
		return repositorioRol.save(rol);
	}
	
	@Override
	@Transactional
	public void eliminarRol(Long id) {
		repositorioRol.deleteById(id);
	}
}
