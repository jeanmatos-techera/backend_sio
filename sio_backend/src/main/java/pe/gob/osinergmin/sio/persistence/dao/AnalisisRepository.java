package pe.gob.osinergmin.sio.persistence.dao;


import pe.gob.osinergmin.sio.entity.Analisis;
import pe.gob.osinergmin.sio.ro.in.RegistroAnalisisInRO;

public interface AnalisisRepository {
	
	public Analisis registrarAnalisis(RegistroAnalisisInRO analisis);
	
	public Analisis actualizarAnalisis(RegistroAnalisisInRO analisis);	
	
	public Analisis obtenerAnalisisPorIdIncidente(Integer idIncidente);
	
	public Analisis obtenerAnalisisPorId(Integer idAnalisis);
}
