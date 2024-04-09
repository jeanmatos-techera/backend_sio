package pe.gob.osinergmin.sio.persistence;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import pe.gob.osinergmin.sio.entity.Rol;

public interface RolCrud extends CrudRepository<Rol, Integer>{

	@Query("SELECT r FROM Rol r WHERE key = ?1")
	public Rol obtenerRolPorKey(String key);
	
	
	
}
