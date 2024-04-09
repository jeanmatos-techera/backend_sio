package pe.gob.osinergmin.sio.ro.out;

public class FotosComunicacionOutRO {
	private Integer idFoto;
	private Integer idComunicacion;
	private String foto;
	
	public FotosComunicacionOutRO(Integer idFoto, Integer idComunicacion, String foto) {
		super();
		this.idFoto = idFoto;
		this.idComunicacion = idComunicacion;
		this.foto = foto;
	}
	
	public Integer getIdFoto() {
		return idFoto;
	}
	public void setIdFoto(Integer idFoto) {
		this.idFoto = idFoto;
	}
	
	public String getFoto() {
		return foto;
	}
	public void setFoto(String foto1) {
		this.foto = foto1;
	}

	public Integer getIdComunicacion() {
		return idComunicacion;
	}

	public void setIdComunicacion(Integer idComunicacion) {
		this.idComunicacion = idComunicacion;
	}
	
	
	
}
