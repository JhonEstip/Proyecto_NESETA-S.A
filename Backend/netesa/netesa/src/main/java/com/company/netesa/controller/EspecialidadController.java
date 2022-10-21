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

import com.company.netesa.model.Especialidad;
import com.company.netesa.service.EspecialidadService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;

@RestController
@RequestMapping("/api/especialidad")
public class EspecialidadController {
	
	@Autowired
	private EspecialidadService especialidadService;
	
	@PostMapping
	public ResponseEntity<?> create(@RequestBody Especialidad especialidad){
		return ResponseEntity.status(HttpStatus.CREATED).body(especialidadService.guardarEspecialidad(especialidad));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> read(@PathVariable Long id){
		Optional<Especialidad> especialidad = especialidadService.consultarEspecialidadId(id);
		if(!especialidad.isPresent()) {
			return ResponseEntity.notFound().build();
		} 
		return ResponseEntity.ok(especialidad);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@RequestBody Especialidad EspecialidadDetalle, @PathVariable Long id){
		Optional<Especialidad> especialidad = especialidadService.consultarEspecialidadId(id);
		if(!especialidad.isPresent()) {
			return ResponseEntity.notFound().build();
		} 
		BeanUtils.copyProperties(EspecialidadDetalle, especialidad.get());
		especialidad.get().setId(id);
		return ResponseEntity.status(HttpStatus.CREATED).body(especialidadService.guardarEspecialidad(especialidad.get()));
	}
	
	@PatchMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> updatePartial(@RequestBody JsonPatch patch, @PathVariable Long id){
		try {
			Optional<Especialidad> especialidad = especialidadService.consultarEspecialidadId(id);
			if(!especialidad.isPresent()) {
				return ResponseEntity.notFound().build();
			}
			
			ObjectMapper objectMapper = new ObjectMapper();
			JsonNode patched = patch.apply(objectMapper.convertValue(especialidad.get(), JsonNode.class));
			Especialidad especialidadPatched = objectMapper.treeToValue(patched, Especialidad.class);
			
			return ResponseEntity.status(HttpStatus.CREATED).body(especialidadService.guardarEspecialidad(especialidadPatched));
	    } catch (JsonPatchException | JsonProcessingException e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	    } 
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id){
		Optional<Especialidad> especialidad = especialidadService.consultarEspecialidadId(id);
		if(!especialidad.isPresent()) {
			return ResponseEntity.notFound().build();
		} 
		especialidadService.eliminarEspecialidad(id);
		return ResponseEntity.ok().build();
	}
	
	@GetMapping
	public List<Especialidad> readAll(){
		return especialidadService.consultarEspecialidad();
	}
}
