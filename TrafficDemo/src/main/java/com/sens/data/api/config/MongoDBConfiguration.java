package com.sens.data.api.config;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.core.convert.DefaultDbRefResolver;
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;

import javax.annotation.Resource;

import java.net.UnknownHostException;
import java.util.Arrays;

/**
 * Represents the configuration for MongoDB connection attributes
 *
 * @author kaushal
 */
@Configuration
public class MongoDBConfiguration {

    @Resource
    private MongoDBConfigParams mongoDbConfigParams;
    
    @Bean
    public MongoDbFactory mongoDbFactory() throws UnknownHostException {

        //String username = mongoDbConfigParams.getUsername();
        //String password = mongoDbConfigParams.getPassword();
        //String authDB = mongoDbConfigParams.getAuthenticationDB();
        String database = mongoDbConfigParams.getDatabase();
        String hostname = mongoDbConfigParams.getHost();
        int port = mongoDbConfigParams.getPort();

      //  MongoCredential credential = MongoCredential.createCredential(username,
        //        authDB, password.toCharArray());
        ServerAddress serverAddress = new ServerAddress(hostname, port);

        MongoClient client = new MongoClient(serverAddress);
        
    	return new SimpleMongoDbFactory(client, database);
    }

    @Bean
    public MongoTemplate mongoTemplate() throws Exception {
        MappingMongoConverter converter = new MappingMongoConverter(
                new DefaultDbRefResolver(mongoDbFactory()),
                new MongoMappingContext());
        // Removing _class attribute generated by default.
        converter.setTypeMapper(new DefaultMongoTypeMapper(null));
        // Map with keys containing "dots" will be rejected as the conversion
        // for the entire object will fail.
        // Setting the replacement value of "-" for all "dots" in keys.
         //converter.setMapKeyDotReplacement("\\-");
         MongoTemplate mongoTemplate = new MongoTemplate(mongoDbFactory(),
                converter);
        return mongoTemplate;
    }
}