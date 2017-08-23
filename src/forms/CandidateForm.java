package forms;

import static beans.Bean.answerField;
import static beans.Bean.countryField;
import static beans.Bean.emailField;
import static beans.Bean.firstNameField;
import static beans.Bean.folderField;
import static beans.Bean.idField;
import static beans.Bean.initialsField;
import static beans.Bean.jobFunctionField;
import static beans.Bean.lastNameField;
import static beans.Bean.livesAtField;
import static beans.Bean.localityField;
import static beans.Bean.notTransmittedField;
import static beans.Bean.numStreetField;
import static beans.Bean.postCodeField;
import static beans.Bean.requestDateField;
import static beans.Bean.sendTypeField;
import static beans.Bean.streetField;
import static beans.Bean.titleField;

import javax.servlet.http.HttpServletRequest;

import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;

import beans.Candidate;
import core.Letter;
import dao.AnswerDAO;
import dao.CandidateDAO;
import dao.DAOException;

/**
 * Create or update candidate from form. Generate the letters at this time.
 * 
 * @author stevenliatti
 *
 */
public class CandidateForm extends Form {
	private CandidateDAO candidateDAO; 
	private AnswerDAO answerDAO;

	public CandidateForm(CandidateDAO candidateDAO, AnswerDAO answerDAO) {
		this.candidateDAO = candidateDAO;
		this.answerDAO = answerDAO;
	}

	public Candidate createCandidate(HttpServletRequest r) {
		String title = getField(r, titleField);
		String lastName = getField(r, lastNameField);
		String firstName = getField(r, firstNameField);
		String email = getField(r, emailField);
		String postCode = getField(r, postCodeField);
		LocalDate requestDate = null;
		String answer = getField(r, answerField);
		String folder = getField(r, folderField);
		String sendType = getField(r, sendTypeField);
		Candidate candidate = null;
		
		try {
			title = validateString(title, titleField, "Merci de sélectionner un titre");
			lastName = validateString(lastName, lastNameField, "Merci de saisir un nom de famille.");
			firstName = validateString(firstName, firstNameField, "Merci de saisir un prénom.");
			email = validateEmail(email);
			postCode = validatePositiveNumber(postCode, postCodeField);
			requestDate = validateRequestDate(getField(r, requestDateField));
			answer = validateString(answer, answerField, "Merci de choisir une réponse");
			folder = validateString(folder, folderField, "Merci d'indiquer annexe dossier");
			sendType = validateSendType(email, getField(r, streetField), getField(r, numStreetField), postCode, getField(r, localityField), 
					getField(r, countryField), sendType);
			LocalDateTime now = LocalDateTime.now();

			candidate = new Candidate(null, title, upperFirst(lastName), 
					upperFirst(firstName), email, upperFirst(getField(r, livesAtField)), upperFirst(getField(r, streetField)),
					getField(r, numStreetField), postCode, upperFirst(getField(r, localityField)),
					upperFirst(getField(r, countryField)), requestDate, now, now, getField(r, initialsField), 
					getField(r, jobFunctionField), 
					answer, null,
					folder,
					sendType, null, getField(r, notTransmittedField)
					);
			
			Letter.makeLetter(candidate, answerDAO.read(answer));

			if (errors.isEmpty()) {
				candidateDAO.create(candidate);
				result = "Candidat bien inséré";
			}
			else {
				result = "Erreurs dans le formulaire";
			}
		} catch (DAOException e) {
			result = "Échec de la création d'un candidat.";
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return candidate;
	}
	
	public Candidate updateCandidate(HttpServletRequest r) {
		Candidate updateCandidate = null;
		
		Long id = Long.parseLong(getField(r, idField));
		String title = getField(r, titleField);
		String lastName = getField(r, lastNameField);
		String firstName = getField(r, firstNameField);
		String email = getField(r, emailField);
		String postCode = getField(r, postCodeField);
		LocalDate requestDate = null;
		String answer = getField(r, answerField);
		String folder = getField(r, folderField);
		String sendType = getField(r, sendTypeField);
		
		try {
			title = validateString(title, titleField, "Merci de sélectionner un titre");
			lastName = validateString(lastName, lastNameField, "Merci de saisir un nom de famille.");
			firstName = validateString(firstName, firstNameField, "Merci de saisir un prénom.");
			email = validateEmail(email);
			postCode = validatePositiveNumber(postCode, postCodeField);
			requestDate = validateRequestDate(getField(r, requestDateField));
			answer = validateString(answer, answerField, "Merci de choisir une réponse");
			folder = validateString(folder, folderField, "Merci d'indiquer annexe dossier");
			sendType = validateSendType(email, getField(r, streetField), getField(r, numStreetField), postCode, getField(r, localityField), 
					getField(r, countryField), sendType);
			LocalDateTime now = LocalDateTime.now();
			
			Candidate candidate = candidateDAO.read(id);

			updateCandidate = new Candidate(id, title, upperFirst(lastName), 
					upperFirst(firstName), email, upperFirst(getField(r, livesAtField)), upperFirst(getField(r, streetField)),
					getField(r, numStreetField), postCode, upperFirst(getField(r, localityField)),
					upperFirst(getField(r, countryField)), requestDate, candidate.getInsertDate(), now, 
					getField(r, initialsField), getField(r, jobFunctionField), 
					answer, null,
					folder,
					sendType, null, getField(r, notTransmittedField)
					);

			Letter.makeLetter(updateCandidate, answerDAO.read(answer));
			
			if (errors.isEmpty()) {
				candidateDAO.update(updateCandidate);
				result = "Candidat bien modifié";
			}
			else {
				result = "Erreurs dans le formulaire";
			}
		} catch (DAOException e) {
			result = "Échec de la modification d'un candidat.";
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return updateCandidate;
	}
}
