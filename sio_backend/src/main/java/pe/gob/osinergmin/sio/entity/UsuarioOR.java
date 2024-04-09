package pe.gob.osinergmin.sio.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "SIO_TM_USUARIO_OR")
public class UsuarioOR extends Auditoria{
	
	@Id
    @Column(name = "ID_USUARIO_OR")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SIO_SEQ_USUARIO_OR")
    @SequenceGenerator(name = "SIO_SEQ_USUARIO_OR", sequenceName = "SIO_SEQ_USUARIO_OR", allocationSize = 1)
	private Integer idUsuarioOR;
	@Column(name = "ID_USUARIO")
	private Integer idUsuario;
	@Column(name = "ID_OFICINA_REGIONAL")
	private Integer idOficinaRegional;
	
	public Integer getIdUsuarioOR() {
		return idUsuarioOR;
	}
	public void setIdUsuarioOR(Integer idUsuarioOR) {
		this.idUsuarioOR = idUsuarioOR;
	}
	public Integer getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}
	public Integer getIdOficinaRegional() {
		return idOficinaRegional;
	}
	public void setIdOficinaRegional(Integer idOficinaRegional) {
		this.idOficinaRegional = idOficinaRegional;
	}
}
