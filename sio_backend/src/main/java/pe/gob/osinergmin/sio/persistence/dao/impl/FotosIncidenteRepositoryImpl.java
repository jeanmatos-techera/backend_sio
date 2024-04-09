package pe.gob.osinergmin.sio.persistence.dao.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import pe.gob.osinergmin.sio.entity.FotosIncidente;
import pe.gob.osinergmin.sio.persistence.FotosIncidenteCrud;
import pe.gob.osinergmin.sio.persistence.dao.FotosIncidenteRepository;

@Repository
@Transactional(rollbackFor = Exception.class)
public class FotosIncidenteRepositoryImpl implements FotosIncidenteRepository {

	@Autowired
	FotosIncidenteCrud fotosIncidenteCrud;
	
	@Override
	public FotosIncidente registrar(FotosIncidente fotosIncidente) {
		FotosIncidente fotos = null;
		try {
			fotosIncidente.setUsuarioCreacion("OSINERG");
			fotosIncidente.setFechaCreacion(new Date());
			fotosIncidente.setTerminalCreacion("Localhost");
			
			fotosIncidente = fotosIncidenteCrud.save(fotosIncidente);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return fotos;
	}

	@Override
	public FotosIncidente obtenerFotosPorIdIncidente(Integer idIncidente) {
		FotosIncidente fotos = null;
		try {
			fotos = fotosIncidenteCrud.obtenerFotosPorIdIncidente(idIncidente);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return fotos;
	}

}
