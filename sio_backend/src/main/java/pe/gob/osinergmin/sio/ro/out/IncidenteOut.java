package pe.gob.osinergmin.sio.ro.out;

import java.util.Date;

public class IncidenteOut extends BaseOutRO{
	private Integer idIncidente;
	private Integer idUsuario;
	private String titulo;
	private String comentario;
	private String codigo;
	private Date fechaCreacion;
	private Date fechaActualizacion;

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

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Date getFechaActualizacion() {
		return fechaActualizacion;
	}

	public void setFechaActualizacion(Date fechaActualizacion) {
		this.fechaActualizacion = fechaActualizacion;
	}
	
}
