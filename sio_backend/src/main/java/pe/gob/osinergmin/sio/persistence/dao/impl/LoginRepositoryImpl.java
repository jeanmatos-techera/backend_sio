package pe.gob.osinergmin.sio.persistence.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import pe.gob.osinergmin.sio.entity.Login;
import pe.gob.osinergmin.sio.persistence.LoginCrud;
import pe.gob.osinergmin.sio.persistence.dao.LoginRepository;
import pe.gob.osinergmin.sio.util.Constantes;

@Repository
@Transactional(rollbackFor = Exception.class)
public class LoginRepositoryImpl implements LoginRepository {

	@Autowired
	LoginCrud loginCrud;
	
	@Override
	public void guardarToken(Login login) {
		try {
			loginCrud.cambiarEstados(Constantes.ESTADO_INACTIVO, login.getIdUsuario());
			loginCrud.save(login);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public Login obtenerUltimoToken(Integer idUsuario) {
		Login login = null;
		try {
			List<Login> listLogin = loginCrud.obtenerListaToken(Constantes.ESTADO_ACTIVO, idUsuario);
			if(listLogin != null && listLogin.size() > 0) {
				login = new Login();
				login.setToken(listLogin.get(0).getToken());
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return login;
	}

}
