package pe.gob.osinergmin.sio.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "SIO_TM_UBIGEO")
public class Ubigeo extends Auditoria{

	@Id
    @Column(name = "ID_UBIGEO")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SIO_SEQ_UBIGEO")
    @SequenceGenerator(name = "SIO_SEQ_UBIGEO", sequenceName = "SIO_SEQ_UBIGEO", allocationSize = 1)
	private Integer idUbigeo;
	@Column(name = "CO_UBIGEO")
	private String codUbigeo;
	@Column(name = "CO_DEPARTAMENTO")
	private String codDepartamento;
	@Column(name = "CO_PROVINCIA")
	private String codProvincia;
	@Column(name = "CO_DISTRITO")
	private String codDistrito;
	@Column(name = "DE_UBIGEO")
	private String descUbigeo;
	@Column(name = "DE_DEPARTAMENTO")
	private String descDepartamento;
	@Column(name = "DE_PROVINCIA")
	private String descProvincia;
	@Column(name = "DE_DISTRITO")
	private String descrDistrito;
	public Integer getIdUbigeo() {
		return idUbigeo;
	}
	public void setIdUbigeo(Integer idUbigeo) {
		this.idUbigeo = idUbigeo;
	}
	public String getCodUbigeo() {
		return codUbigeo;
	}
	public void setCodUbigeo(String codUbigeo) {
		this.codUbigeo = codUbigeo;
	}
	public String getCodDepartamento() {
		return codDepartamento;
	}
	public void setCodDepartamento(String codDepartamento) {
		this.codDepartamento = codDepartamento;
	}
	public String getCodProvincia() {
		return codProvincia;
	}
	public void setCodProvincia(String codProvincia) {
		this.codProvincia = codProvincia;
	}
	public String getCodDistrito() {
		return codDistrito;
	}
	public void setCodDistrito(String codDistrito) {
		this.codDistrito = codDistrito;
	}
	public String getDescUbigeo() {
		return descUbigeo;
	}
	public void setDescUbigeo(String descUbigeo) {
		this.descUbigeo = descUbigeo;
	}
	public String getDescDepartamento() {
		return descDepartamento;
	}
	public void setDescDepartamento(String descDepartamento) {
		this.descDepartamento = descDepartamento;
	}
	public String getDescProvincia() {
		return descProvincia;
	}
	public void setDescProvincia(String descProvincia) {
		this.descProvincia = descProvincia;
	}
	public String getDescrDistrito() {
		return descrDistrito;
	}
	public void setDescrDistrito(String descrDistrito) {
		this.descrDistrito = descrDistrito;
	}
	
	
	
}
