package fr.sncf.d2d.up2dev.colibri2.common.rest;

import org.springframework.stereotype.Component;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import fr.sncf.d2d.up2dev.colibri2.common.models.SetFieldValueExtractor;
import jakarta.validation.Configuration;

@Component
public class CustomLocalValidatorFactoryBean extends LocalValidatorFactoryBean {
    
    @Override
    protected void postProcessConfiguration(Configuration<?> configuration) {
        configuration.addValueExtractor(new SetFieldValueExtractor());
    }
}
