package pe.gob.osinergmin.sio.ro.out;

public class FotosTareaOutRO {
	private Integer idFoto;
	private Integer idTarea;
	private String foto;
	
	public FotosTareaOutRO(Integer idFoto, Integer idTarea, String foto) {
		super();
		this.idFoto = idFoto;
		this.idTarea = idTarea;
		this.foto = foto;
	}
	
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
	public String getFoto() {
		return foto;
	}
	public void setFoto(String foto1) {
		this.foto = foto1;
	}
	
	
}
