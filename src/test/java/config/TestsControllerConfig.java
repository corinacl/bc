package config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {ResourceNames.CONTROLLERS})
public class TestsControllerConfig {

}
