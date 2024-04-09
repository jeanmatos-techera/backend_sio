package pe.gob.osinergmin.sio.persistence;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import pe.gob.osinergmin.sio.entity.Login;

public interface LoginCrud extends CrudRepository<Login, Integer>{

	@Modifying
	@Transactional
	@Query("UPDATE Login SET estado = ?1 WHERE idUsuario = ?2")
	public void cambiarEstados(String estado, Integer idUsuario);
	
	@Query("SELECT l FROM Login l WHERE estado = ?1 AND idUsuario = ?2 ORDER BY idLogin DESC")
	public List<Login> obtenerListaToken(String estado, Integer idUsuario);
	
}
