package pe.gob.osinergmin.sio.service;

import pe.gob.osinergmin.sio.ro.out.ListDetalleSectorOutRO;
import pe.gob.osinergmin.sio.ro.out.ListSectorOutRO;

public interface SectorService {
	
	public ListSectorOutRO listarSectores();
	
	public ListDetalleSectorOutRO listarDetalleSectores();
}
