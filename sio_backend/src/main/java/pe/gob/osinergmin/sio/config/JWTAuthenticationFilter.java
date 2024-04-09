package pe.gob.osinergmin.sio.config;

import java.io.IOException;
import java.util.Collections;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import pe.gob.osinergmin.sio.entity.Login;
import pe.gob.osinergmin.sio.entity.Usuario;
import pe.gob.osinergmin.sio.persistence.dao.LoginRepository;
import pe.gob.osinergmin.sio.util.Constantes;
import pe.gob.osinergmin.sio.util.JsonUtil;
import com.fasterxml.jackson.databind.ObjectMapper;


public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter{

	private final LoginRepository loginRepository;

    public JWTAuthenticationFilter(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException{
		Usuario authCredentials = new Usuario();
		try {
			authCredentials = new ObjectMapper().readValue(request.getReader(),Usuario.class);

		}catch(IOException e){
			
		}
		UsernamePasswordAuthenticationToken usernamePAT = new UsernamePasswordAuthenticationToken(
				authCredentials.getCorreo(),
				authCredentials.getContraseña(),
				Collections.emptyList()
				);
		return getAuthenticationManager().authenticate(usernamePAT);
	}
	
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
			FilterChain chain, Authentication authResult) throws IOException, ServletException{
		
		UserDetailsImpl userDetails = (UserDetailsImpl) authResult.getPrincipal();
		String token = TokenUtils.createToken(userDetails.getUsuario().getIdUsuario(),userDetails.getUsername());
		//response.addHeader("Authorization","bearer "+token);
	
		String usuarioJson = JsonUtil.convertirUsuarioAJson(userDetails.getUsuarioOutRO());

		response.setCharacterEncoding("UTF-8");
		response.setHeader("Content-Type", "application/json; charset=UTF-8");
        //response.getWriter().write(usuarioJson);
		//response.getWriter().flush();
		
		for(GrantedAuthority obj: userDetails.getAuthorities()) {
			if(obj.getAuthority().equals("USER_NO_VALIDATE")) {
				response.addHeader("Authorization", "USER_NO_VALIDATE");
			} else {
				response.addHeader("Authorization","bearer "+token);
				response.getWriter().write(usuarioJson);
			}
		}
		response.getWriter().flush();
		
		super.successfulAuthentication(request,response,chain,authResult);
		Login login = new Login();
		login.setIdUsuario(userDetails.getUsuario().getIdUsuario());
		login.setEstado(Constantes.ESTADO_ACTIVO);
		login.setFechaCreacion(new Date());
		login.setUsuarioCreacion("OSI");
		login.setTerminalCreacion("LOCALHOST");
		login.setToken(token);
		loginRepository.guardarToken(login);
	}
	
}
