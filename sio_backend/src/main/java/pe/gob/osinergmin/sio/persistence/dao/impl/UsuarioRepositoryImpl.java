package pe.gob.osinergmin.sio.persistence.dao.impl;

import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import pe.gob.osinergmin.sio.config.TokenUtils;
import pe.gob.osinergmin.sio.entity.Rol;
import pe.gob.osinergmin.sio.entity.Usuario;
import pe.gob.osinergmin.sio.persistence.RolCrud;
import pe.gob.osinergmin.sio.persistence.UsuarioCrud;
import pe.gob.osinergmin.sio.persistence.dao.UsuarioRepository;
import pe.gob.osinergmin.sio.ro.in.RegistroUsuarioInRO;
import pe.gob.osinergmin.sio.util.Constantes;

@Repository
@Transactional(rollbackFor = Exception.class)
public class UsuarioRepositoryImpl implements UsuarioRepository{

	@Autowired
	UsuarioCrud usuarioCrud;
	
	@Autowired
	RolCrud rolCrud;
	
	@Override
	public Usuario registro(RegistroUsuarioInRO registroUsuarioInRO) {
		Usuario usuario = new Usuario();
		try {			
			usuario.setNombre(registroUsuarioInRO.getNombre());
			usuario.setApePaterno(registroUsuarioInRO.getApePaterno());
			usuario.setApeMaterno(registroUsuarioInRO.getApeMaterno());
			usuario.setCelular(registroUsuarioInRO.getCelular());
			usuario.setIdDocumento(registroUsuarioInRO.getIdDocumento());
			usuario.setNumDocumento(registroUsuarioInRO.getNumDocumento());
			usuario.setDireccion(registroUsuarioInRO.getDireccion());
			usuario.setCorreo(registroUsuarioInRO.getCorreo());
			usuario.setContraseña(registroUsuarioInRO.getClave());
			usuario.setEstado(Constantes.ESTADO_ACTIVO);
			
			if(registroUsuarioInRO.getFotoBase64() != null && registroUsuarioInRO.getFotoBase64().length() > 0) {
				byte[] decodedBytes = Base64.getDecoder().decode(registroUsuarioInRO.getFotoBase64());
				usuario.setFoto(decodedBytes);
			}
			
			Rol rol = rolCrud.obtenerRolPorKey("ROLE_REGULAR");
			usuario.setIdRol(rol);
			
			usuario.setUsuarioCreacion("OSINERG");
			usuario.setFechaCreacion(new Date());
			usuario.setTerminalCreacion("Localhost");
			
			usuario = usuarioCrud.save(usuario);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return usuario;
	}
	
	@Override
	public Usuario actualizacion(RegistroUsuarioInRO registroUsuarioInRO, String token) {
		Usuario usuario = null;
		try {
			usuario = this.obtenerUsuarioPorId(TokenUtils.getUserIdFromToken(token));
			
			if(usuario != null) {
				usuario.setNombre(registroUsuarioInRO.getNombre());
				usuario.setApePaterno(registroUsuarioInRO.getApePaterno());
				usuario.setApeMaterno(registroUsuarioInRO.getApeMaterno());
				usuario.setCelular(registroUsuarioInRO.getCelular());
				usuario.setIdDocumento(registroUsuarioInRO.getIdDocumento());
				usuario.setNumDocumento(registroUsuarioInRO.getNumDocumento());
				usuario.setDireccion(registroUsuarioInRO.getDireccion());
				
				if(registroUsuarioInRO.getFotoBase64() != null && registroUsuarioInRO.getFotoBase64().length() > 0) {
					byte[] decodedBytes = Base64.getDecoder().decode(registroUsuarioInRO.getFotoBase64());
					usuario.setFoto(decodedBytes);
				}
				
				usuario.setUsuarioActualizacion("OSINERG");
				usuario.setFechaActualizacion(new Date());
				usuario.setTerminalActualizacion("Localhost");
				
				usuario = usuarioCrud.save(usuario);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return usuario;
	}

	@Override
	public Usuario obtenerUsuarioCorreo(String correo) {
		Usuario usuario = new Usuario();
		try {
			usuario = usuarioCrud.obtenerUsuarioCorreo(correo);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return usuario;
	}

	@Override
	public Usuario obtenerUsuarioPorId(Integer idUsuario) {
		Usuario usuario = null;
		try {
			usuario = usuarioCrud.findById(idUsuario).orElse(null);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return usuario;
	}

	@Override
	public Usuario guardar(Usuario usuario) {
		try {
			if(usuario.getIdUsuario() != null && usuario.getIdUsuario() > 0) {
				usuario.setUsuarioActualizacion("OSINERG");
				usuario.setFechaActualizacion(new Date());
				usuario.setTerminalActualizacion("Localhost");
			}else {
				usuario.setUsuarioCreacion("OSINERG");
				usuario.setFechaCreacion(new Date());
				usuario.setTerminalCreacion("Localhost");
			}
			usuario = usuarioCrud.save(usuario);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return usuario;
	}
	
	@Override
	public List<Rol> obtenerRoles() {
		List<Rol> roles = new ArrayList<>();
		try {
			Iterable<Rol> list  = rolCrud.findAll();
			for(Rol rol: list) {
				roles.add(rol);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return roles;
	}

	@Override
	public List<Usuario> obtenerUsuariosConRoles() {
		List<Usuario> usuarios = new ArrayList<>();
		try {
			usuarios  = usuarioCrud.findByIdRolIsNotNull();

		}catch(Exception e) {
			e.printStackTrace();
		}
		return usuarios;
	}

	@Override
	public List<Usuario> obtenerUsuariosSinRoles() {
		List<Usuario> usuarios = new ArrayList<>();
		try {
			usuarios  = usuarioCrud.findByIdRolIsNull();

		}catch(Exception e) {
			e.printStackTrace();
		}
		return usuarios;
	}

	@Override
	public Usuario actualizarRol(String correo, Integer idRol) {
		Usuario usuario = null;
		try {
			usuario = this.obtenerUsuarioCorreo(correo);
			
			if(usuario != null) {
				Rol rol = rolCrud.findById(idRol).orElse(null);
				usuario.setIdRol(rol);
				
				usuario.setUsuarioActualizacion("OSINERG");
				usuario.setFechaActualizacion(new Date());
				usuario.setTerminalActualizacion("Localhost");
				
				usuario = usuarioCrud.save(usuario);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return usuario;
	}

}
