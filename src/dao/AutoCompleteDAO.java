package dao;

import static dao.DAOUtilities.close;
import static dao.DAOUtilities.closeAll;
import static dao.DAOUtilities.initPreparedStatement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * This class regroup all the queries for the autocomplete form.
 * 
 * @author stevenliatti
 *
 */
public class AutoCompleteDAO {
	private DAOFactory daoFactory;

	public AutoCompleteDAO(DAOFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

	/**
	 * Insert a new element in table if inexistent.
	 * 
	 * @param table
	 * @param name
	 * @return
	 * @throws DAOException
	 */
	public AutoCompleteDAO checkOrCreate(String table, String name) throws DAOException {
		Connection connection = null;
		PreparedStatement readStatement = null;
		PreparedStatement createStatement = null;
		ResultSet resultSet = null;

		try {
			connection = daoFactory.getConnection();
			readStatement = initPreparedStatement(connection, "SELECT name FROM " + table + " WHERE name = '" + name + "';", false);
			resultSet = readStatement.executeQuery();
			if (!resultSet.next() && name != null && !name.isEmpty()) {
				createStatement = initPreparedStatement(connection, "INSERT INTO " + table + " (name) VALUES (?);", false, name);

				if (createStatement.executeUpdate() == 0) {
					throw new DAOException("Fail to create new entry");
				}
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			closeAll(resultSet, readStatement, connection);
			close(createStatement);
		}
		
		return this;
	}

	/**
	 * Return a list of String from the table that correspond to the term in argument.
	 * 
	 * @param table
	 * @param term
	 * @return
	 */
	public List<String> readAll(String table, String term) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<String> list = new ArrayList<>();

		try {
			connection = daoFactory.getConnection();
			preparedStatement = initPreparedStatement(connection, "SELECT name FROM " + table + " WHERE name LIKE '%" + term + "%' LIMIT 30;", false);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				list.add(resultSet.getString("name"));
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			closeAll(resultSet, preparedStatement, connection);
		}

		return list;
	}
	
	/**
	 * Return a list of String of the table in argument.
	 * 
	 * @param table
	 * @return
	 */
	public List<String> readAll(String table) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<String> list = new ArrayList<>();

		try {
			connection = daoFactory.getConnection();
			preparedStatement = initPreparedStatement(connection, "SELECT name FROM " + table + ";", false);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				list.add(resultSet.getString("name"));
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			closeAll(resultSet, preparedStatement, connection);
		}

		return list;
	}
}
