package pe.gob.osinergmin.sio.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pe.gob.osinergmin.sio.enums.ErrorCode;
import pe.gob.osinergmin.sio.enums.InvocationResult;
import pe.gob.osinergmin.sio.enums.TipoDocumento;
import pe.gob.osinergmin.sio.ro.in.AsignarRolInRO;
import pe.gob.osinergmin.sio.ro.in.CambiarClaveInRO;
import pe.gob.osinergmin.sio.ro.in.CorreoVerificacionInRO;
import pe.gob.osinergmin.sio.ro.in.RecuperarClaveInRO;
import pe.gob.osinergmin.sio.ro.in.RegistroUsuarioInRO;
import pe.gob.osinergmin.sio.ro.out.BaseOutRO;
import pe.gob.osinergmin.sio.ro.out.CorreoVerificacionOutRO;
import pe.gob.osinergmin.sio.ro.out.ListOficinaRegionalOutRO;
import pe.gob.osinergmin.sio.ro.out.ListRolOutRO;
import pe.gob.osinergmin.sio.ro.out.ListUsuarioOutRO;
import pe.gob.osinergmin.sio.ro.out.RegistroUsuarioOutRO;
import pe.gob.osinergmin.sio.service.OficinaRegionalService;
import pe.gob.osinergmin.sio.service.UsuarioService;
import pe.gob.osinergmin.sio.util.Validador;
import pe.gob.osinergmin.sio.util.ValidadorBuilder;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
	
	private static Logger logger = LoggerFactory.getLogger(UsuarioController.class);
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private OficinaRegionalService oficinaRegionalService;
	
	@GetMapping("/bienvenido")
	public String bienvenido(){
		return "Bievenido administrador a SOA Backend";
	}
	
	@PostMapping("/registro/generarCodigo")
	public CorreoVerificacionOutRO generarCodigoVerificacion(@RequestBody CorreoVerificacionInRO correoVerificacionInRO) {
		CorreoVerificacionOutRO correoVerificacionOutRO = new CorreoVerificacionOutRO();
		try {
			
			Validador validador = ValidadorBuilder.builder()
					.caracteres(correoVerificacionInRO.getCorreo()).validar().build();
			
			if (!validador.esValido()) {
				correoVerificacionOutRO.setResultCode(InvocationResult.FAILED.getCode());
				correoVerificacionOutRO.setErrorCode(ErrorCode.INCORRECT_FIELD_VALUE.getErrorCode());
				correoVerificacionOutRO.setMessage(ErrorCode.getMessageByErrorCode(ErrorCode.INCORRECT_FIELD_VALUE.getErrorCode()));
				return correoVerificacionOutRO;
			}
					
			correoVerificacionOutRO = usuarioService.generarCodigoVerificacion(correoVerificacionInRO);
		}catch(Exception e) {
			logger.error(e.getMessage());
			correoVerificacionOutRO.setResultCode(InvocationResult.FAILED.getCode());
			correoVerificacionOutRO.setErrorCode(ErrorCode.ERROR.getErrorCode());
			correoVerificacionOutRO.setMessage("Error al generar código");
		}
		return correoVerificacionOutRO;
	}
	
	@PostMapping("/registro")
	public RegistroUsuarioOutRO registro(@RequestBody RegistroUsuarioInRO registroUsuarioInRO) {
		RegistroUsuarioOutRO registroUsuarioOutRO = new RegistroUsuarioOutRO();
		try {
			
			Validador validador = ValidadorBuilder.builder()
					.numeros(String.valueOf(registroUsuarioInRO.getIdDocumento()))
					.caracteres(registroUsuarioInRO.getNombre(),registroUsuarioInRO.getApeMaterno(),
							registroUsuarioInRO.getApePaterno(),registroUsuarioInRO.getCelular(),
							registroUsuarioInRO.getNumDocumento(), registroUsuarioInRO.getDireccion(),
							registroUsuarioInRO.getCorreo(),registroUsuarioInRO.getClave(),
							registroUsuarioInRO.getCodVerificacion())
					.validar().build();
			
			if (!validador.esValido()) {
				registroUsuarioOutRO.setResultCode(InvocationResult.FAILED.getCode());
				registroUsuarioOutRO.setErrorCode(ErrorCode.INCORRECT_FIELD_VALUE.getErrorCode());
				registroUsuarioOutRO.setMessage(ErrorCode.getMessageByErrorCode(ErrorCode.INCORRECT_FIELD_VALUE.getErrorCode()));
				return registroUsuarioOutRO;
			}
			
			TipoDocumento tipoDocumento = TipoDocumento.get(registroUsuarioInRO.getIdDocumento());
			if(tipoDocumento == null) {
				registroUsuarioOutRO.setResultCode(InvocationResult.FAILED.getCode());
				registroUsuarioOutRO.setErrorCode(ErrorCode.INCORRECT_FIELD_VALUE.getErrorCode());
				registroUsuarioOutRO.setMessage("El tipo de documento no existe");
			
				return registroUsuarioOutRO;
			}
			

			registroUsuarioOutRO = usuarioService.registro(registroUsuarioInRO);
		}catch(Exception e) {
			logger.error(e.getMessage());
			registroUsuarioOutRO.setResultCode(InvocationResult.FAILED.getCode());
			registroUsuarioOutRO.setErrorCode(ErrorCode.ERROR.getErrorCode());
			registroUsuarioOutRO.setMessage("Error al registrar usuario");
		}
		return registroUsuarioOutRO;
	}
	
	@PostMapping("/actualizacion")
	public RegistroUsuarioOutRO actualizacion(@RequestBody RegistroUsuarioInRO registroUsuarioInRO, 
			@RequestHeader("Authorization") String authorizationHeader) {
		RegistroUsuarioOutRO registroUsuarioOutRO = new RegistroUsuarioOutRO();
		try {
			
			Validador validador = ValidadorBuilder.builder()
					.numeros(String.valueOf(registroUsuarioInRO.getIdDocumento()))
					.caracteres(registroUsuarioInRO.getNombre(),registroUsuarioInRO.getApeMaterno(),
							registroUsuarioInRO.getApePaterno(),registroUsuarioInRO.getCelular(),
							registroUsuarioInRO.getNumDocumento(), registroUsuarioInRO.getDireccion(),
							authorizationHeader)
					.validar().build();
			
			if (!validador.esValido()) {
				registroUsuarioOutRO.setResultCode(InvocationResult.FAILED.getCode());
				registroUsuarioOutRO.setErrorCode(ErrorCode.INCORRECT_FIELD_VALUE.getErrorCode());
				registroUsuarioOutRO.setMessage(ErrorCode.getMessageByErrorCode(ErrorCode.INCORRECT_FIELD_VALUE.getErrorCode()));
			
				return registroUsuarioOutRO;
			}
			
			TipoDocumento tipoDocumento = TipoDocumento.get(registroUsuarioInRO.getIdDocumento());
			if(tipoDocumento == null) {
				registroUsuarioOutRO.setResultCode(InvocationResult.FAILED.getCode());
				registroUsuarioOutRO.setErrorCode(ErrorCode.INCORRECT_FIELD_VALUE.getErrorCode());
				registroUsuarioOutRO.setMessage("El tipo de documento no existe");
			
				return registroUsuarioOutRO;
			}

			registroUsuarioOutRO = usuarioService.actualizacion(registroUsuarioInRO, authorizationHeader.substring(7));
		}catch(Exception e) {
			logger.error(e.getMessage());
			registroUsuarioOutRO.setResultCode(InvocationResult.FAILED.getCode());
			registroUsuarioOutRO.setErrorCode(ErrorCode.ERROR.getErrorCode());
			registroUsuarioOutRO.setMessage("Error al actualizar usuario");
		}
		return registroUsuarioOutRO;
	}
	
	@PostMapping("/cambioClave")
	public BaseOutRO cambioClave(@RequestBody CambiarClaveInRO cambiarClaveInRO, 
			@RequestHeader("Authorization") String authorizationHeader) {
		BaseOutRO baseOutRO = new BaseOutRO();
		try {
			
			Validador validador = ValidadorBuilder.builder()
					.caracteres(cambiarClaveInRO.getClaveActual(),cambiarClaveInRO.getNuevaClave(),
							cambiarClaveInRO.getConfirmarNuevaClave(), authorizationHeader)
					.validar().build();
			
			if (!validador.esValido()) {
				baseOutRO.setResultCode(InvocationResult.FAILED.getCode());
				baseOutRO.setErrorCode(ErrorCode.INCORRECT_FIELD_VALUE.getErrorCode());
				baseOutRO.setMessage(ErrorCode.getMessageByErrorCode(ErrorCode.INCORRECT_FIELD_VALUE.getErrorCode()));
			
				return baseOutRO;
			}
			baseOutRO = usuarioService.cambioClave(cambiarClaveInRO, authorizationHeader.substring(7));
		}catch(Exception e) {
			logger.error(e.getMessage());
			baseOutRO.setResultCode(InvocationResult.FAILED.getCode());
			baseOutRO.setErrorCode(ErrorCode.ERROR.getErrorCode());
			baseOutRO.setMessage("Error al cambiar clave");
		}
		return baseOutRO;
	}
	
	@PostMapping("/recuperarClave")
	public BaseOutRO recuperarClave(@RequestBody RecuperarClaveInRO recuperarClaveInRO) {
		BaseOutRO baseOutRO = new BaseOutRO();
		try {
			
			Validador validador = ValidadorBuilder.builder()
					.caracteres(recuperarClaveInRO.getCorreo())
					.validar().build();
			
			if (!validador.esValido()) {
				baseOutRO.setResultCode(InvocationResult.FAILED.getCode());
				baseOutRO.setErrorCode(ErrorCode.INCORRECT_FIELD_VALUE.getErrorCode());
				baseOutRO.setMessage(ErrorCode.getMessageByErrorCode(ErrorCode.INCORRECT_FIELD_VALUE.getErrorCode()));
			
				return baseOutRO;
			}
			baseOutRO = usuarioService.recuperarClave(recuperarClaveInRO);
		}catch(Exception e) {
			logger.error(e.getMessage());
			baseOutRO.setResultCode(InvocationResult.FAILED.getCode());
			baseOutRO.setErrorCode(ErrorCode.ERROR.getErrorCode());
			baseOutRO.setMessage("Error al recuperar clave");
		}
		return baseOutRO;
	}
	
	@GetMapping("/oficinasRegionales")
	public ListOficinaRegionalOutRO listarOficinasRegionales(){
		ListOficinaRegionalOutRO listOficinaRegionaOutRO = new ListOficinaRegionalOutRO();
		try {
			listOficinaRegionaOutRO = oficinaRegionalService.listarOficinasRegionales();
		}catch(Exception e) {
			logger.error(e.getMessage());
			listOficinaRegionaOutRO.setResultCode(InvocationResult.FAILED.getCode());
			listOficinaRegionaOutRO.setErrorCode(ErrorCode.ERROR.getErrorCode());
			listOficinaRegionaOutRO.setMessage("Error al listar oficinas regionales");
		}
		return listOficinaRegionaOutRO;
	}
	
	@GetMapping("/roles")
	public ListRolOutRO listarRoles(){
		ListRolOutRO listRolesOutRO = new ListRolOutRO();
		try {
			listRolesOutRO = usuarioService.listarRoles();
		}catch(Exception e) {
			logger.error(e.getMessage());
			listRolesOutRO.setResultCode(InvocationResult.FAILED.getCode());
			listRolesOutRO.setErrorCode(ErrorCode.ERROR.getErrorCode());
			listRolesOutRO.setMessage("Error al listar oficinas regionales");
		}
		return listRolesOutRO;
	}
	
	@GetMapping("/usuariosConRol")
	public ListUsuarioOutRO obtenerUsuariosConRoles(){
		ListUsuarioOutRO listUsuarioOutRO = new ListUsuarioOutRO();
		try {
			listUsuarioOutRO = usuarioService.obtenerUsuariosConRoles();
		}catch(Exception e) {
			logger.error(e.getMessage());
			listUsuarioOutRO.setResultCode(InvocationResult.FAILED.getCode());
			listUsuarioOutRO.setErrorCode(ErrorCode.ERROR.getErrorCode());
			listUsuarioOutRO.setMessage("Error al listar usuario con rol");
		}
		return listUsuarioOutRO;
	}
	
	@GetMapping("/usuariosSinRol")
	public ListUsuarioOutRO obtenerUsuariosSinRoles(){
		ListUsuarioOutRO listUsuarioOutRO = new ListUsuarioOutRO();
		try {
			listUsuarioOutRO = usuarioService.obtenerUsuariosSinRoles();
		}catch(Exception e) {
			logger.error(e.getMessage());
			listUsuarioOutRO.setResultCode(InvocationResult.FAILED.getCode());
			listUsuarioOutRO.setErrorCode(ErrorCode.ERROR.getErrorCode());
			listUsuarioOutRO.setMessage("Error al listar usuario con rol");
		}
		return listUsuarioOutRO;
	}
	
	@PostMapping("/asignarRol")
	public BaseOutRO asignarRol(@RequestBody AsignarRolInRO asignarRolInRO) {
		BaseOutRO baseOutRO = new BaseOutRO();
		try {
			
			Validador validador = ValidadorBuilder.builder()
					.caracteres(asignarRolInRO.getCorreoUsuario())
					.validar().build();
			
			if (!validador.esValido()) {
				baseOutRO.setResultCode(InvocationResult.FAILED.getCode());
				baseOutRO.setErrorCode(ErrorCode.INCORRECT_FIELD_VALUE.getErrorCode());
				baseOutRO.setMessage(ErrorCode.getMessageByErrorCode(ErrorCode.INCORRECT_FIELD_VALUE.getErrorCode()));
			
				return baseOutRO;
			}
			baseOutRO = usuarioService.asignarRol(asignarRolInRO);
		}catch(Exception e) {
			logger.error(e.getMessage());
			baseOutRO.setResultCode(InvocationResult.FAILED.getCode());
			baseOutRO.setErrorCode(ErrorCode.ERROR.getErrorCode());
			baseOutRO.setMessage("Error al asignar rol a usuario");
		}
		return baseOutRO;
	}
	
	@PostMapping("/actualizarRol")
	public BaseOutRO actualizarRol(@RequestBody AsignarRolInRO asignarRolInRO) {
		BaseOutRO baseOutRO = new BaseOutRO();
		try {
			
			Validador validador = ValidadorBuilder.builder()
					.caracteres(asignarRolInRO.getCorreoUsuario())
					.validar().build();
			
			if (!validador.esValido()) {
				baseOutRO.setResultCode(InvocationResult.FAILED.getCode());
				baseOutRO.setErrorCode(ErrorCode.INCORRECT_FIELD_VALUE.getErrorCode());
				baseOutRO.setMessage(ErrorCode.getMessageByErrorCode(ErrorCode.INCORRECT_FIELD_VALUE.getErrorCode()));
			
				return baseOutRO;
			}
			baseOutRO = usuarioService.actualizarRol(asignarRolInRO);
		}catch(Exception e) {
			logger.error(e.getMessage());
			baseOutRO.setResultCode(InvocationResult.FAILED.getCode());
			baseOutRO.setErrorCode(ErrorCode.ERROR.getErrorCode());
			baseOutRO.setMessage("Error al actualizar rol a usuario");
		}
		return baseOutRO;
	}
	
	@PostMapping("/desasignarRol")
	public BaseOutRO desasignarRol(@RequestBody AsignarRolInRO asignarRolInRO) {
		BaseOutRO baseOutRO = new BaseOutRO();
		try {
			
			Validador validador = ValidadorBuilder.builder()
					.caracteres(asignarRolInRO.getCorreoUsuario())
					.validar().build();
			
			if (!validador.esValido()) {
				baseOutRO.setResultCode(InvocationResult.FAILED.getCode());
				baseOutRO.setErrorCode(ErrorCode.INCORRECT_FIELD_VALUE.getErrorCode());
				baseOutRO.setMessage(ErrorCode.getMessageByErrorCode(ErrorCode.INCORRECT_FIELD_VALUE.getErrorCode()));
			
				return baseOutRO;
			}
			baseOutRO = usuarioService.desasignarRol(asignarRolInRO.getCorreoUsuario());
		}catch(Exception e) {
			logger.error(e.getMessage());
			baseOutRO.setResultCode(InvocationResult.FAILED.getCode());
			baseOutRO.setErrorCode(ErrorCode.ERROR.getErrorCode());
			baseOutRO.setMessage("Error al desasignar rol a usuario");
		}
		return baseOutRO;
	}
}
