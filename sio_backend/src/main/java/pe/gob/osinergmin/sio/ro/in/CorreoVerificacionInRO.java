package pe.gob.osinergmin.sio.ro.in;

import java.time.LocalDateTime;

public class CorreoVerificacionInRO {
	private Integer idVerificacion;
	private String correo;
	private String codigo;
	private LocalDateTime  fechaRegistro;
	private LocalDateTime fechaExpiracion;

	public Integer getIdVerificacion() {
		return idVerificacion;
	}
	public void setIdVerificacion(Integer idVerificacion) {
		this.idVerificacion = idVerificacion;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public LocalDateTime getFechaRegistro() {
		return fechaRegistro;
	}
	public void setFechaRegistro(LocalDateTime fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}
	public LocalDateTime getFechaExpiracion() {
		return fechaExpiracion;
	}
	public void setFechaExpiracion(LocalDateTime fechaExpiracion) {
		this.fechaExpiracion = fechaExpiracion;
	}
	
	
}
