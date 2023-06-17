package in.pk.springcrudapp.mongo.config;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

import in.pk.springcrudapp.constants.DBConstants;
import in.pk.springcrudapp.utils.PropertyUtil;

@Configuration
public class MongoConfig extends AbstractMongoClientConfiguration{
	
	final Properties propertyUtil = PropertyUtil.getStartUpProperties();
	
	@Override
    protected String getDatabaseName() {
        return propertyUtil.getProperty(DBConstants.MONGODB_DATABASE);
    }

    @Override
    public MongoClient mongoClient() {
    	String connectionString = propertyUtil.getProperty(DBConstants.MONGODB_URI).replace("mongodb://", "mongodb://" + propertyUtil.getProperty(DBConstants.MONGODB_USER_NAME) + ":" + propertyUtil.getProperty(DBConstants.MONGODB_PASSWORD) + "@");
		String dbnameWithAuth = getDatabaseName() + "?authSource=MyDB&tls=".concat("tls");
		return MongoClients.create(connectionString.concat("/").concat(dbnameWithAuth));
    }

    @Bean
    public MongoTemplate mongoTemplate() {
        return new MongoTemplate(mongoClient(), getDatabaseName());
    }
}
