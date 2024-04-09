package pe.gob.osinergmin.sio.persistence.dao;

import java.util.List;

import pe.gob.osinergmin.sio.entity.TipoIncidente;

public interface TipoIncidenteRepository {
	
	public List<TipoIncidente> listarActivos();
	
	public TipoIncidente obtenerTipoPorId(Integer idTipo);
	
	public List<TipoIncidente> listarPorSector(Integer idSector);
}
