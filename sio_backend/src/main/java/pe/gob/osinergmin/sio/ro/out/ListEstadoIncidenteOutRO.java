package pe.gob.osinergmin.sio.ro.out;

import java.util.List;

public class ListEstadoIncidenteOutRO extends BaseOutRO{

	private List<EstadoIncidenteOutRO> estados;

	public List<EstadoIncidenteOutRO> getEstados() {
		return estados;
	}

	public void setEstados(List<EstadoIncidenteOutRO> estados) {
		this.estados = estados;
	}

}
