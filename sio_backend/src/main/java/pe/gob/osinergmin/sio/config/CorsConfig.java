package pe.gob.osinergmin.sio.config;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
public class CorsConfig {
	
	@Value("${dominio.sio.frontend}")
	private String dominio_sio_frontend;
	
	@Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin(dominio_sio_frontend);
        config.addAllowedHeader("*");
        config.setExposedHeaders(Arrays.asList("Authorization")); // Expose the Authorization header
        config.addAllowedMethod("*");
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
	
}
