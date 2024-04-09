package pe.gob.osinergmin.sio.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.gob.osinergmin.sio.config.TokenUtils;
import pe.gob.osinergmin.sio.entity.Incidente;
import pe.gob.osinergmin.sio.entity.IncidenteAccion;
import pe.gob.osinergmin.sio.entity.OficinaRegional;
import pe.gob.osinergmin.sio.entity.SubTipoIncidente;
import pe.gob.osinergmin.sio.entity.TipoIncidente;
import pe.gob.osinergmin.sio.entity.Ubigeo;
import pe.gob.osinergmin.sio.entity.Usuario;
import pe.gob.osinergmin.sio.enums.ErrorCode;
import pe.gob.osinergmin.sio.enums.InvocationResult;
import pe.gob.osinergmin.sio.persistence.dao.FotosIncidenteRepository;
import pe.gob.osinergmin.sio.persistence.dao.IncidenteAccionRepository;
import pe.gob.osinergmin.sio.persistence.dao.IncidenteRepository;
import pe.gob.osinergmin.sio.persistence.dao.OficinaRegionalRepository;
import pe.gob.osinergmin.sio.persistence.dao.SubTipoIncidenteRepository;
import pe.gob.osinergmin.sio.persistence.dao.TipoIncidenteRepository;
import pe.gob.osinergmin.sio.persistence.dao.UbigeoRepository;
import pe.gob.osinergmin.sio.persistence.dao.UsuarioRepository;
import pe.gob.osinergmin.sio.ro.in.IncidenteInRO;
import pe.gob.osinergmin.sio.ro.in.IntegrarIncidenteInRO;
import pe.gob.osinergmin.sio.ro.out.BaseOutRO;
import pe.gob.osinergmin.sio.ro.out.CerrarIncidenteOutRO;
import pe.gob.osinergmin.sio.ro.out.DetalleIncidenteOutRO;
import pe.gob.osinergmin.sio.ro.out.IncidenteIntegrarOutRO;
import pe.gob.osinergmin.sio.ro.out.IncidenteOut;
import pe.gob.osinergmin.sio.ro.out.IncidenteOutRO;
import pe.gob.osinergmin.sio.ro.out.IncidenteTablaDinamicaOutRo;
import pe.gob.osinergmin.sio.ro.out.ListIncidenteIntegrarOutRO;
import pe.gob.osinergmin.sio.ro.out.ListIncidenteOutRO;
import pe.gob.osinergmin.sio.ro.out.ListIncidenteTablaDinamicaOutRo;
import pe.gob.osinergmin.sio.service.IncidenteService;
import pe.gob.osinergmin.sio.util.Constantes;

@Service("incidenteService")
public class IncidenteServiceImpl implements IncidenteService {

	@Autowired
	IncidenteRepository incidenteRepository;

	@Autowired
	TipoIncidenteRepository tipoIncidenteRepository;
	
	@Autowired
	SubTipoIncidenteRepository subTipoIncidenteRepository;

	@Autowired
	UsuarioRepository usuarioRepository;

	@Autowired
	FotosIncidenteRepository fotosIncidenteRepository;

	@Autowired
	IncidenteAccionRepository incidenteAccionRepository;

	@Autowired
	UbigeoRepository ubigeoRepository;

	@Autowired
	OficinaRegionalRepository oficinaRegionalRepository;

	@Override
	public IncidenteOut registrarIncidente(IncidenteInRO incidenteInRO, String token) {
		IncidenteOut incidenteOutRO = new IncidenteOut();
		try {
			Integer idUsuario = TokenUtils.getUserIdFromToken(token);
			Usuario usuario = usuarioRepository.obtenerUsuarioPorId(idUsuario);
			TipoIncidente tipoIncidente = tipoIncidenteRepository.obtenerTipoPorId(incidenteInRO.getIdTipo());
			SubTipoIncidente subTipoIncidente = subTipoIncidenteRepository.obtenerSubTipoPorId(incidenteInRO.getIdSubTipo());


			if (usuario == null) {
				incidenteOutRO.setResultCode(InvocationResult.FAILED.getCode());
				incidenteOutRO.setErrorCode(ErrorCode.NO_FOUND_ENTITY.getErrorCode());
				incidenteOutRO.setMessage("Usuario no encontrado.");
				return incidenteOutRO;
			}
			if (tipoIncidente == null) {
				incidenteOutRO.setResultCode(InvocationResult.FAILED.getCode());
				incidenteOutRO.setErrorCode(ErrorCode.NO_FOUND_ENTITY.getErrorCode());
				incidenteOutRO.setMessage("Tipo de incidente no encontrado.");
				return incidenteOutRO;
			}
			if (!tipoIncidente.getEstado().equals(Constantes.ESTADO_ACTIVO)) {
				incidenteOutRO.setResultCode(InvocationResult.FAILED.getCode());
				incidenteOutRO.setErrorCode(ErrorCode.NO_ACTIVE_ENTITY.getErrorCode());
				incidenteOutRO.setMessage("Tipo de incidente inactivo.");
				return incidenteOutRO;
			}
			
			if (subTipoIncidente == null) {
				incidenteOutRO.setResultCode(InvocationResult.FAILED.getCode());
				incidenteOutRO.setErrorCode(ErrorCode.NO_FOUND_ENTITY.getErrorCode());
				incidenteOutRO.setMessage("SubTipo de incidente no encontrado.");
				return incidenteOutRO;
			}
			
			if (!subTipoIncidente.getEstado().equals(Constantes.ESTADO_ACTIVO)) {
				incidenteOutRO.setResultCode(InvocationResult.FAILED.getCode());
				incidenteOutRO.setErrorCode(ErrorCode.NO_ACTIVE_ENTITY.getErrorCode());
				incidenteOutRO.setMessage("SubTipo de incidente inactivo.");
				return incidenteOutRO;
			}

			Incidente incidente = new Incidente();
			incidente.setTitulo(incidenteInRO.getTitulo());
			incidente.setComentario(incidenteInRO.getComentario());
			incidente.setIdUsuario(idUsuario);
			incidente.setIdTipo(incidenteInRO.getIdTipo());
			incidente.setIdSubTipo(incidenteInRO.getIdSubTipo());
			// incidente.setCodigo(incidenteInRO.getCodigo());
			incidente.setIdSector(incidenteInRO.getIdSector());
			incidente.setFechaIncidente(incidenteInRO.getFechaIncidente());
			incidente.setIdEstado(incidenteInRO.getIdEstado());
			incidente.setIdCriticidad(incidenteInRO.getIdCriticidad());
			incidente.setIdOficinaRegional(incidenteInRO.getIdOficinaRegional());
			incidente.setAfectacion(incidenteInRO.getAfectacion());
			incidente.setCausa(incidenteInRO.getCausa());
			incidente.setIdFuente(incidenteInRO.getIdFuente());
			incidente.setCoordenada(incidenteInRO.getCoordenada());
			
			if (incidenteInRO.getUbigeo() != null) {
				incidente.setUbigeo(incidenteInRO.getUbigeo());
				incidente.setIdOficinaRegional(incidenteInRO.getIdOficinaRegional());
			} else {
				Ubigeo ubigeo = ubigeoRepository.obtenerUbigeoPorCodigoDepaProvDist(incidenteInRO.getCodDepartamento(),
				incidenteInRO.getCodProvincia(), incidenteInRO.getCodDistrito());
				
				if (ubigeo != null) {
					incidente.setUbigeo(ubigeo.getCodUbigeo());
					if (ubigeo.getDescDepartamento().equals("LIMA")) {
						incidente.setUbigeo(null);
					} else {
						OficinaRegional oficinaRegional = oficinaRegionalRepository.obtenerOficinaPorNombre(ubigeo.getDescDepartamento());
						incidente.setIdOficinaRegional(oficinaRegional.getIdOficina());
					}
				} else {	
					incidente.setUbigeo(null);
					incidente.setIdOficinaRegional(null);
				}
			}
			
			incidente.setEstado("A");

			incidente.setUsuarioCreacion(usuario.getNombre());
			incidente = incidenteRepository.registrarIncidente(incidente);

			String generarCodigo = String.format("%0" + "8" + "d", incidente.getIdIncidente());

			// Actuliza incidente copn codigo, por el ID
			incidente.setCodigo(generarCodigo);
			incidenteRepository.actualizarCodigoIncidente(incidente.getIdIncidente(), generarCodigo);

			if (incidente != null && incidente.getIdIncidente() != null) {

				incidenteOutRO.setIdIncidente(incidente.getIdIncidente());
				incidenteOutRO.setCodigo(generarCodigo);
				incidenteOutRO.setFechaCreacion(incidente.getFechaCreacion());
				IncidenteAccion incidenteAccion = new IncidenteAccion();
				incidenteAccion.setIdIncidente(incidente.getIdIncidente());
				incidenteAccion.setEstado(Constantes.ESTADO_ACTIVO);
				incidenteAccion.setIdEstadoIncidente(incidente.getIdEstado());
				incidenteAccionRepository.guardar(incidenteAccion);

				incidenteOutRO.setResultCode(InvocationResult.SUCCESS.getCode());
				incidenteOutRO.setMessage("Se ha registrado el incidente correctamente");

			} else {
				incidenteOutRO.setResultCode(InvocationResult.FAILED.getCode());
				incidenteOutRO.setErrorCode(ErrorCode.ERROR.getErrorCode());
				incidenteOutRO.setMessage("Error al registrar incidente");
			}
		} catch (Exception e) {
			incidenteOutRO.setResultCode(InvocationResult.FAILED.getCode());
			incidenteOutRO.setErrorCode(ErrorCode.ERROR.getErrorCode());
			incidenteOutRO.setMessage("Error al registrar incidente");
		}
		return incidenteOutRO;
	}

	@Override
	public ListIncidenteOutRO listarPorUsuario(Integer idUsuario) {
		ListIncidenteOutRO listIncidenteOutRO = new ListIncidenteOutRO();
		try {
			List<IncidenteOutRO> listaOutRO = new ArrayList<>();
			List<Incidente> lista = incidenteRepository.listarPorUsuario(idUsuario);
			for (Incidente incidente : lista) {
				IncidenteOutRO incidenteOutRO = new IncidenteOutRO();
				incidenteOutRO.setIdIncidente(incidente.getIdIncidente());
				incidenteOutRO.setIdUsuario(incidente.getIdUsuario());
				incidenteOutRO.setTitulo(incidente.getTitulo());
				incidenteOutRO.setComentario(incidente.getComentario());
				listaOutRO.add(incidenteOutRO);
			}
			listIncidenteOutRO.setIncidentes(listaOutRO);
			listIncidenteOutRO.setResultCode(InvocationResult.SUCCESS.getCode());
		} catch (Exception e) {
			listIncidenteOutRO.setResultCode(InvocationResult.FAILED.getCode());
			listIncidenteOutRO.setErrorCode(ErrorCode.ERROR.getErrorCode());
			listIncidenteOutRO.setMessage("Error al listar incidentes por usuario");
		}
		return listIncidenteOutRO;
	}

	@Override
	public DetalleIncidenteOutRO detalleIncidente(Integer idIncidente) {
		DetalleIncidenteOutRO detalleIncidenteOutRO = new DetalleIncidenteOutRO();
		try {
			Incidente incidente = incidenteRepository.obtenerIncidenteId(idIncidente);
			if (incidente == null) {
				detalleIncidenteOutRO.setResultCode(InvocationResult.FAILED.getCode());
				detalleIncidenteOutRO.setErrorCode(ErrorCode.NO_FOUND_ENTITY.getErrorCode());
				detalleIncidenteOutRO.setMessage("Error al obtener detalle del incidente");
				return detalleIncidenteOutRO;
			}
			detalleIncidenteOutRO.setIdIncidente(incidente.getIdIncidente());
			detalleIncidenteOutRO.setIdUsuario(incidente.getIdUsuario());
			detalleIncidenteOutRO.setTitulo(incidente.getTitulo());
			detalleIncidenteOutRO.setComentario(incidente.getComentario());

			detalleIncidenteOutRO.setIdTipo(incidente.getIdTipo());
			detalleIncidenteOutRO.setIdSubTipo(incidente.getIdSubTipo());
			detalleIncidenteOutRO.setCodigo(incidente.getCodigo());
			detalleIncidenteOutRO.setIdSector(incidente.getIdSector());
			detalleIncidenteOutRO.setFechaIncidente(incidente.getFechaIncidente());
			detalleIncidenteOutRO.setIdEstado(incidente.getIdEstado());
			detalleIncidenteOutRO.setIdCriticidad(incidente.getIdCriticidad());
			detalleIncidenteOutRO.setAfectacion(incidente.getAfectacion());
			detalleIncidenteOutRO.setCausa(incidente.getCausa());
			detalleIncidenteOutRO.setIdFuente(incidente.getIdFuente());
			detalleIncidenteOutRO.setCoordenada(incidente.getCoordenada());
			if (incidente.getUbigeo() != null) {
				Ubigeo ubigeo = ubigeoRepository.obtenerUbigeoPorCodigo(incidente.getUbigeo());
				detalleIncidenteOutRO.setCodDepartamento(ubigeo.getCodDepartamento());
				detalleIncidenteOutRO.setCodProvincia(ubigeo.getCodProvincia());
				detalleIncidenteOutRO.setCodDistrito(ubigeo.getCodDistrito());
			}
			detalleIncidenteOutRO.setFechaCreacion(incidente.getFechaCreacion());
			detalleIncidenteOutRO.setFechaActualizacion(incidente.getFechaActualizacion());
			detalleIncidenteOutRO.setGrupo(incidente.getGrupo());
			detalleIncidenteOutRO.setUbigeo(incidente.getUbigeo());
			detalleIncidenteOutRO.setIdOficinaRegional(incidente.getIdOficinaRegional());
			
			detalleIncidenteOutRO.setEsPadre(incidente.getEsPadre());
			detalleIncidenteOutRO.setIdPadre(incidente.getIdPadre());

			detalleIncidenteOutRO.setResultCode(InvocationResult.SUCCESS.getCode());
		} catch (Exception e) {
			detalleIncidenteOutRO.setResultCode(InvocationResult.FAILED.getCode());
			detalleIncidenteOutRO.setErrorCode(ErrorCode.ERROR.getErrorCode());
			detalleIncidenteOutRO.setMessage("Error al obtener detalle del incidente");
		}
		return detalleIncidenteOutRO;
	}

	@Override
	public ListIncidenteOutRO listarPorTipo(Integer idTipo) {
		ListIncidenteOutRO listIncidenteOutRO = new ListIncidenteOutRO();
		try {
			List<IncidenteOutRO> listaOutRO = new ArrayList<>();
			List<Incidente> lista = incidenteRepository.listarPorTipo(idTipo);
			for (Incidente incidente : lista) {
				IncidenteOutRO incidenteOutRO = new IncidenteOutRO();
				incidenteOutRO.setIdIncidente(incidente.getIdIncidente());
				incidenteOutRO.setIdUsuario(incidente.getIdUsuario());
				incidenteOutRO.setTitulo(incidente.getTitulo());
				incidenteOutRO.setComentario(incidente.getComentario());
				incidenteOutRO.setCodigo(incidente.getCodigo());
				incidenteOutRO.setFechaRegistro(incidente.getFechaCreacion());
				listaOutRO.add(incidenteOutRO);
			}
			listIncidenteOutRO.setIncidentes(listaOutRO);
			listIncidenteOutRO.setResultCode(InvocationResult.SUCCESS.getCode());
		} catch (Exception e) {
			listIncidenteOutRO.setResultCode(InvocationResult.FAILED.getCode());
			listIncidenteOutRO.setErrorCode(ErrorCode.ERROR.getErrorCode());
			listIncidenteOutRO.setMessage("Error al listar incidentes por tipo");
		}
		return listIncidenteOutRO;
	}

	@Override
	public ListIncidenteOutRO listarPorPalabraClave(Integer vi_idSector, Integer vi_idTipoIncidencia,
			String vi_palabraClave) {
		// TODO Auto-generated method stub
		ListIncidenteOutRO listIncidenteOutRO = new ListIncidenteOutRO();
		try {
			List<IncidenteOutRO> listaOutRO = new ArrayList<>();
			List<Incidente> lista = incidenteRepository.listarPorPalabraClave(vi_idSector, vi_idTipoIncidencia,
					vi_palabraClave);

			for (Incidente incidente : lista) {
				IncidenteOutRO incidenteOutRO = new IncidenteOutRO();
				incidenteOutRO.setIdIncidente(incidente.getIdIncidente());
				incidenteOutRO.setIdUsuario(incidente.getIdUsuario());
				incidenteOutRO.setTitulo(incidente.getTitulo());
				incidenteOutRO.setComentario(incidente.getComentario());
				incidenteOutRO.setCodigo(incidente.getCodigo());
				incidenteOutRO.setFechaIncidente(incidente.getFechaIncidente());
				listaOutRO.add(incidenteOutRO);
			}
			listIncidenteOutRO.setIncidentes(listaOutRO);
			listIncidenteOutRO.setResultCode(InvocationResult.SUCCESS.getCode());
		} catch (Exception e) {
			listIncidenteOutRO.setResultCode(InvocationResult.FAILED.getCode());
			listIncidenteOutRO.setErrorCode(ErrorCode.ERROR.getErrorCode());
			listIncidenteOutRO.setMessage("Error al listar incidentes por tipo");
		}
		return listIncidenteOutRO;

	}

	@Override
	public CerrarIncidenteOutRO cerrarIncidente(Integer idIncidente, String token, String motivo) {
		CerrarIncidenteOutRO cerrarIncidenteOutRO = new CerrarIncidenteOutRO();
		try {
			Integer idUsuario = TokenUtils.getUserIdFromToken(token);
			Usuario usuario = usuarioRepository.obtenerUsuarioPorId(idUsuario);

			if (usuario == null) {
				cerrarIncidenteOutRO.setResultCode(InvocationResult.FAILED.getCode());
				cerrarIncidenteOutRO.setErrorCode(ErrorCode.NO_FOUND_ENTITY.getErrorCode());
				cerrarIncidenteOutRO.setMessage("Usuario no encontrado.");
				return cerrarIncidenteOutRO;
			}
			
			Incidente incidente = incidenteRepository.obtenerIncidenteId(idIncidente);

			if (incidente == null) {
				cerrarIncidenteOutRO.setResultCode(InvocationResult.FAILED.getCode());
				cerrarIncidenteOutRO.setErrorCode(ErrorCode.NO_FOUND_ENTITY.getErrorCode());
			} else {

				//Eliminar incidentes hijos
		        List<Incidente> lista = incidenteRepository.listarParaIntegrarV2(incidente.getIdSubTipo(), incidente.getUbigeo(), incidente.getIdIncidente());
		        for(Incidente inc : lista) {
		            if(inc.getIdPadre() == idIncidente){
		            	inc.setEstado("I");
		            	inc.setUsuarioActualizacion(usuario.getNombre());
		            	inc.setMotivoEliminacion(motivo);
		            	inc = incidenteRepository.actualizarIncidente(inc);
		            }
		        }
		        
				incidente.setEstado("I");
				incidente.setUsuarioActualizacion(usuario.getNombre());
				incidente.setMotivoEliminacion(motivo);
				incidente = incidenteRepository.actualizarIncidente(incidente);

				if (incidente == null) {
					cerrarIncidenteOutRO.setResultCode(InvocationResult.FAILED.getCode());
					cerrarIncidenteOutRO.setErrorCode(ErrorCode.ERROR.getErrorCode());
					cerrarIncidenteOutRO.setMessage("Error al cerrar incidente");
				} else {
					cerrarIncidenteOutRO.setResultCode(InvocationResult.SUCCESS.getCode());
					cerrarIncidenteOutRO.setMessage("Se ha cerrado correctamente");
				}
			}
		} catch (Exception e) {
			cerrarIncidenteOutRO.setResultCode(InvocationResult.FAILED.getCode());
			cerrarIncidenteOutRO.setErrorCode(ErrorCode.ERROR.getErrorCode());
			cerrarIncidenteOutRO.setMessage("Error al cerrar incidente");
		}
		return cerrarIncidenteOutRO;
	}

	@Override
	public IncidenteOut actualizarIncidente(IncidenteInRO incidenteInRO, String token) {
		IncidenteOut incidenteOutRO = new IncidenteOut();
		try {
			Integer idUsuario = TokenUtils.getUserIdFromToken(token);
			Usuario usuario = usuarioRepository.obtenerUsuarioPorId(idUsuario);
			TipoIncidente tipoIncidente = tipoIncidenteRepository.obtenerTipoPorId(incidenteInRO.getIdTipo());
			SubTipoIncidente subTipoIncidente = subTipoIncidenteRepository.obtenerSubTipoPorId(incidenteInRO.getIdSubTipo());

			if (usuario == null) {
				incidenteOutRO.setResultCode(InvocationResult.FAILED.getCode());
				incidenteOutRO.setErrorCode(ErrorCode.NO_FOUND_ENTITY.getErrorCode());
				incidenteOutRO.setMessage("Usuario no encontrado.");
				return incidenteOutRO;
			}
			if (tipoIncidente == null) {
				incidenteOutRO.setResultCode(InvocationResult.FAILED.getCode());
				incidenteOutRO.setErrorCode(ErrorCode.NO_FOUND_ENTITY.getErrorCode());
				incidenteOutRO.setMessage("Tipo de incidente no encontrado.");
				return incidenteOutRO;
			}
			if (!tipoIncidente.getEstado().equals(Constantes.ESTADO_ACTIVO)) {
				incidenteOutRO.setResultCode(InvocationResult.FAILED.getCode());
				incidenteOutRO.setErrorCode(ErrorCode.NO_ACTIVE_ENTITY.getErrorCode());
				incidenteOutRO.setMessage("Tipo de incidente inactivo.");
				return incidenteOutRO;
			}

			if (subTipoIncidente == null) {
				incidenteOutRO.setResultCode(InvocationResult.FAILED.getCode());
				incidenteOutRO.setErrorCode(ErrorCode.NO_FOUND_ENTITY.getErrorCode());
				incidenteOutRO.setMessage("SubTipo de incidente no encontrado.");
				return incidenteOutRO;
			}
			
			if (!subTipoIncidente.getEstado().equals(Constantes.ESTADO_ACTIVO)) {
				incidenteOutRO.setResultCode(InvocationResult.FAILED.getCode());
				incidenteOutRO.setErrorCode(ErrorCode.NO_ACTIVE_ENTITY.getErrorCode());
				incidenteOutRO.setMessage("SubTipo de incidente inactivo.");
				return incidenteOutRO;
			}
			
			Incidente incidente = new Incidente();
			incidente.setIdIncidente(incidenteInRO.getIdIncidente());
			incidente.setTitulo(incidenteInRO.getTitulo());
			incidente.setComentario(incidenteInRO.getComentario());
			incidente.setIdUsuario(idUsuario);
			incidente.setIdTipo(incidenteInRO.getIdTipo());
			incidente.setIdSubTipo(incidenteInRO.getIdSubTipo());
			// incidente.setCodigo(incidenteInRO.getCodigo());
			incidente.setIdSector(incidenteInRO.getIdSector());
			incidente.setFechaIncidente(incidenteInRO.getFechaIncidente());
			incidente.setIdEstado(incidenteInRO.getIdEstado());
			incidente.setIdCriticidad(incidenteInRO.getIdCriticidad());
			incidente.setAfectacion(incidenteInRO.getAfectacion());
			incidente.setCausa(incidenteInRO.getCausa());
			incidente.setIdFuente(incidenteInRO.getIdFuente());
			incidente.setCoordenada(incidenteInRO.getCoordenada());

			if (incidenteInRO.getUbigeo() != null) {
				incidente.setUbigeo(incidenteInRO.getUbigeo());
				incidente.setIdOficinaRegional(incidenteInRO.getIdOficinaRegional());
			} else {
				Ubigeo ubigeo = ubigeoRepository.obtenerUbigeoPorCodigoDepaProvDist(incidenteInRO.getCodDepartamento(),
				incidenteInRO.getCodProvincia(), incidenteInRO.getCodDistrito());
				
				if (ubigeo != null) {
					incidente.setUbigeo(ubigeo.getCodUbigeo());
					if (ubigeo.getDescDepartamento().equals("LIMA")) {
						incidente.setUbigeo(null);
					} else {
						OficinaRegional oficinaRegional = oficinaRegionalRepository.obtenerOficinaPorNombre(ubigeo.getDescDepartamento());
						incidente.setIdOficinaRegional(oficinaRegional.getIdOficina());
					}
				} else {	
					incidente.setUbigeo(null);
					incidente.setIdOficinaRegional(null);
				}
			}
			
			incidente.setMotivoEliminacion("");
			incidente.setUsuarioActualizacion(usuario.getNombre());

			incidente = incidenteRepository.actualizarIncidente(incidente);

			if (incidente != null && incidente.getIdIncidente() != null) {

				incidenteOutRO.setIdIncidente(incidente.getIdIncidente());
				incidenteOutRO.setCodigo(incidente.getCodigo());
				incidenteOutRO.setFechaCreacion(incidente.getFechaCreacion());
				incidenteOutRO.setFechaActualizacion(incidente.getFechaActualizacion());

				IncidenteAccion incidenteAccion = new IncidenteAccion();
				incidenteAccion.setIdIncidente(incidente.getIdIncidente());
				incidenteAccion.setEstado(Constantes.ESTADO_ACTIVO);
				incidenteAccion.setIdEstadoIncidente(incidente.getIdEstado());
				incidenteAccionRepository.guardar(incidenteAccion);

				incidenteOutRO.setResultCode(InvocationResult.SUCCESS.getCode());
				incidenteOutRO.setMessage("Se ha actualizado el incidente correctamente");

			} else {
				incidenteOutRO.setResultCode(InvocationResult.FAILED.getCode());
				incidenteOutRO.setErrorCode(ErrorCode.ERROR.getErrorCode());
				incidenteOutRO.setMessage("Error al actualizar incidente");
			}
		} catch (Exception e) {
			incidenteOutRO.setResultCode(InvocationResult.FAILED.getCode());
			incidenteOutRO.setErrorCode(ErrorCode.ERROR.getErrorCode());
			incidenteOutRO.setMessage("Error al actualizar incidente");
		}
		return incidenteOutRO;
	}

	@Override
	public ListIncidenteTablaDinamicaOutRo listarInciParaTablaDinamica() {
		// TODO Auto-generated method stub
		ListIncidenteTablaDinamicaOutRo listIncidenteTablaDinamicaOutRo = new ListIncidenteTablaDinamicaOutRo();
		try {
			List<IncidenteTablaDinamicaOutRo> lista = incidenteRepository.listarInciParaTablaDinamica();
			listIncidenteTablaDinamicaOutRo.setIncidentes(lista);
			listIncidenteTablaDinamicaOutRo.setResultCode(InvocationResult.SUCCESS.getCode());
		} catch (Exception e) {
			listIncidenteTablaDinamicaOutRo.setResultCode(InvocationResult.FAILED.getCode());
			listIncidenteTablaDinamicaOutRo.setErrorCode(ErrorCode.ERROR.getErrorCode());
			listIncidenteTablaDinamicaOutRo.setMessage("Error al listar incidentes por tipo");
		}
		return listIncidenteTablaDinamicaOutRo;
	}

	@Override
	public ListIncidenteIntegrarOutRO listarParaIntegrar(Integer idTipo, String ubigeo, Integer idIncidente) {
		ListIncidenteIntegrarOutRO listIncidenteIntegrarOutRO = new ListIncidenteIntegrarOutRO();
		try {
			List<IncidenteIntegrarOutRO> listaOutRO = new ArrayList<>();
			Incidente objIncidente = incidenteRepository.obtenerIncidenteId(idIncidente);
			if(objIncidente == null) {
				listIncidenteIntegrarOutRO.setIncidentes(null);
				listIncidenteIntegrarOutRO.setResultCode(InvocationResult.FAILED.getCode());
				listIncidenteIntegrarOutRO.setErrorCode(ErrorCode.NO_FOUND_ENTITY.getErrorCode());
				return listIncidenteIntegrarOutRO;
			}
			List<Incidente> lista = incidenteRepository.listarParaIntegrar(idTipo, ubigeo, idIncidente);
			String[] codIncidenciasRela = null;
			if(objIncidente.getGrupo() != null && objIncidente.getGrupo().length() > 0) {
				codIncidenciasRela = objIncidente.getGrupo().split(",");
			}
			
			for (Incidente incidente : lista) {

				IncidenteIntegrarOutRO incidenteOutRO = new IncidenteIntegrarOutRO();
				incidenteOutRO.setIdIncidente(incidente.getIdIncidente());
				incidenteOutRO.setTitulo(incidente.getTitulo());
				incidenteOutRO.setCodigo(incidente.getCodigo());
				
				if(codIncidenciasRela != null) {
					boolean presente = false;
					for (int i = 0; i < codIncidenciasRela.length; i++) {
						String id = codIncidenciasRela[i];
						Integer idInc = Integer.parseInt(id);
						if(idInc.equals(incidente.getIdIncidente())) {
							presente = true;
							break;
						}
					}
					if(!presente) {
						listaOutRO.add(incidenteOutRO);
					}
				}else {
					listaOutRO.add(incidenteOutRO);
				}
				
			}
			listIncidenteIntegrarOutRO.setIncidentes(listaOutRO);
			listIncidenteIntegrarOutRO.setResultCode(InvocationResult.SUCCESS.getCode());
		} catch (Exception e) {
			listIncidenteIntegrarOutRO.setResultCode(InvocationResult.FAILED.getCode());
			listIncidenteIntegrarOutRO.setErrorCode(ErrorCode.ERROR.getErrorCode());
			listIncidenteIntegrarOutRO.setMessage("Error al listar para integrar incidentes");
		}
		return listIncidenteIntegrarOutRO;
	}
	
	@Override
	public ListIncidenteIntegrarOutRO listarParaIntegrarV2(Integer idSubTipo, String ubigeo, Integer idIncidente) {
		ListIncidenteIntegrarOutRO listIncidenteIntegrarOutRO = new ListIncidenteIntegrarOutRO();
		try {
			List<IncidenteIntegrarOutRO> listaOutRO = new ArrayList<>();
			Incidente objIncidenteActual = incidenteRepository.obtenerIncidenteId(idIncidente);
			if(objIncidenteActual == null) {
				listIncidenteIntegrarOutRO.setIncidentes(null);
				listIncidenteIntegrarOutRO.setResultCode(InvocationResult.FAILED.getCode());
				listIncidenteIntegrarOutRO.setErrorCode(ErrorCode.NO_FOUND_ENTITY.getErrorCode());
				return listIncidenteIntegrarOutRO;
			}
			List<Incidente> lista = incidenteRepository.listarParaIntegrarV2(idSubTipo, ubigeo, idIncidente);
			
			for (Incidente incidente : lista) {
				System.out.println("Incidente> "+incidente.getIdIncidente());
				System.out.println("Incidente2> "+incidente.getTitulo());
				System.out.println("Incidente3> "+incidente.getCodigo());
				
				IncidenteIntegrarOutRO incidenteOutRO = new IncidenteIntegrarOutRO();
				incidenteOutRO.setIdIncidente(incidente.getIdIncidente());
				incidenteOutRO.setTitulo(incidente.getTitulo());
				incidenteOutRO.setCodigo(incidente.getCodigo());
				incidenteOutRO.setIdPadre(incidente.getIdPadre());
				incidenteOutRO.setEsPadre(incidente.getEsPadre());
		
				
				if(incidente.getIdPadre() != null){//Integrado
					incidenteOutRO.setIntegrado(1);
				}else if(objIncidenteActual.getEsPadre() == null || objIncidenteActual.getEsPadre() != 1){//No es padre
		            if(objIncidenteActual.getIdPadre() == null){
		            	//No tiene padre/No es hijo
			                if (incidente.getEsPadre()  != null && incidente.getEsPadre() == 1) { // si incidente de la lista ES padre
			                	incidenteOutRO.setIntegrado(0);
			                }else if(incidente.getIdPadre() == null){ //Si incidente de la lista NO tiene padre aun
			                	incidenteOutRO.setIntegrado(0);
			                }

		            }else{
		              //Tiene padre
		                if (incidente.getIdIncidente()  != objIncidenteActual.getIdPadre()) {
		                	//incidente de la lista NO ES el padre del incidente mostrado en la pantalla principal
			                   if(incidente.getIdPadre() == null || incidente.getIdPadre() != objIncidenteActual.getIdPadre()){
			                    //Si incidente de la lista, aun NO tiene padre
			                	   incidenteOutRO.setIntegrado(0);
			                   }
		                }else {
		                	incidenteOutRO.setIntegrado(1);
		                }
		            }
			    }else{ //Incidente actual SI es padre
			           if (incidente.getIdPadre() == null || incidente.getIdPadre() != objIncidenteActual.getIdIncidente()) {
			                incidenteOutRO.setIntegrado(0);
			           }
			    }
				
				
				listaOutRO.add(incidenteOutRO);
								
			}
			listIncidenteIntegrarOutRO.setIncidentes(listaOutRO);
			listIncidenteIntegrarOutRO.setResultCode(InvocationResult.SUCCESS.getCode());
		} catch (Exception e) {
			listIncidenteIntegrarOutRO.setResultCode(InvocationResult.FAILED.getCode());
			listIncidenteIntegrarOutRO.setErrorCode(ErrorCode.ERROR.getErrorCode());
			listIncidenteIntegrarOutRO.setMessage("Error al listar para integrar incidentes");
		}
		return listIncidenteIntegrarOutRO;
	}

	@Override
	public BaseOutRO integrarIncidentes(String codigoIncidencias, String token) {
		BaseOutRO baseOutRO = new BaseOutRO();
		try {
			
			Integer idUsuario = TokenUtils.getUserIdFromToken(token);
			Usuario usuario = usuarioRepository.obtenerUsuarioPorId(idUsuario);
			if (usuario == null) {
				baseOutRO.setResultCode(InvocationResult.FAILED.getCode());
				baseOutRO.setErrorCode(ErrorCode.NO_FOUND_ENTITY.getErrorCode());
				baseOutRO.setMessage("Usuario no encontrado.");
				return baseOutRO;
			}

			String[] ids = codigoIncidencias.split(",");

			for (String id : ids) {
				Incidente incidente = incidenteRepository.obtenerIncidenteId(Integer.parseInt(id));
				if (incidente == null) {
					baseOutRO.setResultCode(InvocationResult.FAILED.getCode());
					baseOutRO.setErrorCode(ErrorCode.NO_FOUND_ENTITY.getErrorCode());
					break;
				} else {
					incidente.setMotivoEliminacion("");
					incidente.setGrupo(codigoIncidencias);
					incidente.setUsuarioActualizacion(usuario.getNombre());
					incidente = incidenteRepository.actualizarIncidente(incidente);
				}
			}

			baseOutRO.setResultCode(InvocationResult.SUCCESS.getCode());
			baseOutRO.setMessage("Se ha integrado correctamente");

		} catch (Exception e) {
			baseOutRO.setResultCode(InvocationResult.FAILED.getCode());
			baseOutRO.setErrorCode(ErrorCode.ERROR.getErrorCode());
			baseOutRO.setMessage("Error al integrar incidentes");
		}
		return baseOutRO;
	}
	
	@Override
	public BaseOutRO integrarIncidentesV2(IntegrarIncidenteInRO integrarIncidenteInRO, String token) {
		BaseOutRO baseOutRO = new BaseOutRO();
		try {
			
			Integer idUsuario = TokenUtils.getUserIdFromToken(token);
			Usuario usuario = usuarioRepository.obtenerUsuarioPorId(idUsuario);
			if (usuario == null) {
				baseOutRO.setResultCode(InvocationResult.FAILED.getCode());
				baseOutRO.setErrorCode(ErrorCode.NO_FOUND_ENTITY.getErrorCode());
				baseOutRO.setMessage("Usuario no encontrado.");
				return baseOutRO;
			}
				int procesoOk=0;
				Incidente incidenteSeleccionado = incidenteRepository.obtenerIncidenteId(integrarIncidenteInRO.getIdIncidenteSeleccionado());//seleccionado en la pantalla principal
				Incidente incidenteAIntegrar = incidenteRepository.obtenerIncidenteId(integrarIncidenteInRO.getIdIncidenteAIntegrar());//El del combobox
				if (incidenteSeleccionado == null || incidenteAIntegrar == null) {
					baseOutRO.setResultCode(InvocationResult.FAILED.getCode());
					baseOutRO.setErrorCode(ErrorCode.NO_FOUND_ENTITY.getErrorCode());
				} else {
					
					int idIncidenteSeleccionado = integrarIncidenteInRO.getIdIncidenteSeleccionado();
					int idIncidenteAIntegrar = integrarIncidenteInRO.getIdIncidenteAIntegrar();
				    int idIncidenteP = 0;
				    int idIncidenteH = 0;
				    int integrarAmbos = 0;
				    int actualizaPadre = 0;

				      if(incidenteSeleccionado.getEsPadre() !=null && incidenteSeleccionado.getEsPadre() == 1){  
				    	//Si incidente selecionado en la pantalla principal es padre
				        idIncidenteP= idIncidenteSeleccionado;
				        idIncidenteH= idIncidenteAIntegrar;
				      }else {
				    	  //Si incidente seleccionado en la pantalla principal, NO es padre
				    	  
				    	  if(incidenteSeleccionado.getIdPadre() !=null){ 
						    	//Si incidente selecionado en la pantalla principal tiene padre
						        idIncidenteP= incidenteSeleccionado.getIdPadre();
						        idIncidenteH= idIncidenteAIntegrar;
						  }else{
							  //No es padre, No es hijo
							  
							  if(incidenteAIntegrar.getEsPadre() !=null && incidenteAIntegrar.getEsPadre() == 1){//Incidente a integrar es Padre  
							        idIncidenteP= idIncidenteAIntegrar;
							        idIncidenteH= idIncidenteSeleccionado;
							  }else if (incidenteAIntegrar.getIdPadre() !=null){//Incidente a integrar SI TIENE padre
								  idIncidenteP= incidenteAIntegrar.getIdPadre();
					              idIncidenteH= idIncidenteSeleccionado;
							  }else {
								  //Incidente a integrar> No es padre, No es hijo
								  
								  int idPadre = 0;
						          List<Incidente> lista = incidenteRepository.listarParaIntegrarV2(incidenteSeleccionado.getIdSubTipo(), incidenteSeleccionado.getUbigeo(), incidenteSeleccionado.getIdIncidente());
						          //Verifica si alguno de los otros incidentes con los q guarda relacion, ya es padre
						          for(Incidente inc : lista) {
						            if(inc.getEsPadre() != null &&  inc.getEsPadre() ==1){
						              idPadre = inc.getIdIncidente();
						              break;
						            }
						          }
						          
						          if(idPadre != 0){ 
							            //Alguno de los incidentes con los que guarda relacion, ya es padre. Toma ese idPadre y lo asigna a ambos
							        idIncidenteP= idPadre;
							        idIncidenteH= idIncidenteAIntegrar;
							        integrarAmbos= 1;
							      }else{
							            //Ninguna integracion.
							    	  idIncidenteP= idIncidenteSeleccionado;
							          idIncidenteH= idIncidenteAIntegrar;
							          actualizaPadre = 1;
							          }
						        
							  }
							  	
						  }
				    	  
				      }
				     			 
				  
				   if(idIncidenteP != 0 && idIncidenteH != 0) {
				      if(actualizaPadre == 0) {
				    	  
				    	  if(integrarAmbos == 0) {
				    	  
					    	  if(idIncidenteH == idIncidenteAIntegrar) {
							      incidenteAIntegrar = asignaPadreAIncidente(incidenteAIntegrar, idIncidenteP, usuario.getNombre());
							      if(incidenteAIntegrar != null) {
							    	  procesoOk = 1;
						    	  }
					    	  }else {
						    	  incidenteSeleccionado = asignaPadreAIncidente(incidenteSeleccionado, idIncidenteP, usuario.getNombre());
						    	  if(incidenteSeleccionado != null) {
							    	  procesoOk = 1;
						    	  }
					    	  }
				    	  }else {
				    		  
						      incidenteAIntegrar = asignaPadreAIncidente(incidenteAIntegrar, idIncidenteP, usuario.getNombre());
						      if(incidenteAIntegrar != null) {
						    	  procesoOk = 1;
						    	  incidenteSeleccionado = asignaPadreAIncidente(incidenteSeleccionado, idIncidenteP, usuario.getNombre());
					    	  }
				    	  }
				      }else {
				    	  incidenteSeleccionado.setEsPadre(1);
				    	  incidenteSeleccionado.setIdPadre(null);
				    	  incidenteSeleccionado.setUsuarioActualizacion(usuario.getNombre());
				    	  incidenteSeleccionado = incidenteRepository.actualizarIncidente(incidenteSeleccionado);
				    	  
				    	  if(incidenteSeleccionado != null) {
				    		  incidenteAIntegrar = asignaPadreAIncidente(incidenteAIntegrar, idIncidenteP, usuario.getNombre());
						      
						      if(incidenteAIntegrar != null) {
						    	  procesoOk=1;
					    	  }else {
					    		  incidenteSeleccionado.setEsPadre(null);
					    		  incidenteSeleccionado.setIdPadre(null);
						    	  incidenteSeleccionado.setUsuarioActualizacion(usuario.getNombre());
						    	  incidenteSeleccionado = incidenteRepository.actualizarIncidente(incidenteSeleccionado);
						    	  procesoOk=0;
					    	  }
				    	  }
				      }
				      
				   }else {
					   baseOutRO.setResultCode(InvocationResult.FAILED.getCode());
					   baseOutRO.setErrorCode(ErrorCode.NO_FOUND_ENTITY.getErrorCode());
					   baseOutRO.setMessage("No se pudo asignar el incidente padre");
				   }
			    }

	
			if(procesoOk == 1) {
				baseOutRO.setResultCode(InvocationResult.SUCCESS.getCode());
				baseOutRO.setMessage("Se ha integrado correctamente");
			}else {
				baseOutRO.setResultCode(InvocationResult.FAILED.getCode());
				baseOutRO.setErrorCode(ErrorCode.ERROR.getErrorCode());
				baseOutRO.setMessage("Error al integrar incidentes");
			}

		} catch (Exception e) {
			baseOutRO.setResultCode(InvocationResult.FAILED.getCode());
			baseOutRO.setErrorCode(ErrorCode.ERROR.getErrorCode());
			baseOutRO.setMessage("Error al integrar incidentes");
		}
		return baseOutRO;
	}
	
	private Incidente asignaPadreAIncidente(Incidente incidente, int idIncidenteP, String usuario) {
		
		incidente.setIdPadre(idIncidenteP);
		incidente.setEsPadre(null);
		incidente.setUsuarioActualizacion(usuario);
		incidente = incidenteRepository.actualizarIncidente(incidente);
		
		return incidente;
	}

	@Override
	public ListIncidenteOutRO ObtenerIncidentesPor(String idSector, String idTipo, String idSubTipo, String idDep, String idProv, String idDist) {

		ListIncidenteOutRO listIncidenteOutRO = new ListIncidenteOutRO();
		List<IncidenteOutRO> listaOutRO = new ArrayList<>();
		List<Incidente> lista =new ArrayList<>();
		
		try {
			
			if(!idSector.equals("-") && !idDep.equals("-")) {
				//Indica que se ha seleccionado el bloque de sector y el bloque de departamento
				
				if(!idSubTipo.equals("-")) {
					if(!idDist.equals("-")) {
						lista = incidenteRepository.listarPorDistYSubtipoInc(idDep,idProv,idDist,Integer.parseInt(idSubTipo));
					}else if(!idProv.equals("-")) {
						lista = incidenteRepository.listarPorDistYSubtipoInc(idDep,idProv,Integer.parseInt(idSubTipo));
					}else {
						lista = incidenteRepository.listarPorDistYSubtipoInc(idDep,Integer.parseInt(idSubTipo));
					}
					
				}else if(!idTipo.equals("-")) {//Si el tipo de incidencia no es nulo
					
					if(!idDist.equals("-")) {
						lista = incidenteRepository.listarPorDistYtipoInc(idDep,idProv,idDist,Integer.parseInt(idTipo));
					}else if(!idProv.equals("-")) {
						lista = incidenteRepository.listarPorProvYtipoInc(idDep,idProv,Integer.parseInt(idTipo));
					}else {
						lista = incidenteRepository.listarPorDepYtipoInc(idDep,Integer.parseInt(idTipo));
					}
					
				}else { //Si el tipo de incidencia es nulo, indica que solo se ha marcado el sector
					
					if(!idDist.equals("-")) {
						lista = incidenteRepository.listarPorDistYsectorInc(idDep,idProv,idDist,Integer.parseInt(idSector));
					}else if(!idProv.equals("-")) {
						lista = incidenteRepository.listarPorProvYsectorInc(idDep,idProv,Integer.parseInt(idSector));
					}else {
						lista = incidenteRepository.listarPorDepYsectorInc(idDep,Integer.parseInt(idSector));
					}
				}
				
				
			}else if(!idSector.equals("-")) {
				//inidica que solo se ha seleccionado el bloque del sector
				
				if(!idSubTipo.equals("-")) {
					lista = incidenteRepository.listarPorSubTipo(Integer.parseInt(idSubTipo));
				}else if(!idTipo.equals("-")) {
					lista = incidenteRepository.listarPorTipo(Integer.parseInt(idTipo));
				}else {
					lista = incidenteRepository.listarPorSector(Integer.parseInt(idSector));
				}
				
			}else if(!idDep.equals("-")) {
				//Indica q solo se ha selecciona el bloque de Departamento
				
				if(!idDist.equals("-")) {
					lista = incidenteRepository.listarPorDist(idDep,idProv,idDist);
				}else if(!idProv.equals("-")) {
					lista = incidenteRepository.listarPorProv(idDep, idProv);
				}else {
					lista = incidenteRepository.listarPorDep(idDep);
				}
			}else {
				
				listIncidenteOutRO.setResultCode(InvocationResult.SUCCESS.getCode());
			}
			
			
			if(lista.size()>0) {
			    for (Incidente incidente : lista) {
				IncidenteOutRO incidenteOutRO = new IncidenteOutRO();
				incidenteOutRO.setIdIncidente(incidente.getIdIncidente());
				incidenteOutRO.setIdUsuario(incidente.getIdUsuario());
				incidenteOutRO.setTitulo(incidente.getTitulo());
				incidenteOutRO.setComentario(incidente.getComentario());
				incidenteOutRO.setCodigo(incidente.getCodigo());
				incidenteOutRO.setFechaRegistro(incidente.getFechaCreacion());
				listaOutRO.add(incidenteOutRO);
			    }
			    
			    listIncidenteOutRO.setIncidentes(listaOutRO);
				listIncidenteOutRO.setResultCode(InvocationResult.SUCCESS.getCode());
			}else {
				listIncidenteOutRO.setResultCode(InvocationResult.SUCCESS.getCode());
			}
			    
			    
			
		} catch (Exception e) {
			listIncidenteOutRO.setResultCode(InvocationResult.FAILED.getCode());
			listIncidenteOutRO.setErrorCode(ErrorCode.ERROR.getErrorCode());
			listIncidenteOutRO.setMessage("Error al listar incidentes por parametro");
		}
		return listIncidenteOutRO;

	}

	@Override
	public ListIncidenteOutRO listarPorSubTipo(Integer idSubTipo) {
		ListIncidenteOutRO listIncidenteOutRO = new ListIncidenteOutRO();
		try {
			List<IncidenteOutRO> listaOutRO = new ArrayList<>();
			List<Incidente> lista = incidenteRepository.listarPorSubTipo(idSubTipo);
			for (Incidente incidente : lista) {
				IncidenteOutRO incidenteOutRO = new IncidenteOutRO();
				incidenteOutRO.setIdIncidente(incidente.getIdIncidente());
				incidenteOutRO.setIdUsuario(incidente.getIdUsuario());
				incidenteOutRO.setTitulo(incidente.getTitulo());
				incidenteOutRO.setComentario(incidente.getComentario());
				incidenteOutRO.setCodigo(incidente.getCodigo());
				incidenteOutRO.setFechaRegistro(incidente.getFechaCreacion());
				listaOutRO.add(incidenteOutRO);
			}
			listIncidenteOutRO.setIncidentes(listaOutRO);
			listIncidenteOutRO.setResultCode(InvocationResult.SUCCESS.getCode());
		} catch (Exception e) {
			listIncidenteOutRO.setResultCode(InvocationResult.FAILED.getCode());
			listIncidenteOutRO.setErrorCode(ErrorCode.ERROR.getErrorCode());
			listIncidenteOutRO.setMessage("Error al listar incidentes por tipo");
		}
		return listIncidenteOutRO;
	}

}
