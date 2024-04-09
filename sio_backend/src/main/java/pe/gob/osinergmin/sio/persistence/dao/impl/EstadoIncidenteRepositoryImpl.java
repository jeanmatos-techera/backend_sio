package pe.gob.osinergmin.sio.persistence.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import pe.gob.osinergmin.sio.entity.EstadoIncidente;
import pe.gob.osinergmin.sio.persistence.EstadoIncidenteCrud;
import pe.gob.osinergmin.sio.persistence.dao.EstadoIncidenteRepository;

@Repository
@Transactional(rollbackFor = Exception.class)
public class EstadoIncidenteRepositoryImpl implements EstadoIncidenteRepository {

	@Autowired
	EstadoIncidenteCrud estadoIncidenteCrud;

	@Override
	public List<EstadoIncidente> listarEstados() {
		List<EstadoIncidente> listArray = new ArrayList<>();
		try {
			Iterable<EstadoIncidente> list = estadoIncidenteCrud.findAll();
			for(EstadoIncidente estadoIncidente: list) {
				listArray.add(estadoIncidente);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return listArray;
	}

}
