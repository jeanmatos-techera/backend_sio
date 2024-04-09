package pe.gob.osinergmin.sio.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import pe.gob.osinergmin.sio.entity.Comunicacion;

public interface ComunicacionCrud extends CrudRepository<Comunicacion, Integer> {

	@Query("SELECT c FROM Comunicacion c WHERE c.idIncidente = ?1")
	public List<Comunicacion> obtenerComunicacionPorIdIncidente(Integer idIncidente);
}
