package pe.gob.osinergmin.sio.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.gob.osinergmin.sio.entity.Ubigeo;
import pe.gob.osinergmin.sio.enums.ErrorCode;
import pe.gob.osinergmin.sio.enums.InvocationResult;
import pe.gob.osinergmin.sio.persistence.dao.UbigeoRepository;
import pe.gob.osinergmin.sio.ro.out.DepartamentoOutRO;
import pe.gob.osinergmin.sio.ro.out.DistritoOutRO;
import pe.gob.osinergmin.sio.ro.out.ListaDepartamentoOutRO;
import pe.gob.osinergmin.sio.ro.out.ListaDistritoOutRO;
import pe.gob.osinergmin.sio.ro.out.ListaProvinciaOutRO;
import pe.gob.osinergmin.sio.ro.out.ProvinciaOutRO;
import pe.gob.osinergmin.sio.ro.out.UbigeoOutRO;
import pe.gob.osinergmin.sio.service.UbigeoService;

@Service
public class UbigeoServiceImpl implements UbigeoService{

	@Autowired
	UbigeoRepository ubigeoRepository;
	
	@Override
	public ListaDepartamentoOutRO listarDepartamentos() {
		ListaDepartamentoOutRO listaDepartamentoOutRO = new ListaDepartamentoOutRO();
		List<DepartamentoOutRO> listOutRO = new ArrayList<>();

		try {
			List<Ubigeo> list = ubigeoRepository.listarDepartamentos();

			for (Ubigeo ubigeo : list) {
				DepartamentoOutRO ubigeoOutRO = new DepartamentoOutRO();
				ubigeoOutRO.setCodDpto(ubigeo.getCodDepartamento());
				ubigeoOutRO.setDepartamento(ubigeo.getDescDepartamento());
				listOutRO.add(ubigeoOutRO);
			}

			listaDepartamentoOutRO.setResultCode(InvocationResult.SUCCESS.getCode());
		} catch (Exception e) {
			listaDepartamentoOutRO.setResultCode(InvocationResult.FAILED.getCode());
			listaDepartamentoOutRO.setErrorCode(ErrorCode.ERROR.getErrorCode());
			listaDepartamentoOutRO.setMessage("Error al listar los Usuarios con Rol");
		}
		listaDepartamentoOutRO.setDepartamentos(listOutRO);
		return listaDepartamentoOutRO;
	}
	
	@Override
	public ListaProvinciaOutRO listarProvincias(String codDep) {
		ListaProvinciaOutRO listaProvinciaOutRO = new ListaProvinciaOutRO();
		List<ProvinciaOutRO> listOutRO = new ArrayList<>();

		try {
			List<Ubigeo> list = ubigeoRepository.listarProvincias(codDep);

			for (Ubigeo ubigeo : list) {
				ProvinciaOutRO ubigeoOutRO = new ProvinciaOutRO();
				ubigeoOutRO.setCodProv(ubigeo.getCodProvincia());
				ubigeoOutRO.setProvincia(ubigeo.getDescProvincia());
				listOutRO.add(ubigeoOutRO);
			}

			listaProvinciaOutRO.setResultCode(InvocationResult.SUCCESS.getCode());
		} catch (Exception e) {
			listaProvinciaOutRO.setResultCode(InvocationResult.FAILED.getCode());
			listaProvinciaOutRO.setErrorCode(ErrorCode.ERROR.getErrorCode());
			listaProvinciaOutRO.setMessage("Error al listar los Usuarios con Rol");
		}
		listaProvinciaOutRO.setProvincias(listOutRO);
		return listaProvinciaOutRO;
	}
	
	@Override
	public ListaDistritoOutRO listarDistritos(String codDep, String codProv) {
		ListaDistritoOutRO listaDsitritoOutRO = new ListaDistritoOutRO();
		List<DistritoOutRO> listOutRO = new ArrayList<>();

		try {
			List<Ubigeo> list = ubigeoRepository.listarDistritos(codDep, codProv);

			for (Ubigeo ubigeo : list) {
				DistritoOutRO ubigeoOutRO = new DistritoOutRO();
				ubigeoOutRO.setCodDist(ubigeo.getCodDistrito());
				ubigeoOutRO.setDistrito(ubigeo.getDescrDistrito());
				ubigeoOutRO.setUbigeo(ubigeo.getCodUbigeo());
				listOutRO.add(ubigeoOutRO);
			}

			listaDsitritoOutRO.setResultCode(InvocationResult.SUCCESS.getCode());
		} catch (Exception e) {
			listaDsitritoOutRO.setResultCode(InvocationResult.FAILED.getCode());
			listaDsitritoOutRO.setErrorCode(ErrorCode.ERROR.getErrorCode());
			listaDsitritoOutRO.setMessage("Error al listar los Usuarios con Rol");
		}
		listaDsitritoOutRO.setDistritos(listOutRO);
		return listaDsitritoOutRO;
	}

	@Override
	public UbigeoOutRO obtenerUbigeo(String codDep, String codProv, String codDist) {
		UbigeoOutRO ubigeoOutRO = new UbigeoOutRO();

		try {
			Ubigeo ubigeo = ubigeoRepository.obtenerUbigeoPorCodigoDepaProvDist(codDep, codProv,codDist);
			ubigeoOutRO.setUbigeo(ubigeo.getCodUbigeo());

			ubigeoOutRO.setResultCode(InvocationResult.SUCCESS.getCode());
		} catch (Exception e) {
			ubigeoOutRO.setResultCode(InvocationResult.FAILED.getCode());
			ubigeoOutRO.setErrorCode(ErrorCode.ERROR.getErrorCode());
			ubigeoOutRO.setMessage("Error al obtener Ubigeo");
		}
		
		return ubigeoOutRO;
	}

}
