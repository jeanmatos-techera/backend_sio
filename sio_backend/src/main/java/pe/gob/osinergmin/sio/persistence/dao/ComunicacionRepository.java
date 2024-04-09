package pe.gob.osinergmin.sio.persistence.dao;

import pe.gob.osinergmin.sio.entity.Comunicacion;
import pe.gob.osinergmin.sio.ro.in.RegistroComunicacionInRO;

public interface ComunicacionRepository {

	public Comunicacion registrarComunicacion(RegistroComunicacionInRO comunicacion);
	
	public Comunicacion actualizarComunicacion(RegistroComunicacionInRO comunicacion);
	
	public Comunicacion obtenerComunicacionPorIdIncidente(Integer idIncidente);
	
	public Comunicacion obtenerComunicacionPorId(Integer idComunicacion);
}
