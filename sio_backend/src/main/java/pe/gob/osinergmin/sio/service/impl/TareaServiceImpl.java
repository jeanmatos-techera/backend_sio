package pe.gob.osinergmin.sio.service.impl;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.gob.osinergmin.sio.entity.FotosTarea;
import pe.gob.osinergmin.sio.entity.Tarea;
import pe.gob.osinergmin.sio.enums.ErrorCode;
import pe.gob.osinergmin.sio.enums.InvocationResult;
import pe.gob.osinergmin.sio.persistence.dao.FotosTareaRepository;
import pe.gob.osinergmin.sio.persistence.dao.TareaRepository;
import pe.gob.osinergmin.sio.ro.in.FotosTareaInRO;
import pe.gob.osinergmin.sio.ro.in.RegistroTareaInRO;
import pe.gob.osinergmin.sio.ro.in.TareaInRO;
import pe.gob.osinergmin.sio.ro.out.DetalleTareaOutRO;
import pe.gob.osinergmin.sio.ro.out.FotosTareaOutRO;
import pe.gob.osinergmin.sio.ro.out.ListTareaOutRO;
import pe.gob.osinergmin.sio.ro.out.RegistroTareaOutRO;
import pe.gob.osinergmin.sio.ro.out.TareaOutRO;
import pe.gob.osinergmin.sio.service.TareaService;

@Service
public class TareaServiceImpl implements TareaService {

	@Autowired
	TareaRepository tareaRepository;

	@Autowired
	FotosTareaRepository fotosTareaRepository;

	@Override
	public RegistroTareaOutRO registrarTarea(TareaInRO tareaInRO) {
		RegistroTareaOutRO registroTareaOutRO = new RegistroTareaOutRO();
		try {

			RegistroTareaInRO tarea = new RegistroTareaInRO();
			tarea.setIdIncidente(tareaInRO.getIdIncidente());
			tarea.setNombre(tareaInRO.getNombre());
			tarea.setDescripcion(tareaInRO.getDescripcion());

			Tarea tareaCreado = tareaRepository.registrarTarea(tarea);
			registroTareaOutRO.setIdTarea(tareaCreado.getIdTarea());

			if (tareaCreado != null && tareaCreado.getIdTarea() != null) {

				List<FotosTareaInRO> fotos = tareaInRO.getFotos();
				for (FotosTareaInRO foto : fotos) {
					FotosTarea fotoTarea = new FotosTarea();
					fotoTarea.setIdTarea(tareaCreado.getIdTarea());
					fotoTarea.setFoto(foto.getFoto() != null && foto.getFoto().length() > 0
							? Base64.getDecoder().decode(foto.getFoto())
							: null);
					fotoTarea = fotosTareaRepository.registrar(fotoTarea);
				}
				registroTareaOutRO.setResultCode(InvocationResult.SUCCESS.getCode());
				registroTareaOutRO.setMessage("Se ha registrado tarea");
			} else {
				registroTareaOutRO.setResultCode(InvocationResult.FAILED.getCode());
				registroTareaOutRO.setErrorCode(ErrorCode.ERROR.getErrorCode());
				registroTareaOutRO.setMessage("Error al registrar tarea");
			}

		} catch (Exception e) {
			registroTareaOutRO.setResultCode(InvocationResult.FAILED.getCode());
			registroTareaOutRO.setErrorCode(ErrorCode.ERROR.getErrorCode());
			registroTareaOutRO.setMessage("Error al registrar tarea");
		}
		return registroTareaOutRO;
	}

	@Override
	public RegistroTareaOutRO actualizarTarea(TareaInRO tareaInRO) {
		RegistroTareaOutRO registroTareaOutRO = new RegistroTareaOutRO();
		try {
			
			RegistroTareaInRO tarea = new RegistroTareaInRO();
			tarea.setIdTarea(tareaInRO.getIdTarea());
			tarea.setIdIncidente(tareaInRO.getIdIncidente());
			tarea.setNombre(tareaInRO.getNombre());
			tarea.setDescripcion(tareaInRO.getDescripcion());

			Tarea tareaActualizada = tareaRepository.actualizarTarea(tarea);

			if (tareaActualizada != null) {
				List<FotosTareaInRO> fotos = tareaInRO.getFotos();
				for (FotosTareaInRO foto : fotos) {
					if(foto.getAccion().equals("N")) {
						FotosTarea fotoTarea = new FotosTarea();
						fotoTarea.setIdTarea(tareaActualizada.getIdTarea());
						fotoTarea.setFoto(foto.getFoto() != null && foto.getFoto().length() > 0
								? Base64.getDecoder().decode(foto.getFoto())
								: null);
						fotosTareaRepository.registrar(fotoTarea);
					} else if(foto.getAccion().equals("U")) {
						FotosTarea fotoTarea = new FotosTarea();
						fotoTarea.setIdFoto(foto.getId());
						fotoTarea.setIdTarea(tareaActualizada.getIdTarea());
						fotoTarea.setFoto(foto.getFoto() != null && foto.getFoto().length() > 0
								? Base64.getDecoder().decode(foto.getFoto())
								: null);
						fotosTareaRepository.actualizar(fotoTarea);
					} else if(foto.getAccion().equals("D")) {
						fotosTareaRepository.eliminar(foto.getId());
					}
				}
				
				registroTareaOutRO.setResultCode(InvocationResult.SUCCESS.getCode());
				registroTareaOutRO.setMessage("Se ha actualizado correctamente");
				
			} else {
				registroTareaOutRO.setResultCode(InvocationResult.FAILED.getCode());
				registroTareaOutRO.setErrorCode(ErrorCode.NO_FOUND_ENTITY.getErrorCode());
				registroTareaOutRO.setMessage("Error al actualizar tarea");
			}
		} catch (Exception e) {
			registroTareaOutRO.setResultCode(InvocationResult.FAILED.getCode());
			registroTareaOutRO.setErrorCode(ErrorCode.ERROR.getErrorCode());
			registroTareaOutRO.setMessage("Error al actualizar tarea");
		}
		return registroTareaOutRO;
	}

	@Override
	public ListTareaOutRO listarPorIncidente(Integer idIncidente) {
		ListTareaOutRO listTareaOutRO = new ListTareaOutRO();
		try {
			List<TareaOutRO> listaOutRO = new ArrayList<>();
			List<Tarea> lista = tareaRepository.listarPorIncidente(idIncidente);
			for (Tarea tarea : lista) {
				TareaOutRO TareaOutRO = new TareaOutRO(tarea.getIdTarea(), tarea.getIdIncidente(),
						tarea.getNombre());
				listaOutRO.add(TareaOutRO);
			}
			listTareaOutRO.setTareas(listaOutRO);
			listTareaOutRO.setResultCode(InvocationResult.SUCCESS.getCode());
		} catch (Exception e) {
			listTareaOutRO.setResultCode(InvocationResult.FAILED.getCode());
			listTareaOutRO.setErrorCode(ErrorCode.ERROR.getErrorCode());
			listTareaOutRO.setMessage("Error al listar tareas por incidente");
		}
		return listTareaOutRO;
	}

	@Override
	public DetalleTareaOutRO detalleTarea(Integer idTarea) {
		DetalleTareaOutRO detalleTareaOutRO = new DetalleTareaOutRO();
		try {
			Tarea tarea = tareaRepository.obtenerTarea(idTarea);
			if(tarea == null) {
				detalleTareaOutRO.setResultCode(InvocationResult.FAILED.getCode());
				detalleTareaOutRO.setErrorCode(ErrorCode.NO_FOUND_ENTITY.getErrorCode());
				detalleTareaOutRO.setMessage("Error al obtener detalle de la tarea");
				return detalleTareaOutRO;
			}
			detalleTareaOutRO.setIdTarea(tarea.getIdTarea());
			detalleTareaOutRO.setIdIncidente(tarea.getIdIncidente());
			detalleTareaOutRO.setNombre(tarea.getNombre());
			detalleTareaOutRO.setDescripcion(tarea.getDescripcion());
			
			List<FotosTareaOutRO> listaOutRO = new ArrayList<>();
			List<FotosTarea> fotos = fotosTareaRepository.listarPorTarea(idTarea);
			for(FotosTarea foto : fotos) {
				FotosTareaOutRO fotosTareaOutRO = new FotosTareaOutRO(foto.getIdFoto(),
						foto.getIdTarea(), Base64.getEncoder().encodeToString(foto.getFoto()));
				listaOutRO.add(fotosTareaOutRO);
			}
			detalleTareaOutRO.setFotos(listaOutRO);
			
			detalleTareaOutRO.setResultCode(InvocationResult.SUCCESS.getCode());
		}catch(Exception e) {
			detalleTareaOutRO.setResultCode(InvocationResult.FAILED.getCode());
			detalleTareaOutRO.setErrorCode(ErrorCode.ERROR.getErrorCode());
			detalleTareaOutRO.setMessage("Error al obtener detalle de la tarea");
		}
		return detalleTareaOutRO;
	}

}
