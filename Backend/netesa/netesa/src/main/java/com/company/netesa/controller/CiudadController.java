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

import com.company.netesa.model.Ciudad;
import com.company.netesa.model.Genero;
import com.company.netesa.service.CiudadService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;


@RestController
@RequestMapping("/api/ciudad")
public class CiudadController {
	
	@Autowired
	private CiudadService ciudadService;
	
	@PostMapping
	public ResponseEntity<?> create (@RequestBody Ciudad ciudad){
		return ResponseEntity.status(HttpStatus.CREATED).body(ciudadService.guardarCiudad(ciudad));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> read(@PathVariable Long id){
		Optional<Ciudad> ciudad = ciudadService.consultarCiudadId(id);
		if(!ciudad.isPresent()) {
			return ResponseEntity.notFound().build();
		} 
		return ResponseEntity.ok(ciudad);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id){
		Optional<Ciudad> ciudad = ciudadService.consultarCiudadId(id);
		if(!ciudad.isPresent()) {
			return ResponseEntity.notFound().build();
		} 
		ciudadService.eliminarCiudad(id);
		return ResponseEntity.ok().build();
	}
	
	@GetMapping
	public List<Ciudad> readAll(){
		return ciudadService.consultarCiudad();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@RequestBody Ciudad CiudadDetalle, @PathVariable Long id){
		Optional<Ciudad> ciudad = ciudadService.consultarCiudadId(id);
		if(!ciudad.isPresent()) {
			return ResponseEntity.notFound().build();
		} 
		BeanUtils.copyProperties(CiudadDetalle, ciudad.get());
		ciudad.get().setId_ciudad(id);
		return ResponseEntity.status(HttpStatus.CREATED).body(ciudadService.guardarCiudad(ciudad.get()));
	}
	
	@PatchMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> updatePartial(@RequestBody JsonPatch patch, @PathVariable Long id){
		try {
			Optional<Ciudad> ciudad = ciudadService.consultarCiudadId(id);
			if(!ciudad.isPresent()) {
				return ResponseEntity.notFound().build();
			}
			
			ObjectMapper objectMapper = new ObjectMapper();
			JsonNode patched = patch.apply(objectMapper.convertValue(ciudad.get(), JsonNode.class));
		    Ciudad ciudadPatched = objectMapper.treeToValue(patched, Ciudad.class);
			
			return ResponseEntity.status(HttpStatus.CREATED).body(ciudadService.guardarCiudad(ciudadPatched));
	    } catch (JsonPatchException | JsonProcessingException e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	    } 
	}

}
