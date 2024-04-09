package pe.gob.osinergmin.sio.persistence.dao;

import java.util.List;

import pe.gob.osinergmin.sio.entity.FotosTarea;

public interface FotosTareaRepository {
	
	public FotosTarea registrar(FotosTarea fotosTarea);
	
	public FotosTarea actualizar(FotosTarea fotosTarea);
	
	public void eliminar(Integer idFoto);
	
	public List<FotosTarea> listarPorTarea(Integer idTarea);
	
	public FotosTarea obtenerFotosTareaPorId(Integer idFotosTarea);
}
