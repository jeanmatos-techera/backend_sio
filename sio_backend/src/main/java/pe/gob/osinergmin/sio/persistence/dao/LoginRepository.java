package pe.gob.osinergmin.sio.persistence.dao;

import pe.gob.osinergmin.sio.entity.Login;

public interface LoginRepository {

	public void guardarToken(Login login);
	
	public Login obtenerUltimoToken(Integer idUsuario);
}
