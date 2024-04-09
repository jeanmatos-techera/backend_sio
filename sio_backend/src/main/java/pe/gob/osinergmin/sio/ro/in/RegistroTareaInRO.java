package pe.gob.osinergmin.sio.ro.in;

public class RegistroTareaInRO {
	private Integer idTarea;
	private Integer idIncidente;
	private String nombre;
	private String descripcion;
	
	public RegistroTareaInRO() {
		
	}

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
	
}	
