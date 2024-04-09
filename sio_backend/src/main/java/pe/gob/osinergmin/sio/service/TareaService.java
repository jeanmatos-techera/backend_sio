package pe.gob.osinergmin.sio.service;

import org.springframework.stereotype.Service;

import pe.gob.osinergmin.sio.ro.in.TareaInRO;
import pe.gob.osinergmin.sio.ro.out.DetalleTareaOutRO;
import pe.gob.osinergmin.sio.ro.out.ListTareaOutRO;
import pe.gob.osinergmin.sio.ro.out.RegistroTareaOutRO;

@Service
public interface TareaService {
	
	public RegistroTareaOutRO registrarTarea(TareaInRO tareaInRO);
	
	public RegistroTareaOutRO actualizarTarea(TareaInRO tareaInRO);
	
	public ListTareaOutRO listarPorIncidente(Integer idIncidente);
	
	public DetalleTareaOutRO detalleTarea(Integer idTarea);
}

