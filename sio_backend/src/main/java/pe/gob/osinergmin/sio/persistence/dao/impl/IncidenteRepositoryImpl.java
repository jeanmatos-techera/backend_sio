package pe.gob.osinergmin.sio.persistence.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import oracle.jdbc.OracleTypes;
import pe.gob.osinergmin.sio.entity.Incidente;
import pe.gob.osinergmin.sio.entity.Ubigeo;
import pe.gob.osinergmin.sio.persistence.IncidenteCrud;
import pe.gob.osinergmin.sio.persistence.UbigeoCrud;
import pe.gob.osinergmin.sio.persistence.dao.IncidenteRepository;
import pe.gob.osinergmin.sio.ro.out.IncidenteTablaDinamicaOutRo;

@Repository
@Transactional(rollbackFor = Exception.class)
public class IncidenteRepositoryImpl implements IncidenteRepository {

	@Autowired
	IncidenteCrud incidenteCrud;
	
	@Autowired
	DataSource datasource;
	
	@Autowired
	UbigeoCrud ubigeoCrud;
	
	
	@Override
	public Incidente registrarIncidente(Incidente incidente) {
		try {
			//incidente.setUsuarioCreacion("OSINERG");
			incidente.setFechaCreacion(new Date());
			incidente.setTerminalCreacion("Localhost");
			
			incidente = incidenteCrud.save(incidente);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return incidente;
	}

	@Override
	public List<Incidente> listarPorUsuario(Integer idUsuario) {
		List<Incidente> incidentees = new ArrayList<>();
		try {
			incidentees = incidenteCrud.listarPorUsuario(idUsuario);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return incidentees;
	}

	@Override
	public Incidente obtenerIncidenteId(Integer idIncidente) {
		Incidente incidente = null;
		try {
			incidente = incidenteCrud.findById(idIncidente).orElse(null);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return incidente;
	}
	
	@Override
	public List<Incidente> listarPorTipo(Integer idTipo) {
		List<Incidente> incidentes = new ArrayList<>();
		try {
			incidentes = incidenteCrud.listarPorTipo(idTipo);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return incidentes;
	}

	@Override
	public List<Incidente> listarPorPalabraClave(Integer vi_idSector, Integer vi_idTipoIncidencia,
			String vi_palabraClave) {
		// TODO Auto-generated method stub
		
		ResultSet rs = null;
		CallableStatement callableStatement = null;
		Connection con = null;
		
		List<Incidente> listaIncidentes = new ArrayList<Incidente>();
 		System.out.println("vi_idSector:  " + vi_idSector);
		System.out.println("vi_idTipoIncidencia:  " + vi_idTipoIncidencia);
		System.out.println("vi_palabraClave:  " + vi_palabraClave);
		
		try {
			
			con = datasource.getConnection();
			con.setAutoCommit(false);
			System.out.println("Tiempo inicio = " + new Date());
			callableStatement = con.prepareCall("{call SIO_SERVICIO_PKG.SIO_BUSCAR_INCIDENCIA_PRC(?,?,?,?)}");
			callableStatement.setLong(1, vi_idSector);
			callableStatement.setLong(2, vi_idTipoIncidencia);
			callableStatement.setString(3, vi_palabraClave);
			callableStatement.registerOutParameter(4, OracleTypes.CURSOR);
			callableStatement.executeUpdate();
			rs = (ResultSet) callableStatement.getObject(4);
			System.out.println("Tiempo Fin = " + new Date());
			
			
			while (rs.next()) {
				Incidente incidente = new Incidente();

				Integer vo_idIncidente = rs.getInt("ID_INCIDENTE");
				Integer vo_idUsuario = rs.getInt("ID_USUARIO");
				String vo_titulo = rs.getString("DE_TITULO");
				String vo_comentario = rs.getString("CM_INCIDENTE");
				String vo_codigo = rs.getString("CO_INCIDENTE");
				Date vo_fechaIncidente = rs.getDate("FE_CREACION");


				incidente.setIdIncidente(vo_idIncidente);
				incidente.setIdUsuario(vo_idUsuario);
				incidente.setTitulo(vo_titulo);
				incidente.setComentario(vo_comentario);
				incidente.setCodigo(vo_codigo);
				incidente.setFechaIncidente(vo_fechaIncidente);

				listaIncidentes.add(incidente);
				
			}
			/*for(Incidente li: listaIncidentes ) {
				System.out.println("******INCIDENCIA********");
				System.out.println("ID_INCIDENTE " + li.getIdIncidente());
				System.out.println("ID_USUARIO " + li.getIdUsuario());
				System.out.println("DE_TITULO " + li.getTitulo());
				System.out.println("CM_INCIDENTE " + li.getComentario());
				System.out.println("CO_INCIDENTE " + li.getCodigo());
				System.out.println("FE_CREACION " + li.getFechaIncidente());
			}*/
			
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return listaIncidentes;
		
	}

	@Override
	public Incidente actualizarCodigoIncidente(Integer id, String codigo) {
		Incidente incidenteUpd = new Incidente();
		try {
			Incidente incidente = incidenteCrud.findById(id).orElse(null);
			if(incidente != null) {
				incidente.setCodigo(codigo);
				incidenteUpd = incidenteCrud.save(incidente);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return incidenteUpd;
	}
	
	@Override
	public Incidente actualizarIncidente(Incidente incidente) {
		Incidente incidenteOut = null;
		try {
			incidenteOut = this.obtenerIncidenteId(incidente.getIdIncidente());
			if(incidenteOut != null) {
				incidenteOut.setTitulo(incidente.getTitulo());
				incidenteOut.setComentario(incidente.getComentario());
				incidenteOut.setIdUsuario(incidente.getIdUsuario());
				incidenteOut.setIdTipo(incidente.getIdTipo());
				incidenteOut.setIdSubTipo(incidente.getIdSubTipo());
				incidenteOut.setIdSector(incidente.getIdSector());
				incidenteOut.setFechaIncidente(incidente.getFechaIncidente());
				incidenteOut.setIdEstado(incidente.getIdEstado());
				incidenteOut.setIdCriticidad(incidente.getIdCriticidad());
				incidenteOut.setAfectacion(incidente.getAfectacion());
				incidenteOut.setCausa(incidente.getCausa());
				incidenteOut.setIdFuente(incidente.getIdFuente());
				incidenteOut.setCoordenada(incidente.getCoordenada());
				incidenteOut.setUbigeo(incidente.getUbigeo());
				incidenteOut.setIdOficinaRegional(incidente.getIdOficinaRegional());
				incidenteOut.setMotivoEliminacion(incidente.getMotivoEliminacion());
				incidenteOut.setUsuarioActualizacion(incidente.getUsuarioActualizacion());
				incidenteOut.setFechaActualizacion(new Date());
				incidenteOut.setTerminalActualizacion("Localhost");
				
				incidenteOut = incidenteCrud.save(incidenteOut);
			}			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return incidenteOut;
	}

	@Override
	public List<IncidenteTablaDinamicaOutRo> listarInciParaTablaDinamica() {
		ResultSet rs = null;
		CallableStatement callableStatement = null;
		Connection con = null;
		
		List<IncidenteTablaDinamicaOutRo> listaIncidentes = new ArrayList<IncidenteTablaDinamicaOutRo>();
		
		try {
			
			con = datasource.getConnection();
			con.setAutoCommit(false);
			System.out.println("Tiempo inicio = " + new Date());
			callableStatement = con.prepareCall("{call SIO_SERVICIO_PKG.SIO_LISTA_INCIDENCIAS_TABLA(?)}");
			callableStatement.registerOutParameter(1, OracleTypes.CURSOR);
			callableStatement.executeUpdate();
			rs = (ResultSet) callableStatement.getObject(1);
			System.out.println("Tiempo Fin = " + new Date());
			
			
			while (rs.next()) {
				IncidenteTablaDinamicaOutRo incidente = new IncidenteTablaDinamicaOutRo();

				Integer vo_idIncidente = rs.getInt("ID_INCIDENTE");
				String vo_sector = rs.getString("SECTOR");
				Date vo_fechaIncidente = rs.getDate("FE_INCIDENTE");
				String vo_tipoEvento = rs.getString("TIPO_EVENTO");
				String vo_fuente = rs.getString("FUENTE");
				String vo_departamento = rs.getString("DEPARTAMENTO");
				String vo_provincia = rs.getString("PROVINCIA");
				String vo_distrito = rs.getString("DISTRITO");
				String vo_ubigeo = rs.getString("UBIGEO");
				String vo_descripcion = rs.getString("DESCRIPCION");
				String vo_codigo = rs.getString("CO_INCIDENTE");
				Date vo_fechaRegistro = rs.getDate("FE_CREACION");
				String vo_estado = rs.getString("ESTADO");
				String vo_criticidad = rs.getString("CRITICIDAD");
				String vo_usCreacion = rs.getString("US_CREACION");
				String vo_usActualizacion = rs.getString("US_ACTUALIZACION");
				Date vo_feActualizacion = rs.getDate("FE_ACTUALIZACION");
				String vo_oficinaRegional = rs.getString("OFICINA_REGIONAL");
				

				incidente.setIdIncidente(vo_idIncidente);
				incidente.setSector(vo_sector);
				incidente.setFechaIncidente(vo_fechaIncidente);
				incidente.setTipoEvento(vo_tipoEvento);
				incidente.setFuente(vo_fuente);
				incidente.setDepartamento(vo_departamento);;
				incidente.setProvincia(vo_provincia);
				incidente.setDistrito(vo_distrito);
				incidente.setUbigeo(vo_ubigeo);
				incidente.setDescripcion(vo_descripcion);
				incidente.setCodigo(vo_codigo);
				incidente.setFechaRegistro(vo_fechaRegistro);
				incidente.setEstado(vo_estado);
				incidente.setCriticidad(vo_criticidad);
				incidente.setUsCreacion(vo_usCreacion);
				incidente.setUsActualizacion(vo_usActualizacion);
				incidente.setFeActualizacion(vo_feActualizacion);
				incidente.setOficinaRegional(vo_oficinaRegional);

				listaIncidentes.add(incidente);
				
			}
		
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return listaIncidentes;
	}

	@Override
	public List<Incidente> listarParaIntegrar(Integer idTipo, String ubigeo, Integer idIncidente) {
		List<Incidente> incidentes = new ArrayList<>();
		try {
			incidentes = incidenteCrud.listarParaIntegrar(idTipo,ubigeo,idIncidente);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return incidentes;
	}
	
	@Override
	public List<Incidente> listarParaIntegrarV2(Integer idSubTipo, String ubigeo, Integer idIncidente) {
		List<Incidente> incidentes = new ArrayList<>();
		try {
			incidentes = incidenteCrud.listarParaIntegrarV2(idSubTipo,ubigeo,idIncidente);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return incidentes;
	}

	@Override
	public List<Incidente> listarPorSector(Integer idSector) {
		List<Incidente> incidentes = new ArrayList<>();
		try {
			incidentes = incidenteCrud.listarPorSector(idSector);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return incidentes;
	}

	@Override
	public List<Incidente> listarPorDist(String codDep, String codProv, String codDist) {
		List<Incidente> incidentes = new ArrayList<>();
		try {
			Ubigeo ubigeo = ubigeoCrud.obtenerUbigeoPorCodDepartamentoYCodProvinciaYCodDistrito(codDep,codProv,codDist);
			incidentes = incidenteCrud.listarPorUbigeo(ubigeo.getCodUbigeo());
		}catch(Exception e) {
			e.printStackTrace();
		}
		return incidentes;
	}

	@Override
	public List<Incidente> listarPorProv(String codDep, String codProv) {
		List<Incidente> incidentes = new ArrayList<>();
		try {
			incidentes = incidenteCrud.listarPorProv(codDep, codProv);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return incidentes;
	}

	@Override
	public List<Incidente> listarPorDep(String codDep) {
		List<Incidente> incidentes = new ArrayList<>();
		try {
			incidentes = incidenteCrud.listarPorDep(codDep);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return incidentes;
	}

	@Override
	public List<Incidente> listarPorDistYtipoInc(String codDep, String codProv, String codDist, Integer idTipo) {
		List<Incidente> incidentes = new ArrayList<>();
		try {
			Ubigeo ubigeo = ubigeoCrud.obtenerUbigeoPorCodDepartamentoYCodProvinciaYCodDistrito(codDep,codProv,codDist);
			incidentes = incidenteCrud.listarPorUbigeoYtipoInc(ubigeo.getCodUbigeo(), idTipo);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return incidentes;
	}

	@Override
	public List<Incidente> listarPorProvYtipoInc(String codDep, String codProv, Integer idTipo) {
		List<Incidente> incidentes = new ArrayList<>();
		try {
			incidentes = incidenteCrud.listarPorProvYtipoInc(codDep, codProv, idTipo);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return incidentes;

	}

	@Override
	public List<Incidente> listarPorDepYtipoInc(String codDep, Integer idTipo) {
		List<Incidente> incidentes = new ArrayList<>();
		try {
			incidentes = incidenteCrud.listarPorDepYtipoInc(codDep,idTipo);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return incidentes;
	}

	@Override
	public List<Incidente> listarPorDistYsectorInc(String codDep, String codProv, String codDist, Integer idSector) {
		List<Incidente> incidentes = new ArrayList<>();
		try {
			Ubigeo ubigeo = ubigeoCrud.obtenerUbigeoPorCodDepartamentoYCodProvinciaYCodDistrito(codDep,codProv,codDist);
			incidentes = incidenteCrud.listarPorUbigeoYsectorInc(ubigeo.getCodUbigeo(), idSector);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return incidentes;
	}

	@Override
	public List<Incidente> listarPorProvYsectorInc(String codDep, String codProv, Integer idSector) {
		List<Incidente> incidentes = new ArrayList<>();
		try {
			incidentes = incidenteCrud.listarPorProvYsectorInc(codDep, codProv, idSector);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return incidentes;
	}

	@Override
	public List<Incidente> listarPorDepYsectorInc(String codDep, Integer idSector) {
		List<Incidente> incidentes = new ArrayList<>();
		try {
			incidentes = incidenteCrud.listarPorDepYsectorInc(codDep,idSector);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return incidentes;
	}

	@Override
	public List<Incidente> listarPorSubTipo(Integer idSubTipo) {
		// TODO Auto-generated method stub
		List<Incidente> incidentes = new ArrayList<>();
		try {
			incidentes = incidenteCrud.listarPorSubTipo(idSubTipo);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return incidentes;
	}

	@Override
	public List<Incidente> listarPorDistYSubtipoInc(String codDep, String codProv, String codDist, Integer idSubTipo) {
		List<Incidente> incidentes = new ArrayList<>();
		try {
			Ubigeo ubigeo = ubigeoCrud.obtenerUbigeoPorCodDepartamentoYCodProvinciaYCodDistrito(codDep,codProv,codDist);
			incidentes = incidenteCrud.listarPorUbigeoYSubtipoInc(ubigeo.getCodUbigeo(), idSubTipo);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return incidentes;
	}

	@Override
	public List<Incidente> listarPorDistYSubtipoInc(String codDep, String codProv, Integer idSubTipo) {
		List<Incidente> incidentes = new ArrayList<>();
		try {
			incidentes = incidenteCrud.listarPorProvYSubtipoInc(codDep, codProv, idSubTipo);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return incidentes;
	}

	@Override
	public List<Incidente> listarPorDistYSubtipoInc(String codDep, Integer idSubTipo) {
		List<Incidente> incidentes = new ArrayList<>();
		try {
			incidentes = incidenteCrud.listarPorDepYSubtipoInc(codDep,idSubTipo);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return incidentes;
	}

}
