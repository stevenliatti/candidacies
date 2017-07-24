package dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import com.jolbox.bonecp.BoneCP;
import com.jolbox.bonecp.BoneCPConfig;

public class DAOFactory {
	private static final String PROPERTIES_FILE = "/dao/dao.properties";
	BoneCP connectionPool = null;
	
	DAOFactory(BoneCP connectionPool) {
		this.connectionPool = connectionPool;
	}
	
	public static DAOFactory getInstance() throws DAOConfigurationException {
		Properties properties = new Properties();
        InputStream propertiesFile = Thread.currentThread().getContextClassLoader().getResourceAsStream(PROPERTIES_FILE);
        
        String url;
        String driver;
        String username;
        String password;
        BoneCP connectionPool = null;
        
        if (propertiesFile == null) {
			throw new DAOConfigurationException("file " + PROPERTIES_FILE + " not found");
		}
        
        try {
			properties.load(propertiesFile);
			url = properties.getProperty("url");
			driver = properties.getProperty("driver");
			username = properties.getProperty("username");
			password = properties.getProperty("password");
        } catch (FileNotFoundException e) {
	            throw new DAOConfigurationException("The file " + PROPERTIES_FILE + " is not found.", e);
		} catch (IOException e) {
			throw new DAOConfigurationException("Impossible to load the properties file " + PROPERTIES_FILE, e);
		}
        
        try {
        	Class.forName(driver);
		} catch (ClassNotFoundException e) {
			throw new DAOConfigurationException("driver not found in classpath", e);
		}
        
        try {
        	BoneCPConfig config = new BoneCPConfig();
            config.setJdbcUrl(url);
            config.setUsername(username);
            config.setPassword(password);

            config.setMinConnectionsPerPartition(5);
            config.setMaxConnectionsPerPartition(10);
            config.setPartitionCount(2);

            connectionPool = new BoneCP( config );
		} catch (SQLException e) {
			e.printStackTrace();
            throw new DAOConfigurationException("Error configuration connections' pool.", e);
		}
        
        DAOFactory instance = new DAOFactory(connectionPool);
        return instance;
	}
	
	Connection getConnection() throws SQLException {
		return connectionPool.getConnection();
	}
	
	public CandidateDAO getCandidateDao() {
        return new CandidateDAO(this);
    }
}
