package pe.gob.osinergmin.sio.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.gob.osinergmin.sio.entity.FuenteInformacion;
import pe.gob.osinergmin.sio.enums.ErrorCode;
import pe.gob.osinergmin.sio.enums.InvocationResult;
import pe.gob.osinergmin.sio.persistence.dao.FuenteInformacionRepository;
import pe.gob.osinergmin.sio.ro.out.ListFuenteInformacionOutRO;
import pe.gob.osinergmin.sio.ro.out.FuenteInformacionOutRO;
import pe.gob.osinergmin.sio.service.FuenteInformacionService;

@Service
public class FuenteInformacionServiceImpl implements FuenteInformacionService {

	@Autowired
	FuenteInformacionRepository fuenteInformacionRepository;
	
	@Override
	public ListFuenteInformacionOutRO listarFuentesInformacion() {
		ListFuenteInformacionOutRO listFuenteInformacionOutRO = new ListFuenteInformacionOutRO();
		List<FuenteInformacionOutRO> listOutRO = new ArrayList<>();
		try {
			List<FuenteInformacion> list = fuenteInformacionRepository.listarFuentesInformacion();
			for	(FuenteInformacion fuenteInformacion: list) {
				FuenteInformacionOutRO fuenteInformacionOutRO = new FuenteInformacionOutRO();
				fuenteInformacionOutRO.setIdFuente(fuenteInformacion.getIdFuente());
				fuenteInformacionOutRO.setNombre(fuenteInformacion.getNombre());
				listOutRO.add(fuenteInformacionOutRO);
			}
			listFuenteInformacionOutRO.setResultCode(InvocationResult.SUCCESS.getCode());
		}catch(Exception e) {
			listFuenteInformacionOutRO.setResultCode(InvocationResult.FAILED.getCode());
			listFuenteInformacionOutRO.setErrorCode(ErrorCode.ERROR.getErrorCode());
			listFuenteInformacionOutRO.setMessage("Error al listar las fuentes de información");
		}
		listFuenteInformacionOutRO.setFuentesInformacion(listOutRO);
		return listFuenteInformacionOutRO;
	}
	
}
