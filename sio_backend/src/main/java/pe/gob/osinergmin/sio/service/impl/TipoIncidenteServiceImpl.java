package pe.gob.osinergmin.sio.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.gob.osinergmin.sio.entity.TipoIncidente;
import pe.gob.osinergmin.sio.enums.ErrorCode;
import pe.gob.osinergmin.sio.enums.InvocationResult;
import pe.gob.osinergmin.sio.persistence.dao.TipoIncidenteRepository;
import pe.gob.osinergmin.sio.ro.out.ListTipoIncidenteOutRO;
import pe.gob.osinergmin.sio.ro.out.TipoIncidenteOutRO;
import pe.gob.osinergmin.sio.service.TipoIncidenteService;

@Service("tipoIncidenteService")
public class TipoIncidenteServiceImpl implements TipoIncidenteService {

	@Autowired
	TipoIncidenteRepository tipoIncidenteRepository;

	@Override
	public ListTipoIncidenteOutRO listarActivos() {
		ListTipoIncidenteOutRO listTipoIncidenteOutRO = new ListTipoIncidenteOutRO();
		try {
			List<TipoIncidenteOutRO> listaOutRO = new ArrayList<>();
			List<TipoIncidente> lista = tipoIncidenteRepository.listarActivos();
			for (TipoIncidente tipoIncidente : lista) {
				TipoIncidenteOutRO TipoIncidenteOutRO = new TipoIncidenteOutRO(tipoIncidente.getIdTipo(),
						tipoIncidente.getNombre(), tipoIncidente.getEstado(), tipoIncidente.getIdSector(),
						tipoIncidente.getIcono());
				listaOutRO.add(TipoIncidenteOutRO);
			}
			listTipoIncidenteOutRO.setTipoIncidente(listaOutRO);
			listTipoIncidenteOutRO.setResultCode(InvocationResult.SUCCESS.getCode());
		} catch (Exception e) {
			listTipoIncidenteOutRO.setResultCode(InvocationResult.FAILED.getCode());
			listTipoIncidenteOutRO.setErrorCode(ErrorCode.ERROR.getErrorCode());
			listTipoIncidenteOutRO.setMessage("Error al listar tipos de incidentes activos");
		}
		return listTipoIncidenteOutRO;
	}

	@Override
	public ListTipoIncidenteOutRO listarPorSector(Integer idSector) {
		ListTipoIncidenteOutRO listTipoIncidenteOutRO = new ListTipoIncidenteOutRO();
		try {
			List<TipoIncidenteOutRO> listaOutRO = new ArrayList<>();
			List<TipoIncidente> lista = tipoIncidenteRepository.listarPorSector(idSector);
			for (TipoIncidente tipoIncidente : lista) {
				TipoIncidenteOutRO TipoIncidenteOutRO = new TipoIncidenteOutRO(tipoIncidente.getIdTipo(),
						tipoIncidente.getNombre(), tipoIncidente.getEstado(), tipoIncidente.getIdSector(),
						tipoIncidente.getIcono());
				listaOutRO.add(TipoIncidenteOutRO);
			}
			listTipoIncidenteOutRO.setTipoIncidente(listaOutRO);
			listTipoIncidenteOutRO.setResultCode(InvocationResult.SUCCESS.getCode());
		} catch (Exception e) {
			listTipoIncidenteOutRO.setResultCode(InvocationResult.FAILED.getCode());
			listTipoIncidenteOutRO.setErrorCode(ErrorCode.ERROR.getErrorCode());
			listTipoIncidenteOutRO.setMessage("Error al listar tipos de incidentes por sector");
		}
		return listTipoIncidenteOutRO;
	}
}
