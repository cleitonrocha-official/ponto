package br.com.cleiton.microservice.alocacoes.outbound.database.config;

import java.io.IOException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.mongodb.MongoClient;

import cz.jirutka.spring.embedmongo.EmbeddedMongoFactoryBean;

@Configuration
public class MongodbEmbeededBeanConfig {

	
	    private static final String MONGO_DB_URL = "localhost";
	    private static final String MONGO_DB_NAME = "microservice-alocacao";
	    
	    @Bean
	    public MongoTemplate mongoTemplate() throws IOException {
	        EmbeddedMongoFactoryBean mongo = new EmbeddedMongoFactoryBean();
	        mongo.setBindIp(MONGO_DB_URL);
	        mongo.setPort(62081);
	        MongoClient mongoClient = mongo.getObject();
	        MongoTemplate mongoTemplate = new MongoTemplate(mongoClient, MONGO_DB_NAME);
	        return mongoTemplate;
	    }
}
