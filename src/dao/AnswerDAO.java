package dao;

import static beans.Bean.contentField;
import static beans.Bean.idField;
import static beans.Bean.nameField;
import static beans.Bean.titleField;
import static dao.DAOUtilities.closeAll;
import static dao.DAOUtilities.initPreparedStatement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.Answer;

public class AnswerDAO {
	private DAOFactory daoFactory;

	public AnswerDAO(DAOFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

	public List<Answer> readAll() {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<Answer> list = new ArrayList<>();

		try {
			connection = daoFactory.getConnection();
			preparedStatement = initPreparedStatement(connection, "SELECT * FROM answers", false);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				list.add(map(resultSet));
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			closeAll(resultSet, preparedStatement, connection);
		}

		return list;
	}

	public Answer read(String name) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Answer answer = null;

		try {
			connection = daoFactory.getConnection();
			preparedStatement = initPreparedStatement(connection, "SELECT * FROM answers WHERE name = ?", false, name);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				answer = map(resultSet);
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			closeAll(resultSet, preparedStatement, connection);
		}

		return answer;
	}

	private static Answer map(ResultSet r) throws SQLException {		
		return new Answer(r.getLong(idField), r.getString(nameField),
				r.getString(titleField), r.getString(contentField));
	}
}
