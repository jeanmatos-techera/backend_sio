package pe.gob.osinergmin.sio.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "SIO_TM_TIPO_DOCUMENTO")
public class TipoDocumento extends Auditoria{
	@Id
    @Column(name = "ID_TIPO_DOCUMENTO")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SIO_SEQ_TIPO_DOCU")
    @SequenceGenerator(name = "SIO_SEQ_TIPO_DOCU", sequenceName = "SIO_SEQ_TIPO_DOCU", allocationSize = 1)
	private Integer idTipoDocumento;
	@Column(name = "NO_TIPO_DOCUMENTO")
	private String nombre;
	
	public Integer getIdTipoDocumento() {
		return idTipoDocumento;
	}
	public void setIdTipoDocumento(Integer idTipoDocumento) {
		this.idTipoDocumento = idTipoDocumento;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	
}
