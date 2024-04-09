package pe.gob.osinergmin.sio.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pe.gob.osinergmin.sio.enums.ErrorCode;
import pe.gob.osinergmin.sio.enums.InvocationResult;
import pe.gob.osinergmin.sio.ro.out.ListaDepartamentoOutRO;
import pe.gob.osinergmin.sio.ro.out.ListaDistritoOutRO;
import pe.gob.osinergmin.sio.ro.out.ListaProvinciaOutRO;
import pe.gob.osinergmin.sio.ro.out.UbigeoOutRO;
import pe.gob.osinergmin.sio.service.UbigeoService;

@RestController
@RequestMapping("/ubigeo")
public class UbigeoController {

	private static Logger logger = LoggerFactory.getLogger(UbigeoController.class);
	
	@Autowired
	private UbigeoService ubigeoService;
	
	
	@GetMapping("/departamentos")
	public ListaDepartamentoOutRO listarDepartamentos(){
		ListaDepartamentoOutRO listDepartamentosOutRO = new ListaDepartamentoOutRO();
		try {
			listDepartamentosOutRO = ubigeoService.listarDepartamentos();
		}catch(Exception e) {
			logger.error(e.getMessage());
			listDepartamentosOutRO.setResultCode(InvocationResult.FAILED.getCode());
			listDepartamentosOutRO.setErrorCode(ErrorCode.ERROR.getErrorCode());
			listDepartamentosOutRO.setMessage("Error al listar departamentos");
		}
		return listDepartamentosOutRO;
	}
	
	@GetMapping("/provincias/{codDep}")
	public ListaProvinciaOutRO listarProvincias(@PathVariable("codDep") String codDep){
		ListaProvinciaOutRO listProvinciasOutRO = new ListaProvinciaOutRO();
		try {
			listProvinciasOutRO = ubigeoService.listarProvincias(codDep);
		}catch(Exception e) {
			logger.error(e.getMessage());
			listProvinciasOutRO.setResultCode(InvocationResult.FAILED.getCode());
			listProvinciasOutRO.setErrorCode(ErrorCode.ERROR.getErrorCode());
			listProvinciasOutRO.setMessage("Error al listar provincias");
		}
		return listProvinciasOutRO;
	}
	
	
	@GetMapping("/distritos/{codDep}/{codProv}")
	public ListaDistritoOutRO listarProvincias(@PathVariable("codDep") String codDep, @PathVariable("codProv") String codProv){
		ListaDistritoOutRO listDistritosOutRO = new ListaDistritoOutRO();
		try {
			listDistritosOutRO = ubigeoService.listarDistritos(codDep, codProv);
		}catch(Exception e) {
			logger.error(e.getMessage());
			listDistritosOutRO.setResultCode(InvocationResult.FAILED.getCode());
			listDistritosOutRO.setErrorCode(ErrorCode.ERROR.getErrorCode());
			listDistritosOutRO.setMessage("Error al listar distritos");
		}
		return listDistritosOutRO;
	}
	
	@GetMapping("/{codDep}/{codProv}/{codDist}")
	public UbigeoOutRO obtenerUbigeo(@PathVariable("codDep") String codDep, 
											@PathVariable("codProv") String codProv,
											@PathVariable("codDist") String codDist){
		UbigeoOutRO ubigeoOutRO = new UbigeoOutRO();
		try {
			ubigeoOutRO = ubigeoService.obtenerUbigeo(codDep, codProv,codDist);
		}catch(Exception e) {
			logger.error(e.getMessage());
			ubigeoOutRO.setResultCode(InvocationResult.FAILED.getCode());
			ubigeoOutRO.setErrorCode(ErrorCode.ERROR.getErrorCode());
			ubigeoOutRO.setMessage("Error al obtener ubigeo");
		}
		return ubigeoOutRO;
	}
}
