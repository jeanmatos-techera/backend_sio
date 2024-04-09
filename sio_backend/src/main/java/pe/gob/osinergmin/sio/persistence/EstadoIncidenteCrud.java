package pe.gob.osinergmin.sio.persistence;

import org.springframework.data.repository.CrudRepository;

import pe.gob.osinergmin.sio.entity.EstadoIncidente;

public interface EstadoIncidenteCrud extends CrudRepository<EstadoIncidente, Integer> {
	
}
