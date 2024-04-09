package pe.gob.osinergmin.sio.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import pe.gob.osinergmin.sio.entity.UsuarioOR;

public interface UsuarioORCrud extends CrudRepository<UsuarioOR, Integer> {
	
	@Query("SELECT uor FROM UsuarioOR uor WHERE uor.idUsuario = ?1")
	public List<UsuarioOR> listarPorUsuario(Integer idUsuario);
}
