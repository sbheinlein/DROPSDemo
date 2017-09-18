package com.drops.demo.core;

import java.beans.PropertyVetoException;
import java.sql.SQLException;
import java.util.Properties;

import com.ibm.as400.access.AS400;
import com.ibm.as400.access.AS400JDBCConnection;
import com.ibm.as400.access.AS400JDBCDriver;
import com.ibm.as400.access.AS400JDBCStatement;

public class Connection {
	public static final String IN_SESSION = "connectionInstance";	
	
	protected AS400 ibmi;
	protected AS400JDBCConnection sqlConnection;
	
	
	public Connection(String system, String login, String password){ 
		if(!system.equals("") && !login.equals("")){
			try {
				this.ibmi = new AS400();
				this.ibmi.setGuiAvailable(false);
				this.ibmi.setSystemName(system);
				this.ibmi.setUserId(login);
				this.ibmi.setPassword(password);				
			}
			catch (PropertyVetoException e) {
				e.printStackTrace();
			}
			this.createDB2SQLConnection();
		}
	}

	public boolean isConnected() {
		boolean connected = false;
		try {
			connected = this.ibmi.validateSignon();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return connected;
	}
	
	public String getSystemName(){
		String systemName = "Not connected";
		if(this.isConnected())
			systemName = this.ibmi.getSystemName();
		
		return systemName;
	}
	
	public String getServerVersion(){
		String vrm = "Not connected";
		if(this.isConnected()){
			try {
				vrm = "V" + ibmi.getVersion() + "R" + ibmi.getRelease() + "M" + ibmi.getModification();
			}
			catch (Exception e) {
				vrm = "V?R?M?";
			}
		}
		
		return vrm;
	}
	
	protected void createDB2SQLConnection(){		
		try {
			AS400JDBCDriver driver = new AS400JDBCDriver();
			Properties properties = new Properties();
			properties.put("naming", "system");
			properties.put("libraries", "SEBOBJ, SEBDTA, *LIBL");
			sqlConnection = (AS400JDBCConnection) driver.connect(this.ibmi, properties, null);
			sqlConnection.setTransactionIsolation(AS400JDBCConnection.TRANSACTION_NONE);			
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public AS400JDBCStatement getDB2Statement(){
		AS400JDBCStatement statement = null;;
		try {
			statement = (AS400JDBCStatement) sqlConnection.createStatement();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return statement;
	}
}
