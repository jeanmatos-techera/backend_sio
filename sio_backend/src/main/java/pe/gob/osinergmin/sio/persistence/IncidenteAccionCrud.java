package pe.gob.osinergmin.sio.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import pe.gob.osinergmin.sio.entity.IncidenteAccion;

public interface IncidenteAccionCrud extends CrudRepository<IncidenteAccion, Integer> {

	@Query("SELECT ia FROM IncidenteAccion ia WHERE ia.idIncidente = ?1")
	public List<IncidenteAccion> obtenerAccionesPorIdIncidente(Integer idIncidente);
	
}
