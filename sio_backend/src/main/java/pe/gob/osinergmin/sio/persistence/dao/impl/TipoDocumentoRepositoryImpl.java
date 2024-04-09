package pe.gob.osinergmin.sio.persistence.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import pe.gob.osinergmin.sio.entity.TipoDocumento;
import pe.gob.osinergmin.sio.persistence.TipoDocumentoCrud;
import pe.gob.osinergmin.sio.persistence.dao.TipoDocumentoRepository;

@Repository
@Transactional(rollbackFor = Exception.class)
public class TipoDocumentoRepositoryImpl implements TipoDocumentoRepository{
	
	@Autowired
	TipoDocumentoCrud tipoDocumentoCrud;

	@Override
	public List<TipoDocumento> listarTiposDocumentos() {
		List<TipoDocumento> listArray = new ArrayList<>();
		try {
			Iterable<TipoDocumento> list = tipoDocumentoCrud.findAll();
			for(TipoDocumento tipoDocumento: list) {
				listArray.add(tipoDocumento);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return listArray;
	}
	
}
