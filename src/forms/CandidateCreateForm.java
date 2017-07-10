package forms;

import static java.lang.Integer.parseInt;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import beans.Candidate;
import dao.CandidateDAO;
import dao.DAOException;

public class CandidateCreateForm {
	private static final String titleField = "title";
	private static final String lastNameField = "last_name";
	private static final String firstNameField = "first_name";
	private static final String emailField = "email";
	private static final String livesAtField = "lives_at";
	private static final String streetField = "street";
	private static final String numStreetField = "num_street";
	private static final String postCodeField = "post_code";
	private static final String localityField = "locality";
	private static final String countryField = "country";
	private static final String requestDateField = "request_date";
	private static final String jobTypeField = "job_type";
	private static final String jobFunctionField = "job_function";

	private String result;
	private Map<String, String> errors = new HashMap<>();
	private CandidateDAO candidateDAO;

	public CandidateCreateForm(CandidateDAO candidateDAO) {
		this.candidateDAO = candidateDAO;
	}

	public String getResult() {
		return result;
	}

	public Map<String, String> getErrors() {
		return errors;
	}

	public Candidate createCandidate(HttpServletRequest r) {
		String lastName = getField(r, lastNameField);
		String firstName = getField(r, firstNameField);
		String email = getField(r, emailField);
		LocalDate requestDate = null;
		Candidate candidate = null;
		try {
			lastName = validateLastName(lastName);
			firstName = validateFirstName(firstName);
			email = validateEmail(email);
			requestDate = validateRequestDate(getField(r, requestDateField));
			LocalDateTime now = LocalDateTime.now();

			candidate = new Candidate(null, getField(r, titleField), lastName, 
					firstName, email, getField(r, livesAtField), getField(r, streetField),
					getField(r, numStreetField), getField(r, postCodeField), getField(r, localityField),
					getField(r, countryField), requestDate, now, now, now,
					"bob", getField(r, jobTypeField), getField(r, jobFunctionField), "non");

			if (errors.isEmpty()) {
				candidateDAO.create(candidate);
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

	private String validateLastName(String lastName) throws Exception {
		try {
			if (lastName == null || lastName.isEmpty())
				throw new Exception("Merci de saisir un nom de famille.");
		} catch (Exception e) {
			setError(lastNameField, e.getMessage());
		}
		return lastName;
	}

	private String validateFirstName(String firstName) throws Exception {
		try {
			if (firstName == null || firstName.isEmpty())
				throw new Exception("Merci de saisir un prénom.");
		} catch (Exception e) {
			setError(firstNameField, e.getMessage());
		}
		return firstName;
	}

	private String validateEmail(String email) throws Exception {
		try {
			if (email != null) {
				if (!email.matches("([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)"))
					throw new Exception("Merci de saisir une adresse mail valide.");
			} 
			//			else {
			//				throw new Exception("Merci de saisir une adresse mail.");
			//			}
		} catch (Exception e) {
			setError(emailField, e.getMessage());
		}
		return email;
	}

	private LocalDate validateRequestDate(String dateString) throws Exception {
		LocalDate requestDate = null;
		try {
			if (dateString != null) {
				if (!dateString.matches("(0[1-9]|[12][0-9]|3[01])[.](0[1-9]|1[012])[.](20[0-9][0-9])")) {
					throw new Exception("Merci de saisir une date au format suivant : jj.mm.aaaa");
				}
				String dateArray[] = dateString.split("\\.");
				requestDate = LocalDate.of(parseInt(dateArray[2]), parseInt(dateArray[1]), parseInt(dateArray[0]));
				if (requestDate.isAfter(LocalDate.now())) {
					throw new Exception("Merci de saisir une date antérieure ou égale à aujourd'hui.");
				}
			}
		} catch (Exception e) {
			setError(requestDateField, e.getMessage());
		}
		return requestDate;
	}

	private String getField(HttpServletRequest request, String fieldName) {
		String value = request.getParameter(fieldName);
		if (value.isEmpty() || value == null) { return null; }
		else { return value.trim(); }
	}

	private void setError(String field, String message) {
		errors.put(field, message);
	}
}
