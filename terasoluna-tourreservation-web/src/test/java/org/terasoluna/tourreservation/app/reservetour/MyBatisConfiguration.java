package org.terasoluna.tourreservation.app.reservetour;

import java.io.IOException;

import javax.sql.DataSource;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

@Configuration
@MapperScan(basePackages = "org.terasoluna.tourreservation.domain.repository")
public class MyBatisConfiguration {

    @Autowired
    private DataSource dataSource;

    @Bean
    public SqlSessionFactoryBean sqlSessionFactory() throws IOException {

        PathMatchingResourcePatternResolver resourceFinder = new PathMatchingResourcePatternResolver();
        Resource[] mappers = resourceFinder
                .getResources("classpath*:org.terasoluna.tourreservation.domain.repository/**/*.xml");

        SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
        sqlSessionFactory.setDataSource(dataSource);

        sqlSessionFactory.setMapperLocations(mappers);
        sqlSessionFactory.setTypeAliasesPackage("org.terasoluna.tourreservation.domain");
        return sqlSessionFactory;
    }
}
