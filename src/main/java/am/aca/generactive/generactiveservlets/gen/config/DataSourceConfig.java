package am.aca.generactive.generactiveservlets.gen.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
public class DataSourceConfig {
    @Bean
    public DataSource dataSource(@Qualifier("hibernateProperties") Properties hibernateProperties){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
//        dataSource.setConnectionProperties(hibernateProperties);
        dataSource.setConnectionProperties(hibernateProperties);
        dataSource.setUrl("jdbc:postgresql://localhost:5432/postgres");
        dataSource.setUsername("postgres");
        dataSource.setPassword("12345");
        dataSource.setDriverClassName("org.postgresql.Driver");
        return dataSource;
    }
}