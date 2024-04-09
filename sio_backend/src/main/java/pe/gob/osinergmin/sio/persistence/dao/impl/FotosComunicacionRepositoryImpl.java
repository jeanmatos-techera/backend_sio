package pe.gob.osinergmin.sio.persistence.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import pe.gob.osinergmin.sio.entity.FotosComunicacion;
import pe.gob.osinergmin.sio.persistence.FotosComunicacionCrud;
import pe.gob.osinergmin.sio.persistence.dao.FotosComunicacionRepository;

@Repository
@Transactional(rollbackFor = Exception.class)
public class FotosComunicacionRepositoryImpl implements FotosComunicacionRepository {

	@Autowired
	FotosComunicacionCrud fotosComunicacionCrud;
	
	@Override
	public FotosComunicacion registrar(FotosComunicacion fotosComunicacion) {
		try {
			fotosComunicacion.setUsuarioCreacion("OSINERG");
			fotosComunicacion.setFechaCreacion(new Date());
			fotosComunicacion.setTerminalCreacion("Localhost");
			
			fotosComunicacion = fotosComunicacionCrud.save(fotosComunicacion);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return fotosComunicacion;
	}

	@Override
	public List<FotosComunicacion> listarFotosPorComunicacion(Integer idComunicacion) {
		List<FotosComunicacion> lista = new ArrayList<>();
		try {
			Iterable<FotosComunicacion> fotos = fotosComunicacionCrud.findAllByIdComunicacion(idComunicacion);
			for(FotosComunicacion f: fotos) {
				lista.add(f);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}

	@Override
	public FotosComunicacion actualizar(FotosComunicacion fotosComunicacion) {
		FotosComunicacion fotos = null;
		try {
			fotos = this.obtenerFotosComunicacionPorId(fotosComunicacion.getIdFoto());
			
			if(fotos != null) {
				fotos.setIdComunicacion(fotosComunicacion.getIdComunicacion());
				fotos.setFoto(fotosComunicacion.getFoto());
				fotos.setUsuarioActualizacion("OSINERG");
				fotos.setFechaActualizacion(new Date());
				fotos.setTerminalActualizacion("Localhost");
				
				fotos = fotosComunicacionCrud.save(fotos);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return fotos;
	}

	@Override
	public void eliminar(Integer idFoto) {
		fotosComunicacionCrud.deleteById(idFoto);
		
	}
	
	@Override
	public FotosComunicacion obtenerFotosComunicacionPorId(Integer idFotosComunicacion) {
		FotosComunicacion fotosComuncacion = null;
		try {
			fotosComuncacion = fotosComunicacionCrud.findById(idFotosComunicacion).orElse(null);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return fotosComuncacion;
	}

}
