package com.yhh.jdbc;

import java.io.FileInputStream;
import java.sql.Connection;
import javax.sql.DataSource;
import javax.sql.PooledConnection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.yhh.jdbc.hikaricp.HikariCPDataSource;

public class ConnectionFactory {
	
	String dbms;
	String dbName;
	static String serverName;
	static String portNumber;
	static String userName;
	static String password;
	private static final Properties connectionProps;
	
	private static final String JDBC_DRIVER_7 = "com.mysql.jdbc.Driver";  
	private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";  // MySQL 8.0 以上版本 - JDBC 驱动名及数据库 URL
	//static final String DB_URL = "jdbc:mysql://localhost:3306/RUNOOB?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
	      	
	private DataSource ds;
	static {
		connectionProps = new Properties();
		try (FileInputStream in = new FileInputStream("src/main/resources/dbconfig.properties")){
			connectionProps.load(in);
			serverName = connectionProps.getProperty("db_server", "localhost");
			portNumber = connectionProps.getProperty("db_serverport", "3306");
			
			in.close();
		}catch(Exception e) {
			e.printStackTrace();
		}

	}
	
	public static Properties getDBConfig() {
		return connectionProps;
	}
	
	public static Connection getConnection() throws SQLException, ClassNotFoundException {
		Connection conn = null;
		Class.forName(JDBC_DRIVER);
		conn = DriverManager.getConnection(connectionProps.getProperty("jdbcUrl"), connectionProps);
		return conn;
	}
	
	
	public static void main(String[] args) {

		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {

			ConnectionFactory connFactory = new ConnectionFactory();
			con = connFactory.getConnection();
			pst = con.prepareStatement("SELECT * FROM coffees");
			rs = pst.executeQuery();

			while (rs.next()) {

				System.out.format("%s %d %f %d %d", rs.getString(1), rs.getInt(2), rs.getFloat(3), rs.getInt(4),
						rs.getInt(5));
			}

		} catch (SQLException | ClassNotFoundException ex) {

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

			} catch (SQLException ex) {

				Logger lgr = Logger.getLogger(HikariCPDataSource.class.getName());
				lgr.log(Level.WARNING, ex.getMessage(), ex);
			}
		}
	}
}
