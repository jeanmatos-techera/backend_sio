package pe.gob.osinergmin.sio.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "SIO_TM_FOTOS_INCIDENTE")
public class FotosIncidente extends Auditoria{
	@Id
    @Column(name = "ID_FOTO")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SIO_SEQ_FOTO_INCID")
    @SequenceGenerator(name = "SIO_SEQ_FOTO_INCID", sequenceName = "SIO_SEQ_FOTO_INCID", allocationSize = 1)
	private Integer idFotos;
	@Column(name = "ID_INCIDENTE")
	private Integer idIncidente;
	@Column(name = "DE_FOTO1")
	private byte[] foto1;
	@Column(name = "DE_FOTO2")
	private byte[] foto2;
	@Column(name = "DE_FOTO3")
	private byte[] foto3;
	@Column(name = "DE_FOTO4")
	private byte[] foto4;
	@Column(name = "DE_FOTO5")
	private byte[] foto5;
	@Column(name = "DE_FOTO6")
	private byte[] foto6;
	@Column(name = "DE_FOTO7")
	private byte[] foto7;
	@Column(name = "DE_FOTO8")
	private byte[] foto8;
	
	public Integer getIdFotos() {
		return idFotos;
	}
	public void setIdFotos(Integer idFotos) {
		this.idFotos = idFotos;
	}
	public Integer getIdIncidente() {
		return idIncidente;
	}
	public void setIdIncidente(Integer idIncidente) {
		this.idIncidente = idIncidente;
	}
	public byte[] getFoto1() {
		return foto1;
	}
	public void setFoto1(byte[] foto1) {
		this.foto1 = foto1;
	}
	public byte[] getFoto2() {
		return foto2;
	}
	public void setFoto2(byte[] foto2) {
		this.foto2 = foto2;
	}
	public byte[] getFoto3() {
		return foto3;
	}
	public void setFoto3(byte[] foto3) {
		this.foto3 = foto3;
	}
	public byte[] getFoto4() {
		return foto4;
	}
	public void setFoto4(byte[] foto4) {
		this.foto4 = foto4;
	}
	public byte[] getFoto5() {
		return foto5;
	}
	public void setFoto5(byte[] foto5) {
		this.foto5 = foto5;
	}
	public byte[] getFoto6() {
		return foto6;
	}
	public void setFoto6(byte[] foto6) {
		this.foto6 = foto6;
	}
	public byte[] getFoto7() {
		return foto7;
	}
	public void setFoto7(byte[] foto7) {
		this.foto7 = foto7;
	}
	public byte[] getFoto8() {
		return foto8;
	}
	public void setFoto8(byte[] foto8) {
		this.foto8 = foto8;
	}
	
	
}
