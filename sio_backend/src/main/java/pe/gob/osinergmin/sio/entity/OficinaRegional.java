package pe.gob.osinergmin.sio.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "SIO_TM_OFICINA_REGIONAL")
public class OficinaRegional extends Auditoria{

	@Id
    @Column(name = "ID_OFICINA_REGIONAL")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SIO_SEQ_OFIC_REGIO")
    @SequenceGenerator(name = "SIO_SEQ_OFIC_REGIO", sequenceName = "SIO_SEQ_OFIC_REGIO", allocationSize = 1)
	private Integer idOficina;
	@Column(name = "NO_OFICINA_REGIONAL")
	private String nombre;
	
	public Integer getIdOficina() {
		return idOficina;
	}
	public void setIdOficina(Integer idOficina) {
		this.idOficina = idOficina;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
}
