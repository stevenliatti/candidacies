package forms;

import static beans.Bean.emailField;
import static beans.Bean.requestDateField;
import static java.lang.Integer.parseInt;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.joda.time.LocalDate;

import dao.ObjectDAO;

public abstract class Form {
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
		} catch (Exception e) {
			setError(emailField, e.getMessage());
		}
		return email;
	}
	
	protected String validatePositiveNumber(String str, String field) throws Exception {
		int number = Integer.parseInt(str);
		try {
			if (number < 0) {
				throw new Exception("Merci d'indiquer un nombre positif");
			}
		} catch (Exception e) {
			setError(field, e.getMessage());
		}
		return str;
	}

	protected LocalDate validateRequestDate(String dateString) throws Exception {
		LocalDate requestDate = null;
		try {
			if (dateString != null) {
				if (!dateString.matches("(0[1-9]|[12][0-9]|3[01])[.](0[1-9]|1[012])[.](20[0-9][0-9])")) {
					throw new Exception("Merci de saisir une date au format suivant : jj.mm.aaaa");
				}
				String dateArray[] = dateString.split("\\.");
				requestDate = new LocalDate(parseInt(dateArray[2]), parseInt(dateArray[1]), parseInt(dateArray[0]));
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
		if (fieldName == null || fieldName.isEmpty()) {
			return null;
		}
		String value = request.getParameter(fieldName);
		if (value == null || value.isEmpty()) { return null; }
		else { return value.trim(); }
	}

	protected void setError(String field, String message) {
		errors.put(field, message);
	}
}
