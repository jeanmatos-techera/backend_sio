package pe.gob.osinergmin.sio.persistence.dao;

import java.util.List;

import pe.gob.osinergmin.sio.entity.UsuarioOR;

public interface UsuarioORRepository {

	public List<UsuarioOR> listarPorUsuario(Integer idUsuario);
	
	public UsuarioOR registrar(UsuarioOR usuarioOR);
	
	public UsuarioOR actualizar(UsuarioOR usuarioOR);
	
	public void eliminar(Integer idUsuarioOR);
	
	public UsuarioOR obtenerUsuarioORPorId(Integer idUsuarioOR);
}
