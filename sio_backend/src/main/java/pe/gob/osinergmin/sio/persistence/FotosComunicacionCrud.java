package pe.gob.osinergmin.sio.persistence;

import org.springframework.data.repository.CrudRepository;

import pe.gob.osinergmin.sio.entity.FotosComunicacion;

public interface FotosComunicacionCrud extends CrudRepository<FotosComunicacion, Integer>{

	public Iterable<FotosComunicacion> findAllByIdComunicacion(Integer idComunicacion);
}
