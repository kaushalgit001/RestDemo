package com.sens.data.api.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Initializes and loads application.yml file into a PropertySource (represents
 * a name & value property pairs)
 * 
 * @author kaushal
 */
@Component
@ConfigurationProperties(prefix = "application")
public class ApplicationConfigParams {

    // Defines the key for encryption algorithm
    private String decryptionKey;
    private String metadataServiceEnv;

    public String getDecryptionKey() {
        return decryptionKey;
    }

    public void setDecryptionKey(String decryptionKey) {
        this.decryptionKey = decryptionKey;
    }

    public String getMetadataServiceEnv() {
        return metadataServiceEnv;
    }

    public void setMetadataServiceEnv(String metadataServiceEnv) {
        this.metadataServiceEnv = metadataServiceEnv;
    }
}
