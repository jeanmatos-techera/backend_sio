package pe.gob.osinergmin.sio.ro.out;

import java.util.List;

public class ComunicacionOutRO extends BaseOutRO {
	private Integer idComunicacion;
	private Integer idIncidente;
	private String descripcion;
	private String familiasAfectadas;
	private String personasAfectadas;
	private String viviendasAfectadas;
	private List<FotosComunicacionOutRO> fotos;
	
	public Integer getIdComunicacion() {
		return idComunicacion;
	}
	public void setIdComunicacion(Integer idComunicacion) {
		this.idComunicacion = idComunicacion;
	}
	public Integer getIdIncidente() {
		return idIncidente;
	}
	public void setIdIncidente(Integer idIncidente) {
		this.idIncidente = idIncidente;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getFamiliasAfectadas() {
		return familiasAfectadas;
	}
	public void setFamiliasAfectadas(String familiasAfectadas) {
		this.familiasAfectadas = familiasAfectadas;
	}
	public String getPersonasAfectadas() {
		return personasAfectadas;
	}
	public void setPersonasAfectadas(String personasAfectadas) {
		this.personasAfectadas = personasAfectadas;
	}
	public String getViviendasAfectadas() {
		return viviendasAfectadas;
	}
	public void setViviendasAfectadas(String viviendasAfectadas) {
		this.viviendasAfectadas = viviendasAfectadas;
	}
	public List<FotosComunicacionOutRO> getFotos() {
		return fotos;
	}
	public void setFotos(List<FotosComunicacionOutRO> fotos) {
		this.fotos = fotos;
	}
	
	
}
