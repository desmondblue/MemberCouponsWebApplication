package com.coding.Configuration;

import com.coding.encryption.EncryptorClass;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.tomcat.jdbc.pool.DataSourceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.sql.DataSource;
import java.util.Base64;
import java.util.Properties;
@Configuration
@PropertySource("classpath:database.properties")
public class DataSourceConfiguration {

    Logger logger = LogManager.getLogger(DataSourceConfiguration.class);

    /*
    Properties Object to retrieve database connection properties for the MembersCouponsDatabase
     */
    @ConfigurationProperties(prefix = "spring.datasource.members-coupon-database")
    @Bean(value="primaryDataSourceProperties")
    public Properties getPrimaryDataSourceProperties() throws Exception{
        return new Properties();
    }
    /*
    Configuring database properties;To be further used with JDBC Template for DAO methods
     */
    @Bean(value="primaryDataSource")
    public DataSource primaryDataSourceConfig() throws Exception{
        try{
            Properties primaryDataSourceProperties = getPrimaryDataSourceProperties();
            primaryDataSourceProperties.put("password", new EncryptorClass().decrypt(primaryDataSourceProperties.getProperty("password")));

        }catch (Exception exception){
            logger.error("Exception occured while configurind Data Source",exception);
        }
        return new DataSourceFactory().createDataSource(getPrimaryDataSourceProperties());

    }
}
