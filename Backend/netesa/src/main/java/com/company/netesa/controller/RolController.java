package com.company.netesa.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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

import com.company.netesa.model.Rol;
import com.company.netesa.service.RolService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;

@RestController
@RequestMapping("/api/rol")
public class RolController {
	@Autowired
	private RolService rolService;
	
	@PostMapping
	public ResponseEntity<?> create(@RequestBody Rol rol){
		return ResponseEntity.status(HttpStatus.CREATED).body(rolService.guardarRol(rol));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> read(@PathVariable Long id){
		Optional<Rol> rol = rolService.consultarRolId(id);
		if(!rol.isPresent()) {
			return ResponseEntity.notFound().build();
		} 
		return ResponseEntity.ok(rol);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@RequestBody Rol RolDetalle, @PathVariable Long id){
		Optional<Rol> rol = rolService.consultarRolId(id);
		if(!rol.isPresent()) {
			return ResponseEntity.notFound().build();
		} 
		BeanUtils.copyProperties(RolDetalle, rol.get());
		rol.get().setId(id);
		return ResponseEntity.status(HttpStatus.CREATED).body(rolService.guardarRol(rol.get()));
	}
	
	@PatchMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> updatePartial(@RequestBody JsonPatch patch, @PathVariable Long id){
		try {
			Optional<Rol> rol = rolService.consultarRolId(id);
			if(!rol.isPresent()) {
				return ResponseEntity.notFound().build();
			}
			
			ObjectMapper objectMapper = new ObjectMapper();
			JsonNode patched = patch.apply(objectMapper.convertValue(rol.get(), JsonNode.class));
		    Rol rolPatched = objectMapper.treeToValue(patched, Rol.class);
			
			return ResponseEntity.status(HttpStatus.CREATED).body(rolService.guardarRol(rolPatched));
	    } catch (JsonPatchException | JsonProcessingException e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	    } 
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id){
		Optional<Rol> rol = rolService.consultarRolId(id);
		if(!rol.isPresent()) {
			return ResponseEntity.notFound().build();
		} 
		rolService.eliminarRol(id);
		return ResponseEntity.ok().build();
	}
	
	@GetMapping
	public List<Rol> readAll(){
		return rolService.consultarRol();
	}


}
