package pe.gob.osinergmin.sio.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "SIO_TM_ROL")
public class Rol extends Auditoria{
	@Id
    @Column(name = "ID_ROL")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SIO_SEQ_ROL")
    @SequenceGenerator(name = "SIO_SEQ_ROL", sequenceName = "SIO_SEQ_ROL", allocationSize = 1)
	private Integer idRol;
	@Column(name = "NO_ROL")
	private String nombre;
	@Column(name = "DE_ROL")
	private String descripcion;
	@Column(name = "KEY_ROL")
	private String key;
	
	public Integer getIdRol() {
		return idRol;
	}
	public void setIdRol(Integer idRol) {
		this.idRol = idRol;
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
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
}
