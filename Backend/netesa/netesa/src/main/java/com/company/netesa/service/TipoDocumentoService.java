package com.company.netesa.service;

import java.util.List;
import java.util.Optional;

import com.company.netesa.model.Citas;
import com.company.netesa.model.TipoDocumento;

public interface TipoDocumentoService {

	public List<TipoDocumento> consultarTipoDocumento();
	public Optional<TipoDocumento> consultarTipoDocumentoId(Long id);
	public TipoDocumento guardarTipoDocumento(TipoDocumento tipodocumento);
	void eliminarTipoDocumento(Long id);

}
