package forms;

import static java.lang.Integer.parseInt;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import dao.ObjectDAO;

public abstract class Form {
	protected static final String titleField = "title";
	protected static final String lastNameField = "last_name";
	protected static final String firstNameField = "first_name";
	protected static final String emailField = "email";
	protected static final String livesAtField = "lives_at";
	protected static final String streetField = "street";
	protected static final String numStreetField = "num_street";
	protected static final String postCodeField = "post_code";
	protected static final String localityField = "locality";
	protected static final String countryField = "country";
	protected static final String requestDateField = "request_date";
	protected static final String jobTypeField = "job_type";
	protected static final String jobFunctionField = "job_function";
	
	protected static final String userNameField = "user_name";
	protected static final String passwordField = "password";
	protected static final String initialsField = "initials";
	
	protected static final String nameField = "name";
	protected static final String contentField = "content";

	protected String result;
	protected Map<String, String> errors = new HashMap<>();
	protected ObjectDAO objectDAO;

	public Form(ObjectDAO objectDAO) {
		this.objectDAO = objectDAO;
	}

	public String getResult() {
		return result;
	}

	public Map<String, String> getErrors() {
		return errors;
	}
	
	protected String validateString(String name, String field, String message) throws Exception {
		try {
			if (name == null || name.isEmpty())
				throw new Exception(message);
		} catch (Exception e) {
			setError(field, e.getMessage());
		}
		return name;
	}

	protected String validateEmail(String email) throws Exception {
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

	protected LocalDate validateRequestDate(String dateString) throws Exception {
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

	protected String getField(HttpServletRequest request, String fieldName) {
		String value = request.getParameter(fieldName);
		if (value.isEmpty() || value == null) { return null; }
		else { return value.trim(); }
	}

	protected void setError(String field, String message) {
		errors.put(field, message);
	}
}
