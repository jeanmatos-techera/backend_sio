package pe.gob.osinergmin.sio.persistence.dao;

import java.util.List;

import pe.gob.osinergmin.sio.entity.Incidente;
import pe.gob.osinergmin.sio.ro.out.IncidenteTablaDinamicaOutRo;

public interface IncidenteRepository {

	public Incidente registrarIncidente(Incidente incidente);
	
	public Incidente actualizarCodigoIncidente(Integer id, String codigo);
	
	public List<Incidente> listarPorUsuario(Integer idUsuario);
	
	public Incidente obtenerIncidenteId(Integer idIncidente);
	
	public List<Incidente> listarPorTipo(Integer idTipo);
	
	public List<Incidente> listarPorPalabraClave(Integer vi_idSector, Integer vi_idTipoIncidencia, String vi_palabraClave);
	
	public Incidente actualizarIncidente(Incidente incidente);
	
	public List<IncidenteTablaDinamicaOutRo> listarInciParaTablaDinamica();
	
	public List<Incidente> listarParaIntegrar(Integer idTipo, String ubigeo, Integer idIncidente);
	
	public List<Incidente> listarParaIntegrarV2(Integer idSubTipo, String ubigeo, Integer idIncidente);

	public List<Incidente> listarPorSector(Integer idSector);

	public List<Incidente> listarPorDist(String codDep, String codProv, String codDist);

	public List<Incidente> listarPorProv(String codProv, String codDep);

	public List<Incidente> listarPorDep(String codDep);

	public List<Incidente> listarPorDistYtipoInc(String codDep, String codProv, String codDist, Integer idTipo);

	public List<Incidente> listarPorProvYtipoInc(String codDep, String codProv, Integer idTipo);

	public List<Incidente> listarPorDepYtipoInc(String codDep, Integer idTipo);

	public List<Incidente> listarPorDistYsectorInc(String codDep, String codProv, String codDist, Integer idSector);

	public List<Incidente> listarPorProvYsectorInc(String codDep, String codProv, Integer idSector);

	public List<Incidente> listarPorDepYsectorInc(String codDep, Integer idSector);

	public List<Incidente> listarPorSubTipo(Integer idSubTipo);
	
	public List<Incidente> listarPorDistYSubtipoInc(String codDep, String codProv, String codDist, Integer idSubTipo);

	public List<Incidente> listarPorDistYSubtipoInc(String codDep, String codProv, Integer idSubTipo);

	public List<Incidente> listarPorDistYSubtipoInc(String codDep, Integer idSubTipo);
}
