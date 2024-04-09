package pe.gob.osinergmin.sio.persistence.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import pe.gob.osinergmin.sio.entity.OficinaRegional;
import pe.gob.osinergmin.sio.persistence.OficinaRegionalCrud;
import pe.gob.osinergmin.sio.persistence.dao.OficinaRegionalRepository;

@Repository
@Transactional(rollbackFor = Exception.class)
public class OficinaRegionalRepositoryImpl implements OficinaRegionalRepository {

	@Autowired
	OficinaRegionalCrud oficinaRegionalCrud;

	@Override
	public List<OficinaRegional> listarOficinasRegionales() {
		List<OficinaRegional> listArray = new ArrayList<>();
		try {
			Iterable<OficinaRegional> list = oficinaRegionalCrud.findAllByOrderByNombreAsc();
			for(OficinaRegional sector: list) {
				listArray.add(sector);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return listArray;
	}

	@Override
	public OficinaRegional obtenerOficinaPorNombre(String nombre) {
		OficinaRegional oficianRegional = new OficinaRegional();
		try {
			oficianRegional = oficinaRegionalCrud.obtenerOficinaRegionalPorNombre(nombre);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return oficianRegional;
	}

}
