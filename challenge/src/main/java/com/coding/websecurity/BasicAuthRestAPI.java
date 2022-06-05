package com.coding.websecurity;

import com.coding.encryption.EncryptorClass;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.parameters.P;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.lang.annotation.Inherited;
import java.util.Properties;

@EnableWebSecurity
@Configuration
@PropertySource("classpath:credentials.properties")
public class BasicAuthRestAPI extends WebSecurityConfigurerAdapter {

    @Bean
    @ConfigurationProperties(prefix = "spring.basicauth")
    Properties credentialsProperties(){
        return  new Properties();
    }
    /*
    Method for setting up basic authentication credentials to be used for authentication -
    instead of configuration credentials can also be stored in db in encrypted format
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        Properties credentials = credentialsProperties();
        EncryptorClass encryptorClass = new EncryptorClass();
        auth.inMemoryAuthentication().withUser(encryptorClass.decrypt(credentials.getProperty("username"))).password("{noop}"+encryptorClass.decrypt(credentials.getProperty("password"))).roles("ADMIN");
    }

    /*
    Method to authenticate incoming requests to the endpoint with the specified credentials
     */
    @Override
    protected void configure(HttpSecurity http)throws Exception{
        http.authorizeRequests().antMatchers("/coupons/**").permitAll().anyRequest().authenticated().and().httpBasic().and().csrf().disable();
    }
}
