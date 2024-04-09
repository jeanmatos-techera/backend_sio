package pe.gob.osinergmin.sio.ro.out;

import java.util.List;

public class ListTipoIncidenteOutRO extends BaseOutRO{
	
	private List<TipoIncidenteOutRO> tipoIncidente;

	public List<TipoIncidenteOutRO> getTipoIncidente() {
		return tipoIncidente;
	}

	public void setTipoIncidente(List<TipoIncidenteOutRO> tipoIncidente) {
		this.tipoIncidente = tipoIncidente;
	}

}
