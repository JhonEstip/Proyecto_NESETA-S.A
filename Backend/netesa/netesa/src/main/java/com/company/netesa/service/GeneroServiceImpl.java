package com.company.netesa.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.netesa.model.Genero;
import com.company.netesa.repository.GeneroRepository;


@Service
public class GeneroServiceImpl implements GeneroService{
	@Autowired
	private GeneroRepository repositorioGenero;
	
	@Override
	@Transactional
	public List<Genero> consultarGenero(){
		return repositorioGenero.findAll();
	}
	
	@Override
	@Transactional
	public Optional<Genero> consultarGeneroId(Long id) {
		return repositorioGenero.findById(id);
	}
	
	@Override
	@Transactional
	public Genero guardarGenero(Genero genero) {
		return repositorioGenero.save(genero);
	}
	
	@Override
	@Transactional
	public void eliminarGenero(Long id) {
		repositorioGenero.deleteById(id);
	}
}
