package pe.gob.osinergmin.sio.email;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.spring5.SpringTemplateEngine;

@Configuration
public class ThymeleafTemplateConfig {

	@Bean
    public SpringTemplateEngine springTemplateEngine() {
        SpringTemplateEngine springTemplateEngine = new SpringTemplateEngine();
        //springTemplateEngine.addTemplateResolver(emailTemplateResolver());
        return springTemplateEngine;
    }
}
