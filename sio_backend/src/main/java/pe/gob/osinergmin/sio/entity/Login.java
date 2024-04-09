package pe.gob.osinergmin.sio.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "SIO_TM_LOGIN")
public class Login extends Auditoria{

	@Id
    @Column(name = "ID_LOGIN")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SIO_SEQ_LOGIN")
    @SequenceGenerator(name = "SIO_SEQ_LOGIN", sequenceName = "SIO_SEQ_LOGIN", allocationSize = 1)
	private Integer idLogin;
	@Column(name = "ID_USUARIO")
	private Integer idUsuario;
	@Column(name = "ES_TOKEN")
	private String estado;
	@Column(name = "DE_TOKEN")
	private String token;
	
	public Integer getIdLogin() {
		return idLogin;
	}
	public void setIdLogin(Integer idLogin) {
		this.idLogin = idLogin;
	}
	public Integer getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	
}
