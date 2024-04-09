package pe.gob.osinergmin.sio.persistence.dao;

import pe.gob.osinergmin.sio.entity.FotosIncidente;

public interface FotosIncidenteRepository {
	
	public FotosIncidente registrar(FotosIncidente fotosIncidente);
	
	public FotosIncidente obtenerFotosPorIdIncidente(Integer idIncidente);
	
}
