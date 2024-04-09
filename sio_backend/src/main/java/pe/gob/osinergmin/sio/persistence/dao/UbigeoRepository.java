package pe.gob.osinergmin.sio.persistence.dao;

import java.util.List;

import pe.gob.osinergmin.sio.entity.Ubigeo;

public interface UbigeoRepository {
	public List<Ubigeo> listarDepartamentos();
	public List<Ubigeo> listarProvincias(String codDep);
	public List<Ubigeo> listarDistritos(String codDep, String codProv);
	public Ubigeo obtenerUbigeoPorCodigoDepaProvDist(String codDep, String codProv, String codDist);
	public Ubigeo obtenerUbigeoPorCodigo(String codigo);
}
