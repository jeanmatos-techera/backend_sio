package pe.gob.osinergmin.sio.persistence.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import pe.gob.osinergmin.sio.entity.TipoIncidente;
import pe.gob.osinergmin.sio.persistence.TipoIncidenteCrud;
import pe.gob.osinergmin.sio.persistence.dao.TipoIncidenteRepository;

@Repository
@Transactional(rollbackFor = Exception.class)
public class TipoIncidenteRepositoryImpl implements TipoIncidenteRepository{

	@Autowired
	TipoIncidenteCrud tipoIncidenteCrud;

	@Override
	public List<TipoIncidente> listarActivos() {
		List<TipoIncidente> lista = new ArrayList<>();
		try {
			lista = tipoIncidenteCrud.listarActivos();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}

	@Override
	public TipoIncidente obtenerTipoPorId(Integer idTipo) {
		TipoIncidente tipoIncidente = null;
		try {
			tipoIncidente = tipoIncidenteCrud.findById(idTipo).orElse(null);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return tipoIncidente;
	}
	
	@Override
	public List<TipoIncidente> listarPorSector(Integer idSector) {
		List<TipoIncidente> lista = new ArrayList<>();
		try {
			lista = tipoIncidenteCrud.listarPorSector(idSector);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
}
