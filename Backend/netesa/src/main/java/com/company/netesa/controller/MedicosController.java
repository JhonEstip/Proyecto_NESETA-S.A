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

import com.company.netesa.model.Medicos;
import com.company.netesa.service.MedicosService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;

@RestController
@RequestMapping("/api/medicos")
public class MedicosController {
	
	@Autowired
	private MedicosService medicosService;
	
	@PostMapping
	public ResponseEntity<?> create(@RequestBody Medicos citas){
		return ResponseEntity.status(HttpStatus.CREATED).body(medicosService.guardarMedico(citas));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> read(@PathVariable Long id){
		Optional<Medicos> medico = medicosService.consultarMedicoId(id);
		if(!medico.isPresent()) {
			return ResponseEntity.notFound().build();
		} 
		return ResponseEntity.ok(medico);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@RequestBody Medicos CitasDetalle, @PathVariable Long id){
		Optional<Medicos> medico = medicosService.consultarMedicoId(id);
		if(!medico.isPresent()) {
			return ResponseEntity.notFound().build();
		} 
		BeanUtils.copyProperties(CitasDetalle, medico.get());
		medico.get().setId(id);
		return ResponseEntity.status(HttpStatus.CREATED).body(medicosService.guardarMedico(medico.get()));
	}
	
	@PatchMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> updatePartial(@RequestBody JsonPatch patch, @PathVariable Long id){
		try {
			Optional<Medicos> medico = medicosService.consultarMedicoId(id);
			if(!medico.isPresent()) {
				return ResponseEntity.notFound().build();
			}
			
			ObjectMapper objectMapper = new ObjectMapper();
			JsonNode patched = patch.apply(objectMapper.convertValue(medico.get(), JsonNode.class));
			Medicos medicoPatched = objectMapper.treeToValue(patched, Medicos.class);
			
			return ResponseEntity.status(HttpStatus.CREATED).body(medicosService.guardarMedico(medicoPatched));
	    } catch (JsonPatchException | JsonProcessingException e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	    } 
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id){
		Optional<Medicos> medico = medicosService.consultarMedicoId(id);
		if(!medico.isPresent()) {
			return ResponseEntity.notFound().build();
		} 
		medicosService.eliminarMedico(id);
		return ResponseEntity.ok().build();
	}
	
	@GetMapping
	public List<Medicos> readAll(){
		return medicosService.consultarMedicos();
	}
}
