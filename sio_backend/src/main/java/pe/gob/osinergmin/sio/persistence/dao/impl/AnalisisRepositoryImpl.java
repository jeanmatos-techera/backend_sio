package pe.gob.osinergmin.sio.persistence.dao.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import pe.gob.osinergmin.sio.entity.Analisis;
import pe.gob.osinergmin.sio.persistence.AnalisisCrud;
import pe.gob.osinergmin.sio.persistence.dao.AnalisisRepository;
import pe.gob.osinergmin.sio.ro.in.RegistroAnalisisInRO;

@Repository
@Transactional(rollbackFor = Exception.class)
public class AnalisisRepositoryImpl implements AnalisisRepository{

	@Autowired
	AnalisisCrud analisisCrud;
	
	@Override
	public Analisis registrarAnalisis(RegistroAnalisisInRO registroAnalisisInRO) {
		Analisis analisis = new Analisis();
		try {			
			analisis.setIdIncidente(registroAnalisisInRO.getIdIncidente());
			analisis.setDescripcion(registroAnalisisInRO.getDescripcion());
			
			analisis.setUsuarioCreacion("OSINERG");
			analisis.setFechaCreacion(new Date());
			analisis.setTerminalCreacion("Localhost");
			
			analisis = analisisCrud.save(analisis);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return analisis;
	}
	
	@Override
	public Analisis actualizarAnalisis(RegistroAnalisisInRO registroAnalisisInRO) {
		Analisis analisis = null;
		try {
			analisis = this.obtenerAnalisisPorId(registroAnalisisInRO.getIdAnalisis());
			if(analisis != null) {
				analisis.setIdIncidente(registroAnalisisInRO.getIdIncidente());
				analisis.setDescripcion(registroAnalisisInRO.getDescripcion());
				
				analisis.setUsuarioActualizacion("OSINERG");
				analisis.setFechaActualizacion(new Date());
				analisis.setTerminalActualizacion("Localhost");
				
				analisis = analisisCrud.save(analisis);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return analisis;
	}
	
	@Override
	public Analisis obtenerAnalisisPorIdIncidente(Integer idIncidente) {
		Analisis analisis = new Analisis();
		try {
			analisis = analisisCrud.listarPorIncidente(idIncidente).get(0);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return analisis;
	}

	@Override
	public Analisis obtenerAnalisisPorId(Integer idAnalisis) {
		Analisis analisis = null;
		try {
			analisis = analisisCrud.findById(idAnalisis).orElse(null);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return analisis;
	}
	
}
