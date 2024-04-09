package pe.gob.osinergmin.sio.ro.out;

import java.util.List;

public class ListConsultaPlacaOutRO extends BaseOutRO {
	
	private List<ConsultaPlacaOutRO> consultaPlaca;
	
	public ListConsultaPlacaOutRO() {
		
	}

	public List<ConsultaPlacaOutRO> getConsultaPlaca() {
		return consultaPlaca;
	}

	public void setConsultaPlaca(List<ConsultaPlacaOutRO> consultaPlaca) {
		this.consultaPlaca = consultaPlaca;
	}
	
}
