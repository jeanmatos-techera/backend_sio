package pe.gob.osinergmin.sio.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import pe.gob.osinergmin.sio.entity.Usuario;

public interface UsuarioCrud extends CrudRepository<Usuario, Integer>{

	@Query("SELECT u FROM Usuario u WHERE UPPER(u.correo) = UPPER(?1)")
	public Usuario obtenerUsuarioCorreo(String correo);
	
	public List<Usuario> findByIdRolIsNotNull();
	
	public List<Usuario> findByIdRolIsNull();
}
