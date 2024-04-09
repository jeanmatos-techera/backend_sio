package pe.gob.osinergmin.sio.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pe.gob.osinergmin.sio.enums.ErrorCode;
import pe.gob.osinergmin.sio.enums.InvocationResult;
import pe.gob.osinergmin.sio.ro.in.RegistroAnalisisInRO;
import pe.gob.osinergmin.sio.ro.out.AnalisisOutRO;
import pe.gob.osinergmin.sio.ro.out.RegistroAnalisisOutRO;
import pe.gob.osinergmin.sio.service.AnalisisService;
import pe.gob.osinergmin.sio.util.Validador;
import pe.gob.osinergmin.sio.util.ValidadorBuilder;

@RestController
@RequestMapping("/analisis")
public class AnalisisController {

	private static Logger logger = LoggerFactory.getLogger(AnalisisController.class);
	
	@Autowired
	private AnalisisService analisisService;
		
	@PostMapping("/registrar")
	public RegistroAnalisisOutRO registrar(@RequestBody RegistroAnalisisInRO registroAnalisisInRO){
		RegistroAnalisisOutRO registroAnalisisOutRO = new RegistroAnalisisOutRO();
		try {		
			
			Validador validador = ValidadorBuilder.builder()
					.numeros(String.valueOf(registroAnalisisInRO.getIdIncidente()))
					.caracteres(registroAnalisisInRO.getDescripcion())
					.validar().build();
			
			if (!validador.esValido()) {
				registroAnalisisOutRO.setResultCode(InvocationResult.FAILED.getCode());
				registroAnalisisOutRO.setErrorCode(ErrorCode.INCORRECT_FIELD_VALUE.getErrorCode());
				registroAnalisisOutRO.setMessage(ErrorCode.getMessageByErrorCode(ErrorCode.INCORRECT_FIELD_VALUE.getErrorCode()));
			
				return registroAnalisisOutRO;
			}
			
			registroAnalisisOutRO = analisisService.registrarAnalisis(registroAnalisisInRO);
		}catch(Exception e) {
			logger.error(e.getMessage());
			registroAnalisisOutRO.setResultCode(InvocationResult.FAILED.getCode());
			registroAnalisisOutRO.setErrorCode(ErrorCode.ERROR.getErrorCode());
			registroAnalisisOutRO.setMessage("Error al registrar analisis");
		}
		return registroAnalisisOutRO;
	}
	
	@PostMapping("/actualizar")
	public RegistroAnalisisOutRO actualizar(@RequestBody RegistroAnalisisInRO registroAnalisisInRO){
		RegistroAnalisisOutRO registroAnalisisOutRO = new RegistroAnalisisOutRO();
		try {	
			
			Validador validador = ValidadorBuilder.builder()
					.numeros(String.valueOf(registroAnalisisInRO.getIdIncidente()))
					.caracteres(registroAnalisisInRO.getDescripcion())
					.validar().build();
			
			if (!validador.esValido()) {
				registroAnalisisOutRO.setResultCode(InvocationResult.FAILED.getCode());
				registroAnalisisOutRO.setErrorCode(ErrorCode.INCORRECT_FIELD_VALUE.getErrorCode());
				registroAnalisisOutRO.setMessage(ErrorCode.getMessageByErrorCode(ErrorCode.INCORRECT_FIELD_VALUE.getErrorCode()));
			
				return registroAnalisisOutRO;
			}
			
			registroAnalisisOutRO = analisisService.actualizarAnalisis(registroAnalisisInRO);
		}catch(Exception e) {
			logger.error(e.getMessage());
			registroAnalisisOutRO.setResultCode(InvocationResult.FAILED.getCode());
			registroAnalisisOutRO.setErrorCode(ErrorCode.ERROR.getErrorCode());
			registroAnalisisOutRO.setMessage("Error al actualizar analisis");
		}
		return registroAnalisisOutRO;
	}
	
	@GetMapping("/obtener/{idIncidente}")
	public AnalisisOutRO obtenerAnalisisPorIncidente(@PathVariable("idIncidente") Integer idIncidente){
		AnalisisOutRO analisisOutRO = new AnalisisOutRO();
		try {
			analisisOutRO = analisisService.obtenerAnalisisPorIdIncidente(idIncidente);
		}catch(Exception e) {
			logger.error(e.getMessage());
			analisisOutRO.setResultCode(InvocationResult.FAILED.getCode());
			analisisOutRO.setErrorCode(ErrorCode.ERROR.getErrorCode());
			analisisOutRO.setMessage("Error al obtener analisis por incidente");
		}
		return analisisOutRO;
	}
}
