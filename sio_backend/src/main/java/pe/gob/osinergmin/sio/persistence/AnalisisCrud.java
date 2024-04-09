package pe.gob.osinergmin.sio.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import pe.gob.osinergmin.sio.entity.Analisis;

public interface AnalisisCrud extends CrudRepository<Analisis, Integer> {
	
	@Query("SELECT a FROM Analisis a WHERE idIncidente = ?1")
	public List<Analisis> listarPorIncidente(Integer idIncidente);
}
