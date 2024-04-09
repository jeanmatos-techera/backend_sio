package pe.gob.osinergmin.sio.persistence.dao;

import java.util.List;

import pe.gob.osinergmin.sio.entity.OficinaRegional;

public interface OficinaRegionalRepository {

	public List<OficinaRegional> listarOficinasRegionales();
	
	public OficinaRegional obtenerOficinaPorNombre(String nombre);
}
