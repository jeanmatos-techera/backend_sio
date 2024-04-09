package pe.gob.osinergmin.sio.persistence.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import pe.gob.osinergmin.sio.entity.IncidenteAccion;
import pe.gob.osinergmin.sio.persistence.dao.IncidenteAccionRepository;
import pe.gob.osinergmin.sio.persistence.IncidenteAccionCrud;

@Repository
@Transactional(rollbackFor = Exception.class)
public class IncidenteAccionRepositoryImpl implements IncidenteAccionRepository {

	@Autowired
	IncidenteAccionCrud IncidenteAccionCrud;
	
	@Override
	public IncidenteAccion guardar(IncidenteAccion incidenteAccion) {
		try {
			incidenteAccion.setUsuarioCreacion("OSINERG");
			incidenteAccion.setFechaCreacion(new Date());
			incidenteAccion.setTerminalCreacion("Localhost");
			
			incidenteAccion = IncidenteAccionCrud.save(incidenteAccion);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return incidenteAccion;
	}

	@Override
	public List<IncidenteAccion> obtenerAccionesPorIdIncidente(Integer idIncidente) {
		List<IncidenteAccion> list = new ArrayList<>();
		try {
			list = IncidenteAccionCrud.obtenerAccionesPorIdIncidente(idIncidente);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}

}
