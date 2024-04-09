package pe.gob.osinergmin.sio.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "SIO_TM_SUBTIPO_INCIDENTE")
public class SubTipoIncidente extends Auditoria{

	@Id
    @Column(name = "ID_SUBTIPO_INCIDENTE")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SIO_SEQ_SUBTIPO_INCID")
    @SequenceGenerator(name = "SIO_SEQ_SUBTIPO_INCID", sequenceName = "SIO_SEQ_SUBTIPO_INCID", allocationSize = 1)
	private Integer idSubTipo;
	@Column(name = "NO_SUBTIPO_INCIDENTE")
	private String nombre;
	@Column(name = "ES_SUBTIPO_INCIDENTE")
	private String estado;
	@Column(name = "ID_TIPO_INCIDENTE")
	private Integer idTipo;	
	@Column(name = "NO_ICONO")
	private String icono;
	
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

	public Integer getIdSubTipo() {
		return idSubTipo;
	}
	public void setIdSubTipo(Integer idSubTipo) {
		this.idSubTipo = idSubTipo;
	}
	public String getIcono() {
		return icono;
	}
	public void setIcono(String icono) {
		this.icono = icono;
	}
	
}
