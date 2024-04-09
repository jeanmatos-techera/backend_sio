package pe.gob.osinergmin.sio.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.gob.osinergmin.sio.entity.Analisis;
import pe.gob.osinergmin.sio.enums.ErrorCode;
import pe.gob.osinergmin.sio.enums.InvocationResult;
import pe.gob.osinergmin.sio.persistence.dao.AnalisisRepository;
import pe.gob.osinergmin.sio.ro.in.RegistroAnalisisInRO;
import pe.gob.osinergmin.sio.ro.out.AnalisisOutRO;
import pe.gob.osinergmin.sio.ro.out.RegistroAnalisisOutRO;
import pe.gob.osinergmin.sio.service.AnalisisService;

@Service
public class AnalisisServiceImpl implements AnalisisService{
	
	@Autowired
	AnalisisRepository analisisRepository;

	@Override
	public RegistroAnalisisOutRO registrarAnalisis(RegistroAnalisisInRO registroAnalisisInRO) {
		RegistroAnalisisOutRO registroAnalisisOutRO = new RegistroAnalisisOutRO();
		try {			
			
			Analisis analisisCreado = analisisRepository.registrarAnalisis(registroAnalisisInRO);
			
			if(analisisCreado != null && analisisCreado.getIdAnalisis() != null) {
				registroAnalisisOutRO.setIdAnalisis(analisisCreado.getIdAnalisis());
				registroAnalisisOutRO.setResultCode(InvocationResult.SUCCESS.getCode());
				registroAnalisisOutRO.setMessage("Se ha registrado analisis");
			}else {
				registroAnalisisOutRO.setResultCode(InvocationResult.FAILED.getCode());
				registroAnalisisOutRO.setErrorCode(ErrorCode.ERROR.getErrorCode());
				registroAnalisisOutRO.setMessage("Error al registrar analisis");
			}
			
		}catch(Exception e) {
			registroAnalisisOutRO.setResultCode(InvocationResult.FAILED.getCode());
			registroAnalisisOutRO.setErrorCode(ErrorCode.ERROR.getErrorCode());
			registroAnalisisOutRO.setMessage("Error al registrar analisis");
		}
		return registroAnalisisOutRO;
	}

	@Override
	public RegistroAnalisisOutRO actualizarAnalisis(RegistroAnalisisInRO registroAnalisisInRO) {
		RegistroAnalisisOutRO registroAnalisisOutRO = new RegistroAnalisisOutRO();
		try {
			
			Analisis analisis = analisisRepository.actualizarAnalisis(registroAnalisisInRO);
			
			if(analisis == null) {
				registroAnalisisOutRO.setResultCode(InvocationResult.FAILED.getCode());
				registroAnalisisOutRO.setErrorCode(ErrorCode.NO_FOUND_ENTITY.getErrorCode());
			}else {
				registroAnalisisOutRO.setResultCode(InvocationResult.SUCCESS.getCode());
				registroAnalisisOutRO.setMessage("Se ha actualizado correctamente");
			}
		}catch(Exception e) {
			registroAnalisisOutRO.setResultCode(InvocationResult.FAILED.getCode());
			registroAnalisisOutRO.setErrorCode(ErrorCode.ERROR.getErrorCode());
			registroAnalisisOutRO.setMessage("Error al actualizar analisis");
		}
		return registroAnalisisOutRO;
	}

	@Override
	public AnalisisOutRO obtenerAnalisisPorIdIncidente(Integer idIncidente) {
		AnalisisOutRO analisisOutRO = new AnalisisOutRO();
		try {
			Analisis analisis = analisisRepository.obtenerAnalisisPorIdIncidente(idIncidente);
			
			analisisOutRO.setIdAnalisis(analisis.getIdAnalisis());
			analisisOutRO.setIdIncidente(analisis.getIdIncidente());
			analisisOutRO.setDescripcion(analisis.getDescripcion());
			
			analisisOutRO.setResultCode(InvocationResult.SUCCESS.getCode());
		} catch (Exception e) {
			analisisOutRO.setResultCode(InvocationResult.FAILED.getCode());
			analisisOutRO.setErrorCode(ErrorCode.ERROR.getErrorCode());
			analisisOutRO.setMessage("Error al obtener analisis por incidente");
		}
		return analisisOutRO;
	}	

}
