/*
 * Class: FirstDataSource
 *
 * Created on Feb 26, 2018
 *
 * (c) Copyright Swiss Post Solution, unpublished work
 * All use, disclosure, and/or reproduction of this material is prohibited
 * unless authorized in writing.  All Rights Reserved.
 * Rights in this program belong to:
 * Swiss Post Solution.
 * Floor 4-5-8, ICT Tower, Quang Trung Software City
 */
package com.sps.vn.datasource;

import java.util.Map;

import javax.annotation.Resource;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.sps.vn.datasource.core.FirstJpaProperties;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages= "com.sps.vn.repository",
                       transactionManagerRef= "transactionManager",
                       entityManagerFactoryRef= "entityManagerFactory")
public class FirstDataSource extends AbstractDataSource{
    
    @Resource(name= "firstJpaProperties", type= FirstJpaProperties.class)
    private JpaProperties firstJpaProperties;

    @Override
    @Bean(name="dataSource")
    public DataSource dataSource() {
        org.apache.tomcat.jdbc.pool.DataSource dataSource= new org.apache.tomcat.jdbc.pool.DataSource();
        dataSource.setUrl("jdbc:postgresql://localhost:5432/study");
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUsername("postgres");
        dataSource.setPassword("12345!fra");
        return dataSource;
    }
    
    @Bean
    @Primary
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
        return this.getEntityManagerFactoryBean(dataSource);
    }

    @Override
    @Bean
    @Primary
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        return this.getTransactionManager(entityManagerFactory);
    }

    @Override
    protected String getPackagesToScan() {
        return "com.sps.vn.entities";
    }

    @Override
    protected Map<String, String> getProperties() {
        return firstJpaProperties.getProperties();
    }

    @Override
    protected String getUnitName() {
        return "firstUnit";
    }
}
