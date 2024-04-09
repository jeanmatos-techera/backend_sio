package pe.gob.osinergmin.sio.ro.out;

public class TareaOutRO {
	private Integer idTarea;
	private Integer idIncidente;
	private String nombre;
	
	public TareaOutRO(Integer idTarea, Integer idIncidente, String nombre) {
		super();
		this.idTarea = idTarea;
		this.idIncidente = idIncidente;
		this.nombre = nombre;
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
	
}
