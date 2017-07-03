package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DAOUtilities {
	/**
	 * Init 
	 * @param connection
	 * @param sql
	 * @param returnGeneratedKeys
	 * @param objects
	 * @return
	 * @throws SQLException
	 */
	public static PreparedStatement initPreparedStatement(Connection connection, String sql, boolean returnGeneratedKeys, 
			Object... objects) throws SQLException {
	    PreparedStatement preparedStatement = connection.prepareStatement(sql, returnGeneratedKeys ? 
	    		Statement.RETURN_GENERATED_KEYS : Statement.NO_GENERATED_KEYS);
	    for (int i = 0; i < objects.length; i++) {
	        preparedStatement.setObject(i + 1, objects[i]);
	    }
	    return preparedStatement;
	}
	
	public static void close(ResultSet resultSet) {
	    if (resultSet != null) {
	        try {
	            resultSet.close();
	        } catch (SQLException e) {
	            System.out.println("Fail to close ResultSet : " + e.getMessage());
	        }
	    }
	}

	public static void close(Statement statement) {
	    if (statement != null) {
	        try {
	            statement.close();
	        } catch (SQLException e) {
	            System.out.println("Fail to close Statement : " + e.getMessage());
	        }
	    }
	}

	public static void close(Connection connection) {
	    if (connection != null) {
	        try {
	            connection.close();
	        } catch (SQLException e) {
	            System.out.println("Fail to close Connection : " + e.getMessage());
	        }
	    }
	}

	public static void closeAll(ResultSet resultSet, Statement statement) {
	    close(resultSet);
	    close(statement);
	}

	public static void closeAll(ResultSet resultSet, Statement statement, Connection connection) {
	    close(resultSet);
	    close(statement);
	    close(connection);
	}
}
