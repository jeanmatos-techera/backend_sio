package pe.gob.osinergmin.sio.persistence;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import pe.gob.osinergmin.sio.entity.OficinaRegional;

public interface OficinaRegionalCrud extends CrudRepository<OficinaRegional, Integer> {
	
	@Query("SELECT o FROM OficinaRegional o WHERE UPPER(o.nombre) = UPPER(?1)")
	public OficinaRegional obtenerOficinaRegionalPorNombre(String nombre);
	
	public Iterable<OficinaRegional> findAllByOrderByNombreAsc();
}
