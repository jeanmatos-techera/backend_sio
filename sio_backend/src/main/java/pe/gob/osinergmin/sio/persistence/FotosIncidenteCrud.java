package pe.gob.osinergmin.sio.persistence;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import pe.gob.osinergmin.sio.entity.FotosIncidente;

public interface FotosIncidenteCrud extends CrudRepository<FotosIncidente, Integer>{

	@Query("SELECT fi FROM FotosIncidente fi WHERE fi.idIncidente = ?1")
	public FotosIncidente obtenerFotosPorIdIncidente(Integer idIncidente);
	
}
