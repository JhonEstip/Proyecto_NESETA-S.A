package com.company.netesa.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.netesa.model.Citas;
import com.company.netesa.model.TipoDocumento;
import com.company.netesa.model.User;
import com.company.netesa.repository.CitasRepository;
import com.company.netesa.repository.TipoDocumentoRepository;
import com.company.netesa.repository.UserRepository;

@Service
public class TipoDocumentoServiceImpl implements TipoDocumentoService{
	@Autowired
	private TipoDocumentoRepository repositorioTipoDocumento;
	
	@Override
	@Transactional
	public List<TipoDocumento> consultarTipoDocumento(){
		return repositorioTipoDocumento.findAll();
	}
	
	@Override
	@Transactional
	public Optional<TipoDocumento> consultarTipoDocumentoId(Long id) {
		return repositorioTipoDocumento.findById(id);
	}
	
	@Override
	@Transactional
	public TipoDocumento guardarTipoDocumento(TipoDocumento tipodocumento) {
		return repositorioTipoDocumento.save(tipodocumento);
	}
	
	@Override
	@Transactional
	public void eliminarTipoDocumento(Long id) {
		repositorioTipoDocumento.deleteById(id);
	}

}
