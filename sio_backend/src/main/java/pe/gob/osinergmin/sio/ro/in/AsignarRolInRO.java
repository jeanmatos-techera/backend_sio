package pe.gob.osinergmin.sio.ro.in;

import java.util.List;

public class AsignarRolInRO {
	private String correoUsuario;
	private Integer idRol;
	private List<UsuarioORInRO> oficinasRegionales;
	
	public String getCorreoUsuario() {
		return correoUsuario;
	}
	public void setCorreoUsuario(String correoUsuario) {
		this.correoUsuario = correoUsuario;
	}
	public Integer getIdRol() {
		return idRol;
	}
	public void setIdRol(Integer idRol) {
		this.idRol = idRol;
	}
	public List<UsuarioORInRO> getOficinasRegionales() {
		return oficinasRegionales;
	}
	public void setOficinasRegionales(List<UsuarioORInRO> oficinasRegionales) {
		this.oficinasRegionales = oficinasRegionales;
	}
	
}
