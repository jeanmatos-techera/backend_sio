package pe.gob.osinergmin.sio.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "SIO_TZ_INCIDENTE_ACCION")
public class IncidenteAccion extends Auditoria{
	
	@Id
    @Column(name = "ID_INCIDENTE_ACCION")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SIO_SEQ_INCID_ACCI")
    @SequenceGenerator(name = "SIO_SEQ_INCID_ACCI", sequenceName = "SIO_SEQ_INCID_ACCI", allocationSize = 1)
	private Integer idAccion;
	@Column(name = "DE_ACCION")
	private String accion;
	@Column(name = "ID_INCIDENTE")
	private Integer idIncidente;
	@Column(name = "ID_ESTADO_INCIDENTE")
	private Integer idEstadoIncidente;
	@Column(name = "ES_ACCION")
	private String estado;
	
	public Integer getIdAccion() {
		return idAccion;
	}
	public void setIdAccion(Integer idAccion) {
		this.idAccion = idAccion;
	}
	public String getAccion() {
		return accion;
	}
	public void setAccion(String accion) {
		this.accion = accion;
	}
	public Integer getIdIncidente() {
		return idIncidente;
	}
	public void setIdIncidente(Integer idIncidente) {
		this.idIncidente = idIncidente;
	}
	public Integer getIdEstadoIncidente() {
		return idEstadoIncidente;
	}
	public void setIdEstadoIncidente(Integer idEstadoIncidente) {
		this.idEstadoIncidente = idEstadoIncidente;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}

}
