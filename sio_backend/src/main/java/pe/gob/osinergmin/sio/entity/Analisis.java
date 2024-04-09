package pe.gob.osinergmin.sio.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "SIO_TM_ANALISIS")
public class Analisis extends Auditoria{

	@Id
    @Column(name = "ID_ANALISIS")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SIO_SEQ_ANALISIS")
    @SequenceGenerator(name = "SIO_SEQ_ANALISIS", sequenceName = "SIO_SEQ_ANALISIS", allocationSize = 1)
	private Integer idAnalisis;
	@Column(name = "ID_INCIDENTE")
	private Integer idIncidente;
	@Column(name = "DE_ANALISIS")
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
