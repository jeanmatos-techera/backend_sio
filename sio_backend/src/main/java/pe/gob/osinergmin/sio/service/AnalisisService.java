package pe.gob.osinergmin.sio.service;

import org.springframework.stereotype.Service;

import pe.gob.osinergmin.sio.ro.in.RegistroAnalisisInRO;
import pe.gob.osinergmin.sio.ro.out.AnalisisOutRO;
import pe.gob.osinergmin.sio.ro.out.RegistroAnalisisOutRO;

@Service
public interface AnalisisService {
	
	public RegistroAnalisisOutRO registrarAnalisis(RegistroAnalisisInRO registroAnalisisInRO);
	
	public RegistroAnalisisOutRO actualizarAnalisis(RegistroAnalisisInRO registroAnalisisInRO);
	
	public AnalisisOutRO obtenerAnalisisPorIdIncidente(Integer idIncidente);
}

