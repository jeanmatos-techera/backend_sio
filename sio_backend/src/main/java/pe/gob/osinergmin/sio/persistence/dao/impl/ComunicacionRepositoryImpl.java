package pe.gob.osinergmin.sio.persistence.dao.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import pe.gob.osinergmin.sio.entity.Comunicacion;
import pe.gob.osinergmin.sio.persistence.ComunicacionCrud;
import pe.gob.osinergmin.sio.persistence.dao.ComunicacionRepository;
import pe.gob.osinergmin.sio.ro.in.RegistroComunicacionInRO;

@Repository
@Transactional(rollbackFor = Exception.class)
public class ComunicacionRepositoryImpl implements ComunicacionRepository{

	@Autowired
	ComunicacionCrud comunicacionCrud;
	
	@Override
	public Comunicacion registrarComunicacion(RegistroComunicacionInRO registroComunicacionInRO) {
		Comunicacion comunicacion = new Comunicacion();
		try {			
			comunicacion.setIdIncidente(registroComunicacionInRO.getIdIncidente());
			comunicacion.setDescripcion(registroComunicacionInRO.getDescripcion());
			comunicacion.setFamiliasAfectadas(registroComunicacionInRO.getFamiliasAfectadas());
			comunicacion.setPersonasAfectadas(registroComunicacionInRO.getPersonasAfectadas());
			comunicacion.setViviendasAfectadas(registroComunicacionInRO.getViviendasAfectadas());
			
			comunicacion.setUsuarioCreacion("OSINERG");
			comunicacion.setFechaCreacion(new Date());
			comunicacion.setTerminalCreacion("Localhost");
			
			comunicacion = comunicacionCrud.save(comunicacion);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return comunicacion;
	}
	
	@Override
	public Comunicacion actualizarComunicacion(RegistroComunicacionInRO registroComunicacionInRO) {
		Comunicacion comunicacion = null;
		try {
			
			comunicacion = this.obtenerComunicacionPorId(registroComunicacionInRO.getIdComunicacion());
			if(comunicacion != null) {
				comunicacion.setIdIncidente(registroComunicacionInRO.getIdIncidente());
				comunicacion.setDescripcion(registroComunicacionInRO.getDescripcion());
				comunicacion.setFamiliasAfectadas(registroComunicacionInRO.getFamiliasAfectadas());
				comunicacion.setPersonasAfectadas(registroComunicacionInRO.getPersonasAfectadas());
				comunicacion.setViviendasAfectadas(registroComunicacionInRO.getViviendasAfectadas());
				
				comunicacion.setUsuarioActualizacion("OSINERG");
				comunicacion.setFechaActualizacion(new Date());
				comunicacion.setTerminalActualizacion("Localhost");
				
				comunicacion = comunicacionCrud.save(comunicacion);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return comunicacion;
	}

	@Override
	public Comunicacion obtenerComunicacionPorIdIncidente(Integer idIncidente) {
		Comunicacion comunicacion = new Comunicacion();
		try {
			List<Comunicacion> list = comunicacionCrud.obtenerComunicacionPorIdIncidente(idIncidente);
			if(list == null || list.size() == 0) {
				comunicacion = null;
			}else {
				comunicacion = comunicacionCrud.obtenerComunicacionPorIdIncidente(idIncidente).get(0);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return comunicacion;
	}

	@Override
	public Comunicacion obtenerComunicacionPorId(Integer idComunicacion) {
		Comunicacion comunicacion = null;
		try {
			comunicacion = comunicacionCrud.findById(idComunicacion).orElse(null);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return comunicacion;
	}

}
