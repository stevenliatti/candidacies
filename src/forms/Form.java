package forms;

import static beans.Bean.countryField;
import static beans.Bean.emailField;
import static beans.Bean.localityField;
import static beans.Bean.numStreetField;
import static beans.Bean.postCodeField;
import static beans.Bean.requestDateField;
import static beans.Bean.streetField;
import static java.lang.Integer.parseInt;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.joda.time.LocalDate;

import beans.Bean;

public abstract class Form {
	protected String result;
	protected Map<String, String> errors = new HashMap<>();

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

	protected String validatePositiveNumber(String str, String field, String message) throws Exception {
		try {
			if (str == null || str.isEmpty()) {
				throw new Exception(message);
			}
			else {
				return validatePositiveNumber(str, field);
			}
		} catch (Exception e) {
			setError(field, message);
		}
		return str;
	}

	protected String validatePositiveNumber(String str, String field) throws Exception {
		try {
			if (str != null) {
				int number = Integer.parseInt(str);
				if (number < 0) {
					throw new Exception("Merci d'indiquer un nombre positif");
				}
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

	protected String validateSendType(String email, String street, String numStreet, String postCode, 
			String locality, String country, String sendType) throws Exception {
		try {
			if (sendType.equals("email")) {
				if (email == null || email.isEmpty()) {
					throw new Exception("Pour envoyer un mail, il faut renseigner une adresse valide.");
				}
				validateEmail(email);
			}
			else if (sendType.equals("pdf")) {
				validateString(street, streetField, "Manque la rue.");
				validateString(numStreet, numStreetField, "Manque le numéro de rue.");
				validatePositiveNumber(postCode, postCodeField, "Manque le numéro postal.");
				validateString(locality, localityField, "Manque la localité");
				validateString(country, countryField, "Manque le pays.");
			}
			else {
				throw new NullPointerException("Merci de renseigner un type d'envoi");
			}
		} catch (NullPointerException e) {
			setError(Bean.sendTypeField, e.getMessage());
		} catch (Exception e) {
			setError(Bean.sendTypeField, e.getMessage());
		}
		return sendType;
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
