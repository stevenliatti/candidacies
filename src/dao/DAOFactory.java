package dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DAOFactory {
	private static final String PROPERTIES_FILE = "/dao/dao.properties";
	
	private String url;
	private String username;
	private String password;
	
	public DAOFactory(String url, String username, String password) {
		this.url = url;
		this.username = username;
		this.password = password;
	}
	
	public static DAOFactory getInstance() throws DAOConfigurationException {
		Properties properties = new Properties();
        InputStream propertiesFile = Thread.currentThread().getContextClassLoader().getResourceAsStream(PROPERTIES_FILE);
        
        String url;
        String driver;
        String username;
        String password;
        
        if (propertiesFile == null) {
			throw new DAOConfigurationException("file " + PROPERTIES_FILE + " not found");
		}
        
        try {
			properties.load(propertiesFile);
			url = properties.getProperty("url");
			driver = properties.getProperty("driver");
			username = properties.getProperty("username");
			password = properties.getProperty("password");
		} catch (IOException e) {
			throw new DAOConfigurationException("Impossible to load the properties file " + PROPERTIES_FILE, e);
		}
        
        try {
        	Class.forName(driver);
		} catch (ClassNotFoundException e) {
			throw new DAOConfigurationException("driver not found in classpath", e);
		}
        
        DAOFactory instance = new DAOFactory(url, username, password);
        return instance;
	}
	
	Connection getConnection() throws SQLException {
		return DriverManager.getConnection(url, username, password);
	}
	
	public UserDAO getUserDao() {
        return new UserDAO(this);
    }
}
