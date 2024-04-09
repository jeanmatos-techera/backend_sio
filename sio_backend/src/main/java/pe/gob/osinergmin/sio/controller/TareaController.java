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
import pe.gob.osinergmin.sio.ro.in.FotosTareaInRO;
import pe.gob.osinergmin.sio.ro.in.TareaInRO;
import pe.gob.osinergmin.sio.ro.out.DetalleTareaOutRO;
import pe.gob.osinergmin.sio.ro.out.ListTareaOutRO;
import pe.gob.osinergmin.sio.ro.out.RegistroTareaOutRO;
import pe.gob.osinergmin.sio.service.TareaService;
import pe.gob.osinergmin.sio.util.Validador;
import pe.gob.osinergmin.sio.util.ValidadorBuilder;

@RestController
@RequestMapping("/tarea")
public class TareaController {

	private static Logger logger = LoggerFactory.getLogger(TareaController.class);
		
	@Autowired
	private TareaService tareaService;
	
	@PostMapping("/registrar")
	public RegistroTareaOutRO registrar(@RequestBody TareaInRO tareaInRO){
		RegistroTareaOutRO registroTareaOutRO = new RegistroTareaOutRO();
		try {		
			
			Validador validador = ValidadorBuilder.builder()
					.numeros(String.valueOf(tareaInRO.getIdIncidente()))
					.caracteres(tareaInRO.getNombre())
					.validar().build();
			
			if (!validador.esValido()) {
				registroTareaOutRO.setResultCode(InvocationResult.FAILED.getCode());
				registroTareaOutRO.setErrorCode(ErrorCode.INCORRECT_FIELD_VALUE.getErrorCode());
				registroTareaOutRO.setMessage(ErrorCode.getMessageByErrorCode(ErrorCode.INCORRECT_FIELD_VALUE.getErrorCode()));
			
				return registroTareaOutRO;
			}
			
			if(tareaInRO.getFotos().size() > 5) {
				registroTareaOutRO.setResultCode(InvocationResult.FAILED.getCode());
				registroTareaOutRO.setErrorCode(ErrorCode.INCORRECT_FIELD_VALUE.getErrorCode());
				registroTareaOutRO.setMessage("máximo 5 fotos por tarea");
			
				return registroTareaOutRO;
			}
			
			registroTareaOutRO = tareaService.registrarTarea(tareaInRO);
		}catch(Exception e) {
			logger.error(e.getMessage());
			registroTareaOutRO.setResultCode(InvocationResult.FAILED.getCode());
			registroTareaOutRO.setErrorCode(ErrorCode.ERROR.getErrorCode());
			registroTareaOutRO.setMessage("Error al registrar tare");
		}
		return registroTareaOutRO;
	}
	
	@PostMapping("/actualizar")
	public RegistroTareaOutRO actualizar(@RequestBody TareaInRO tareaInRO){
		RegistroTareaOutRO registroTareaOutRO = new RegistroTareaOutRO();
		try {	
			
			Validador validador = ValidadorBuilder.builder()
					.numeros(String.valueOf(tareaInRO.getIdIncidente()))
					.caracteres(tareaInRO.getNombre())
					.validar().build();
			
			if (!validador.esValido()) {
				registroTareaOutRO.setResultCode(InvocationResult.FAILED.getCode());
				registroTareaOutRO.setErrorCode(ErrorCode.INCORRECT_FIELD_VALUE.getErrorCode());
				registroTareaOutRO.setMessage(ErrorCode.getMessageByErrorCode(ErrorCode.INCORRECT_FIELD_VALUE.getErrorCode()));
			
				return registroTareaOutRO;
			}
			
			if(tareaInRO.getFotos() != null) {
				Integer fotosEliminadas = 0;
				for(FotosTareaInRO foto: tareaInRO.getFotos()) {
					if(foto.getAccion().equals("D")) {
						fotosEliminadas++;
					}
				}
				
				if(tareaInRO.getFotos().size() - fotosEliminadas > 5) {
					registroTareaOutRO.setResultCode(InvocationResult.FAILED.getCode());
					registroTareaOutRO.setErrorCode(ErrorCode.INCORRECT_FIELD_VALUE.getErrorCode());
					registroTareaOutRO.setMessage("máximo 5 fotos por tarea");
				
					return registroTareaOutRO;
				}
				
			}
			
			registroTareaOutRO = tareaService.actualizarTarea(tareaInRO);
		}catch(Exception e) {
			logger.error(e.getMessage());
			registroTareaOutRO.setResultCode(InvocationResult.FAILED.getCode());
			registroTareaOutRO.setErrorCode(ErrorCode.ERROR.getErrorCode());
			registroTareaOutRO.setMessage("Error al actualizar tarea");
		}
		return registroTareaOutRO;
	}
	
	@GetMapping("/listar/{idIncidente}")
	public ListTareaOutRO listarPorIncidente(@PathVariable("idIncidente") Integer idIncidente){
		ListTareaOutRO listTareaOutRO = new ListTareaOutRO();
		try {
			listTareaOutRO = tareaService.listarPorIncidente(idIncidente);
		}catch(Exception e) {
			logger.error(e.getMessage());
			listTareaOutRO.setResultCode(InvocationResult.FAILED.getCode());
			listTareaOutRO.setErrorCode(ErrorCode.ERROR.getErrorCode());
			listTareaOutRO.setMessage("Error al listar tareas por incidente");
		}
		return listTareaOutRO;
	}
	
	@GetMapping("/detalle/{idTarea}")
	public DetalleTareaOutRO detalleTarea(@PathVariable("idTarea") Integer idTarea){
		DetalleTareaOutRO detalleTareaOutRO = new DetalleTareaOutRO();
		try {			
			Validador validador = ValidadorBuilder.builder()
					.numeros(String.valueOf(idTarea))
					.validar().build();
			
			if (!validador.esValido()) {
				detalleTareaOutRO.setResultCode(InvocationResult.FAILED.getCode());
				detalleTareaOutRO.setErrorCode(ErrorCode.INCORRECT_FIELD_VALUE.getErrorCode());
				detalleTareaOutRO.setMessage(ErrorCode.getMessageByErrorCode(ErrorCode.INCORRECT_FIELD_VALUE.getErrorCode()));
				return detalleTareaOutRO;
			}
			
			detalleTareaOutRO = tareaService.detalleTarea(idTarea);
		}catch(Exception e) {
			logger.error(e.getMessage());
			detalleTareaOutRO.setResultCode(InvocationResult.FAILED.getCode());
			detalleTareaOutRO.setErrorCode(ErrorCode.ERROR.getErrorCode());
			detalleTareaOutRO.setMessage("Error al obtener detalle de tarea");
		}
		return detalleTareaOutRO;
	}
}
