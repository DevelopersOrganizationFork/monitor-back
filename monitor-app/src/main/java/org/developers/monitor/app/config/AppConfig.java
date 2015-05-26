package org.developers.monitor.app.config;  
  
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@ComponentScan("org.developers.monitor") 
@EnableWebMvc
@EnableAutoConfiguration
public class AppConfig {  

}  
