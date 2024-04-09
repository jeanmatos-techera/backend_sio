package pe.gob.osinergmin.sio.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Configuration
@PropertySource({ "classpath:properties/sio_backend.properties" })
public class PropertiesConfiguration {
	
	@Autowired
	private Environment env;

	public Environment getEnv() {
		return env;
	}
	
}
