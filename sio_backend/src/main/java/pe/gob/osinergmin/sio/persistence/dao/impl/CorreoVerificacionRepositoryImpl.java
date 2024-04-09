package pe.gob.osinergmin.sio.persistence.dao.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import pe.gob.osinergmin.sio.entity.CorreoVerificacion;
import pe.gob.osinergmin.sio.persistence.CorreoVerificacionCrud;
import pe.gob.osinergmin.sio.persistence.dao.CorreoVerificacionRepository;
import pe.gob.osinergmin.sio.ro.in.CorreoVerificacionInRO;

@Repository
@Transactional(rollbackFor = Exception.class)
public class CorreoVerificacionRepositoryImpl implements CorreoVerificacionRepository {

	@Autowired
	CorreoVerificacionCrud correoVerificacionCrud;
	
	@Override
	public CorreoVerificacion registro(CorreoVerificacionInRO correoVerificacionInRO) {
		CorreoVerificacion correoVerificacion = new CorreoVerificacion();
		try {
			correoVerificacion.setIdVerificacion(correoVerificacionInRO.getIdVerificacion());
			correoVerificacion.setCorreo(correoVerificacionInRO.getCorreo());
			correoVerificacion.setCodigo(correoVerificacionInRO.getCodigo());
			correoVerificacion.setFechaRegistro(correoVerificacionInRO.getFechaRegistro());
			correoVerificacion.setFechaExpiracion(correoVerificacionInRO.getFechaExpiracion());
			
			correoVerificacion.setUsuarioCreacion("OSINERG");
			correoVerificacion.setFechaCreacion(new Date());
			correoVerificacion.setTerminalCreacion("Localhost");
			
			correoVerificacion = correoVerificacionCrud.save(correoVerificacion);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return correoVerificacion;
	}

	@Override
	public CorreoVerificacion obtenerCorreoVerificacion(String correo) {
		CorreoVerificacion correoVerificacion = new CorreoVerificacion();
		try {
			correoVerificacion = correoVerificacionCrud.obtenerCorreoVerificacion(correo);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return correoVerificacion;
	}

}
