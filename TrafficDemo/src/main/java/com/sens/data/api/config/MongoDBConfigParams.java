package com.sens.data.api.config;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import com.sens.data.api.util.CommonUtil;

//import com.sens.data.api.util.CommonUtil;

//import javax.annotation.PostConstruct;

/**
 * Defines attributes to connect to MongoDB database (with prefix "mongodb")
 * specified in the application.yml configuration file
 *
 * @author kaushal
 */
@Component
@ConfigurationProperties(prefix = "mongodb")
public class MongoDBConfigParams {

    @Autowired
    private ApplicationConfigParams applicationConfigParams;

    private String host;
    private int port;
    private String database;
    private String username;
    private String password;
    private String authenticationDB;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getDatabase() {
        return database;
    }

    public void setDatabase(String database) {
        this.database = database;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAuthenticationDB() {
        return authenticationDB;
    }

    public void setAuthenticationDB(String authenticationDB) {
        this.authenticationDB = authenticationDB;
    }

    @PostConstruct
    public void decrypt() {
        this.password = CommonUtil.decrypt(this.password,
                applicationConfigParams.getDecryptionKey());
    }
}