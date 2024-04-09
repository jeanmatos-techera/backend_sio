package pe.gob.osinergmin.sio.persistence.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import pe.gob.osinergmin.sio.entity.FotosTarea;
import pe.gob.osinergmin.sio.persistence.FotosTareaCrud;
import pe.gob.osinergmin.sio.persistence.dao.FotosTareaRepository;

@Repository
@Transactional(rollbackFor = Exception.class)
public class FotosTareaRepositoryImpl implements FotosTareaRepository {

	@Autowired
	FotosTareaCrud fotosTareaCrud;
	
	@Override
	public FotosTarea registrar(FotosTarea fotosTarea) {
		try {
			fotosTarea.setUsuarioCreacion("OSINERG");
			fotosTarea.setFechaCreacion(new Date());
			fotosTarea.setTerminalCreacion("Localhost");
			
			fotosTarea = fotosTareaCrud.save(fotosTarea);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return fotosTarea;
	}
	
	@Override
	public FotosTarea actualizar(FotosTarea fotosTarea) {
		FotosTarea fotos = null;
		try {
			fotos = this.obtenerFotosTareaPorId(fotosTarea.getIdFoto());
			if(fotos != null) {
				fotos.setIdTarea(fotosTarea.getIdTarea());
				fotos.setFoto(fotosTarea.getFoto());
				fotos.setUsuarioActualizacion("OSINERG");
				fotos.setFechaActualizacion(new Date());
				fotos.setTerminalActualizacion("Localhost");
				
				fotos = fotosTareaCrud.save(fotos);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return fotos;
	}
	
	@Override
	public void eliminar(Integer idFoto) {
		fotosTareaCrud.deleteById(idFoto);
	}

	@Override
	public List<FotosTarea> listarPorTarea(Integer idTarea) {
		List<FotosTarea> lista = new ArrayList<>();
		try {
			lista = fotosTareaCrud.listarPorTarea(idTarea);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}

	@Override
	public FotosTarea obtenerFotosTareaPorId(Integer idFotosTarea) {
		FotosTarea fotosTarea = null;
		try {
			fotosTarea = fotosTareaCrud.findById(idFotosTarea).orElse(null);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return fotosTarea;
	}

}
