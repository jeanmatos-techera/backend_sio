package pe.gob.osinergmin.sio.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import pe.gob.osinergmin.sio.entity.TipoIncidente;

public interface TipoIncidenteCrud extends CrudRepository<TipoIncidente, Integer> {

	@Query("SELECT ti FROM TipoIncidente ti WHERE estado = 'A'")
	public List<TipoIncidente> listarActivos();
	
	@Query("SELECT ti FROM TipoIncidente ti WHERE estado = 'A' and idSector = ?1")
	public List<TipoIncidente> listarPorSector(Integer idSector);
}
