package pe.gob.osinergmin.sio.service.impl;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.gob.osinergmin.sio.entity.Comunicacion;
import pe.gob.osinergmin.sio.entity.FotosComunicacion;
import pe.gob.osinergmin.sio.entity.FotosTarea;
import pe.gob.osinergmin.sio.enums.ErrorCode;
import pe.gob.osinergmin.sio.enums.InvocationResult;
import pe.gob.osinergmin.sio.persistence.dao.ComunicacionRepository;
import pe.gob.osinergmin.sio.persistence.dao.FotosComunicacionRepository;
import pe.gob.osinergmin.sio.ro.in.FotosComunicacionInRO;
import pe.gob.osinergmin.sio.ro.in.FotosTareaInRO;
import pe.gob.osinergmin.sio.ro.in.RegistroComunicacionInRO;
import pe.gob.osinergmin.sio.ro.out.ComunicacionOutRO;
import pe.gob.osinergmin.sio.ro.out.FotosComunicacionOutRO;
import pe.gob.osinergmin.sio.ro.out.FotosTareaOutRO;
import pe.gob.osinergmin.sio.ro.out.RegistroComunicacionOutRO;
import pe.gob.osinergmin.sio.service.ComunicacionService;

@Service
public class ComunicacionServiceImpl implements ComunicacionService{
	
	@Autowired
	ComunicacionRepository comunicacionRepository;

	@Autowired
	FotosComunicacionRepository fotosComunicacionRepository;
	
	@Override
	public RegistroComunicacionOutRO registrarComunicacion(RegistroComunicacionInRO registroComunicacionInRO) {
		RegistroComunicacionOutRO registroComunicacionOutRO = new RegistroComunicacionOutRO();
		try {			
			
			Comunicacion comunicacionCreado = comunicacionRepository.registrarComunicacion(registroComunicacionInRO);
			
			if(comunicacionCreado != null && comunicacionCreado.getIdComunicacion() != null) {
				
				List<FotosComunicacionInRO> fotos = registroComunicacionInRO.getFotos();
				for (FotosComunicacionInRO foto : fotos) {
					FotosComunicacion fotoComunicacion = new FotosComunicacion();
					fotoComunicacion.setIdComunicacion(comunicacionCreado.getIdComunicacion());
					fotoComunicacion.setFoto(foto.getFoto() != null && foto.getFoto().length() > 0
							? Base64.getDecoder().decode(foto.getFoto())
							: null);
					fotoComunicacion = fotosComunicacionRepository.registrar(fotoComunicacion);
				}
				
				registroComunicacionOutRO.setIdComunicacion(comunicacionCreado.getIdComunicacion());
				registroComunicacionOutRO.setResultCode(InvocationResult.SUCCESS.getCode());
				registroComunicacionOutRO.setMessage("Se ha registrado correctamente");
			}else {
				registroComunicacionOutRO.setResultCode(InvocationResult.FAILED.getCode());
				registroComunicacionOutRO.setErrorCode(ErrorCode.ERROR.getErrorCode());
				registroComunicacionOutRO.setMessage("Error al registrar comunicacion");
			}
			
		}catch(Exception e) {
			registroComunicacionOutRO.setResultCode(InvocationResult.FAILED.getCode());
			registroComunicacionOutRO.setErrorCode(ErrorCode.ERROR.getErrorCode());
			registroComunicacionOutRO.setMessage("Error al registrar comunicacion");
		}
		return registroComunicacionOutRO;
	}

	@Override
	public RegistroComunicacionOutRO actualizarComunicacion(RegistroComunicacionInRO registroComunicacionInRO) {
		RegistroComunicacionOutRO registroComunicacionOutRO = new RegistroComunicacionOutRO();
		try {
			
			Comunicacion comunicacionActualizada = comunicacionRepository.actualizarComunicacion(registroComunicacionInRO);
			
			if(comunicacionActualizada == null) {			
				registroComunicacionOutRO.setResultCode(InvocationResult.FAILED.getCode());
				registroComunicacionOutRO.setErrorCode(ErrorCode.NO_FOUND_ENTITY.getErrorCode());
			}else {
				
				List<FotosComunicacionInRO> fotos = registroComunicacionInRO.getFotos();
				for (FotosComunicacionInRO foto : fotos) {
					if(foto.getAccion().equals("N")) {
						FotosComunicacion fotosComunicacion = new FotosComunicacion();
						fotosComunicacion.setIdComunicacion(comunicacionActualizada.getIdComunicacion());
						fotosComunicacion.setFoto(foto.getFoto() != null && foto.getFoto().length() > 0
								? Base64.getDecoder().decode(foto.getFoto())
								: null);
						fotosComunicacionRepository.registrar(fotosComunicacion);
					} else if(foto.getAccion().equals("U")) {
						FotosComunicacion fotosComunicacion = new FotosComunicacion();
						fotosComunicacion.setIdFoto(foto.getId());
						fotosComunicacion.setIdComunicacion(comunicacionActualizada.getIdComunicacion());
						fotosComunicacion.setFoto(foto.getFoto() != null && foto.getFoto().length() > 0
								? Base64.getDecoder().decode(foto.getFoto())
								: null);
						fotosComunicacionRepository.actualizar(fotosComunicacion);
					} else if(foto.getAccion().equals("D")) {
						fotosComunicacionRepository.eliminar(foto.getId());
					}
				}
				
				registroComunicacionOutRO.setResultCode(InvocationResult.SUCCESS.getCode());
				registroComunicacionOutRO.setMessage("Se ha actualizado correctamente");
			}
		}catch(Exception e) {
			registroComunicacionOutRO.setResultCode(InvocationResult.FAILED.getCode());
			registroComunicacionOutRO.setErrorCode(ErrorCode.ERROR.getErrorCode());
			registroComunicacionOutRO.setMessage("Error al actualizar comunicacion");
		}
		return registroComunicacionOutRO;
	}

	@Override
	public ComunicacionOutRO obtenerComunicacionPorIdIncidente(Integer idIncidente) {
		ComunicacionOutRO comunicacionOutRO = new ComunicacionOutRO();
		try {
			Comunicacion comunicacion = comunicacionRepository.obtenerComunicacionPorIdIncidente(idIncidente);
			
			if(comunicacion == null) {
				comunicacionOutRO.setResultCode(InvocationResult.FAILED.getCode());
				comunicacionOutRO.setErrorCode(ErrorCode.NO_FOUND_ENTITY.getErrorCode());
				return comunicacionOutRO;
			}
			comunicacionOutRO.setIdComunicacion(comunicacion.getIdComunicacion());
			comunicacionOutRO.setIdIncidente(comunicacion.getIdIncidente());
			comunicacionOutRO.setDescripcion(comunicacion.getDescripcion());
			comunicacionOutRO.setFamiliasAfectadas(comunicacion.getFamiliasAfectadas());
			comunicacionOutRO.setPersonasAfectadas(comunicacion.getPersonasAfectadas());
			comunicacionOutRO.setViviendasAfectadas(comunicacion.getViviendasAfectadas());
			
			List<FotosComunicacionOutRO> listaOutRO = new ArrayList<>();
			List<FotosComunicacion> fotos = fotosComunicacionRepository.listarFotosPorComunicacion(comunicacion.getIdComunicacion());
			for(FotosComunicacion foto : fotos) {
				FotosComunicacionOutRO fotosComunicacionOutRO = new FotosComunicacionOutRO
															(foto.getIdFoto(),foto.getIdComunicacion(), Base64.getEncoder().encodeToString(foto.getFoto()));
				listaOutRO.add(fotosComunicacionOutRO);
			}
			comunicacionOutRO.setFotos(listaOutRO);
			
			comunicacionOutRO.setResultCode(InvocationResult.SUCCESS.getCode());
		} catch (Exception e) {
			comunicacionOutRO.setResultCode(InvocationResult.FAILED.getCode());
			comunicacionOutRO.setErrorCode(ErrorCode.ERROR.getErrorCode());
			comunicacionOutRO.setMessage("Error al obtener comunicacion por incidente");
		}
		return comunicacionOutRO;
	}

}
