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

import com.company.netesa.model.OrdenMedica;
import com.company.netesa.service.OrdenMedicaService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;

@RestController
@RequestMapping("/api/ordenmedica")
public class OrdenMedicaController {
	
	@Autowired
	private OrdenMedicaService ordenService;
	
	@PostMapping
	public ResponseEntity<?> create(@RequestBody OrdenMedica orden){
		return ResponseEntity.status(HttpStatus.CREATED).body(ordenService.guardarOrden(orden));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> read(@PathVariable Long id){
		Optional<OrdenMedica> orden = ordenService.consultarOrdenId(id);
		if(!orden.isPresent()) {
			return ResponseEntity.notFound().build();
		} 
		return ResponseEntity.ok(orden);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@RequestBody OrdenMedica OrdenDetalle, @PathVariable Long id){
		Optional<OrdenMedica> orden = ordenService.consultarOrdenId(id);
		if(!orden.isPresent()) {
			return ResponseEntity.notFound().build();
		} 
		BeanUtils.copyProperties(OrdenDetalle, orden.get());
		orden.get().setId(id);
		return ResponseEntity.status(HttpStatus.CREATED).body(ordenService.guardarOrden(orden.get()));
	}
	
	@PatchMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> updatePartial(@RequestBody JsonPatch patch, @PathVariable Long id){
		try {
			Optional<OrdenMedica> citas = ordenService.consultarOrdenId(id);
			if(!citas.isPresent()) {
				return ResponseEntity.notFound().build();
			}
			
			ObjectMapper objectMapper = new ObjectMapper();
			JsonNode patched = patch.apply(objectMapper.convertValue(citas.get(), JsonNode.class));
			OrdenMedica ordenPatched = objectMapper.treeToValue(patched, OrdenMedica.class);
			
			return ResponseEntity.status(HttpStatus.CREATED).body(ordenService.guardarOrden(ordenPatched));
	    } catch (JsonPatchException | JsonProcessingException e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	    } 
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id){
		Optional<OrdenMedica> orden = ordenService.consultarOrdenId(id);
		if(!orden.isPresent()) {
			return ResponseEntity.notFound().build();
		} 
		ordenService.eliminarOrden(id);
		return ResponseEntity.ok().build();
	}
	
	@GetMapping
	public List<OrdenMedica> readAll(){
		return ordenService.consultarOrdenes();
	}
}
