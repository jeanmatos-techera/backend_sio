package pe.gob.osinergmin.sio.service;

import org.springframework.stereotype.Service;

import pe.gob.osinergmin.sio.ro.in.AsignarRolInRO;
import pe.gob.osinergmin.sio.ro.in.CambiarClaveInRO;
import pe.gob.osinergmin.sio.ro.in.CorreoVerificacionInRO;
import pe.gob.osinergmin.sio.ro.in.RecuperarClaveInRO;
import pe.gob.osinergmin.sio.ro.in.RegistroUsuarioInRO;
import pe.gob.osinergmin.sio.ro.out.BaseOutRO;
import pe.gob.osinergmin.sio.ro.out.CorreoVerificacionOutRO;
import pe.gob.osinergmin.sio.ro.out.ListRolOutRO;
import pe.gob.osinergmin.sio.ro.out.ListUsuarioOutRO;
import pe.gob.osinergmin.sio.ro.out.RegistroUsuarioOutRO;

@Service
public interface UsuarioService {

	public CorreoVerificacionOutRO generarCodigoVerificacion(CorreoVerificacionInRO correoVerificacionInRO);
	
	public RegistroUsuarioOutRO registro(RegistroUsuarioInRO registroUsuarioInRO);
	
	public RegistroUsuarioOutRO actualizacion(RegistroUsuarioInRO registroUsuarioInRO, String token);
	
	public BaseOutRO cambioClave(CambiarClaveInRO cambiarClaveInRO, String token);
	
	public BaseOutRO recuperarClave(RecuperarClaveInRO recuperarClaveInRO);
	
	public ListRolOutRO listarRoles();
	
	public ListUsuarioOutRO obtenerUsuariosConRoles();
	
	public ListUsuarioOutRO obtenerUsuariosSinRoles();
	
	public BaseOutRO asignarRol(AsignarRolInRO asignarRolInRO);
	
	public BaseOutRO actualizarRol(AsignarRolInRO asignarRolInRO);
	
	public BaseOutRO desasignarRol(String correo);
}

