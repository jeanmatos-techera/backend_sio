package pe.gob.osinergmin.sio.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "SIO_TM_FUENTE_INFORMACION")
public class FuenteInformacion extends Auditoria{

	@Id
    @Column(name = "ID_FUENTE_INFORMACION")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SIO_SEQ_FUEN_INFOR")
    @SequenceGenerator(name = "SIO_SEQ_FUEN_INFOR", sequenceName = "SIO_SEQ_FUEN_INFOR", allocationSize = 1)
	private Integer idFuente;
	@Column(name = "NO_FUENTE_INFORMACION")
	private String nombre;
	
	public Integer getIdFuente() {
		return idFuente;
	}
	public void setIdFuente(Integer idFuente) {
		this.idFuente = idFuente;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	
}
