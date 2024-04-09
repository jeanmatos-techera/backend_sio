package pe.gob.osinergmin.sio.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.gob.osinergmin.sio.entity.Sector;
import pe.gob.osinergmin.sio.entity.SubTipoIncidente;
import pe.gob.osinergmin.sio.entity.TipoIncidente;
import pe.gob.osinergmin.sio.enums.ErrorCode;
import pe.gob.osinergmin.sio.enums.InvocationResult;
import pe.gob.osinergmin.sio.persistence.dao.SectorRepository;
import pe.gob.osinergmin.sio.persistence.dao.SubTipoIncidenteRepository;
import pe.gob.osinergmin.sio.persistence.dao.TipoIncidenteRepository;
import pe.gob.osinergmin.sio.ro.out.DetalleSectorOutRO;
import pe.gob.osinergmin.sio.ro.out.ListDetalleSectorOutRO;
import pe.gob.osinergmin.sio.ro.out.ListSectorOutRO;
import pe.gob.osinergmin.sio.ro.out.SectorOutRO;
import pe.gob.osinergmin.sio.ro.out.SubTipoIncidenteOutRO;
import pe.gob.osinergmin.sio.ro.out.TipoIncidenteOutRO;
import pe.gob.osinergmin.sio.service.SectorService;

@Service
public class SectorServiceImpl implements SectorService {

	@Autowired
	SectorRepository sectorRepository;

	@Autowired
	TipoIncidenteRepository tipoIncidenteRepository;
	
	@Autowired
	SubTipoIncidenteRepository subTipoIncidenteRepository;

	@Override
	public ListSectorOutRO listarSectores() {
		ListSectorOutRO listSectorOutRO = new ListSectorOutRO();
		List<SectorOutRO> listOutRO = new ArrayList<>();
		try {
			List<Sector> list = sectorRepository.listarSectores();
			for (Sector sector : list) {
				SectorOutRO sectorOutRO = new SectorOutRO();
				sectorOutRO.setIdSector(sector.getIdSector());
				sectorOutRO.setNombre(sector.getNombre());
				listOutRO.add(sectorOutRO);
			}
			listSectorOutRO.setResultCode(InvocationResult.SUCCESS.getCode());
		} catch (Exception e) {
			listSectorOutRO.setResultCode(InvocationResult.FAILED.getCode());
			listSectorOutRO.setErrorCode(ErrorCode.ERROR.getErrorCode());
			listSectorOutRO.setMessage("Error al listar los sectores");
		}
		listSectorOutRO.setSector(listOutRO);
		return listSectorOutRO;
	}

	@Override
	public ListDetalleSectorOutRO listarDetalleSectores() {
		ListDetalleSectorOutRO listDetalleSectorOutRO = new ListDetalleSectorOutRO();
		List<DetalleSectorOutRO> listOutRO = new ArrayList<>();
		try {
			List<Sector> listSector = sectorRepository.listarSectores();
			for (Sector sector : listSector) {
				DetalleSectorOutRO detalleSectorOutRO = new DetalleSectorOutRO();
				detalleSectorOutRO.setIdSector(sector.getIdSector());
				detalleSectorOutRO.setNombre(sector.getNombre());

				List<TipoIncidenteOutRO> listaTipoIncidenteOutRO = new ArrayList<>();
				List<TipoIncidente> listTipoIncidente = tipoIncidenteRepository.listarPorSector(sector.getIdSector());
				for (TipoIncidente tipoIncidente : listTipoIncidente) {
					TipoIncidenteOutRO TipoIncidenteOutRO = new TipoIncidenteOutRO();
					
					TipoIncidenteOutRO.setIdTipo(tipoIncidente.getIdTipo());
					TipoIncidenteOutRO.setNombre(tipoIncidente.getNombre());
					TipoIncidenteOutRO.setEstado(tipoIncidente.getEstado());
					TipoIncidenteOutRO.setIdSector(tipoIncidente.getIdSector());
					TipoIncidenteOutRO.setIcono(tipoIncidente.getIcono());
					
					List<SubTipoIncidenteOutRO> listaSubTipoIncidenteOutRO = new ArrayList<>();
					List<SubTipoIncidente> listSubTipoIncidente = subTipoIncidenteRepository.listarPorTipo(tipoIncidente.getIdTipo());
					
						for (SubTipoIncidente subTipoIncidente : listSubTipoIncidente) {
							SubTipoIncidenteOutRO subTipoIncidenteOutRO = new SubTipoIncidenteOutRO();
							
							subTipoIncidenteOutRO.setIdSubTipo(subTipoIncidente.getIdSubTipo());
							subTipoIncidenteOutRO.setNombre(subTipoIncidente.getNombre());
							subTipoIncidenteOutRO.setEstado(subTipoIncidente.getEstado());
							subTipoIncidenteOutRO.setIdTipo(subTipoIncidente.getIdTipo());
							subTipoIncidenteOutRO.setIcono(subTipoIncidente.getIcono());				
							
							listaSubTipoIncidenteOutRO.add(subTipoIncidenteOutRO); //Se va creando lista de SUBTIPOS
						}
					TipoIncidenteOutRO.setSubTiposIncidente(listaSubTipoIncidenteOutRO); // agrega la lista de SUBTIPOS en un Tipo
										
					listaTipoIncidenteOutRO.add(TipoIncidenteOutRO); // se va creando la lista de Tipos
				}

				detalleSectorOutRO.setTiposIncidente(listaTipoIncidenteOutRO);
				listOutRO.add(detalleSectorOutRO);
			}
			listDetalleSectorOutRO.setResultCode(InvocationResult.SUCCESS.getCode());
		} catch (Exception e) {
			listDetalleSectorOutRO.setResultCode(InvocationResult.FAILED.getCode());
			listDetalleSectorOutRO.setErrorCode(ErrorCode.ERROR.getErrorCode());
			listDetalleSectorOutRO.setMessage("Error al listar el detalle de los sectores");
		}
		listDetalleSectorOutRO.setSectores(listOutRO);
		return listDetalleSectorOutRO;
	}
}
