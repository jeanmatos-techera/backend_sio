package pe.gob.osinergmin.sio.persistence;

import org.springframework.data.repository.CrudRepository;

import pe.gob.osinergmin.sio.entity.FuenteInformacion;

public interface FuenteInformacionCrud extends CrudRepository<FuenteInformacion, Integer> {
	
}
