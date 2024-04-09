package pe.gob.osinergmin.sio.ro.in;

import java.util.List;

public class RegistroComunicacionInRO {
	private Integer idComunicacion;
	private Integer idIncidente;
	private String nombre;
	private String descripcion;
	private String familiasAfectadas;
	private String personasAfectadas;
	private String viviendasAfectadas;
	
	private List<FotosComunicacionInRO> fotos;
	
	public RegistroComunicacionInRO() {
		
	}

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

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
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

	public List<FotosComunicacionInRO> getFotos() {
		return fotos;
	}

	public void setFotos(List<FotosComunicacionInRO> fotos) {
		this.fotos = fotos;
	}
	
	
	
}	
