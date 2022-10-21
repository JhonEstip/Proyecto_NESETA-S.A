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

import com.company.netesa.model.HistoriaClinica;
import com.company.netesa.service.HistoriaClinicaService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;


@RestController
@RequestMapping("/api/historia")
public class HistoriaClinicaController {
	
	@Autowired
	private HistoriaClinicaService historiaService;
	
	@PostMapping
	public ResponseEntity<?> create(@RequestBody HistoriaClinica historia){
		return ResponseEntity.status(HttpStatus.CREATED).body(historiaService.guardarHistoria(historia));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> read(@PathVariable Long id){
		Optional<HistoriaClinica> historia = historiaService.consultarHistoriaId(id);
		if(!historia.isPresent()) {
			return ResponseEntity.notFound().build();
		} 
		return ResponseEntity.ok(historia);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@RequestBody HistoriaClinica HistoriaDetalle, @PathVariable Long id){
		Optional<HistoriaClinica> historia = historiaService.consultarHistoriaId(id);
		if(!historia.isPresent()) {
			return ResponseEntity.notFound().build();
		} 
		BeanUtils.copyProperties(HistoriaDetalle, historia.get());
		historia.get().setId(id);
		return ResponseEntity.status(HttpStatus.CREATED).body(historiaService.guardarHistoria(historia.get()));
	}
	
	@PatchMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> updatePartial(@RequestBody JsonPatch patch, @PathVariable Long id){
		try {
			Optional<HistoriaClinica> historia = historiaService.consultarHistoriaId(id);
			if(!historia.isPresent()) {
				return ResponseEntity.notFound().build();
			}
			
			ObjectMapper objectMapper = new ObjectMapper();
			JsonNode patched = patch.apply(objectMapper.convertValue(historia.get(), JsonNode.class));
			HistoriaClinica historiaPatched = objectMapper.treeToValue(patched, HistoriaClinica.class);
			
			return ResponseEntity.status(HttpStatus.CREATED).body(historiaService.guardarHistoria(historiaPatched));
	    } catch (JsonPatchException | JsonProcessingException e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	    } 
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id){
		Optional<HistoriaClinica> historia = historiaService.consultarHistoriaId(id);
		if(!historia.isPresent()) {
			return ResponseEntity.notFound().build();
		} 
		historiaService.eliminarHistoria(id);
		return ResponseEntity.ok().build();
	}
	
	@GetMapping
	public List<HistoriaClinica> readAll(){
		return historiaService.consultarHistorias();
	}

}
