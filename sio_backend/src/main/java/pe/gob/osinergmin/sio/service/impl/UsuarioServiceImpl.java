package pe.gob.osinergmin.sio.service.impl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import pe.gob.osinergmin.sio.config.TokenUtils;
import pe.gob.osinergmin.sio.config.UserDetailsImpl;
import pe.gob.osinergmin.sio.entity.CorreoVerificacion;
import pe.gob.osinergmin.sio.entity.Rol;
import pe.gob.osinergmin.sio.entity.Usuario;
import pe.gob.osinergmin.sio.entity.UsuarioOR;
import pe.gob.osinergmin.sio.enums.ErrorCode;
import pe.gob.osinergmin.sio.enums.InvocationResult;
import pe.gob.osinergmin.sio.persistence.dao.CorreoVerificacionRepository;
import pe.gob.osinergmin.sio.persistence.dao.UsuarioORRepository;
import pe.gob.osinergmin.sio.persistence.dao.UsuarioRepository;
import pe.gob.osinergmin.sio.ro.in.AsignarRolInRO;
import pe.gob.osinergmin.sio.ro.in.CambiarClaveInRO;
import pe.gob.osinergmin.sio.ro.in.CorreoVerificacionInRO;
import pe.gob.osinergmin.sio.ro.in.RecuperarClaveInRO;
import pe.gob.osinergmin.sio.ro.in.RegistroUsuarioInRO;
import pe.gob.osinergmin.sio.ro.in.UsuarioORInRO;
import pe.gob.osinergmin.sio.ro.out.BaseOutRO;
import pe.gob.osinergmin.sio.ro.out.CorreoVerificacionOutRO;
import pe.gob.osinergmin.sio.ro.out.ListRolOutRO;
import pe.gob.osinergmin.sio.ro.out.ListUsuarioOutRO;
import pe.gob.osinergmin.sio.ro.out.RegistroUsuarioOutRO;
import pe.gob.osinergmin.sio.ro.out.RolOutRO;
import pe.gob.osinergmin.sio.ro.out.UsuarioOROutRO;
import pe.gob.osinergmin.sio.ro.out.UsuarioOutRO;
import pe.gob.osinergmin.sio.service.EmailService;
import pe.gob.osinergmin.sio.service.UsuarioService;
import pe.gob.osinergmin.sio.util.Generador;

@Service("userDetailsService")
public class UsuarioServiceImpl implements UsuarioService, UserDetailsService {

	@Value("${sio.codigo.verificacion.subject}")
	private String subjectCodigoVerificacion;

	@Value("${sio.recuperar.clave.subject}")
	private String subjectRecuperarClave;

	@Value("${sio.codigo.verificacion.template}")
	private String templateCodigoVerificacion;

	@Value("${sio.recuperar.clave.template}")
	private String templateRecuperarClave;

	@Value("${sio.codigo.verificacion.minutes.expiration}")
	private String minutosExpiracion;

	@Value("${sio.codigo.verificacion.length}")
	private String length;

	@Autowired
	CorreoVerificacionRepository correoVerificacionRepository;

	@Autowired
	UsuarioRepository usuarioRepository;

	@Autowired
	EmailService emailService;

	@Autowired
	UsuarioORRepository usuarioORRepository;

	@Override
	public CorreoVerificacionOutRO generarCodigoVerificacion(CorreoVerificacionInRO correoVerificacionInRO) {
		CorreoVerificacionOutRO correoVerificacionOutRO = new CorreoVerificacionOutRO();
		try {

			Usuario getUsuario = usuarioRepository.obtenerUsuarioCorreo(correoVerificacionInRO.getCorreo());

			if (getUsuario != null && getUsuario.getIdUsuario() != null) {
				correoVerificacionOutRO.setResultCode(InvocationResult.FAILED.getCode());
				correoVerificacionOutRO.setErrorCode(ErrorCode.INCORRECT_ACTION_VALUE.getErrorCode());
				correoVerificacionOutRO.setMessage("El correo ya se encuentra registrado, intente con otro correo.");
				return correoVerificacionOutRO;
			}

			CorreoVerificacion getCorreoVerificacion = correoVerificacionRepository
					.obtenerCorreoVerificacion(correoVerificacionInRO.getCorreo());

			if (getCorreoVerificacion != null && getCorreoVerificacion.getIdVerificacion() != null) {
				correoVerificacionInRO.setIdVerificacion(getCorreoVerificacion.getIdVerificacion());
			} else {
				correoVerificacionInRO.setIdVerificacion(null);
			}

			LocalDateTime registro = LocalDateTime.now();
			LocalDateTime expiracion = registro.plusMinutes(Integer.parseInt(minutosExpiracion));
			correoVerificacionInRO.setCodigo(Generador.generarCodigoVerificacionAleatorio(Integer.parseInt(length)));
			correoVerificacionInRO.setFechaRegistro(registro);
			correoVerificacionInRO.setFechaExpiracion(expiracion);

			CorreoVerificacion correoVerificacion = correoVerificacionRepository.registro(correoVerificacionInRO);

			if (correoVerificacion == null || correoVerificacion.getIdVerificacion() == null) {
				correoVerificacionOutRO.setResultCode(InvocationResult.FAILED.getCode());
				correoVerificacionOutRO.setErrorCode(ErrorCode.ERROR.getErrorCode());
				correoVerificacionOutRO.setMessage("No se pudo crear el código de verificación");
				return correoVerificacionOutRO;
			}

			String plantillaEmail = templateCodigoVerificacion;
			DateTimeFormatter formatear = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			String formatearExpiracion = correoVerificacion.getFechaExpiracion().format(formatear);

			plantillaEmail = plantillaEmail.replace("{codigo}", correoVerificacion.getCodigo());
			plantillaEmail = plantillaEmail.replace("{expiracion}", formatearExpiracion);
			emailService.enviarEmail(correoVerificacion.getCorreo(), subjectCodigoVerificacion, plantillaEmail);

			correoVerificacionOutRO.setResultCode(InvocationResult.SUCCESS.getCode());
			correoVerificacionOutRO.setFechaExpiracion(formatearExpiracion);
			correoVerificacionOutRO.setMessage("El código de verificación se ha enviado a su correo");
		} catch (Exception e) {
			correoVerificacionOutRO.setFechaExpiracion(null);
			correoVerificacionOutRO.setResultCode(InvocationResult.FAILED.getCode());
			correoVerificacionOutRO.setErrorCode(ErrorCode.ERROR.getErrorCode());
			correoVerificacionOutRO.setMessage("Error al crear generar código de verificación");
		}

		return correoVerificacionOutRO;
	}

	@Override
	public RegistroUsuarioOutRO registro(RegistroUsuarioInRO registroUsuarioInRO) {
		RegistroUsuarioOutRO registroUsuarioOutRO = new RegistroUsuarioOutRO();
		try {

			Usuario getUsuario = usuarioRepository.obtenerUsuarioCorreo(registroUsuarioInRO.getCorreo());

			if (getUsuario != null && getUsuario.getIdUsuario() != null) {
				registroUsuarioOutRO.setResultCode(InvocationResult.FAILED.getCode());
				registroUsuarioOutRO.setMessage("El correo ya se encuentra registrado");

				return registroUsuarioOutRO;
			}

			LocalDateTime tiempoActual = LocalDateTime.now();
			CorreoVerificacion getCorreoVerificacion = correoVerificacionRepository
					.obtenerCorreoVerificacion(registroUsuarioInRO.getCorreo());

			if (getCorreoVerificacion == null || getCorreoVerificacion.getIdVerificacion() == null) {
				registroUsuarioOutRO.setResultCode(InvocationResult.FAILED.getCode());
				registroUsuarioOutRO.setMessage("No ha generado código de verificación");
				registroUsuarioOutRO.setErrorCode(ErrorCode.ERROR.getErrorCode());
				return registroUsuarioOutRO;
			}

			if (!registroUsuarioInRO.getCodVerificacion().equals(getCorreoVerificacion.getCodigo())) {
				registroUsuarioOutRO.setResultCode(InvocationResult.FAILED.getCode());
				registroUsuarioOutRO.setMessage("El código de verificación no coincide");
				registroUsuarioOutRO.setErrorCode(ErrorCode.INCORRECT_ACTION_VALUE.getErrorCode());
				return registroUsuarioOutRO;
			}

			if (tiempoActual.isAfter(getCorreoVerificacion.getFechaExpiracion())) {
				registroUsuarioOutRO.setResultCode(InvocationResult.FAILED.getCode());
				registroUsuarioOutRO.setMessage("El código de verificación expiró");
				registroUsuarioOutRO.setErrorCode(ErrorCode.INCORRECT_ACTION_VALUE.getErrorCode());
				return registroUsuarioOutRO;
			}

			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			registroUsuarioInRO.setClave(passwordEncoder.encode(registroUsuarioInRO.getClave()));
			Usuario usuarioCreado = usuarioRepository.registro(registroUsuarioInRO);

			if (usuarioCreado != null && usuarioCreado.getIdUsuario() != null) {
				registroUsuarioOutRO.setResultCode(InvocationResult.SUCCESS.getCode());
				registroUsuarioOutRO.setMessage("Se ha registrado correctamente");
			} else {
				registroUsuarioOutRO.setResultCode(InvocationResult.SUCCESS.getCode());
				registroUsuarioOutRO.setErrorCode(ErrorCode.ERROR.getErrorCode());
				registroUsuarioOutRO.setMessage("Error al registrar usuario");
			}

		} catch (Exception e) {
			registroUsuarioOutRO.setResultCode(InvocationResult.FAILED.getCode());
			registroUsuarioOutRO.setErrorCode(ErrorCode.ERROR.getErrorCode());
			registroUsuarioOutRO.setMessage("Error al registrar usuario");
		}
		return registroUsuarioOutRO;
	}

	@Override
	public RegistroUsuarioOutRO actualizacion(RegistroUsuarioInRO registroUsuarioInRO, String token) {
		RegistroUsuarioOutRO registroUsuarioOutRO = new RegistroUsuarioOutRO();
		try {

			Usuario usuario = usuarioRepository.actualizacion(registroUsuarioInRO, token);

			if (usuario == null) {
				registroUsuarioOutRO.setResultCode(InvocationResult.FAILED.getCode());
				registroUsuarioOutRO.setErrorCode(ErrorCode.NO_FOUND_ENTITY.getErrorCode());
			} else {
				registroUsuarioOutRO.setResultCode(InvocationResult.SUCCESS.getCode());
				registroUsuarioOutRO.setMessage("Se ha actualizado correctamente");
			}
		} catch (Exception e) {
			registroUsuarioOutRO.setResultCode(InvocationResult.FAILED.getCode());
			registroUsuarioOutRO.setErrorCode(ErrorCode.ERROR.getErrorCode());
			registroUsuarioOutRO.setMessage("Error al actualizar usuario");
		}
		return registroUsuarioOutRO;
	}

	@Override
	public BaseOutRO cambioClave(CambiarClaveInRO cambiarClaveInRO, String token) {
		BaseOutRO baseOutRO = new BaseOutRO();
		try {
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

			if (!cambiarClaveInRO.getNuevaClave().equals(cambiarClaveInRO.getConfirmarNuevaClave())) {
				baseOutRO.setResultCode(InvocationResult.FAILED.getCode());
				baseOutRO.setErrorCode(ErrorCode.INCORRECT_ACTION_VALUE.getErrorCode());
				baseOutRO.setMessage("La nueva clave no coincide con la clave de confirmación");
				return baseOutRO;
			}

			Integer idUsuario = TokenUtils.getUserIdFromToken(token);
			Usuario usuario = usuarioRepository.obtenerUsuarioPorId(idUsuario);

			if (!passwordEncoder.matches(cambiarClaveInRO.getClaveActual(), usuario.getContraseña())) {
				baseOutRO.setResultCode(InvocationResult.FAILED.getCode());
				baseOutRO.setErrorCode(ErrorCode.INCORRECT_ACTION_VALUE.getErrorCode());
				baseOutRO.setMessage("La contraseña actual es incorrecta");
				return baseOutRO;
			}

			usuario.setContraseña(passwordEncoder.encode(cambiarClaveInRO.getNuevaClave()));

			usuarioRepository.guardar(usuario);

			baseOutRO.setResultCode(InvocationResult.SUCCESS.getCode());
			baseOutRO.setMessage("Se ha actualizado correctamente");
		} catch (Exception e) {
			baseOutRO.setResultCode(InvocationResult.FAILED.getCode());
			baseOutRO.setErrorCode(ErrorCode.ERROR.getErrorCode());
			baseOutRO.setMessage("Error al cambiar clave");
		}
		return baseOutRO;
	}

	@Override
	public BaseOutRO recuperarClave(RecuperarClaveInRO recuperarClaveInRO) {
		BaseOutRO baseOutRO = new BaseOutRO();
		try {
			Usuario usuario = usuarioRepository.obtenerUsuarioCorreo(recuperarClaveInRO.getCorreo());

			if (usuario == null) {
				baseOutRO.setResultCode(InvocationResult.FAILED.getCode());
				baseOutRO.setErrorCode(ErrorCode.NO_FOUND_ENTITY.getErrorCode());
				baseOutRO.setMessage("El correo no estra registrado");
				return baseOutRO;
			}

			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			String nuevaClave = Generador.generarClaveSeguraAleatoria(10);

			usuario.setContraseña(passwordEncoder.encode(nuevaClave));
			usuarioRepository.guardar(usuario);

			String plantillaEmail = templateRecuperarClave;
			plantillaEmail = plantillaEmail.replace("{clave}", nuevaClave);
			emailService.enviarEmail(recuperarClaveInRO.getCorreo(), subjectRecuperarClave, plantillaEmail);

			baseOutRO.setResultCode(InvocationResult.SUCCESS.getCode());
			baseOutRO.setMessage("Se envio su nueva clave a su correo");
		} catch (Exception e) {
			baseOutRO.setResultCode(InvocationResult.FAILED.getCode());
			baseOutRO.setErrorCode(ErrorCode.ERROR.getErrorCode());
			baseOutRO.setMessage("Error al cambiar clave");
		}
		return baseOutRO;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = usuarioRepository.obtenerUsuarioCorreo(username);
		List<UsuarioOROutRO> listaOutRO = new ArrayList<>();
		List<UsuarioOR> listUsuarioOR = usuarioORRepository.listarPorUsuario(usuario.getIdUsuario());
		for (UsuarioOR usuarioOR : listUsuarioOR) {
			UsuarioOROutRO usuarioOROutRO = new UsuarioOROutRO(usuarioOR.getIdUsuarioOR(), usuarioOR.getIdUsuario(),
					usuarioOR.getIdOficinaRegional());
			listaOutRO.add(usuarioOROutRO);
		}

		List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
		if (usuario.getIdRol() != null) {
			grantedAuthorities.add(new SimpleGrantedAuthority(usuario.getIdRol().getKey()));
		} else {
			grantedAuthorities.add(new SimpleGrantedAuthority("USER_NO_VALIDATE"));
		}

		UsuarioOutRO usuarioOutRO = new UsuarioOutRO();
		usuarioOutRO.setNombre(usuario.getNombre());
		usuarioOutRO.setCelular(usuario.getCelular());
		usuarioOutRO.setApePaterno(usuario.getApePaterno());
		usuarioOutRO.setApeMaterno(usuario.getApeMaterno());
		usuarioOutRO.setIdDocumento(usuario.getIdDocumento());
		usuarioOutRO.setNumDocumento(usuario.getNumDocumento());
		usuarioOutRO.setDireccion(usuario.getDireccion());
		usuarioOutRO.setCorreo(usuario.getCorreo());
		usuarioOutRO.setOficinasRegionales(listaOutRO);
		if (usuario.getFoto() != null) {
			usuarioOutRO.setFotoBase64(Base64.getEncoder().encodeToString(usuario.getFoto()));
		} else {
			usuarioOutRO.setFotoBase64("");
		}
		usuarioOutRO.setKeyRol(usuario.getIdRol().getKey());
		usuarioOutRO.setNombreRol(usuario.getIdRol().getNombre());
		
		return new UserDetailsImpl(usuario, usuarioOutRO, grantedAuthorities);
	}

	@Override
	public ListRolOutRO listarRoles() {
		ListRolOutRO listRolesOutRO = new ListRolOutRO();
		List<RolOutRO> listOutRO = new ArrayList<>();

		try {
			List<Rol> list = usuarioRepository.obtenerRoles();

			for (Rol rol : list) {
				RolOutRO rolOutRO = new RolOutRO();
				rolOutRO.setIdRol(rol.getIdRol());
				rolOutRO.setDescripcion(rol.getDescripcion());
				rolOutRO.setNombre(rol.getNombre());
				rolOutRO.setKey(rol.getKey());
				listOutRO.add(rolOutRO);
			}

			listRolesOutRO.setResultCode(InvocationResult.SUCCESS.getCode());
		} catch (Exception e) {
			listRolesOutRO.setResultCode(InvocationResult.FAILED.getCode());
			listRolesOutRO.setErrorCode(ErrorCode.ERROR.getErrorCode());
			listRolesOutRO.setMessage("Error al listar los Roles");
		}
		listRolesOutRO.setRoles(listOutRO);
		return listRolesOutRO;
	}

	@Override
	public ListUsuarioOutRO obtenerUsuariosConRoles() {
		ListUsuarioOutRO listUsuarioOutRO = new ListUsuarioOutRO();
		List<UsuarioOutRO> listOutRO = new ArrayList<>();

		try {
			List<Usuario> list = usuarioRepository.obtenerUsuariosConRoles();

			for (Usuario usuario : list) {
				UsuarioOutRO usuarioOutRO = new UsuarioOutRO();
				usuarioOutRO.setId(usuario.getIdUsuario());
				usuarioOutRO.setNombre(usuario.getNombre());
				usuarioOutRO.setCelular(usuario.getCelular());
				usuarioOutRO.setApePaterno(usuario.getApePaterno());
				usuarioOutRO.setApeMaterno(usuario.getApeMaterno());
				usuarioOutRO.setIdDocumento(usuario.getIdDocumento());
				usuarioOutRO.setNumDocumento(usuario.getNumDocumento());
				usuarioOutRO.setDireccion(usuario.getDireccion());
				usuarioOutRO.setCorreo(usuario.getCorreo());
				// usuarioOutRO.setIdOficinaRegional(usuario.getIdOficinaRegional());
				usuarioOutRO.setIdRol(usuario.getIdRol().getIdRol());
				usuarioOutRO.setNombreRol(usuario.getIdRol().getNombre());

				List<UsuarioOR> listOR = usuarioORRepository.listarPorUsuario(usuario.getIdUsuario());
				List<UsuarioOROutRO> oficinasRegionales = new ArrayList<>();
				for (UsuarioOR uOr : listOR) {
					UsuarioOROutRO usuarioORG = new UsuarioOROutRO();
					usuarioORG.setIdUsuario(uOr.getIdUsuario());
					usuarioORG.setIdOficinaRegional(uOr.getIdOficinaRegional());
					usuarioORG.setIdUsuarioOR(uOr.getIdUsuarioOR());
					oficinasRegionales.add(usuarioORG);
				}
				usuarioOutRO.setOficinasRegionales(oficinasRegionales);
				listOutRO.add(usuarioOutRO);
			}

			listUsuarioOutRO.setResultCode(InvocationResult.SUCCESS.getCode());
		} catch (Exception e) {
			listUsuarioOutRO.setResultCode(InvocationResult.FAILED.getCode());
			listUsuarioOutRO.setErrorCode(ErrorCode.ERROR.getErrorCode());
			listUsuarioOutRO.setMessage("Error al listar los Usuarios con Rol");
		}
		listUsuarioOutRO.setUsuarios(listOutRO);
		return listUsuarioOutRO;
	}

	@Override
	public ListUsuarioOutRO obtenerUsuariosSinRoles() {
		ListUsuarioOutRO listUsuarioOutRO = new ListUsuarioOutRO();
		List<UsuarioOutRO> listOutRO = new ArrayList<>();

		try {
			List<Usuario> list = usuarioRepository.obtenerUsuariosSinRoles();

			for (Usuario usuario : list) {
				UsuarioOutRO usuarioOutRO = new UsuarioOutRO();
				usuarioOutRO.setId(usuario.getIdUsuario());
				usuarioOutRO.setNombre(usuario.getNombre());
				usuarioOutRO.setCelular(usuario.getCelular());
				usuarioOutRO.setApePaterno(usuario.getApePaterno());
				usuarioOutRO.setApeMaterno(usuario.getApeMaterno());
				usuarioOutRO.setIdDocumento(usuario.getIdDocumento());
				usuarioOutRO.setNumDocumento(usuario.getNumDocumento());
				usuarioOutRO.setDireccion(usuario.getDireccion());
				usuarioOutRO.setCorreo(usuario.getCorreo());
				// usuarioOutRO.setIdOficinaRegional(usuario.getIdOficinaRegional());
				List<UsuarioOR> listOR = usuarioORRepository.listarPorUsuario(usuario.getIdUsuario());
				List<UsuarioOROutRO> oficinasRegionales = new ArrayList<>();
				for (UsuarioOR uOr : listOR) {
					UsuarioOROutRO usuarioORG = new UsuarioOROutRO();
					usuarioORG.setIdUsuario(uOr.getIdUsuario());
					usuarioORG.setIdOficinaRegional(uOr.getIdOficinaRegional());
					usuarioORG.setIdUsuarioOR(uOr.getIdUsuarioOR());
					oficinasRegionales.add(usuarioORG);
				}
				usuarioOutRO.setOficinasRegionales(oficinasRegionales);
				listOutRO.add(usuarioOutRO);
			}

			listUsuarioOutRO.setResultCode(InvocationResult.SUCCESS.getCode());
		} catch (Exception e) {
			listUsuarioOutRO.setResultCode(InvocationResult.FAILED.getCode());
			listUsuarioOutRO.setErrorCode(ErrorCode.ERROR.getErrorCode());
			listUsuarioOutRO.setMessage("Error al listar los Usuarios con Rol");
		}
		listUsuarioOutRO.setUsuarios(listOutRO);
		return listUsuarioOutRO;
	}

	@Override
	public BaseOutRO asignarRol(AsignarRolInRO asignarRolInRO) {
		BaseOutRO baseOutRO = new BaseOutRO();
		try {

			Usuario usuario = usuarioRepository.actualizarRol(asignarRolInRO.getCorreoUsuario(),
					asignarRolInRO.getIdRol());

			if (usuario == null) {
				baseOutRO.setResultCode(InvocationResult.FAILED.getCode());
				baseOutRO.setErrorCode(ErrorCode.NO_FOUND_ENTITY.getErrorCode());
			} else {
				for (UsuarioORInRO uOr : asignarRolInRO.getOficinasRegionales()) {
					if (uOr.getIdOficinaRegional() != null) {
						UsuarioOR usuarioOR = new UsuarioOR();
						usuarioOR.setIdUsuario(usuario.getIdUsuario());
						usuarioOR.setIdOficinaRegional(uOr.getIdOficinaRegional());

						usuarioORRepository.registrar(usuarioOR);
					}
				}
				baseOutRO.setResultCode(InvocationResult.SUCCESS.getCode());
				baseOutRO.setMessage("Se ha asignado correctamente");
			}
		} catch (Exception e) {
			baseOutRO.setResultCode(InvocationResult.FAILED.getCode());
			baseOutRO.setErrorCode(ErrorCode.ERROR.getErrorCode());
			baseOutRO.setMessage("Error al asignar rol a usuario");
		}
		return baseOutRO;
	}

	@Override
	public BaseOutRO actualizarRol(AsignarRolInRO asignarRolInRO) {
		BaseOutRO baseOutRO = new BaseOutRO();
		try {

			Usuario usuario = usuarioRepository.actualizarRol(asignarRolInRO.getCorreoUsuario(),
					asignarRolInRO.getIdRol());

			if (usuario == null) {
				baseOutRO.setResultCode(InvocationResult.FAILED.getCode());
				baseOutRO.setErrorCode(ErrorCode.NO_FOUND_ENTITY.getErrorCode());
			} else {
				
				//admin
				if(asignarRolInRO.getIdRol() == 1) {
					List<UsuarioOR> list = usuarioORRepository.listarPorUsuario(usuario.getIdUsuario());
					Integer idUOR = 0;
					if(list.size() > 0) {
						for(UsuarioOR uOr : list) {
							idUOR = uOr.getIdUsuarioOR();
							break;
						}
					}
					
					if(idUOR != 0) {
						usuarioORRepository.eliminar(idUOR);
					}
				}else {
					for (UsuarioORInRO uOr : asignarRolInRO.getOficinasRegionales()) {
						if (uOr.getIdOficinaRegional() != null) {
							List<UsuarioOR> list = usuarioORRepository.listarPorUsuario(usuario.getIdUsuario());
							if (list.size() > 0) {
								UsuarioOR usuarioOR = list.get(0);
								usuarioOR.setIdOficinaRegional(uOr.getIdOficinaRegional());

								usuarioORRepository.actualizar(usuarioOR);
							}else {
								UsuarioOR usuarioOR = new UsuarioOR();
								usuarioOR.setIdUsuario(usuario.getIdUsuario());
								usuarioOR.setIdOficinaRegional(uOr.getIdOficinaRegional());

								usuarioORRepository.registrar(usuarioOR);
								
							}
						}
					}
				}
				
				baseOutRO.setResultCode(InvocationResult.SUCCESS.getCode());
				baseOutRO.setMessage("Se ha actualizado correctamente");
			}
		} catch (Exception e) {
			baseOutRO.setResultCode(InvocationResult.FAILED.getCode());
			baseOutRO.setErrorCode(ErrorCode.ERROR.getErrorCode());
			baseOutRO.setMessage("Error al actualizar rol a usuario");
		}
		return baseOutRO;
	}

	@Override
	public BaseOutRO desasignarRol(String correo) {
		BaseOutRO baseOutRO = new BaseOutRO();
		try {

			Usuario usuario = usuarioRepository.actualizarRol(correo, 0);

			if (usuario == null) {
				baseOutRO.setResultCode(InvocationResult.FAILED.getCode());
				baseOutRO.setErrorCode(ErrorCode.NO_FOUND_ENTITY.getErrorCode());
			} else {
				List<UsuarioOR> list = usuarioORRepository.listarPorUsuario(usuario.getIdUsuario());
				for (UsuarioOR uOr : list) {
					usuarioORRepository.eliminar(uOr.getIdUsuarioOR());
				}
				baseOutRO.setResultCode(InvocationResult.SUCCESS.getCode());
				baseOutRO.setMessage("Se ha desasignado correctamente");
			}
		} catch (Exception e) {
			baseOutRO.setResultCode(InvocationResult.FAILED.getCode());
			baseOutRO.setErrorCode(ErrorCode.ERROR.getErrorCode());
			baseOutRO.setMessage("Error al desasignar rol a usuario");
		}
		return baseOutRO;
	}

}
