package pe.gob.osinergmin.sio.persistence.dao;

import java.util.List;

import pe.gob.osinergmin.sio.entity.SubTipoIncidente;


public interface SubTipoIncidenteRepository {
	public List<SubTipoIncidente> listarPorTipo(Integer idTipo);

	public SubTipoIncidente obtenerSubTipoPorId(Integer idSubTipo);
}
