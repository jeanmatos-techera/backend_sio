package pe.gob.osinergmin.sio.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "SIO_TM_COMUNICACION")
public class Comunicacion extends Auditoria{
	
	@Id
    @Column(name = "ID_COMUNICACION")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SIO_SEQ_COMUNICACION")
    @SequenceGenerator(name = "SIO_SEQ_COMUNICACION", sequenceName = "SIO_SEQ_COMUNICACION", allocationSize = 1)
	private Integer idComunicacion;
    @Column(name = "ID_INCIDENTE")
	private Integer idIncidente;
	@Column(name = "DE_COMUNICACION")
	private String descripcion;
	@Column(name = "DE_FAMILIAS_AFECTADAS")
	private String familiasAfectadas;
	@Column(name = "DE_PERSONAS_AFECTADAS")
	private String personasAfectadas;
	@Column(name = "DE_VIVIENDAS_AFECTADAS")
	private String viviendasAfectadas;
	
	public Integer getIdComunicacion() {
		return idComunicacion;
	}
	public void setIdComunicacion(Integer idComunicacion) {
		this.idComunicacion = idComunicacion;
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
	public String getFamiliasAfectadas() {
		return familiasAfectadas;
	}
	public void setFamiliasAfectadas(String familiasAfectadas) {
		this.familiasAfectadas = familiasAfectadas;
	}
	public String getPersonasAfectadas() {
		return personasAfectadas;
	}
	public void setPersonasAfectadas(String personasAfectadas) {
		this.personasAfectadas = personasAfectadas;
	}
	public String getViviendasAfectadas() {
		return viviendasAfectadas;
	}
	public void setViviendasAfectadas(String viviendasAfectadas) {
		this.viviendasAfectadas = viviendasAfectadas;
	}
	
}
