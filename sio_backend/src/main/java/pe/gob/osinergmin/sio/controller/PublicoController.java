package pe.gob.osinergmin.sio.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pe.gob.osinergmin.sio.enums.ErrorCode;
import pe.gob.osinergmin.sio.enums.InvocationResult;
import pe.gob.osinergmin.sio.ro.out.ListTipoDocumentoOutRO;
import pe.gob.osinergmin.sio.service.TipoDocumentoService;

@RestController
@RequestMapping("/publico")
public class PublicoController {

	private static Logger logger = LoggerFactory.getLogger(UsuarioController.class);
	
	@Autowired
	private TipoDocumentoService tipoDocumentoService;
	
	@GetMapping("/tiposDocumento")
	public ListTipoDocumentoOutRO listarTiposDocumento(){
		ListTipoDocumentoOutRO listTipoDocumentoOutRO = new ListTipoDocumentoOutRO();
		try {
			listTipoDocumentoOutRO = tipoDocumentoService.listarTiposDocumentos();
		}catch(Exception e) {
			logger.error(e.getMessage());
			listTipoDocumentoOutRO.setResultCode(InvocationResult.FAILED.getCode());
			listTipoDocumentoOutRO.setErrorCode(ErrorCode.ERROR.getErrorCode());
			listTipoDocumentoOutRO.setMessage("Error al listar tipos de documento");
		}
		return listTipoDocumentoOutRO;
	}
}
