package pe.gob.osinergmin.sio.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import pe.gob.osinergmin.sio.persistence.dao.LoginRepository;

//import lombok.AllArgsConstructor;

@Configuration
//@AllArgsConstructor
public class SecurityConfig {
	
	@Autowired
    private LoginRepository loginRepository;
	
	private final UserDetailsService userDetailsService;
	private final JWTAuthorizationFilter jwtAuthorizationFilter;
	
	public SecurityConfig(LoginRepository loginRepository, UserDetailsService userDetailsService,
			JWTAuthorizationFilter jwtAuthorizationFilter) {
		super();
		this.loginRepository = loginRepository;
		this.userDetailsService = userDetailsService;
		this.jwtAuthorizationFilter = jwtAuthorizationFilter;
	}

	@Bean
	SecurityFilterChain filterChain(HttpSecurity http, AuthenticationManager authManager) throws Exception {
		JWTAuthenticationFilter jwtAuthenticationFilter = new JWTAuthenticationFilter(loginRepository);
		jwtAuthenticationFilter.setAuthenticationManager(authManager);
		jwtAuthenticationFilter.setFilterProcessesUrl("/login");
		
		return http
				.cors()
                .and()
                .csrf().disable()
				.authorizeRequests()
				.antMatchers(HttpMethod.GET, "/publico/**").permitAll()
				.antMatchers(HttpMethod.POST, "/usuario/registro/**").permitAll()
				.antMatchers("/incidente/**").permitAll()
				.antMatchers("/comunicacion/**").permitAll()
				.antMatchers("/tarea/**").permitAll()
				.antMatchers("/analisis/**").permitAll()
				//.antMatchers("/incidente/**").hasAnyAuthority("ROLE_ADMINISTRADOR", "ROLE_REGULAR")
				.antMatchers("/usuario/bienvenido").hasAnyAuthority("ROLE_ADMINISTRADOR")
				.antMatchers("/usuario/actualizacion").authenticated()
				.antMatchers("/usuario/cambioClave").authenticated()
				.antMatchers("/usuario/recuperarClave").permitAll()
				.and()
				.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and()
				.addFilter(jwtAuthenticationFilter)
				.addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class)
				.build();
	}
	
	@Bean
	AuthenticationManager authManager(HttpSecurity http) throws Exception {
		return http.getSharedObject(AuthenticationManagerBuilder.class)
				.userDetailsService(userDetailsService)
				.passwordEncoder(passwordEncoder())
				.and()
				.build();
	}
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
