package com.spsvietnam.connectionpool;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.commons.dbcp.AbandonedConfig;
import org.apache.commons.dbcp.ConnectionFactory;
import org.apache.commons.dbcp.DriverManagerConnectionFactory;
import org.apache.commons.dbcp.PoolableConnectionFactory;
import org.apache.commons.dbcp.PoolingDataSource;
import org.apache.commons.pool.impl.GenericObjectPool;

public class DemoPoolingDataSource {

    private static DemoPoolingDataSource instance;

    private static DataSource setupDataSource;

    public static DemoPoolingDataSource getInstance() {
        if (instance == null) {
            instance = new DemoPoolingDataSource();
            setupDataSource = setupDataSource();
        }
        return instance;
    }

    public Connection getConnection() throws SQLException {
        return setupDataSource.getConnection();
    }

    public static DataSource setupDataSource() {

        final ConnectionFactory connectionFactory = new DriverManagerConnectionFactory(
            "jdbc:postgresql://localhost:5432/tracker_system", "postgres",
            "12345!fra");

        final AbandonedConfig abandonedConfig = new AbandonedConfig();
        abandonedConfig.setRemoveAbandonedTimeout(10);
        abandonedConfig.setRemoveAbandoned(true);

        //        AbandonedObjectPool objectPool = new AbandonedObjectPool(
        //            null, abandonedConfig);

        GenericObjectPool objectPool = new GenericObjectPool();

        final PoolableConnectionFactory poolableConnection = new PoolableConnectionFactory(
            connectionFactory, objectPool, null, null, false, false,
            abandonedConfig);

        objectPool.setMaxActive(6);
        objectPool.setMaxIdle(3);
        objectPool.setMinIdle(1);
        objectPool.setMaxWait(30000);
        objectPool.setNumTestsPerEvictionRun(5);
        objectPool.setMinEvictableIdleTimeMillis(5000);
        objectPool.setTimeBetweenEvictionRunsMillis(5000);
        objectPool.setFactory(poolableConnection);
        objectPool.setTestOnBorrow(true);
        objectPool.setLifo(false);
        

        DataSource dataSource = new PoolingDataSource(objectPool);

        return dataSource;
    }
}
