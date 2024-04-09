package pe.gob.osinergmin.sio.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "SIO_TM_INCIDENTE")
public class Incidente extends Auditoria{
	@Id
    @Column(name = "ID_INCIDENTE")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SIO_SEQ_INCIDENTE")
    @SequenceGenerator(name = "SIO_SEQ_INCIDENTE", sequenceName = "SIO_SEQ_INCIDENTE", allocationSize = 1)
	private Integer idIncidente;
	@Column(name = "DE_TITULO")
	private String titulo;
	@Column(name = "CM_INCIDENTE")
	private String comentario;
	@Column(name = "ID_USUARIO")
	private Integer idUsuario;
	@Column(name = "ID_TIPO")
	private Integer idTipo;	
	@Column(name = "ID_SUBTIPO")
	private Integer idSubTipo;
	@Column(name = "FE_CREACION")
	private Date fechaCreacion;	
	@Column(name = "CO_INCIDENTE")
	private String codigo;
	@Column(name = "ID_SECTOR")
	private Integer idSector;
	@Column(name = "FE_INCIDENTE")
	private Date fechaIncidente;
	@Column(name = "ID_ESTADO")
	private Integer idEstado;
	@Column(name = "ID_CRITICIDAD")
	private Integer idCriticidad;
	@Column(name = "DE_AFECTACION")
	private Integer afectacion;
	@Column(name = "DE_CAUSA")
	private String causa;
	@Column(name = "ID_FUENTE")
	private Integer idFuente;
	@Column(name = "DE_COORDENADA")
	private String coordenada;
	@Column(name = "DE_UBIGEO")
	private String ubigeo;
	@Column(name = "ES_INCIDENTE")
	private String estado;
	@Column(name = "ID_OFICINA_REGIONAL")
	private Integer idOficinaRegional;
	@Column(name = "ID_GRUPO")
	private String grupo;
	@Column(name = "DE_ELIMINACION")
	private String motivoEliminacion;
	@Column(name = "ES_PADRE")
	private Integer esPadre;
	@Column(name = "ID_PADRE")
	private Integer idPadre;
	
	public Integer getIdIncidente() {
		return idIncidente;
	}
	public void setIdIncidente(Integer idIncidente) {
		this.idIncidente = idIncidente;
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
	public Integer getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}
	public Integer getIdTipo() {
		return idTipo;
	}
	public void setIdTipo(Integer idTipo) {
		this.idTipo = idTipo;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public Integer getIdSector() {
		return idSector;
	}
	public void setIdSector(Integer idSector) {
		this.idSector = idSector;
	}
	public Date getFechaIncidente() {
		return fechaIncidente;
	}
	public void setFechaIncidente(Date fechaIncidente) {
		this.fechaIncidente = fechaIncidente;
	}
	public Integer getIdEstado() {
		return idEstado;
	}
	public void setIdEstado(Integer idEstado) {
		this.idEstado = idEstado;
	}
	public Integer getIdCriticidad() {
		return idCriticidad;
	}
	public void setIdCriticidad(Integer idCriticidad) {
		this.idCriticidad = idCriticidad;
	}
	public Integer getAfectacion() {
		return afectacion;
	}
	public void setAfectacion(Integer afectacion) {
		this.afectacion = afectacion;
	}
	public String getCausa() {
		return causa;
	}
	public void setCausa(String causa) {
		this.causa = causa;
	}
	public Integer getIdFuente() {
		return idFuente;
	}
	public void setIdFuente(Integer idFuente) {
		this.idFuente = idFuente;
	}
	public String getCoordenada() {
		return coordenada;
	}
	public void setCoordenada(String coordenada) {
		this.coordenada = coordenada;
	}
	public String getUbigeo() {
		return ubigeo;
	}
	public void setUbigeo(String ubigeo) {
		this.ubigeo = ubigeo;
	}
	public Date getFechaCreacion() {
		return fechaCreacion;
	}
	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public Integer getIdOficinaRegional() {
		return idOficinaRegional;
	}
	public void setIdOficinaRegional(Integer idOficinaRegional) {
		this.idOficinaRegional = idOficinaRegional;
	}
	public String getGrupo() {
		return grupo;
	}
	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}
	public String getMotivoEliminacion() {
		return motivoEliminacion;
	}
	public void setMotivoEliminacion(String motivoEliminacion) {
		this.motivoEliminacion = motivoEliminacion;
	}
	public Integer getEsPadre() {
		return esPadre;
	}
	public void setEsPadre(Integer esPadre) {
		this.esPadre = esPadre;
	}
	public Integer getIdPadre() {
		return idPadre;
	}
	public void setIdPadre(Integer idPadre) {
		this.idPadre = idPadre;
	}
	public Integer getIdSubTipo() {
		return idSubTipo;
	}
	public void setIdSubTipo(Integer idSubTipo) {
		this.idSubTipo = idSubTipo;
	}
	
    
	
}
