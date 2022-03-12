package org.terasoluna.tourreservation.app.reservetour;


import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PostgresDataSourceConfiguration {
    
    @Bean
    public DataSource dataSource() {
        
        BasicDataSource datasource = new BasicDataSource();
        datasource.setDriverClassName("org.postgresql.Driver");
        datasource.setUrl("jdbc:postgresql://localhost/tourreserve");
        datasource.setUsername("postgres");
        datasource.setPassword("postgres");
        return datasource;
    }
}
