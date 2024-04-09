package pe.gob.osinergmin.sio.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import pe.gob.osinergmin.sio.entity.Login;
import pe.gob.osinergmin.sio.persistence.dao.LoginRepository;

@Component
public class JWTAuthorizationFilter extends OncePerRequestFilter {
	
	@Autowired
    private LoginRepository loginRepository;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String bearerToken = request.getHeader("Authorization");
		
		if(bearerToken != null && bearerToken.startsWith("Bearer ")) {
			String token = bearerToken.replace("Bearer ","");
			Integer idUsuario = TokenUtils.getUserIdFromToken(token);
					
            Login ultimoLogin = loginRepository.obtenerUltimoToken(idUsuario);

            if (ultimoLogin != null && ultimoLogin.getToken().equals(token)) {
                UsernamePasswordAuthenticationToken usernamePAT = TokenUtils.getAuthentication(token);
                SecurityContextHolder.getContext().setAuthentication(usernamePAT);
            }
		}
		filterChain.doFilter(request,response);
	}

	
}