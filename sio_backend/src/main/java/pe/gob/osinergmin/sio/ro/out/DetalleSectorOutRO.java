package pe.gob.osinergmin.sio.ro.out;

import java.util.List;

public class DetalleSectorOutRO {

	private Integer idSector;
	private String nombre;
	private List<TipoIncidenteOutRO> tiposIncidente;
	
	public Integer getIdSector() {
		return idSector;
	}
	public void setIdSector(Integer idSector) {
		this.idSector = idSector;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public List<TipoIncidenteOutRO> getTiposIncidente() {
		return tiposIncidente;
	}
	public void setTiposIncidente(List<TipoIncidenteOutRO> tiposIncidente) {
		this.tiposIncidente = tiposIncidente;
	}
	
	
	
}
