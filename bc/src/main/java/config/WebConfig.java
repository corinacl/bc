package config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebMvc
@EnableSpringDataWebSupport 
@ComponentScan(basePackages = {ResourceNames.SERVICES, ResourceNames.REST_API, ResourceNames.CONTROLLERS, ResourceNames.DAOS,
        })
public class WebConfig extends WebMvcConfigurerAdapter {

    // CORS
    @Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**").allowedOrigins("*").maxAge(3600);
    }
}
