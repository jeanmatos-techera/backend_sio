package pe.gob.osinergmin.sio.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import pe.gob.osinergmin.sio.entity.Incidente;

public interface IncidenteCrud extends CrudRepository<Incidente, Integer>{

	@Query("SELECT i FROM Incidente i WHERE i.estado = 'A' and i.idUsuario = ?1")
	public List<Incidente> listarPorUsuario(Integer idUsuario);
	
	@Query("SELECT i FROM Incidente i WHERE i.estado = 'A' and i.idTipo = ?1 ORDER BY i.fechaCreacion DESC")
	public List<Incidente> listarPorTipo(Integer idTipo);
	
	@Query("SELECT i FROM Incidente i WHERE i.estado = 'A' and i.idTipo = ?1 and i.ubigeo = ?2 and i.idIncidente != ?3")
	public List<Incidente> listarParaIntegrar(Integer idTipo, String ubigeo, Integer idIncidente);
	
	@Query("SELECT i FROM Incidente i WHERE i.estado = 'A' and i.idSubTipo = ?1 and i.ubigeo = ?2 and i.idIncidente != ?3")
	public List<Incidente> listarParaIntegrarV2(Integer idSubTipo, String ubigeo, Integer idIncidente);
	
	@Query("SELECT i FROM Incidente i WHERE i.estado = 'A' and i.idSector = ?1 ORDER BY i.fechaCreacion DESC")
	public List<Incidente> listarPorSector(Integer idSector);

	@Query("SELECT i FROM Incidente i WHERE i.estado = 'A' and i.ubigeo = ?1 ORDER BY i.fechaCreacion DESC")
	public List<Incidente> listarPorUbigeo(String codUbigeo);

	@Query("SELECT i FROM Incidente i INNER JOIN Ubigeo u ON i.ubigeo = u.codUbigeo WHERE i.estado = 'A' and u.codDepartamento LIKE ?1 and u.codProvincia LIKE ?2 ORDER BY i.fechaCreacion DESC")
	public List<Incidente> listarPorProv(String codDep, String codProv);

	@Query("SELECT i FROM Incidente i INNER JOIN Ubigeo u ON i.ubigeo = u.codUbigeo WHERE i.estado = 'A' and u.codDepartamento LIKE ?1 ORDER BY i.fechaCreacion DESC")
	public List<Incidente> listarPorDep(String codDep);

	@Query("SELECT i FROM Incidente i WHERE i.estado = 'A' and i.ubigeo = ?1 and i.idTipo = ?2 ORDER BY i.fechaCreacion DESC")
	public List<Incidente> listarPorUbigeoYtipoInc(String codUbigeo, Integer idTipo);

	@Query("SELECT i FROM Incidente i INNER JOIN Ubigeo u ON i.ubigeo = u.codUbigeo WHERE i.estado = 'A' and u.codDepartamento LIKE ?1 and u.codProvincia LIKE ?2 and i.idTipo = ?3 ORDER BY i.fechaCreacion DESC")
	public List<Incidente> listarPorProvYtipoInc(String codDep, String codProv, Integer idTipo);

	@Query("SELECT i FROM Incidente i INNER JOIN Ubigeo u ON i.ubigeo = u.codUbigeo WHERE i.estado = 'A' and u.codDepartamento LIKE ?1 and i.idTipo = ?2 ORDER BY i.fechaCreacion DESC")
	public List<Incidente> listarPorDepYtipoInc(String codDep, Integer idTipo);

	@Query("SELECT i FROM Incidente i WHERE i.estado = 'A' and i.ubigeo = ?1 and i.idSector = ?2 ORDER BY i.fechaCreacion DESC")
	public List<Incidente> listarPorUbigeoYsectorInc(String codUbigeo, Integer idSector);

	@Query("SELECT i FROM Incidente i INNER JOIN Ubigeo u ON i.ubigeo = u.codUbigeo WHERE i.estado = 'A' and u.codDepartamento LIKE ?1 and u.codProvincia LIKE ?2 and i.idSector = ?3 ORDER BY i.fechaCreacion DESC")
	public List<Incidente> listarPorProvYsectorInc(String codDep, String codProv, Integer idSector);

	@Query("SELECT i FROM Incidente i INNER JOIN Ubigeo u ON i.ubigeo = u.codUbigeo WHERE i.estado = 'A' and u.codDepartamento LIKE ?1 and i.idSector = ?2 ORDER BY i.fechaCreacion DESC")
	public List<Incidente> listarPorDepYsectorInc(String codDep, Integer idSector);

	@Query("SELECT i FROM Incidente i WHERE i.estado = 'A' and i.idSubTipo = ?1 ORDER BY i.fechaCreacion DESC")
	public List<Incidente> listarPorSubTipo(Integer idSubTipo);

	@Query("SELECT i FROM Incidente i WHERE i.estado = 'A' and i.ubigeo = ?1 and i.idSubTipo = ?2 ORDER BY i.fechaCreacion DESC")
	public List<Incidente> listarPorUbigeoYSubtipoInc(String codUbigeo, Integer idSubTipo);

	@Query("SELECT i FROM Incidente i INNER JOIN Ubigeo u ON i.ubigeo = u.codUbigeo WHERE i.estado = 'A' and u.codDepartamento LIKE ?1 and u.codProvincia LIKE ?2 and i.idSubTipo = ?3 ORDER BY i.fechaCreacion DESC")
	public List<Incidente> listarPorProvYSubtipoInc(String codDep, String codProv, Integer idSubTipo);

	@Query("SELECT i FROM Incidente i INNER JOIN Ubigeo u ON i.ubigeo = u.codUbigeo WHERE i.estado = 'A' and u.codDepartamento LIKE ?1 and i.idSubTipo = ?2 ORDER BY i.fechaCreacion DESC")
	public List<Incidente> listarPorDepYSubtipoInc(String codDep, Integer idSubTipo);
}
