package pe.gob.osinergmin.sio.persistence.dao;

import java.util.List;

import pe.gob.osinergmin.sio.entity.EstadoIncidente;

public interface EstadoIncidenteRepository {

	public List<EstadoIncidente> listarEstados();
	
}
