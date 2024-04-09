package pe.gob.osinergmin.sio.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.gob.osinergmin.sio.entity.EstadoIncidente;
import pe.gob.osinergmin.sio.enums.ErrorCode;
import pe.gob.osinergmin.sio.enums.InvocationResult;
import pe.gob.osinergmin.sio.persistence.dao.EstadoIncidenteRepository;
import pe.gob.osinergmin.sio.ro.out.ListEstadoIncidenteOutRO;
import pe.gob.osinergmin.sio.ro.out.EstadoIncidenteOutRO;
import pe.gob.osinergmin.sio.service.EstadoIncidenteService;

@Service
public class EstadoIncidenteServiceImpl implements EstadoIncidenteService {

	@Autowired
	EstadoIncidenteRepository estadoIncidenteRepository;

	@Override
	public ListEstadoIncidenteOutRO listarEstados() {
		ListEstadoIncidenteOutRO listSectorOutRO = new ListEstadoIncidenteOutRO();
		List<EstadoIncidenteOutRO> listOutRO = new ArrayList<>();
		try {
			List<EstadoIncidente> list = estadoIncidenteRepository.listarEstados();
			for (EstadoIncidente estado : list) {
				EstadoIncidenteOutRO estadoIncidenteOutRO = new EstadoIncidenteOutRO();
				estadoIncidenteOutRO.setIdEstado(estado.getIdEstado());
				estadoIncidenteOutRO.setNombre(estado.getNombre());
				listOutRO.add(estadoIncidenteOutRO);
			}
			listSectorOutRO.setResultCode(InvocationResult.SUCCESS.getCode());
		} catch (Exception e) {
			listSectorOutRO.setResultCode(InvocationResult.FAILED.getCode());
			listSectorOutRO.setErrorCode(ErrorCode.ERROR.getErrorCode());
			listSectorOutRO.setMessage("Error al listar los estados");
		}
		listSectorOutRO.setEstados(listOutRO);
		return listSectorOutRO;
	}
}
