package pe.gob.osinergmin.sio.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import pe.gob.osinergmin.sio.entity.Ubigeo;

public interface UbigeoCrud extends CrudRepository<Ubigeo, Integer>{

	@Query("SELECT ub FROM Ubigeo ub WHERE ub.idUbigeo IN (SELECT u.idUbigeo FROM Ubigeo u WHERE u.codDepartamento=ub.codDepartamento AND ROWNUM = 1) ORDER BY ub.descDepartamento")
	public List<Ubigeo> descDepartamento();
	
	
	@Query("SELECT ubi FROM Ubigeo ubi WHERE ubi.idUbigeo IN (SELECT u.idUbigeo FROM Ubigeo u WHERE u.codDepartamento=?1 and u.codProvincia=ubi.codProvincia AND ROWNUM = 1) ORDER BY ubi.descProvincia ASC ")
	public List<Ubigeo> listarProvincias(String codDep);
	
	public List<Ubigeo> findByCodDepartamentoAndCodProvinciaOrderByDescrDistritoAsc(String codDep, String codProv);
	
	@Query("SELECT ub FROM Ubigeo ub WHERE ub.codDepartamento=?1 AND ub.codProvincia=?2 AND ub.codDistrito=?3")
	public Ubigeo obtenerUbigeoPorCodDepartamentoYCodProvinciaYCodDistrito(String codDep, String codProv, String codDist);

	@Query("SELECT ub FROM Ubigeo ub WHERE ub.codUbigeo=?1")
	public Ubigeo obtenerUbigeoPorCodigo(String codigo);
}
