package pe.gob.osinergmin.sio.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "SIO_TM_SECTOR")
public class Sector extends Auditoria{

	@Id
    @Column(name = "ID_SECTOR")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SIO_SEQ_SECTOR")
    @SequenceGenerator(name = "SIO_SEQ_SECTOR", sequenceName = "SIO_SEQ_SECTOR", allocationSize = 1)
	private Integer idSector;
	@Column(name = "NO_SECTOR")
	private String nombre;
	
	public Integer getIdSector() {
		return idSector;
	}
	public void setIdSector(Integer idSector) {
		this.idSector = idSector;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	
}
