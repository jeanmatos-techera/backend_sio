package pe.gob.osinergmin.sio.persistence.dao;

import pe.gob.osinergmin.sio.entity.CorreoVerificacion;
import pe.gob.osinergmin.sio.ro.in.CorreoVerificacionInRO;

public interface CorreoVerificacionRepository {

	public CorreoVerificacion registro(CorreoVerificacionInRO correoVerificacionInRO);
	
	public CorreoVerificacion obtenerCorreoVerificacion(String correo);
	
}
