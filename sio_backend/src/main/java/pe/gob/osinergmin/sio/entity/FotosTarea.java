package pe.gob.osinergmin.sio.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "SIO_TM_FOTOS_TAREA")
public class FotosTarea extends Auditoria{
	
	@Id
    @Column(name = "ID_FOTO")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SIO_SEQ_FOTO_TAREA")
    @SequenceGenerator(name = "SIO_SEQ_FOTO_TAREA", sequenceName = "SIO_SEQ_FOTO_TAREA", allocationSize = 1)
	private Integer idFoto;
    @Column(name = "ID_TAREA")
	private Integer idTarea;
	@Column(name = "DE_FOTO")
	private byte[] foto;
	
	public Integer getIdFoto() {
		return idFoto;
	}
	public void setIdFoto(Integer idFoto) {
		this.idFoto = idFoto;
	}
	public Integer getIdTarea() {
		return idTarea;
	}
	public void setIdTarea(Integer idTarea) {
		this.idTarea = idTarea;
	}
	public byte[] getFoto() {
		return foto;
	}
	public void setFoto(byte[] foto) {
		this.foto = foto;
	}
	
}
