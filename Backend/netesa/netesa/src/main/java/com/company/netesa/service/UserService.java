package com.company.netesa.service;

import java.util.List;
import java.util.Optional;

import com.company.netesa.model.User;

public interface UserService {
	public List<User> consultarUsuarios();
	public Optional<User> consultarUsuarioId(Long id);
	public User guardarUsuario(User usuario);
	public void eliminarUsuario(Long id);
	public List<User> findUserById(Long id);
}
