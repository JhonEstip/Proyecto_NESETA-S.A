package com.company.netesa.controller;

import org.springframework.http.MediaType;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.netesa.model.User;
import com.company.netesa.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;


@RestController
@RequestMapping("/api/usuarios")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping
	public ResponseEntity<?> create(@RequestBody User usuario){
		return ResponseEntity.status(HttpStatus.CREATED).body(userService.guardarUsuario(usuario));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> read(@PathVariable Long id){
		Optional<User> usuario = userService.consultarUsuarioId(id);
		if(!usuario.isPresent()) {
			return ResponseEntity.notFound().build();
		} 
		return ResponseEntity.ok(usuario);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@RequestBody User UserDetalle, @PathVariable Long id){
		Optional<User> user = userService.consultarUsuarioId(id);
		if(!user.isPresent()) {
			return ResponseEntity.notFound().build();
		} 
		BeanUtils.copyProperties(UserDetalle, user.get());
		user.get().setId(id);
		return ResponseEntity.status(HttpStatus.CREATED).body(userService.guardarUsuario(user.get()));
	}
	
	@PatchMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> updatePartial(@RequestBody JsonPatch patch, @PathVariable Long id){
		try {
			Optional<User> usuario = userService.consultarUsuarioId(id);
			if(!usuario.isPresent()) {
				return ResponseEntity.notFound().build();
			}
			
			ObjectMapper objectMapper = new ObjectMapper();
			JsonNode patched = patch.apply(objectMapper.convertValue(usuario.get(), JsonNode.class));
		    User usuarioPatched = objectMapper.treeToValue(patched, User.class);
			
			return ResponseEntity.status(HttpStatus.CREATED).body(userService.guardarUsuario(usuarioPatched));
	    } catch (JsonPatchException | JsonProcessingException e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	    } 
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id){
		Optional<User> usuario = userService.consultarUsuarioId(id);
		if(!usuario.isPresent()) {
			return ResponseEntity.notFound().build();
		} 
		userService.eliminarUsuario(id);
		return ResponseEntity.ok().build();
	}
	
	@GetMapping
	public List<User> readAll(){
		return userService.consultarUsuarios();
	}

}
