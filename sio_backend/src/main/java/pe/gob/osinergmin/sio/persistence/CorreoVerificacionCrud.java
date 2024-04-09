package pe.gob.osinergmin.sio.persistence;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import pe.gob.osinergmin.sio.entity.CorreoVerificacion;

public interface CorreoVerificacionCrud extends CrudRepository<CorreoVerificacion, Integer>{

	@Query("SELECT cv FROM CorreoVerificacion cv WHERE UPPER(cv.correo) = UPPER(?1)")
	public CorreoVerificacion obtenerCorreoVerificacion(String correo);
}
