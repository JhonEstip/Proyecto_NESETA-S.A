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

import com.company.netesa.model.Consultorio;
import com.company.netesa.service.ConsultorioService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;


@RestController
@RequestMapping("/api/consultorio")
public class ConsultorioController {
	
	@Autowired
	private ConsultorioService consultorioService;
	
	@PostMapping
	public ResponseEntity<?> create (@RequestBody Consultorio consultorio){
		return ResponseEntity.status(HttpStatus.CREATED).body(consultorioService.guardarConsultorio(consultorio));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> read(@PathVariable Long id){
		Optional<Consultorio> consultorio = consultorioService.consultarConsultorioId(id);
		if(!consultorio.isPresent()) {
			return ResponseEntity.notFound().build();
		} 
		return ResponseEntity.ok(consultorio);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id){
		Optional<Consultorio> consultorio = consultorioService.consultarConsultorioId(id);
		if(!consultorio.isPresent()) {
			return ResponseEntity.notFound().build();
		} 
		consultorioService.eliminarConsultorio(id);
		return ResponseEntity.ok().build();
	}
	
	@GetMapping
	public List<Consultorio> readAll(){
		return consultorioService.consultarConsultorio();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@RequestBody Consultorio ConsultorioDetalle, @PathVariable Long id){
		Optional<Consultorio> consultorio = consultorioService.consultarConsultorioId(id);
		if(!consultorio.isPresent()) {
			return ResponseEntity.notFound().build();
		} 
		BeanUtils.copyProperties(ConsultorioDetalle, consultorio.get());
		consultorio.get().setId_consultorio(id);
		return ResponseEntity.status(HttpStatus.CREATED).body(consultorioService.guardarConsultorio(consultorio.get()));
	}
	
	@PatchMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> updatePartial(@RequestBody JsonPatch patch, @PathVariable Long id){
		try {
			Optional<Consultorio> consultorio = consultorioService.consultarConsultorioId(id);
			if(!consultorio.isPresent()) {
				return ResponseEntity.notFound().build();
			}
			
			ObjectMapper objectMapper = new ObjectMapper();
			JsonNode patched = patch.apply(objectMapper.convertValue(consultorio.get(), JsonNode.class));
		    Consultorio consultorioPatched = objectMapper.treeToValue(patched, Consultorio.class);
			
			return ResponseEntity.status(HttpStatus.CREATED).body(consultorioService.guardarConsultorio(consultorioPatched));
	    } catch (JsonPatchException | JsonProcessingException e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	    } 
	}

}
