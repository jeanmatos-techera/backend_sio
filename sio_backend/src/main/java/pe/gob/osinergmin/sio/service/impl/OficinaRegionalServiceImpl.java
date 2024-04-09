package pe.gob.osinergmin.sio.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.gob.osinergmin.sio.entity.OficinaRegional;
import pe.gob.osinergmin.sio.enums.ErrorCode;
import pe.gob.osinergmin.sio.enums.InvocationResult;
import pe.gob.osinergmin.sio.persistence.dao.OficinaRegionalRepository;
import pe.gob.osinergmin.sio.ro.out.ListOficinaRegionalOutRO;
import pe.gob.osinergmin.sio.ro.out.OficinaRegionalOutRO;
import pe.gob.osinergmin.sio.service.OficinaRegionalService;

@Service
public class OficinaRegionalServiceImpl implements OficinaRegionalService {

	@Autowired
	OficinaRegionalRepository oficinaRegionalRepository;

	@Override
	public ListOficinaRegionalOutRO listarOficinasRegionales() {
		ListOficinaRegionalOutRO listOficinaRegionalOutRO = new ListOficinaRegionalOutRO();
		List<OficinaRegionalOutRO> listOutRO = new ArrayList<>();
		try {
			List<OficinaRegional> list = oficinaRegionalRepository.listarOficinasRegionales();
			for (OficinaRegional oficina : list) {
				OficinaRegionalOutRO oficinaOutRO = new OficinaRegionalOutRO();
				oficinaOutRO.setIdOficina(oficina.getIdOficina());
				oficinaOutRO.setNombre(oficina.getNombre());
				listOutRO.add(oficinaOutRO);
			}
			listOficinaRegionalOutRO.setResultCode(InvocationResult.SUCCESS.getCode());
		} catch (Exception e) {
			listOficinaRegionalOutRO.setResultCode(InvocationResult.FAILED.getCode());
			listOficinaRegionalOutRO.setErrorCode(ErrorCode.ERROR.getErrorCode());
			listOficinaRegionalOutRO.setMessage("Error al listar las oficinas regionales");
		}
		listOficinaRegionalOutRO.setOficinasRegionales(listOutRO);
		return listOficinaRegionalOutRO;
	}
}
