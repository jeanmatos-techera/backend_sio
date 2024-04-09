package pe.gob.osinergmin.sio.ro.in;

import java.util.List;

public class TareaInRO {
	private Integer idTarea;
	private Integer idIncidente;
	private String nombre;
	private String descripcion;
	private List<FotosTareaInRO> fotos;
	
	public Integer getIdTarea() {
		return idTarea;
	}
	public void setIdTarea(Integer idTarea) {
		this.idTarea = idTarea;
	}
	public Integer getIdIncidente() {
		return idIncidente;
	}
	public void setIdIncidente(Integer idIncidente) {
		this.idIncidente = idIncidente;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public List<FotosTareaInRO> getFotos() {
		return fotos;
	}
	public void setFotos(List<FotosTareaInRO> fotos) {
		this.fotos = fotos;
	}
	
}
