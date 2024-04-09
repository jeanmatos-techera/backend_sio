package pe.gob.osinergmin.sio.persistence;

import org.springframework.data.repository.CrudRepository;

import pe.gob.osinergmin.sio.entity.Sector;

public interface SectorCrud extends CrudRepository<Sector, Integer> {
	
}
