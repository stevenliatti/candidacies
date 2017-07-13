package forms;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;

import beans.Candidate;
import dao.DAOException;
import dao.ObjectDAO;

public class CandidateCreateForm extends Form {

	public CandidateCreateForm(ObjectDAO objectDAO) {
		super(objectDAO);
	}

	public Candidate createCandidate(HttpServletRequest r) {
		String lastName = getField(r, lastNameField);
		String firstName = getField(r, firstNameField);
		String email = getField(r, emailField);
		LocalDate requestDate = null;
		Candidate candidate = null;
		try {
			lastName = validateName(lastName, lastNameField, "Merci de saisir un nom de famille.");
			firstName = validateName(firstName, firstNameField, "Merci de saisir un prénom.");
			email = validateEmail(email);
			requestDate = validateRequestDate(getField(r, requestDateField));
			LocalDateTime now = LocalDateTime.now();

			candidate = new Candidate(null, getField(r, titleField), lastName, 
					firstName, email, getField(r, livesAtField), getField(r, streetField),
					getField(r, numStreetField), getField(r, postCodeField), getField(r, localityField),
					getField(r, countryField), requestDate, now, now, now,
					"bob", getField(r, jobTypeField), getField(r, jobFunctionField), "non");

			if (errors.isEmpty()) {
				objectDAO.create(candidate);
				result = "success";
			}
			else {
				result = "errors";
			}
		} catch (DAOException e) {
			result = "Échec de la création d'un candidat.";
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return candidate;
	}
	
	public Candidate updateCandidate(HttpServletRequest r, Candidate candidate) {
		String lastName = candidate.getLastName();
		String firstName = candidate.getFirstName();
		String email = candidate.getEmail();
		LocalDate requestDate = candidate.getRequestDate();
		try {
			lastName = validateName(lastName, lastNameField, "Merci de saisir un nom de famille.");
			firstName = validateName(firstName, firstNameField, "Merci de saisir un prénom.");
			email = validateEmail(email);
			requestDate = validateRequestDate(getField(r, requestDateField));
			LocalDateTime now = LocalDateTime.now();

			candidate = new Candidate(null, getField(r, titleField), lastName, 
					firstName, email, getField(r, livesAtField), getField(r, streetField),
					getField(r, numStreetField), getField(r, postCodeField), getField(r, localityField),
					getField(r, countryField), requestDate, candidate.getInsertDate(), now, candidate.getSendDate(),
					"bob", getField(r, jobTypeField), getField(r, jobFunctionField), "non");

			if (errors.isEmpty()) {
				objectDAO.update(candidate);
				result = "success";
			}
			else {
				result = "errors";
			}
		} catch (DAOException e) {
			result = "Échec de la création d'un candidat.";
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return candidate;
	}
}
