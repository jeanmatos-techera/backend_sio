package pe.gob.osinergmin.sio.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "SIO_TM_TAREA")
public class Tarea extends Auditoria{
	
	@Id
    @Column(name = "ID_TAREA")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SIO_SEQ_TAREA")
    @SequenceGenerator(name = "SIO_SEQ_TAREA", sequenceName = "SIO_SEQ_TAREA", allocationSize = 1)
	private Integer idTarea;
    @Column(name = "ID_INCIDENTE")
	private Integer idIncidente;
	@Column(name = "NO_TAREA")
	private String nombre;
	@Column(name = "DE_TAREA")
	private String descripcion;
	
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
