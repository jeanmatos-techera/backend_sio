package pe.gob.osinergmin.sio.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import pe.gob.osinergmin.sio.entity.FotosTarea;

public interface FotosTareaCrud extends CrudRepository<FotosTarea, Integer>{

	@Query("SELECT ft FROM FotosTarea ft WHERE ft.idTarea = ?1")
	public List<FotosTarea> listarPorTarea(Integer idTarea);
	
}
