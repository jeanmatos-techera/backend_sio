package pe.gob.osinergmin.sio.persistence.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import pe.gob.osinergmin.sio.entity.Ubigeo;
import pe.gob.osinergmin.sio.persistence.UbigeoCrud;
import pe.gob.osinergmin.sio.persistence.dao.UbigeoRepository;

@Repository
@Transactional(rollbackFor = Exception.class)
public class UbigeoRepositoryImpl implements UbigeoRepository{

	@Autowired
	UbigeoCrud ubigeoCrud;
	
	@Override
	public List<Ubigeo> listarDepartamentos() {
		List<Ubigeo> departamentos = new ArrayList<>();
		try {
			departamentos  = ubigeoCrud.descDepartamento();

		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return departamentos;
	}

	@Override
	public List<Ubigeo> listarProvincias(String codDep) {
		List<Ubigeo> provincias = new ArrayList<>();
		try {
			provincias  = ubigeoCrud.listarProvincias(codDep);

		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return provincias;
	}
	
	@Override
	public List<Ubigeo> listarDistritos(String codDep, String codProv) {
		List<Ubigeo> provincias = new ArrayList<>();
		try {
			provincias  = ubigeoCrud.findByCodDepartamentoAndCodProvinciaOrderByDescrDistritoAsc(codDep, codProv);

		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return provincias;
	}

	@Override
	public Ubigeo obtenerUbigeoPorCodigoDepaProvDist(String codDep, String codProv, String codDist) {
		Ubigeo ubigeo = null;
		try {
			ubigeo = ubigeoCrud.obtenerUbigeoPorCodDepartamentoYCodProvinciaYCodDistrito(codDep,codProv,codDist);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return ubigeo;
	}

	@Override
	public Ubigeo obtenerUbigeoPorCodigo(String codigo) {
		Ubigeo ubigeo = null;
		try {
			ubigeo = ubigeoCrud.obtenerUbigeoPorCodigo(codigo);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return ubigeo;
	}

}
