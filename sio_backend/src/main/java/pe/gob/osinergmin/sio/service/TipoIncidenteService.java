package pe.gob.osinergmin.sio.service;

import pe.gob.osinergmin.sio.ro.out.ListTipoIncidenteOutRO;

public interface TipoIncidenteService {
	
	ListTipoIncidenteOutRO listarActivos();
	
	ListTipoIncidenteOutRO listarPorSector(Integer idSector);
}
