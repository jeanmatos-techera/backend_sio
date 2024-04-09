package pe.gob.osinergmin.sio.persistence.dao;

import java.util.List;

import pe.gob.osinergmin.sio.entity.Tarea;
import pe.gob.osinergmin.sio.ro.in.RegistroTareaInRO;

public interface TareaRepository {
	
	public Tarea registrarTarea(RegistroTareaInRO tarea);
	
	public Tarea actualizarTarea(RegistroTareaInRO tarea);	
	
	public List<Tarea> listarPorIncidente(Integer idIncidente);
	
	public Tarea obtenerTarea(Integer idTarea);
}
