package pe.gob.osinergmin.sio.persistence.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import pe.gob.osinergmin.sio.entity.Sector;
import pe.gob.osinergmin.sio.persistence.SectorCrud;
import pe.gob.osinergmin.sio.persistence.dao.SectorRepository;

@Repository
@Transactional(rollbackFor = Exception.class)
public class SectorRepositoryImpl implements SectorRepository {

	@Autowired
	SectorCrud sectorCrud;

	@Override
	public List<Sector> listarSectores() {
		List<Sector> listArray = new ArrayList<>();
		try {
			Iterable<Sector> list = sectorCrud.findAll();
			for(Sector sector: list) {
				listArray.add(sector);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return listArray;
	}

}
