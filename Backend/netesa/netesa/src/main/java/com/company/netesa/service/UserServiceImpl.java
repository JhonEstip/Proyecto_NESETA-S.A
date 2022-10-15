package com.company.netesa.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.netesa.model.User;
import com.company.netesa.repository.UserRepository;


@Service
public class UserServiceImpl implements UserService{
	@Autowired
	private UserRepository repositorioUsuario;
	
	@Override
	@Transactional
	public List<User> consultarUsuarios(){
		return repositorioUsuario.findAll();
	}
	
	@Override
	@Transactional
	public Optional<User> consultarUsuarioId(Long id) {
		return repositorioUsuario.findById(id);
	}
	
	@Override
	@Transactional
	public User guardarUsuario(User usuario) {
		return repositorioUsuario.save(usuario);
	}
	
	@Override
	@Transactional
	public void eliminarUsuario(Long id) {
		repositorioUsuario.deleteById(id);
	}
	
	@Override
	public List<User> findUserById(Long id) {
		List<User> personas = repositorioUsuario.findUserById(id);
		return personas;
	}

}
