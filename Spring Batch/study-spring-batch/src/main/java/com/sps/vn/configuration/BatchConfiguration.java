/*
 * Class: BatchConfiguration
 *
 * Created on Mar 5, 2018
 *
 * (c) Copyright Swiss Post Solution, unpublished work
 * All use, disclosure, and/or reproduction of this material is prohibited
 * unless authorized in writing.  All Rights Reserved.
 * Rights in this program belong to:
 * Swiss Post Solution.
 * Floor 4-5-8, ICT Tower, Quang Trung Software City
 */
package com.sps.vn.configuration;


import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.batch.core.configuration.annotation.BatchConfigurer;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.JobRepositoryFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class BatchConfiguration implements BatchConfigurer{
    
    private DataSource dataSource= new DataSource();

    protected DataSource getDataSource() {
        dataSource.setUrl("jdbc:postgresql://localhost:5432/study");
        dataSource.setUsername("postgres");
        dataSource.setPassword("12345!fra");
        dataSource.setDriverClassName("org.postgresql.Driver");
        return dataSource;
    }
    
    @Bean
    protected ThreadPoolTaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor poolTaskExecutor= new ThreadPoolTaskExecutor();
        poolTaskExecutor.setCorePoolSize(1);
        poolTaskExecutor.setMaxPoolSize(1);
        poolTaskExecutor.setQueueCapacity(0);
        poolTaskExecutor.setKeepAliveSeconds(30);
        poolTaskExecutor.setWaitForTasksToCompleteOnShutdown(true);
        poolTaskExecutor.afterPropertiesSet();
        poolTaskExecutor.initialize();
        return poolTaskExecutor;
    }
    
    
    @Override
    @Bean
    public JobRepository getJobRepository() throws Exception {
        JobRepositoryFactoryBean factory= new JobRepositoryFactoryBean();
        factory.setTransactionManager(this.getTransactionManager());
        factory.setDataSource(getDataSource());
        return factory.getObject();
    }

    @Override
    @Bean
    public PlatformTransactionManager getTransactionManager() throws Exception {
        DataSourceTransactionManager transactionManager= new DataSourceTransactionManager();
        transactionManager.setDataSource(getDataSource());
        transactionManager.afterPropertiesSet();
        return transactionManager;
    }

    @Override
    @Bean
    public JobLauncher getJobLauncher() throws Exception {
        SimpleJobLauncher jobLauncher = new SimpleJobLauncher();
        jobLauncher.setJobRepository(this.getJobRepository());
        jobLauncher.setTaskExecutor(taskExecutor());
        jobLauncher.afterPropertiesSet();
        return jobLauncher;
    }

    @Override
    public JobExplorer getJobExplorer() throws Exception {
        // TODO Auto-generated method stub
        return null;
    }
    
}
