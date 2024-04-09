package pe.gob.osinergmin.sio.ro.out;

import java.util.Date;

public class IncidenteOutRO{
	
	private Integer idIncidente;
	private Integer idUsuario;
	private String titulo;
	private String comentario;
	private String codigo;
	private Date fechaIncidente;
	private Date fechaRegistro;

	public Integer getIdIncidente() {
		return idIncidente;
	}

	public void setIdIncidente(Integer idIncidente) {
		this.idIncidente = idIncidente;
	}

	public Integer getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public Date getFechaIncidente() {
		return fechaIncidente;
	}

	public void setFechaIncidente(Date fechaIncidente) {
		this.fechaIncidente = fechaIncidente;
	}

	public Date getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}
	
	
}
