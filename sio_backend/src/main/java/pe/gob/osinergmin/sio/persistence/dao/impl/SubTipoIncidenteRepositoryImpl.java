package pe.gob.osinergmin.sio.persistence.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import pe.gob.osinergmin.sio.entity.SubTipoIncidente;
import pe.gob.osinergmin.sio.entity.TipoIncidente;
import pe.gob.osinergmin.sio.persistence.SubTipoIncidenteCrud;
import pe.gob.osinergmin.sio.persistence.dao.SubTipoIncidenteRepository;


@Repository
@Transactional(rollbackFor = Exception.class)
public class SubTipoIncidenteRepositoryImpl implements SubTipoIncidenteRepository{
	@Autowired
	SubTipoIncidenteCrud subTipoIncidenteCrud;

	@Override
	public List<SubTipoIncidente> listarPorTipo(Integer idTipo) {
		List<SubTipoIncidente> lista = new ArrayList<>();
		try {
			lista = subTipoIncidenteCrud.listarPorTipo(idTipo);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}

	@Override
	public SubTipoIncidente obtenerSubTipoPorId(Integer idSubTipo) {
		SubTipoIncidente subTipoIncidente = null;
		try {
			subTipoIncidente = subTipoIncidenteCrud.findById(idSubTipo).orElse(null);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return subTipoIncidente;// TODO Auto-generated method stub
	}
}
