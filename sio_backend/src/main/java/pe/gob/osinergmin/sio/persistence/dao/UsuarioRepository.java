package pe.gob.osinergmin.sio.persistence.dao;

import java.util.List;

import pe.gob.osinergmin.sio.entity.Rol;
import pe.gob.osinergmin.sio.entity.Usuario;
import pe.gob.osinergmin.sio.ro.in.RegistroUsuarioInRO;

public interface UsuarioRepository {

	public Usuario registro(RegistroUsuarioInRO registroUsuarioInRO);
	
	public Usuario actualizacion(RegistroUsuarioInRO registroUsuarioInRO, String token);
	
	public Usuario obtenerUsuarioCorreo(String correo);
	
	public Usuario obtenerUsuarioPorId(Integer idUsuario);
	
	public Usuario guardar(Usuario usuario);
	
	public List<Rol> obtenerRoles();
		
	public List<Usuario> obtenerUsuariosConRoles();
	
	public List<Usuario> obtenerUsuariosSinRoles();
	
	public Usuario actualizarRol(String correo, Integer idRol);
	
}
