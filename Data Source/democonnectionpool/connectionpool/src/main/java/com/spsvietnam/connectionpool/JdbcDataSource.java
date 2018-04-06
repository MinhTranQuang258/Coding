/*
 * Class: JdbcDataSource
 *
 * Created on Jan 18, 2018
 *
 * (c) Copyright Swiss Post Solution, unpublished work
 * All use, disclosure, and/or reproduction of this material is prohibited
 * unless authorized in writing.  All Rights Reserved.
 * Rights in this program belong to:
 * Swiss Post Solution.
 * Floor 4-5-8, ICT Tower, Quang Trung Software City
 */
package com.spsvietnam.connectionpool;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;

public class JdbcDataSource {
    
    private static PoolProperties poolProperties;
    
    private static DataSource dataSource;
    
    private static JdbcDataSource instance;
    
    public static JdbcDataSource getInstance() {
        if(instance== null) {
            instance= new JdbcDataSource();
            dataSource= new DataSource(configPool());
            return instance;
        }
        else {
            return instance;
        }
    }
    
    private JdbcDataSource() {
        super();
    }

    private static PoolProperties configPool() {
       
        poolProperties= new PoolProperties();
        poolProperties.setUrl("jdbc:postgresql://localhost:5432/tracker_system");
        poolProperties.setUsername("postgres");
        poolProperties.setPassword("12345!fra");
        poolProperties.setDriverClassName("org.postgresql.Driver");
        poolProperties.setMaxActive(10);
        poolProperties.setMaxIdle(5);
        poolProperties.setMinIdle(1);
        poolProperties.setMaxAge(1000000);
        poolProperties.setInitialSize(10);
        poolProperties.setTestOnBorrow(true);
        poolProperties.setMinEvictableIdleTimeMillis(3000);
        poolProperties.setJmxEnabled(true);
        poolProperties.setDefaultAutoCommit(true);
        poolProperties.setValidationQuery("SELECT 1");
        poolProperties.setTestWhileIdle(false);
        poolProperties.setTestOnReturn(true);
        poolProperties.setValidationInterval(3000);
//        poolProperties.setTimeBetweenEvictionRunsMillis(3000);0
        poolProperties.setMaxWait(10000);
        poolProperties.setUseDisposableConnectionFacade(false);
        
        return poolProperties;
    }
    
    public DataSource getDataSource() {
        return dataSource;
    }
}
