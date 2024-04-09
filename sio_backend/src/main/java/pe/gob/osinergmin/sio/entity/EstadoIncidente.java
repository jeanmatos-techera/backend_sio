package pe.gob.osinergmin.sio.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "SIO_TM_ESTADO_INCIDENTE")
public class EstadoIncidente extends Auditoria{
	
	@Id
    @Column(name = "ID_ESTADO_INCIDENTE")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SIO_SEQ_ESTA_INCID")
    @SequenceGenerator(name = "SIO_SEQ_ESTA_INCID", sequenceName = "SIO_SEQ_ESTA_INID", allocationSize = 1)
	private Integer idEstado;
	@Column(name = "NO_ESTADO")
	private String nombre;
	
	public Integer getIdEstado() {
		return idEstado;
	}
	public void setIdEstado(Integer idEstado) {
		this.idEstado = idEstado;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}
