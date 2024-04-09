package pe.gob.osinergmin.sio.ro.out;

public class IncidenteIntegrarOutRO {
	private Integer idIncidente;
	private String titulo;
	private String codigo;
	private Integer idPadre;
	private Integer esPadre;
	private Integer integrado;
	
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
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public Integer getIdPadre() {
		return idPadre;
	}
	public void setIdPadre(Integer idPadre) {
		this.idPadre = idPadre;
	}
	public Integer getEsPadre() {
		return esPadre;
	}
	public void setEsPadre(Integer esPadre) {
		this.esPadre = esPadre;
	}
	public Integer getIntegrado() {
		return integrado;
	}
	public void setIntegrado(Integer integrado) {
		this.integrado = integrado;
	}	
	
	
	
	
}
