package pe.gob.osinergmin.sio.entity;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@MappedSuperclass
public class Auditoria {

	@Basic(optional = false)
	@Column(name = "US_CREACION", updatable = false)
	protected String usuarioCreacion;

	@Basic(optional = false)
	@Column(name = "FE_CREACION", updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	protected Date fechaCreacion;

	@Basic(optional = false)
	@Column(name = "IP_CREACION", updatable = false)
	protected String terminalCreacion;

	@Column(name = "US_ACTUALIZACION", insertable = false)
	protected String usuarioActualizacion;

	@Column(name = "FE_ACTUALIZACION", insertable = false)
	@Temporal(TemporalType.TIMESTAMP)
	protected Date fechaActualizacion;

	@Column(name = "IP_ACTUALIZACION", insertable = false)
	protected String terminalActualizacion;

	public String getUsuarioCreacion() {
		return usuarioCreacion;
	}

	public void setUsuarioCreacion(String usuarioCreacion) {
		this.usuarioCreacion = usuarioCreacion;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public String getTerminalCreacion() {
		return terminalCreacion;
	}

	public void setTerminalCreacion(String terminalCreacion) {
		this.terminalCreacion = terminalCreacion;
	}

	public String getUsuarioActualizacion() {
		return usuarioActualizacion;
	}

	public void setUsuarioActualizacion(String usuarioActualizacion) {
		this.usuarioActualizacion = usuarioActualizacion;
	}

	public Date getFechaActualizacion() {
		return fechaActualizacion;
	}

	public void setFechaActualizacion(Date fechaActualizacion) {
		this.fechaActualizacion = fechaActualizacion;
	}

	public String getTerminalActualizacion() {
		return terminalActualizacion;
	}

	public void setTerminalActualizacion(String terminalActualizacion) {
		this.terminalActualizacion = terminalActualizacion;
	}
	
}
