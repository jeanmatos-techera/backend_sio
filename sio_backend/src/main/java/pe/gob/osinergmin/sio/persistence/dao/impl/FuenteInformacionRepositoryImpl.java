package pe.gob.osinergmin.sio.persistence.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import pe.gob.osinergmin.sio.entity.FuenteInformacion;
import pe.gob.osinergmin.sio.persistence.FuenteInformacionCrud;
import pe.gob.osinergmin.sio.persistence.dao.FuenteInformacionRepository;

@Repository
@Transactional(rollbackFor = Exception.class)
public class FuenteInformacionRepositoryImpl implements FuenteInformacionRepository {

	@Autowired
	FuenteInformacionCrud fuenteInformacionCrud;

	@Override
	public List<FuenteInformacion> listarFuentesInformacion() {
		List<FuenteInformacion> listArray = new ArrayList<>();
		try {
			Iterable<FuenteInformacion> list = fuenteInformacionCrud.findAll();
			for(FuenteInformacion fuenteInformacion: list) {
				listArray.add(fuenteInformacion);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return listArray;
	}

}
