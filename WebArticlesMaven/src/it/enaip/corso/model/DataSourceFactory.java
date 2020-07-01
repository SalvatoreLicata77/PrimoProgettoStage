package it.enaip.corso.model;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.sql.DataSource;

import oracle.jdbc.pool.OracleDataSource;


public class DataSourceFactory {
	private final DataSource daso;
	private static final Logger LOGGER = Logger.getLogger(DataSourceFactory.class.getName());

	DataSourceFactory() {
		
		OracleDataSource daso = null;
		try {
			daso = new OracleDataSource();

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String rootPath = Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource("database.properties")).getPath();
		InputStream input = null;

		try {

			input = new FileInputStream(rootPath);
			Properties prop = new Properties();
			prop.load(input);
			 daso.setDriverType(prop.getProperty("thin"));
			 daso.setServerName(prop.getProperty("serverName"));
			 daso.setDatabaseName(prop.getProperty("databaseName"));
			 daso.setPortNumber(Integer.parseInt(prop.getProperty("port")));
			 daso.setUser(prop.getProperty("user"));
			 daso.setPassword(prop.getProperty("password"));
		}
// Exception occurs when file database.properties not found

		catch (FileNotFoundException e) {
			// For simplicity just Log the Exceptions
			LOGGER.log(Level.SEVERE, "File database.properties Not Found", e);
		}

// Exception occurs when I/O error 
		catch (IOException e) {
			// For simplicity just Log the Exceptions
			LOGGER.log(Level.SEVERE, "IO Error", e);
		} finally {
			if (input != null) {
				try {
					input.close();
				}
// Exception occurs when failed to close input streams 
				catch (IOException e) {
					// For simplicity just Log the Exceptions
					LOGGER.log(Level.SEVERE, "Failed to close streams", e);
				}
			}
		}
		this.daso = daso;
	}

	public static Connection getConnection() throws SQLException {
		return SingletonHelper.INSTANCE.daso.getConnection();
	}

	private static class SingletonHelper {
		private static final DataSourceFactory INSTANCE = new DataSourceFactory();
	}
}
