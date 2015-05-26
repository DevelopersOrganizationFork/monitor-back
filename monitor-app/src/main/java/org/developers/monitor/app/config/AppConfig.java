package org.developers.monitor.app.config;  
  
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@ComponentScan("org.developers.monitor") 
@EnableWebMvc
@EnableAutoConfiguration
@EnableBatchProcessing
public class AppConfig {  

}  
