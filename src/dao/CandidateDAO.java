package dao;

import static dao.DAOUtilities.closeAll;
import static dao.DAOUtilities.initPreparedStatement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.ZoneId;

import beans.Bean;
import beans.Candidate;

public class CandidateDAO extends ObjectDAO {
	
	public CandidateDAO(DAOFactory daoFactory) {
		super(daoFactory);
	}

	@Override
	public void create(Bean bean) throws DAOException {
		Candidate candidate = (Candidate) bean;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet autoGeneratedValues = null;
		
		try {
			connection = daoFactory.getConnection();
			preparedStatement = initPreparedStatement(connection, 
					"INSERT INTO candidates (title, last_name, first_name, email, lives_at, street, num_street, "
					+ "post_code, locality, country, request_date, insert_date, update_date, send_date, "
					+ "writer, job_type, job_function, answer) "
					+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, NOW(), NOW(), ?, ?, ?, ?, ?)", true, 
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
					candidate.getRequestDate(),
					// datetime -> NOW()
					candidate.getSendDate(),
					candidate.getWriter(),
					candidate.getJobType(),
					candidate.getJobFunction(),
					candidate.getAnswer()
			);
			
			int status = preparedStatement.executeUpdate();
			if (status == 0) {
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
	
	@Override
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
	
	@Override
	public void update(Bean bean) throws DAOException {
		// TODO Auto-generated method stub
	}

	@Override
	public void delete(Bean bean) throws DAOException {
		// TODO Auto-generated method stub
	}
	
	/**
	 * 
	 * @param r
	 * @return
	 * @throws SQLException
	 */
	private static Candidate map(ResultSet r) throws SQLException {
	    return new Candidate(r.getLong("id"), r.getString("title"), r.getString("last_name"), 
	    		r.getString("first_name"), r.getString("email"), r.getString("lives_at"), 
	    		r.getString("street"), r.getString("num_street"), r.getString("post_code"), 
	    		r.getString("locality"), r.getString("country"), r.getDate("request_date").toLocalDate(),
	    		LocalDateTime.ofInstant(r.getDate("insert_date").toInstant(), ZoneId.systemDefault()), 
	    		LocalDateTime.ofInstant(r.getDate("update_date").toInstant(), ZoneId.systemDefault()), 
	    		LocalDateTime.ofInstant(r.getDate("send_date").toInstant(), ZoneId.systemDefault()),
	    		r.getString("writer"), r.getString("job_type"), r.getString("job_function"),
	    		r.getString("answer"));
	}
}