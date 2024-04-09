package pe.gob.osinergmin.sio.persistence.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import pe.gob.osinergmin.sio.entity.Tarea;
import pe.gob.osinergmin.sio.persistence.TareaCrud;
import pe.gob.osinergmin.sio.persistence.dao.TareaRepository;
import pe.gob.osinergmin.sio.ro.in.RegistroTareaInRO;

@Repository
@Transactional(rollbackFor = Exception.class)
public class TareaRepositoryImpl implements TareaRepository{

	@Autowired
	TareaCrud tareaCrud;
	
	@Override
	public Tarea registrarTarea(RegistroTareaInRO registroTareaInRO) {
		Tarea tarea = new Tarea();
		try {			
			tarea.setIdIncidente(registroTareaInRO.getIdIncidente());
			tarea.setNombre(registroTareaInRO.getNombre());
			tarea.setDescripcion(registroTareaInRO.getDescripcion());
			
			tarea.setUsuarioCreacion("OSINERG");
			tarea.setFechaCreacion(new Date());
			tarea.setTerminalCreacion("Localhost");
			
			tarea = tareaCrud.save(tarea);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return tarea;
	}
	
	@Override
	public Tarea actualizarTarea(RegistroTareaInRO registroTareaInRO) {
		Tarea tarea = null;
		try {
			tarea = this.obtenerTarea(registroTareaInRO.getIdTarea());
			if(tarea != null) {
				tarea.setIdIncidente(registroTareaInRO.getIdIncidente());
				tarea.setNombre(registroTareaInRO.getNombre());
				tarea.setDescripcion(registroTareaInRO.getDescripcion());
				
				tarea.setUsuarioActualizacion("OSINERG");
				tarea.setFechaActualizacion(new Date());
				tarea.setTerminalActualizacion("Localhost");
				
				tarea = tareaCrud.save(tarea);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return tarea;
	}
	
	@Override
	public List<Tarea> listarPorIncidente(Integer idIncidente) {
		List<Tarea> lista = new ArrayList<>();
		try {
			lista = tareaCrud.listarPorIncidente(idIncidente);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}

	@Override
	public Tarea obtenerTarea(Integer idTarea) {
		Tarea tarea = null;
		try {
			tarea = tareaCrud.findById(idTarea).orElse(null);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return tarea;
	}
	
}
