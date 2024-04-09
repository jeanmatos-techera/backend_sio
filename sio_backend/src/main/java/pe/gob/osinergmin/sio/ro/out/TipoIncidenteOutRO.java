package pe.gob.osinergmin.sio.ro.out;

import java.util.List;

public class TipoIncidenteOutRO {
	private Integer idTipo;
	private String nombre;
	private String estado;
	private Integer idSector;
	private String icono;
	private List<SubTipoIncidenteOutRO> subTiposIncidente;
	
	
	
	public TipoIncidenteOutRO() {
		super();
	}
	public TipoIncidenteOutRO(Integer idTipo, String nombre, String estado, Integer idSector, String icono) {
		super();
		this.idTipo = idTipo;
		this.nombre = nombre;
		this.estado = estado;
		this.idSector = idSector;
		this.icono = icono;
	}
	public Integer getIdTipo() {
		return idTipo;
	}
	public void setIdTipo(Integer idTipo) {
		this.idTipo = idTipo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Integer getIdSector() {
		return idSector;
	}

	public void setIdSector(Integer idSector) {
		this.idSector = idSector;
	}

	public String getIcono() {
		return icono;
	}

	public void setIcono(String icono) {
		this.icono = icono;
	}

	public List<SubTipoIncidenteOutRO> getSubTiposIncidente() {
		return subTiposIncidente;
	}

	public void setSubTiposIncidente(List<SubTipoIncidenteOutRO> subTiposIncidente) {
		this.subTiposIncidente = subTiposIncidente;
	}
	
	
}
