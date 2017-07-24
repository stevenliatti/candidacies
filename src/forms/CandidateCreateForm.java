package forms;

import static beans.Bean.answerField;
import static beans.Bean.countryField;
import static beans.Bean.emailField;
import static beans.Bean.firstNameField;
import static beans.Bean.folderField;
import static beans.Bean.initialsField;
import static beans.Bean.jobFunctionField;
import static beans.Bean.lastNameField;
import static beans.Bean.livesAtField;
import static beans.Bean.localityField;
import static beans.Bean.numStreetField;
import static beans.Bean.postCodeField;
import static beans.Bean.requestDateField;
import static beans.Bean.streetField;
import static beans.Bean.titleField;

import javax.servlet.http.HttpServletRequest;

import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;

import beans.Candidate;
import dao.DAOException;
import dao.ObjectDAO;

public class CandidateCreateForm extends Form {

	public CandidateCreateForm(ObjectDAO objectDAO) {
		super(objectDAO);
	}

	public Candidate createCandidate(HttpServletRequest r) {
		String title = getField(r, titleField);
		String lastName = getField(r, lastNameField);
		String firstName = getField(r, firstNameField);
		String email = getField(r, emailField);
		LocalDate requestDate = null;
		String answer = getField(r, answerField);
		String folder = getField(r, folderField);
		Candidate candidate = null;
		try {
			title = validateString(title, titleField, "Merci de sélectionner un titre");
			lastName = validateString(lastName, lastNameField, "Merci de saisir un nom de famille.");
			firstName = validateString(firstName, firstNameField, "Merci de saisir un prénom.");
			email = validateEmail(email);
			requestDate = validateRequestDate(getField(r, requestDateField));
			answer = validateString(answer, answerField, "Merci de choisir une réponse");
			folder = validateString(folder, folderField, "Merci d'indiquer annexe dossier");
			LocalDateTime now = LocalDateTime.now();

			candidate = new Candidate(null, title, lastName, 
					firstName, email, getField(r, livesAtField), getField(r, streetField),
					getField(r, numStreetField), getField(r, postCodeField), getField(r, localityField),
					getField(r, countryField), requestDate, now, now, null,
					getField(r, initialsField), 
					getField(r, jobFunctionField), 
					answer, 
					folder
					);

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
		String title = getField(r, titleField);
		String lastName = getField(r, lastNameField);
		String firstName = getField(r, firstNameField);
		String email = getField(r, emailField);
		LocalDate requestDate = null;
		String answer = getField(r, answerField);
		String folder = getField(r, folderField);
		try {
			title = validateString(title, titleField, "Merci de sélectionner un titre");
			lastName = validateString(lastName, lastNameField, "Merci de saisir un nom de famille.");
			firstName = validateString(firstName, firstNameField, "Merci de saisir un prénom.");
			email = validateEmail(email);
			requestDate = validateRequestDate(getField(r, requestDateField));
			answer = validateString(answer, answerField, "Merci de choisir une réponse");
			folder = validateString(folder, folderField, "Merci d'indiquer annexe dossier");
			LocalDateTime now = LocalDateTime.now();

			candidate = new Candidate(null, getField(r, titleField), lastName, 
					firstName, email, getField(r, livesAtField), getField(r, streetField),
					getField(r, numStreetField), getField(r, postCodeField), getField(r, localityField),
					getField(r, countryField), requestDate, candidate.getInsertDate(), now, candidate.getSendDate(),
					getField(r, initialsField), getField(r, jobFunctionField), 
					answer, 
					folder
					);

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
