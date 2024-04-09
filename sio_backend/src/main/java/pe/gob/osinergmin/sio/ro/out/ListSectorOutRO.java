package pe.gob.osinergmin.sio.ro.out;

import java.util.List;

public class ListSectorOutRO extends BaseOutRO{

	private List<SectorOutRO> sectores;

	public List<SectorOutRO> getSector() {
		return sectores;
	}

	public void setSector(List<SectorOutRO> sectores) {
		this.sectores = sectores;
	}

}
