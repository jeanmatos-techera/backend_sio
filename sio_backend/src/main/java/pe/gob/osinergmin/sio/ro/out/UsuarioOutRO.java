package pe.gob.osinergmin.sio.ro.out;

import java.util.List;

public class UsuarioOutRO {
	private Integer id;
	private String nombre;
	private String apePaterno;
	private String apeMaterno;
	private String celular;
	private Integer idDocumento;
	private String numDocumento;
	private String direccion;
	private String fotoBase64;
	private String correo;
	private Integer idRol;
	private String nombreRol;
	private String keyRol;
	public String getKeyRol() {
		return keyRol;
	}
	public void setKeyRol(String keyRol) {
		this.keyRol = keyRol;
	}
	private List<UsuarioOROutRO> oficinasRegionales;
	
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
	public String getFotoBase64() {
		return fotoBase64;
	}
	public void setFotoBase64(String fotoBase64) {
		this.fotoBase64 = fotoBase64;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public Integer getIdRol() {
		return idRol;
	}
	public void setIdRol(Integer idRol) {
		this.idRol = idRol;
	}
	public List<UsuarioOROutRO> getOficinasRegionales() {
		return oficinasRegionales;
	}
	public void setOficinasRegionales(List<UsuarioOROutRO> oficinasRegionales) {
		this.oficinasRegionales = oficinasRegionales;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNombreRol() {
		return nombreRol;
	}
	public void setNombreRol(String nombreRol) {
		this.nombreRol = nombreRol;
	}	
	
	
	
}
