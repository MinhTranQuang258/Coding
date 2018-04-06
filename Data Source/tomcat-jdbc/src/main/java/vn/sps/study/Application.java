/*
 * Class: Application
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
package vn.sps.study;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;

public class Application {

    public static void main(String[] args) throws Exception {
        final PoolProperties p = new PoolProperties();
        p.setUrl("jdbc:postgresql://localhost:5432/tracker_system");
        p.setDriverClassName("org.postgresql.Driver");
        p.setUsername("postgres");
        p.setPassword("12345!fra");
        p.setJmxEnabled(true);
        p.setTestWhileIdle(false);
        p.setTestOnBorrow(true);
        p.setValidationQuery("SELECT 1");
        p.setTestOnReturn(false);
        p.setValidationInterval(3000);
        p.setTimeBetweenEvictionRunsMillis(3000);
        p.setMaxActive(10);
        p.setInitialSize(1);
        p.setMinIdle(0);
        p.setMaxIdle(3);
        p.setMaxWait(10000);
        p.setRemoveAbandonedTimeout(60);
        p.setMinEvictableIdleTimeMillis(3000);
        p.setLogAbandoned(true);
        p.setRemoveAbandoned(true);
        p.setJdbcInterceptors(
            "org.apache.tomcat.jdbc.pool.interceptor.ConnectionState;"
                    + "org.apache.tomcat.jdbc.pool.interceptor.StatementFinalizer");
        final DataSource datasource = new DataSource();
        datasource.setPoolProperties(p);

        test(datasource);
        System.out.println("");
        test(datasource);
        System.out.println("");
    }

    private static void test(final DataSource datasource) throws SQLException {
        Connection con = null;
        try {
            con = datasource.getConnection();
//            Statement st = con.createStatement();
//            ResultSet rs = st.executeQuery("select 1");
//            int cnt = 1;
//            while (rs.next()) {
//                System.out.println(
//                    (cnt++) + ". Id:" + rs.getString("id") + " Name:"
//                            + rs.getString("name"));
//            }
//            rs.close();
//            st.close();
        }
        finally {
            if (con != null)
                try {
                    con.close();
                }
                catch (Exception ignore) {
                }
        }
    }
}
