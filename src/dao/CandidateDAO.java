package dao;

import static beans.Bean.answerField;
import static beans.Bean.answerTitleField;
import static beans.Bean.countryField;
import static beans.Bean.emailField;
import static beans.Bean.firstNameField;
import static beans.Bean.folderField;
import static beans.Bean.idField;
import static beans.Bean.initialsField;
import static beans.Bean.insertDateField;
import static beans.Bean.jobFunctionField;
import static beans.Bean.lastNameField;
import static beans.Bean.letterField;
import static beans.Bean.livesAtField;
import static beans.Bean.localityField;
import static beans.Bean.notTransmittedField;
import static beans.Bean.numStreetField;
import static beans.Bean.postCodeField;
import static beans.Bean.requestDateField;
import static beans.Bean.sendTypeField;
import static beans.Bean.streetField;
import static beans.Bean.titleField;
import static beans.Bean.updateDateField;
import static dao.DAOUtilities.closeAll;
import static dao.DAOUtilities.initPreparedStatement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import beans.Bean;
import beans.Candidate;

public class CandidateDAO {
	private DAOFactory daoFactory;

	public CandidateDAO(DAOFactory daoFactory) {
		this.daoFactory = daoFactory;
	}
	
	public List<Candidate> listCandidates(int number) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<Candidate> list = new ArrayList<>();

		try {
			connection = daoFactory.getConnection();
			preparedStatement = initPreparedStatement(connection, "SELECT * FROM candidates ORDER BY id DESC LIMIT " + number + ";", false);
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
	
	public List<Candidate> searchByName(String search) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<Candidate> candidates = new ArrayList<>();
		
		try {
			connection = daoFactory.getConnection();
			preparedStatement = initPreparedStatement(connection, "SELECT * FROM candidates WHERE firstName LIKE '%" + search + 
					"%' OR lastName LIKE '%" + search + "%' ORDER BY id DESC;", false);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				candidates.add(map(resultSet));
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			closeAll(resultSet, preparedStatement, connection);
		}
		
		return candidates;
	}
	
	public List<Candidate> searchByJob(String search) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<Candidate> candidates = new ArrayList<>();
		
		try {
			connection = daoFactory.getConnection();
			preparedStatement = initPreparedStatement(connection, "SELECT * FROM candidates WHERE jobFunction LIKE '%" + search + 
					"%' ORDER BY id DESC;", false);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				candidates.add(map(resultSet));
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			closeAll(resultSet, preparedStatement, connection);
		}
		
		return candidates;
	}

	public List<Candidate> listCandidates(String[] ids, String sendType) {
		if (ids == null || ids.length == 0) {
			return null;
		}
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<Candidate> candidates = new ArrayList<>();
		
		StringBuilder query = new StringBuilder("SELECT * FROM candidates WHERE sendType = '" + sendType + "' AND (");
		for (String id : ids) {
			query.append(" id=" + id + " OR");
		}
		query.delete(query.length() - 3, query.length());
		query.append(");");
		
		try {
			connection = daoFactory.getConnection();
			preparedStatement = initPreparedStatement(connection, query.toString(), false);
			resultSet = preparedStatement.executeQuery(query.toString());
			while (resultSet.next()) {
				candidates.add(map(resultSet));
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			closeAll(resultSet, preparedStatement, connection);
		}

		return candidates;
	}

	public int countCandidatesOfDay(String sendType) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		int count = 0;

		try {
			connection = daoFactory.getConnection();
			preparedStatement = initPreparedStatement(connection, "SELECT COUNT(*) FROM candidates "
					+ "WHERE date(updateDate) = curdate() AND sendType = '" + sendType + "'", false);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				count = resultSet.getInt(1);
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			closeAll(resultSet, preparedStatement, connection);
		}

		return count;
	}

	public List<Candidate> candidatesOfDay(String sendType) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<Candidate> candidates = new ArrayList<>();

		try {
			connection = daoFactory.getConnection();
			preparedStatement = initPreparedStatement(connection, "SELECT * FROM candidates "
					+ "WHERE date(updateDate) = CURDATE() "
					+ "AND sendType='" + sendType + "'", false);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				candidates.add(map(resultSet));
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			closeAll(resultSet, preparedStatement, connection);
		}

		return candidates;
	}

	public List<Candidate> readAll() {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<Candidate> list = new ArrayList<>();

		try {
			connection = daoFactory.getConnection();
			preparedStatement = initPreparedStatement(connection, "SELECT * FROM candidates", false);
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

	public void create(Bean bean) throws DAOException {
		Candidate candidate = (Candidate) bean;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet autoGeneratedValues = null;

		try {
			connection = daoFactory.getConnection();
			preparedStatement = initPreparedStatement(connection, 
					"INSERT INTO candidates (title, lastName, firstName, email, livesAt, street, numStreet, "
							+ "postCode, locality, country, requestDate, insertDate, updateDate, "
							+ "initials, jobFunction, answer, answerTitle, folder, sendType, letter, notTransmitted) "
							+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, NOW(), NOW(), ?, ?, ?, ?, ?, ?, ?, ?)", true, 
							candidate.getTitle(), 
							candidate.getLastName(), 
							candidate.getFirstName(),
							candidate.getEmail(),
							candidate.getLivesAt(),
							candidate.getStreet(),
							candidate.getNumStreet(),
							candidate.getPostCode(),
							candidate.getLocality(),
							candidate.getCountry(),
							candidate.getRequestDateSQLFormatted(),
							// datetime -> NOW()
							candidate.getInitials(),
							candidate.getJobFunction(),
							candidate.getAnswer(),
							candidate.getAnswerTitle(),
							candidate.getFolder(),
							candidate.getSendType(),
							candidate.getLetter(),
							"no"
					);

			if (preparedStatement.executeUpdate() == 0) {
				throw new DAOException("Fail to create new candidate");
			}

			autoGeneratedValues = preparedStatement.getGeneratedKeys();
			if (autoGeneratedValues.next()) {
				candidate.setId(autoGeneratedValues.getLong(1));
			} else {
				throw new DAOException("Fail to create candidate, no auto generated id returned");
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			closeAll(autoGeneratedValues, preparedStatement, connection);
		}
	}

	public Candidate read(Long id) throws DAOException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Candidate candidate = null;

		try {
			connection = daoFactory.getConnection();
			preparedStatement = initPreparedStatement(connection, "SELECT * FROM candidates WHERE id = ?", false, id);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				candidate = map(resultSet);
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			closeAll(resultSet, preparedStatement, connection);
		}

		return candidate;
	}

	public void update(Bean bean) throws DAOException {
		Candidate candidate = (Candidate) bean;
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			connection = daoFactory.getConnection();
			preparedStatement = initPreparedStatement(connection, 
					"UPDATE candidates SET title=?, lastName=?, firstName=?, email=?, livesAt=?, street=?, numStreet=?, "
							+ "postCode=?, locality=?, country=?, requestDate=?, updateDate=NOW(), "
							+ "initials=?, jobFunction=?, answer=?, answerTitle=?, folder=?, sendType=?, letter=?, notTransmitted=? "
							+ "WHERE id=?", false, 
							candidate.getTitle(), 
							candidate.getLastName(), 
							candidate.getFirstName(),
							candidate.getEmail(),
							candidate.getLivesAt(),
							candidate.getStreet(),
							candidate.getNumStreet(),
							candidate.getPostCode(),
							candidate.getLocality(),
							candidate.getCountry(),
							candidate.getRequestDateSQLFormatted(),
							candidate.getInitials(),
							candidate.getJobFunction(),
							candidate.getAnswer(),
							candidate.getAnswerTitle(),
							candidate.getFolder(),
							candidate.getSendType(),
							candidate.getLetter(),
							candidate.getNotTransmitted(),
							candidate.getId()
					);

			if (preparedStatement.executeUpdate() == 0) {
				throw new DAOException("Fail to update candidate");
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			closeAll(preparedStatement, connection);
		}
	}

	public void delete(Long id) throws DAOException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			connection = daoFactory.getConnection();
			preparedStatement = initPreparedStatement(connection, "DELETE FROM candidates WHERE id=?", false, id);

			if (preparedStatement.executeUpdate() == 0) {
				throw new DAOException("Fail to delete candidate");
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			closeAll(preparedStatement, connection);
		}
	}

	private static LocalDate parseLocalDate(String str) {
		if (str == null || str.isEmpty()) {
			return null;
		}
		return LocalDate.parse(str);
	}

	private static LocalDateTime parseLocalDateTime(String str, DateTimeFormatter formatter) {
		if (str == null || str.isEmpty()) {
			return null;
		}
		return LocalDateTime.parse(str, formatter);
	}

	private static Candidate map(ResultSet r) throws SQLException {		
		DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss.S");

		return new Candidate(r.getLong(idField), r.getString(titleField), r.getString(lastNameField), 
				r.getString(firstNameField), r.getString(emailField), r.getString(livesAtField), 
				r.getString(streetField), r.getString(numStreetField), r.getString(postCodeField), 
				r.getString(localityField), r.getString(countryField),
				parseLocalDate(r.getString(requestDateField)),
				parseLocalDateTime(r.getString(insertDateField), formatter),
				parseLocalDateTime(r.getString(updateDateField), formatter),
				r.getString(initialsField), r.getString(jobFunctionField),
				r.getString(answerField), r.getString(answerTitleField), r.getString(folderField), r.getString(sendTypeField), 
				r.getString(letterField), r.getString(notTransmittedField));
	}
}