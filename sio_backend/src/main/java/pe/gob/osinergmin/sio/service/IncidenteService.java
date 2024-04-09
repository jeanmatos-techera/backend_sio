package pe.gob.osinergmin.sio.service;

import pe.gob.osinergmin.sio.ro.in.IncidenteInRO;
import pe.gob.osinergmin.sio.ro.in.IntegrarIncidenteInRO;
import pe.gob.osinergmin.sio.ro.out.BaseOutRO;
import pe.gob.osinergmin.sio.ro.out.CerrarIncidenteOutRO;
import pe.gob.osinergmin.sio.ro.out.DetalleIncidenteOutRO;
import pe.gob.osinergmin.sio.ro.out.IncidenteOut;
import pe.gob.osinergmin.sio.ro.out.ListIncidenteIntegrarOutRO;
import pe.gob.osinergmin.sio.ro.out.ListIncidenteOutRO;
import pe.gob.osinergmin.sio.ro.out.ListIncidenteTablaDinamicaOutRo;

public interface IncidenteService {

	public IncidenteOut registrarIncidente(IncidenteInRO incidenteInRO, String token);
	
	public ListIncidenteOutRO listarPorUsuario(Integer idUsuario);
	
	public DetalleIncidenteOutRO detalleIncidente(Integer idIncidente);
	
	public ListIncidenteOutRO listarPorTipo(Integer idTipo);
	
	public ListIncidenteOutRO listarPorPalabraClave(Integer vi_idSector, Integer vi_idTipoIncidencia, String vi_palabraClave);
	
	public CerrarIncidenteOutRO cerrarIncidente(Integer idIncidente, String token, String motivo);
	
	public IncidenteOut actualizarIncidente(IncidenteInRO incidenteInRO, String token);
	
	public ListIncidenteTablaDinamicaOutRo listarInciParaTablaDinamica();
	
	public ListIncidenteIntegrarOutRO listarParaIntegrar(Integer idTipo, String ubigeo, Integer idIncidente);
	
	public ListIncidenteIntegrarOutRO listarParaIntegrarV2(Integer idSubTipo, String ubigeo, Integer idIncidente);
	
	public BaseOutRO integrarIncidentes(String codigoIncidencias, String token);
	
	public BaseOutRO integrarIncidentesV2(IntegrarIncidenteInRO integrarIncidenteInRO, String token);

	public ListIncidenteOutRO ObtenerIncidentesPor(String idSector, String idTipo, String idSubTipo,String idDep, String idProv,String idDist);

	public ListIncidenteOutRO listarPorSubTipo(Integer idSubTipo);
}
