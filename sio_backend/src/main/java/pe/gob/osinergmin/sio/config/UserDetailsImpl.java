package pe.gob.osinergmin.sio.config;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import pe.gob.osinergmin.sio.entity.Usuario;
import pe.gob.osinergmin.sio.ro.out.UsuarioOutRO;

//import lombok.AllArgsConstructor;

//@AllArgsConstructor
public class UserDetailsImpl implements UserDetails{

	private static final long serialVersionUID = 1L;
	
	private Usuario usuario;
	private UsuarioOutRO usuarioOutRO;
	private List<GrantedAuthority> authoritiesList;
	

	public UserDetailsImpl(Usuario usuario, UsuarioOutRO usuarioOutRO, List<GrantedAuthority> authoritiesList) {
		super();
		this.usuario = usuario;
		this.usuarioOutRO = usuarioOutRO;
		this.authoritiesList = authoritiesList;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authoritiesList;
	}

	@Override
	public String getPassword() {
		return usuario.getContraseña();
	}

	@Override
	public String getUsername() {
		return usuario.getCorreo();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
	
	public String getNombre() {
		return usuario.getNombre();
	}

	protected UsuarioOutRO getUsuarioOutRO() {
		return usuarioOutRO;
	}

	protected Usuario getUsuario() {
		return usuario;
	}

}
