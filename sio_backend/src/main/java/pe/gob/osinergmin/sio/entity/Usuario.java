package pe.gob.osinergmin.sio.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "SIO_TM_USUARIO")
public class Usuario extends Auditoria{
	
	@Id
    @Column(name = "ID_USUARIO")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SIO_SEQ_USUARIO")
    @SequenceGenerator(name = "SIO_SEQ_USUARIO", sequenceName = "SIO_SEQ_USUARIO", allocationSize = 1)
	private Integer idUsuario;
	@Column(name = "NO_USUARIO")
	private String nombre;
	@Column(name = "AP_PATERNO")
	private String apePaterno;
	@Column(name = "AP_MATERNO")
	private String apeMaterno;
	@Column(name = "NU_CELULAR")
	private String celular;
	@Column(name = "ID_DOCUMENTO")
	private Integer idDocumento;
	@Column(name = "NU_DOCUMENTO")
	private String numDocumento;
	@Column(name = "DE_DIRECCION")
	private String direccion;
	@Column(name = "DE_CORREO")
	private String correo;
	@Column(name = "DE_CLAVE")
	private String contraseña;
	@Column(name = "DE_FOTO")
	private byte[] foto;
	@JoinColumn(name = "ID_ROL", referencedColumnName = "ID_ROL")
    @ManyToOne
	private Rol idRol;
	@Column(name = "ES_USUARIO")
	private String estado;
	
	public Integer getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApePaterno() {
		return apePaterno;
	}
	public void setApePaterno(String apePaterno) {
		this.apePaterno = apePaterno;
	}
	public String getApeMaterno() {
		return apeMaterno;
	}
	public void setApeMaterno(String apeMaterno) {
		this.apeMaterno = apeMaterno;
	}
	public String getCelular() {
		return celular;
	}
	public void setCelular(String celular) {
		this.celular = celular;
	}
	public Integer getIdDocumento() {
		return idDocumento;
	}
	public void setIdDocumento(Integer idDocumento) {
		this.idDocumento = idDocumento;
	}
	public String getNumDocumento() {
		return numDocumento;
	}
	public void setNumDocumento(String numDocumento) {
		this.numDocumento = numDocumento;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public String getContraseña() {
		return contraseña;
	}
	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}
	public byte[] getFoto() {
		return foto;
	}
	public void setFoto(byte[] foto) {
		this.foto = foto;
	}
	public Rol getIdRol() {
		return idRol;
	}
	public void setIdRol(Rol idRol) {
		this.idRol = idRol;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	
}
