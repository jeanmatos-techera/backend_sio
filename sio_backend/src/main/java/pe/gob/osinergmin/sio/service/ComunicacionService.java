package pe.gob.osinergmin.sio.service;

import org.springframework.stereotype.Service;

import pe.gob.osinergmin.sio.ro.in.RegistroComunicacionInRO;
import pe.gob.osinergmin.sio.ro.out.ComunicacionOutRO;
import pe.gob.osinergmin.sio.ro.out.RegistroComunicacionOutRO;

@Service
public interface ComunicacionService {
	
	public RegistroComunicacionOutRO registrarComunicacion(RegistroComunicacionInRO registroComunicacionInRO);
	
	public RegistroComunicacionOutRO actualizarComunicacion(RegistroComunicacionInRO registroComunicacionInRO);
	
	public ComunicacionOutRO obtenerComunicacionPorIdIncidente(Integer idIncidente);
}

