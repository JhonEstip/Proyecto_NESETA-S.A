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

import com.company.netesa.model.Genero;
import com.company.netesa.service.GeneroService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;

@RestController
@RequestMapping("/api/genero")
public class GeneroController {
	
	@Autowired
	private GeneroService generoService;
	
	@PostMapping
	public ResponseEntity<?> create(@RequestBody Genero genero){
		return ResponseEntity.status(HttpStatus.CREATED).body(generoService.guardarGenero(genero));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> read(@PathVariable Long id){
		Optional<Genero> genero = generoService.consultarGeneroId(id);
		if(!genero.isPresent()) {
			return ResponseEntity.notFound().build();
		} 
		return ResponseEntity.ok(genero);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@RequestBody Genero GeneroDetalle, @PathVariable Long id){
		Optional<Genero> genero = generoService.consultarGeneroId(id);
		if(!genero.isPresent()) {
			return ResponseEntity.notFound().build();
		} 
		BeanUtils.copyProperties(GeneroDetalle, genero.get());
		genero.get().setId(id);
		return ResponseEntity.status(HttpStatus.CREATED).body(generoService.guardarGenero(genero.get()));
	}
	
	@PatchMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> updatePartial(@RequestBody JsonPatch patch, @PathVariable Long id){
		try {
			Optional<Genero> genero = generoService.consultarGeneroId(id);
			if(!genero.isPresent()) {
				return ResponseEntity.notFound().build();
			}
			
			ObjectMapper objectMapper = new ObjectMapper();
			JsonNode patched = patch.apply(objectMapper.convertValue(genero.get(), JsonNode.class));
		    Genero generoPatched = objectMapper.treeToValue(patched, Genero.class);
			
			return ResponseEntity.status(HttpStatus.CREATED).body(generoService.guardarGenero(generoPatched));
	    } catch (JsonPatchException | JsonProcessingException e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	    } 
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id){
		Optional<Genero> genero = generoService.consultarGeneroId(id);
		if(!genero.isPresent()) {
			return ResponseEntity.notFound().build();
		} 
		generoService.eliminarGenero(id);
		return ResponseEntity.ok().build();
	}
	
	@GetMapping
	public List<Genero> readAll(){
		return generoService.consultarGenero();
	}
}
