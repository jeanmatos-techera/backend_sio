package pe.gob.osinergmin.sio.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import pe.gob.osinergmin.sio.entity.Tarea;

public interface TareaCrud extends CrudRepository<Tarea, Integer> {
	
	@Query("SELECT t FROM Tarea t WHERE idIncidente = ?1")
	public List<Tarea> listarPorIncidente(Integer idIncidente);
}
