package pe.gob.osinergmin.sio.ro.out;

public class AnalisisOutRO extends BaseOutRO {
	private Integer idAnalisis;
	private Integer idIncidente;
	private String descripcion;
	
	public Integer getIdAnalisis() {
		return idAnalisis;
	}
	public void setIdAnalisis(Integer idAnalisis) {
		this.idAnalisis = idAnalisis;
	}
	public Integer getIdIncidente() {
		return idIncidente;
	}
	public void setIdIncidente(Integer idIncidente) {
		this.idIncidente = idIncidente;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
}
