package pe.gob.osinergmin.sio.service;

import org.springframework.stereotype.Service;

import pe.gob.osinergmin.sio.ro.out.ListaDepartamentoOutRO;
import pe.gob.osinergmin.sio.ro.out.ListaDistritoOutRO;
import pe.gob.osinergmin.sio.ro.out.ListaProvinciaOutRO;
import pe.gob.osinergmin.sio.ro.out.UbigeoOutRO;

@Service
public interface UbigeoService {

	public ListaDepartamentoOutRO listarDepartamentos();
	
	public ListaProvinciaOutRO listarProvincias(String codDep);
	
	public ListaDistritoOutRO listarDistritos(String codDep, String codProv);

	public UbigeoOutRO obtenerUbigeo(String codDep, String codProv, String codDist);
}
