package forms;

import static java.lang.Integer.parseInt;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import beans.Candidate;
import dao.CandidateDAO;

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
		try {
			validateLastName(getField(r, lastNameField));
		} catch (Exception e) {
			setError(lastNameField, e.getMessage());
		}

		try {
			validateFirstName(getField(r, firstNameField));
		} catch (Exception e) {
			setError(firstNameField, e.getMessage());
		}

		try {
			validateEmail(getField(r, emailField));
		} catch (Exception e) {
			setError(emailField, e.getMessage());
		}

		LocalDate requestDate = null;
		try {
			requestDate = getDateField(r, requestDateField);
			validateRequestDate(requestDate);
		} catch (Exception e) {
			setError(requestDateField, e.getMessage());
		}
		
		LocalDateTime now = LocalDateTime.now();

		Candidate candidate = new Candidate(null, getField(r, titleField), getField(r, lastNameField), 
				getField(r, firstNameField), 
				getField(r, emailField), getField(r, livesAtField), getField(r, streetField),
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
		
		return candidate;
	}
	
	
	
	private void validateLastName(String lastName) throws Exception {
		if (lastName == null || lastName.isEmpty()) {
			throw new Exception("Merci de saisir un nom de famille.");
		}
	}
	
	private void validateFirstName(String firstName) throws Exception {
		if (firstName == null || firstName.isEmpty()) {
			throw new Exception("Merci de saisir un prénom.");
		}
	}
	
	private void validateEmail(String email) throws Exception {
		if (email != null) {
			if (!email.matches("([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)")) {
				throw new Exception("Merci de saisir une adresse mail valide.");
			}
		} 
//		else {
//			throw new Exception("Merci de saisir une adresse mail.");
//		}
	}

	private void validateRequestDate(LocalDate requestDate) throws Exception {
		if (requestDate != null) {
			if (requestDate.isAfter(LocalDate.now())) {
				throw new Exception("Merci de saisir une date antérieure ou égale à aujourd'hui.");
			}
		}
	}
	
	private String getField(HttpServletRequest request, String fieldName) {
		String value = request.getParameter(fieldName);
		if (value.isEmpty() || value == null) {
			return null;
		}
		else {
			return value.trim();
		}
	}
	
	private LocalDate getDateField(HttpServletRequest request, String fieldName) throws Exception {
		String value = request.getParameter(fieldName);
		if (value.isEmpty() || value == null) {
			return null;
		}
		if (!value.matches("(0[1-9]|[12][0-9]|3[01])[.](0[1-9]|1[012])[.](20[0-9][0-9])")) {
			throw new Exception("Merci de saisir une date au format suivant : jj.mm.aaaa");
		}
		String dateArray[] = value.split("\\.");
		return LocalDate.of(parseInt(dateArray[2]), parseInt(dateArray[1]), parseInt(dateArray[0]));
	}
	
	private void setError(String field, String message) {
		errors.put(field, message);
	}
}
