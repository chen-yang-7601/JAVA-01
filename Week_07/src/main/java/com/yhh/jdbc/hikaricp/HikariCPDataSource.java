package com.yhh.jdbc.hikaricp;

import com.yhh.jdbc.ConnectionFactory;

import com.zaxxer.hikari.HikariDataSource;
import com.zaxxer.hikari.HikariConfig;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import java.util.logging.Level;
import java.util.logging.Logger;

public class HikariCPDataSource {

    private static HikariConfig cpconfig = new HikariConfig("");
    private static HikariDataSource ds;

    static {
    	Properties conf = ConnectionFactory.getDBConfig();
    	cpconfig.setJdbcUrl(conf.getProperty("jdbcUrl"));
    	cpconfig.setUsername(conf.getProperty("user"));
    	cpconfig.setPassword(conf.getProperty("password"));    	
        
        cpconfig.addDataSourceProperty("cachePrepStmts", conf.getProperty("cachePrepStmts"));
        cpconfig.addDataSourceProperty("prepStmtCacheSize", conf.getProperty("prepStmtCacheSize"));
        cpconfig.addDataSourceProperty("prepStmtCacheSqlLimit", conf.getProperty("prepStmtCacheSqlLimit"));

        ds = new HikariDataSource ( cpconfig );
    }

    private HikariCPDataSource() {}

    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }
    
    public static void main(String[] args) {
    	
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {

            con = ds.getConnection();
            pst = con.prepareStatement("SELECT * FROM coffees");
            rs = pst.executeQuery();

            while (rs.next()) {

                System.out.format("%s %d %f %d %d", rs.getString(1), rs.getInt(2), rs.getFloat(3), 
                        rs.getInt(4), rs.getInt(5));
            }

        } catch (SQLException ex) {

            Logger lgr = Logger.getLogger(HikariCPDataSource.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);

        } finally {

            try {
            
                if (rs != null) {
                    rs.close();
                }
                
                if (pst != null) {
                    pst.close();
                }
                
                if (con != null) {
                    con.close();
                }
                
                ds.close();

            } catch (SQLException ex) {

                Logger lgr = Logger.getLogger(HikariCPDataSource.class.getName());
                lgr.log(Level.WARNING, ex.getMessage(), ex);
            }
        }
    }    	
    
}