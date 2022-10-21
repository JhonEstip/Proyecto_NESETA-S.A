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

import com.company.netesa.model.TipoDocumento;
import com.company.netesa.service.TipoDocumentoService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;


@RestController
@RequestMapping("/api/tipodocumento")
public class TipoDocumentoController {
	
	@Autowired
	private TipoDocumentoService tipodocumentoService;
	
	@PostMapping
	public ResponseEntity<?> create (@RequestBody TipoDocumento tipodocumento){
		return ResponseEntity.status(HttpStatus.CREATED).body(tipodocumentoService.guardarTipoDocumento(tipodocumento));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> read(@PathVariable Long id){
		Optional<TipoDocumento> tipodocumento = tipodocumentoService.consultarTipoDocumentoId(id);
		if(!tipodocumento.isPresent()) {
			return ResponseEntity.notFound().build();
		} 
		return ResponseEntity.ok(tipodocumento);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id){
		Optional<TipoDocumento> tipodocumento = tipodocumentoService.consultarTipoDocumentoId(id);
		if(!tipodocumento.isPresent()) {
			return ResponseEntity.notFound().build();
		} 
		tipodocumentoService.eliminarTipoDocumento(id);
		return ResponseEntity.ok().build();
	}
	
	@GetMapping
	public List<TipoDocumento> readAll(){
		return tipodocumentoService.consultarTipoDocumento();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@RequestBody TipoDocumento TipoDocumentoDetalle, @PathVariable Long id){
		Optional<TipoDocumento> tipodocumento = tipodocumentoService.consultarTipoDocumentoId(id);
		if(!tipodocumento.isPresent()) {
			return ResponseEntity.notFound().build();
		} 
		BeanUtils.copyProperties(TipoDocumentoDetalle, tipodocumento.get());
		tipodocumento.get().setId_tipo_documento(id);
		return ResponseEntity.status(HttpStatus.CREATED).body(tipodocumentoService.guardarTipoDocumento(tipodocumento.get()));
	}
	
	@PatchMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> updatePartial(@RequestBody JsonPatch patch, @PathVariable Long id){
		try {
			Optional<TipoDocumento> tipodocumento = tipodocumentoService.consultarTipoDocumentoId(id);
			if(!tipodocumento.isPresent()) {
				return ResponseEntity.notFound().build();
			}
			
			ObjectMapper objectMapper = new ObjectMapper();
			JsonNode patched = patch.apply(objectMapper.convertValue(tipodocumento.get(), JsonNode.class));
			TipoDocumento tipodocumentoPatched = objectMapper.treeToValue(patched, TipoDocumento.class);
			
			return ResponseEntity.status(HttpStatus.CREATED).body(tipodocumentoService.guardarTipoDocumento(tipodocumentoPatched));
	    } catch (JsonPatchException | JsonProcessingException e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	    } 
	}

}
