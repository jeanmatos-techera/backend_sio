package pe.gob.osinergmin.sio.entity;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "SIO_TM_VERIFICACION_CORREO")
public class CorreoVerificacion extends Auditoria{
	
	@Id
    @Column(name = "ID_VERIFICACION")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SIO_SEQ_VERI_CORR")
    @SequenceGenerator(name = "SIO_SEQ_VERI_CORR", sequenceName = "SIO_SEQ_VERI_CORR", allocationSize = 1)
	private Integer idVerificacion;
	@Column(name = "DE_CORREO")
	private String correo;
	@Column(name = "CO_VERIFICACION")
	private String codigo;
	@Column(name = "FE_REGISTRO")
	private LocalDateTime  fechaRegistro;
	@Column(name = "FE_EXPIRACION")
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
