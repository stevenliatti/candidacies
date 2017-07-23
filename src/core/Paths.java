package core;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import dao.DAOConfigurationException;

public class Paths {
	private final static String PROPERTIES_FILE = "/core/path.properties";
	private String outputPath;
	private String latexPath;
	private String generatedFileName;
	
	private final static Paths instance = new Paths();
	
	private Paths() {
		initPaths();
	}
	
	private void initPaths() {
		Properties properties = new Properties();
        InputStream propertiesFile = Thread.currentThread().getContextClassLoader().getResourceAsStream(PROPERTIES_FILE);
        
        if (propertiesFile == null) {
			throw new DAOConfigurationException("file " + PROPERTIES_FILE + " not found");
		}
        
        try {
			properties.load(propertiesFile);
			outputPath = properties.getProperty("outputPath");
			latexPath = properties.getProperty("latexPath");
			generatedFileName = properties.getProperty("generatedFileName");
		} catch (IOException e) {
			throw new DAOConfigurationException("Impossible to load the properties file " + PROPERTIES_FILE, e);
		}
	}
	
	public final static Paths getInstance() {
		return instance;
	}

	public final String getOutputPath() {
		return outputPath;
	}

	public final String getLatexPath() {
		return latexPath;
	}

	public final String getGeneratedFileName() {
		return generatedFileName;
	}
}
