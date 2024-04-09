package pe.gob.osinergmin.sio.persistence.dao;

import java.util.List;

import pe.gob.osinergmin.sio.entity.IncidenteAccion;

public interface IncidenteAccionRepository {

	public IncidenteAccion guardar(IncidenteAccion incidenteAccion);
	
	public List<IncidenteAccion> obtenerAccionesPorIdIncidente(Integer idIncidente); 
}
