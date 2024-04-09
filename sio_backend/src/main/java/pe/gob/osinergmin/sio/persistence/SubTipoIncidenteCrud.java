package pe.gob.osinergmin.sio.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import pe.gob.osinergmin.sio.entity.SubTipoIncidente;

public interface SubTipoIncidenteCrud extends CrudRepository<SubTipoIncidente, Integer>{
	
	@Query("SELECT st FROM SubTipoIncidente st WHERE estado = 'A' and idTipo = ?1")
	public List<SubTipoIncidente> listarPorTipo(Integer idTipo);
}
