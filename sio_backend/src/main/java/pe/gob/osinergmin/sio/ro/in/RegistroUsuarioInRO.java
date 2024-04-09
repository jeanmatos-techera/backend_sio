package pe.gob.osinergmin.sio.ro.in;

public class RegistroUsuarioInRO {
	private String nombre;
	private String apePaterno;
	private String apeMaterno;
	private String celular;
	private Integer idDocumento;
	private String numDocumento;
	private String direccion;
	private String correo;
	private String clave;
	private String codVerificacion;
	private String fotoBase64;
	
	public RegistroUsuarioInRO() {
		
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

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public String getCodVerificacion() {
		return codVerificacion;
	}

	public void setCodVerificacion(String codVerificacion) {
		this.codVerificacion = codVerificacion;
	}

	public String getFotoBase64() {
		return fotoBase64;
	}

	public void setFotoBase64(String fotoBase64) {
		this.fotoBase64 = fotoBase64;
	}
	
}	
