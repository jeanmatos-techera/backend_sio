package pe.gob.osinergmin.sio.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.gob.osinergmin.sio.entity.TipoDocumento;
import pe.gob.osinergmin.sio.enums.ErrorCode;
import pe.gob.osinergmin.sio.enums.InvocationResult;
import pe.gob.osinergmin.sio.persistence.dao.TipoDocumentoRepository;
import pe.gob.osinergmin.sio.ro.out.ListTipoDocumentoOutRO;
import pe.gob.osinergmin.sio.ro.out.TipoDocumentoOutRO;
import pe.gob.osinergmin.sio.service.TipoDocumentoService;

@Service
public class TipoDocumentoServiceImpl implements TipoDocumentoService {

	@Autowired
	TipoDocumentoRepository tipoDocumentoRepository;
	
	@Override
	public ListTipoDocumentoOutRO listarTiposDocumentos() {
		ListTipoDocumentoOutRO listTipoDocumentoOutRO = new ListTipoDocumentoOutRO();
		List<TipoDocumentoOutRO> listOutRO = new ArrayList<>();
		try {
			List<TipoDocumento> list = tipoDocumentoRepository.listarTiposDocumentos();
			for	(TipoDocumento tipoDocumento: list) {
				TipoDocumentoOutRO tipoDocumentoOutRO = new TipoDocumentoOutRO();
				tipoDocumentoOutRO.setIdTipoDocumento(tipoDocumento.getIdTipoDocumento());
				tipoDocumentoOutRO.setNombre(tipoDocumento.getNombre());
				listOutRO.add(tipoDocumentoOutRO);
			}
			listTipoDocumentoOutRO.setResultCode(InvocationResult.SUCCESS.getCode());
		}catch(Exception e) {
			listTipoDocumentoOutRO.setResultCode(InvocationResult.FAILED.getCode());
			listTipoDocumentoOutRO.setErrorCode(ErrorCode.ERROR.getErrorCode());
			listTipoDocumentoOutRO.setMessage("Error al listar tipos de documentos");
		}
		listTipoDocumentoOutRO.setTiposDocumento(listOutRO);
		return listTipoDocumentoOutRO;
	}
	
}
