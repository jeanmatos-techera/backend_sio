package pe.gob.osinergmin.sio.persistence.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import pe.gob.osinergmin.sio.entity.UsuarioOR;
import pe.gob.osinergmin.sio.persistence.UsuarioORCrud;
import pe.gob.osinergmin.sio.persistence.dao.UsuarioORRepository;

@Repository
@Transactional(rollbackFor = Exception.class)
public class UsuarioORRepositoryImpl implements UsuarioORRepository {

	@Autowired
	UsuarioORCrud usuarioORCrud;

	@Override
	public List<UsuarioOR> listarPorUsuario(Integer idUsuario) {
		List<UsuarioOR> listArray = new ArrayList<>();
		try {
			Iterable<UsuarioOR> list = usuarioORCrud.listarPorUsuario(idUsuario);
			for(UsuarioOR usuarioOR: list) {
				listArray.add(usuarioOR);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return listArray;
	}

	@Override
	public UsuarioOR registrar(UsuarioOR usuarioOR) {
		try {
			usuarioOR.setUsuarioCreacion("OSINERG");
			usuarioOR.setFechaCreacion(new Date());
			usuarioOR.setTerminalCreacion("Localhost");
			
			usuarioOR = usuarioORCrud.save(usuarioOR);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return usuarioOR;
	}

	@Override
	public UsuarioOR actualizar(UsuarioOR usuarioOR) {
		UsuarioOR uOR = null;
		try {
			uOR = this.obtenerUsuarioORPorId(usuarioOR.getIdUsuarioOR());
			if(uOR != null) {
				uOR.setIdOficinaRegional(usuarioOR.getIdOficinaRegional());
				uOR.setUsuarioActualizacion("OSINERG");
				uOR.setFechaActualizacion(new Date());
				uOR.setTerminalActualizacion("Localhost");
				
				uOR = usuarioORCrud.save(uOR);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return uOR;
	}

	@Override
	public void eliminar(Integer idUsuarioOR) {
		usuarioORCrud.deleteById(idUsuarioOR);
	}

	@Override
	public UsuarioOR obtenerUsuarioORPorId(Integer idUsuarioOR) {
		UsuarioOR usuarioOR = null;
		try {
			usuarioOR = usuarioORCrud.findById(idUsuarioOR).orElse(null);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return usuarioOR;
	}
}
