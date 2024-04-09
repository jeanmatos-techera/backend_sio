package pe.gob.osinergmin.sio.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pe.gob.osinergmin.sio.enums.CriticidadIncidente;
import pe.gob.osinergmin.sio.enums.ErrorCode;
import pe.gob.osinergmin.sio.enums.InvocationResult;
import pe.gob.osinergmin.sio.ro.in.IncidenteInRO;
import pe.gob.osinergmin.sio.ro.in.IntegrarIncidenteInRO;
import pe.gob.osinergmin.sio.ro.out.BaseOutRO;
import pe.gob.osinergmin.sio.ro.out.CerrarIncidenteOutRO;
import pe.gob.osinergmin.sio.ro.out.DetalleIncidenteOutRO;
import pe.gob.osinergmin.sio.ro.out.IncidenteOut;
import pe.gob.osinergmin.sio.ro.out.ListCriticidadIncidenteOutRO;
import pe.gob.osinergmin.sio.ro.out.ListDetalleSectorOutRO;
import pe.gob.osinergmin.sio.ro.out.ListEstadoIncidenteOutRO;
import pe.gob.osinergmin.sio.ro.out.ListFuenteInformacionOutRO;
import pe.gob.osinergmin.sio.ro.out.ListIncidenteIntegrarOutRO;
import pe.gob.osinergmin.sio.ro.out.ListIncidenteOutRO;
import pe.gob.osinergmin.sio.ro.out.ListIncidenteTablaDinamicaOutRo;
import pe.gob.osinergmin.sio.ro.out.ListSectorOutRO;
import pe.gob.osinergmin.sio.ro.out.ListTipoIncidenteOutRO;
import pe.gob.osinergmin.sio.service.EstadoIncidenteService;
import pe.gob.osinergmin.sio.service.FuenteInformacionService;
import pe.gob.osinergmin.sio.service.IncidenteService;
import pe.gob.osinergmin.sio.service.SectorService;
import pe.gob.osinergmin.sio.service.TipoIncidenteService;
import pe.gob.osinergmin.sio.util.Validador;
import pe.gob.osinergmin.sio.util.ValidadorBuilder;

@RestController
@RequestMapping("/incidente")
public class IncidenteController {

	private static Logger logger = LoggerFactory.getLogger(IncidenteController.class);

	@Autowired
	private SectorService sectorService;

	@Autowired
	private TipoIncidenteService tipoIncidenteService;

	@Autowired
	private EstadoIncidenteService estadoIncidenteService;

	@Autowired
	private IncidenteService incidenteService;

	@Autowired
	private FuenteInformacionService fuenteInformacionService;

	@GetMapping("/detalleSector")
	public ListDetalleSectorOutRO listarDetalleSectores() {
		ListDetalleSectorOutRO listDetalleSectorOutRO = new ListDetalleSectorOutRO();
		try {
			listDetalleSectorOutRO = sectorService.listarDetalleSectores();
		} catch (Exception e) {
			logger.error(e.getMessage());
			listDetalleSectorOutRO.setResultCode(InvocationResult.FAILED.getCode());
			listDetalleSectorOutRO.setErrorCode(ErrorCode.ERROR.getErrorCode());
			listDetalleSectorOutRO.setMessage("Error al listar detalle de sectores");
		}
		return listDetalleSectorOutRO;
	}

	@GetMapping("/tipo/{idTipo}")
	public ListIncidenteOutRO listarIncidentesPorTipo(@PathVariable("idTipo") Integer idTipo) {
		ListIncidenteOutRO listIncidenteOutRO = new ListIncidenteOutRO();
		try {
			listIncidenteOutRO = incidenteService.listarPorTipo(idTipo);
		} catch (Exception e) {
			logger.error(e.getMessage());
			listIncidenteOutRO.setResultCode(InvocationResult.FAILED.getCode());
			listIncidenteOutRO.setErrorCode(ErrorCode.ERROR.getErrorCode());
			listIncidenteOutRO.setMessage("Error al listar incidentes por tipo");
		}
		return listIncidenteOutRO;
	}
	
	@GetMapping("/subtipo/{idSubTipo}")
	public ListIncidenteOutRO listarIncidentesPorSubTipo(@PathVariable("idSubTipo") Integer idSubTipo) {
		ListIncidenteOutRO listIncidenteOutRO = new ListIncidenteOutRO();
		try {
			listIncidenteOutRO = incidenteService.listarPorSubTipo(idSubTipo);
		} catch (Exception e) {
			logger.error(e.getMessage());
			listIncidenteOutRO.setResultCode(InvocationResult.FAILED.getCode());
			listIncidenteOutRO.setErrorCode(ErrorCode.ERROR.getErrorCode());
			listIncidenteOutRO.setMessage("Error al listar incidentes por tipo");
		}
		return listIncidenteOutRO;
	}

	@GetMapping("/sectores")
	public ListSectorOutRO listarSectores() {
		ListSectorOutRO listSectorOutRO = new ListSectorOutRO();
		try {
			listSectorOutRO = sectorService.listarSectores();
		} catch (Exception e) {
			logger.error(e.getMessage());
			listSectorOutRO.setResultCode(InvocationResult.FAILED.getCode());
			listSectorOutRO.setErrorCode(ErrorCode.ERROR.getErrorCode());
			listSectorOutRO.setMessage("Error al listar sectores");
		}
		return listSectorOutRO;
	}

	@GetMapping("/tiposIncidente/{idSector}")
	public ListTipoIncidenteOutRO listarTiposIncidentesPorSector(@PathVariable("idSector") Integer idSector) {
		ListTipoIncidenteOutRO listTipoIncidenteOutRO = new ListTipoIncidenteOutRO();
		try {
			listTipoIncidenteOutRO = tipoIncidenteService.listarPorSector(idSector);
		} catch (Exception e) {
			logger.error(e.getMessage());
			listTipoIncidenteOutRO.setResultCode(InvocationResult.FAILED.getCode());
			listTipoIncidenteOutRO.setErrorCode(ErrorCode.ERROR.getErrorCode());
			listTipoIncidenteOutRO.setMessage("Error al listar tipos de incidentes por sector");
		}
		return listTipoIncidenteOutRO;
	}

	@GetMapping("/estados")
	public ListEstadoIncidenteOutRO listarEstados() {
		ListEstadoIncidenteOutRO listEstadoIncidenteOutRO = new ListEstadoIncidenteOutRO();
		try {
			listEstadoIncidenteOutRO = estadoIncidenteService.listarEstados();
		} catch (Exception e) {
			logger.error(e.getMessage());
			listEstadoIncidenteOutRO.setResultCode(InvocationResult.FAILED.getCode());
			listEstadoIncidenteOutRO.setErrorCode(ErrorCode.ERROR.getErrorCode());
			listEstadoIncidenteOutRO.setMessage("Error al listar los estados");
		}
		return listEstadoIncidenteOutRO;
	}

	@GetMapping("/criticidades")
	public ListCriticidadIncidenteOutRO listarCriticidades() {
		ListCriticidadIncidenteOutRO listCriticidadOutRO = new ListCriticidadIncidenteOutRO();
		try {
			listCriticidadOutRO.setCriticidades(CriticidadIncidente.obtener());
			listCriticidadOutRO.setResultCode(InvocationResult.SUCCESS.getCode());
		} catch (Exception e) {
			logger.error(e.getMessage());
			listCriticidadOutRO.setResultCode(InvocationResult.FAILED.getCode());
			listCriticidadOutRO.setErrorCode(ErrorCode.ERROR.getErrorCode());
			listCriticidadOutRO.setMessage("Error al listar las criticidades");
		}
		return listCriticidadOutRO;
	}

	@GetMapping("/fuentes")
	public ListFuenteInformacionOutRO listarFuentesInformacion() {
		ListFuenteInformacionOutRO listFuenteInformacionOutRO = new ListFuenteInformacionOutRO();
		try {
			listFuenteInformacionOutRO = fuenteInformacionService.listarFuentesInformacion();
		} catch (Exception e) {
			logger.error(e.getMessage());
			listFuenteInformacionOutRO.setResultCode(InvocationResult.FAILED.getCode());
			listFuenteInformacionOutRO.setErrorCode(ErrorCode.ERROR.getErrorCode());
			listFuenteInformacionOutRO.setMessage("Error al listar las fuentes de información");
		}
		return listFuenteInformacionOutRO;
	}

	@PostMapping("/registrar")
	public IncidenteOut registrar(@RequestBody IncidenteInRO incidenteInRO,
			@RequestHeader("Authorization") String authorizationHeader) {
		IncidenteOut incidenteOutRO = new IncidenteOut();
		try {
			incidenteInRO.setTitulo(incidenteInRO.getTitulo() == null ? "" : incidenteInRO.getTitulo());

			Validador validador = ValidadorBuilder.builder().caracteres(incidenteInRO.getTitulo(), authorizationHeader)
					.numeros(String.valueOf(incidenteInRO.getIdTipo())).validar().build();

			if (!validador.esValido()) {
				incidenteOutRO.setResultCode(InvocationResult.FAILED.getCode());
				incidenteOutRO.setErrorCode(ErrorCode.INCORRECT_FIELD_VALUE.getErrorCode());
				incidenteOutRO
						.setMessage(ErrorCode.getMessageByErrorCode(ErrorCode.INCORRECT_FIELD_VALUE.getErrorCode()));
				return incidenteOutRO;
			}

			incidenteOutRO = incidenteService.registrarIncidente(incidenteInRO, authorizationHeader.substring(7));
		} catch (Exception e) {
			logger.error(e.getMessage());
			incidenteOutRO.setResultCode(InvocationResult.FAILED.getCode());
			incidenteOutRO.setErrorCode(ErrorCode.ERROR.getErrorCode());
			incidenteOutRO.setMessage("Error al registrar incidente");
		}
		return incidenteOutRO;
	}

	@GetMapping("/usuario/{idUsuario}")
	public ListIncidenteOutRO listarPorUsuario(@PathVariable("idUsuario") Integer idUsuario) {
		ListIncidenteOutRO listIncidenteOutRO = new ListIncidenteOutRO();
		try {
			Validador validador = ValidadorBuilder.builder().numeros(String.valueOf(idUsuario)).validar().build();

			if (!validador.esValido()) {
				listIncidenteOutRO.setResultCode(InvocationResult.FAILED.getCode());
				listIncidenteOutRO.setErrorCode(ErrorCode.INCORRECT_FIELD_VALUE.getErrorCode());
				listIncidenteOutRO
						.setMessage(ErrorCode.getMessageByErrorCode(ErrorCode.INCORRECT_FIELD_VALUE.getErrorCode()));
				return listIncidenteOutRO;
			}

			listIncidenteOutRO = incidenteService.listarPorUsuario(idUsuario);
		} catch (Exception e) {
			logger.error(e.getMessage());
			listIncidenteOutRO.setResultCode(InvocationResult.FAILED.getCode());
			listIncidenteOutRO.setErrorCode(ErrorCode.ERROR.getErrorCode());
			listIncidenteOutRO.setMessage("Error al listar incidentes por usuario");
		}
		return listIncidenteOutRO;
	}

	@GetMapping("/detalle/{idIncidente}")
	public DetalleIncidenteOutRO detalleIncidente(@PathVariable("idIncidente") Integer idIncidente) {
		DetalleIncidenteOutRO detalleIncidenteOutRO = new DetalleIncidenteOutRO();
		try {
			Validador validador = ValidadorBuilder.builder().numeros(String.valueOf(idIncidente)).validar().build();

			if (!validador.esValido()) {
				detalleIncidenteOutRO.setResultCode(InvocationResult.FAILED.getCode());
				detalleIncidenteOutRO.setErrorCode(ErrorCode.INCORRECT_FIELD_VALUE.getErrorCode());
				detalleIncidenteOutRO
						.setMessage(ErrorCode.getMessageByErrorCode(ErrorCode.INCORRECT_FIELD_VALUE.getErrorCode()));
				return detalleIncidenteOutRO;
			}

			detalleIncidenteOutRO = incidenteService.detalleIncidente(idIncidente);
		} catch (Exception e) {
			logger.error(e.getMessage());
			detalleIncidenteOutRO.setResultCode(InvocationResult.FAILED.getCode());
			detalleIncidenteOutRO.setErrorCode(ErrorCode.ERROR.getErrorCode());
			detalleIncidenteOutRO.setMessage("Error al obtener detalle de incidente");
		}
		return detalleIncidenteOutRO;
	}

	@GetMapping("/palabra/{idSector}/{idTipoIncidencia}/{palabraClave}")
	public ListIncidenteOutRO listarPorPalabraClave(@PathVariable("idSector") Integer idSector,
			@PathVariable("idTipoIncidencia") Integer idTipoIncidencia,
			@PathVariable("palabraClave") String palabraClave) {
		ListIncidenteOutRO listIncidenteOutRO = new ListIncidenteOutRO();
		try {
			Validador validador = ValidadorBuilder.builder()
					.numeros(String.valueOf(idSector), String.valueOf(idTipoIncidencia)).validar().build();

			if (!validador.esValido()) {
				listIncidenteOutRO.setResultCode(InvocationResult.FAILED.getCode());
				listIncidenteOutRO.setErrorCode(ErrorCode.INCORRECT_FIELD_VALUE.getErrorCode());
				listIncidenteOutRO
						.setMessage(ErrorCode.getMessageByErrorCode(ErrorCode.INCORRECT_FIELD_VALUE.getErrorCode()));
				return listIncidenteOutRO;
			}

			listIncidenteOutRO = incidenteService.listarPorPalabraClave(idSector, idTipoIncidencia, palabraClave);
		} catch (Exception e) {
			logger.error(e.getMessage());
			listIncidenteOutRO.setResultCode(InvocationResult.FAILED.getCode());
			listIncidenteOutRO.setErrorCode(ErrorCode.ERROR.getErrorCode());
			listIncidenteOutRO.setMessage("Error al listar incidentes por usuario");
		}
		return listIncidenteOutRO;
	}

	@PostMapping("/cerrar")
	public CerrarIncidenteOutRO cerrarIncidente(@RequestBody IncidenteInRO incidenteInRO,
			@RequestHeader("Authorization") String authorizationHeader) {
		CerrarIncidenteOutRO cerrarIncidenteOutRO = new CerrarIncidenteOutRO();
		try {
			Validador validador = ValidadorBuilder.builder().caracteres(authorizationHeader)
					.numeros(String.valueOf(incidenteInRO.getIdIncidente())).validar().build();

			if (!validador.esValido()) {
				cerrarIncidenteOutRO.setResultCode(InvocationResult.FAILED.getCode());
				cerrarIncidenteOutRO.setErrorCode(ErrorCode.INCORRECT_FIELD_VALUE.getErrorCode());
				cerrarIncidenteOutRO
						.setMessage(ErrorCode.getMessageByErrorCode(ErrorCode.INCORRECT_FIELD_VALUE.getErrorCode()));
				return cerrarIncidenteOutRO;
			}

			cerrarIncidenteOutRO = incidenteService.cerrarIncidente(incidenteInRO.getIdIncidente(), authorizationHeader.substring(7), incidenteInRO.getMotivo());
		} catch (Exception e) {
			logger.error(e.getMessage());
			cerrarIncidenteOutRO.setResultCode(InvocationResult.FAILED.getCode());
			cerrarIncidenteOutRO.setErrorCode(ErrorCode.ERROR.getErrorCode());
			cerrarIncidenteOutRO.setMessage("Error al cerrar incidente");
		}
		return cerrarIncidenteOutRO;
	}

	@PostMapping("/actualizar")
	public IncidenteOut actualizar(@RequestBody IncidenteInRO incidenteInRO,
			@RequestHeader("Authorization") String authorizationHeader) {
		IncidenteOut incidenteOutRO = new IncidenteOut();
		try {
			incidenteInRO.setTitulo(incidenteInRO.getTitulo() == null ? "" : incidenteInRO.getTitulo());

			Validador validador = ValidadorBuilder.builder().caracteres(incidenteInRO.getTitulo(), authorizationHeader)
					.numeros(String.valueOf(incidenteInRO.getIdTipo())).validar().build();

			if (!validador.esValido()) {
				incidenteOutRO.setResultCode(InvocationResult.FAILED.getCode());
				incidenteOutRO.setErrorCode(ErrorCode.INCORRECT_FIELD_VALUE.getErrorCode());
				incidenteOutRO
						.setMessage(ErrorCode.getMessageByErrorCode(ErrorCode.INCORRECT_FIELD_VALUE.getErrorCode()));
				return incidenteOutRO;
			}

			incidenteOutRO = incidenteService.actualizarIncidente(incidenteInRO, authorizationHeader.substring(7));
		} catch (Exception e) {
			logger.error(e.getMessage());
			incidenteOutRO.setResultCode(InvocationResult.FAILED.getCode());
			incidenteOutRO.setErrorCode(ErrorCode.ERROR.getErrorCode());
			incidenteOutRO.setMessage("Error al actualizar incidente");
		}
		return incidenteOutRO;
	}

	@GetMapping("/listaIncidentes")
	public ListIncidenteTablaDinamicaOutRo listarInciParaTablaDinamica() {
		ListIncidenteTablaDinamicaOutRo listIncidenteTablaDinamicaOutRo = new ListIncidenteTablaDinamicaOutRo();
		try {

			listIncidenteTablaDinamicaOutRo = incidenteService.listarInciParaTablaDinamica();
		} catch (Exception e) {
			logger.error(e.getMessage());
			listIncidenteTablaDinamicaOutRo.setResultCode(InvocationResult.FAILED.getCode());
			listIncidenteTablaDinamicaOutRo.setErrorCode(ErrorCode.ERROR.getErrorCode());
			listIncidenteTablaDinamicaOutRo.setMessage("Error al listar incidentes por usuario");
		}
		return listIncidenteTablaDinamicaOutRo;
	}

	@GetMapping("/listaIntegrar/{idSubTipo}/{ubigeo}/{idIncidente}")
	public ListIncidenteIntegrarOutRO listarParaIntegrar(@PathVariable("idSubTipo") Integer idSubTipo,
			@PathVariable("ubigeo") String ubigeo, @PathVariable("idIncidente") Integer idIncidente) {
		ListIncidenteIntegrarOutRO listIncidenteIntegrarOutRO = new ListIncidenteIntegrarOutRO();
		try {

			listIncidenteIntegrarOutRO = incidenteService.listarParaIntegrarV2(idSubTipo, ubigeo, idIncidente);
			
		} catch (Exception e) {
			logger.error(e.getMessage());
			listIncidenteIntegrarOutRO.setResultCode(InvocationResult.FAILED.getCode());
			listIncidenteIntegrarOutRO.setErrorCode(ErrorCode.ERROR.getErrorCode());
			listIncidenteIntegrarOutRO.setMessage("Error al listar incidentes por usuario");
		}
		return listIncidenteIntegrarOutRO;
	}

	/*@PostMapping("/integrar")
	public BaseOutRO integrarIncidentes(@RequestBody IntegrarIncidenteInRO integrarIncidenteInRO,
			@RequestHeader("Authorization") String authorizationHeader) {
		BaseOutRO baseOutRo = new BaseOutRO();
		try {
			Validador validador = ValidadorBuilder.builder()
					.caracteres(integrarIncidenteInRO.getCodigo(), authorizationHeader)
					.validar().build();
			
			if (!validador.esValido()) {
				baseOutRo.setResultCode(InvocationResult.FAILED.getCode());
				baseOutRo.setErrorCode(ErrorCode.INCORRECT_FIELD_VALUE.getErrorCode());
				baseOutRo.setMessage(ErrorCode.getMessageByErrorCode(ErrorCode.INCORRECT_FIELD_VALUE.getErrorCode()));
				return baseOutRo;
			}
			
			baseOutRo = incidenteService.integrarIncidentes(integrarIncidenteInRO.getCodigo(), authorizationHeader.substring(7));
			
		} catch (Exception e) {
			logger.error(e.getMessage());
			baseOutRo.setResultCode(InvocationResult.FAILED.getCode());
			baseOutRo.setErrorCode(ErrorCode.ERROR.getErrorCode());
			baseOutRo.setMessage("Error al integrar incidentes");
		}
		return baseOutRo;
	}*/
	
	@PostMapping("/integrar")
	public BaseOutRO integrarIncidentesV2(@RequestBody IntegrarIncidenteInRO integrarIncidenteInRO,
			@RequestHeader("Authorization") String authorizationHeader) {
		BaseOutRO baseOutRo = new BaseOutRO();
		ListIncidenteIntegrarOutRO listIncidenteIntegrarOutRO = new ListIncidenteIntegrarOutRO();
		try {
			Validador validador = ValidadorBuilder.builder().caracteres(authorizationHeader)
					.numeros(String.valueOf(integrarIncidenteInRO.getIdIncidenteSeleccionado()),
							 String.valueOf(integrarIncidenteInRO.getIdIncidenteAIntegrar())
							).validar().build();
			
			if (!validador.esValido()) {
				baseOutRo.setResultCode(InvocationResult.FAILED.getCode());
				baseOutRo.setErrorCode(ErrorCode.INCORRECT_FIELD_VALUE.getErrorCode());
				baseOutRo.setMessage(ErrorCode.getMessageByErrorCode(ErrorCode.INCORRECT_FIELD_VALUE.getErrorCode()));
				return baseOutRo;
			}
			
			//listIncidenteIntegrarOutRO = incidenteService.listarParaIntegrarV2(idTipo, ubigeo, idIncidente);
			
			baseOutRo = incidenteService.integrarIncidentesV2(integrarIncidenteInRO, authorizationHeader.substring(7));
			
		} catch (Exception e) {
			logger.error(e.getMessage());
			baseOutRo.setResultCode(InvocationResult.FAILED.getCode());
			baseOutRo.setErrorCode(ErrorCode.ERROR.getErrorCode());
			baseOutRo.setMessage("Error al integrar incidentes");
		}
		return baseOutRo;
	}
	
	@GetMapping("/obtenerIncidentesPor/{idSector}/{idTipo}/{idSubTipo}/{idDep}/{idProv}/{idDist}")
	public ListIncidenteOutRO ObtenerIncidentesPor(@PathVariable("idSector") String idSector,
			@PathVariable("idTipo") String idTipo,@PathVariable("idSubTipo") String idSubTipo, @PathVariable("idDep") String idDep, @PathVariable("idProv") String idProv, @PathVariable("idDist") String idDist) {

		ListIncidenteOutRO listIncidenteOutRO = new ListIncidenteOutRO();
		try {
			
			listIncidenteOutRO = incidenteService.ObtenerIncidentesPor(idSector, idTipo,idSubTipo, idDep, idProv, idDist);
			
		} catch (Exception e) {
			logger.error(e.getMessage());
			listIncidenteOutRO.setResultCode(InvocationResult.FAILED.getCode());
			listIncidenteOutRO.setErrorCode(ErrorCode.ERROR.getErrorCode());
			listIncidenteOutRO.setMessage("Error al listar incidentes por parametro");
		}
		return listIncidenteOutRO;
		
	}
	
}
