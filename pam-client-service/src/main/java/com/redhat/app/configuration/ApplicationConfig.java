package com.redhat.app.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
@Configuration
@ConfigurationProperties(prefix="expenses")
public class ApplicationConfig {

    private String processServiceUrl;
    private String validationServiceUrl;
    
    public String getProcessServiceUrl() {
        return this.processServiceUrl;
    }
    
    public void setProcessServiceUrl(String processServiceUrl) {
        this.processServiceUrl = processServiceUrl;
    }
    
    
    public String getValidationServiceUrl() {
        return this.validationServiceUrl;
    }    
    
    public void setValidationServiceUrl(String validationServiceUrl) {
        this.validationServiceUrl = validationServiceUrl;
    }    
}
