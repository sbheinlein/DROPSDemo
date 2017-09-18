package com.drops.demo.tools;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class SQLTools {
	protected static String JDBC_MYSQL = "%1$s?user=%2$s&password=%3$s";
	protected static boolean JDBC_MYSQL_LOADED = false;
	protected static boolean JDBC_ORACLE_LOADED = false;
	
	public static ResultSet getResultSetFromQuery(Connection connection, String query) throws SQLException{
		ResultSet resultSet = null;
		Statement sql = connection.createStatement();
		resultSet = sql.executeQuery(query);
			
		return resultSet;
	}
	
	public static Connection getMySQLConnection() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException, FileNotFoundException, IOException{
		if(!JDBC_MYSQL_LOADED){
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            JDBC_MYSQL_LOADED = true;
		}
		Properties configuration = ConfigTools.getConfiguration();
		String jdbcURL = String.format(JDBC_MYSQL,
							configuration.getProperty("drops.mysql.url"),
							configuration.getProperty("drops.mysql.user"),
							configuration.getProperty("drops.mysql.pass"));
		
		return DriverManager.getConnection(jdbcURL);
		
	}
	
	public static Connection getOracleConnection() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException, FileNotFoundException, IOException{
		if(!JDBC_ORACLE_LOADED){
            Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
            JDBC_ORACLE_LOADED = true;
		}
		Properties configuration = ConfigTools.getConfiguration();
		return DriverManager.getConnection(	configuration.getProperty("drops.oracle.url"),
											configuration.getProperty("drops.oracle.user"),
											configuration.getProperty("drops.oracle.pass"));
		
	}
}
