package pe.gob.osinergmin.sio.persistence.dao;

import java.util.List;

import pe.gob.osinergmin.sio.entity.FotosComunicacion;
import pe.gob.osinergmin.sio.entity.FotosTarea;

public interface FotosComunicacionRepository {
	
	public FotosComunicacion registrar(FotosComunicacion fotosComunicacion);
	
	public FotosComunicacion actualizar(FotosComunicacion fotosComunicacion);
	
	public void eliminar(Integer idFoto);
	
	public List<FotosComunicacion> listarFotosPorComunicacion(Integer idComunicacion);
	
	public FotosComunicacion obtenerFotosComunicacionPorId(Integer idFotosComunicacion);

}
