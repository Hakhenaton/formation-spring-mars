package fr.sncf.d2d.up2dev.colibri2.dummy;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyConfiguration {

    @Bean
    public ClassB toto(){
        return new ClassB();
    }
    
    @Bean
    public ClassB tata(){
        return new ClassB();
    }
}
