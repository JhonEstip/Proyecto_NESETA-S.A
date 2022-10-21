package com.company.netesa.service;

import java.util.List;
import java.util.Optional;

import com.company.netesa.model.Genero;

public interface GeneroService {
	public List<Genero> consultarGenero();
	public Optional<Genero> consultarGeneroId(Long id);
	public Genero guardarGenero(Genero genero);
	public void eliminarGenero(Long id);
}
