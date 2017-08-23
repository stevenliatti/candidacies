package beans;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 * Represent a bean, a model for a table in database.
 * 
 * @author stevenliatti
 *
 */
public abstract class Bean {
	public static final String idField = "id";
	public static final String titleField = "title";
	public static final String lastNameField = "lastName";
	public static final String firstNameField = "firstName";
	public static final String emailField = "email";
	public static final String livesAtField = "livesAt";
	public static final String streetField = "street";
	public static final String numStreetField = "numStreet";
	public static final String postCodeField = "postCode";
	public static final String localityField = "locality";
	public static final String countryField = "country";
	public static final String requestDateField = "requestDate";
	public static final String insertDateField = "insertDate";
	public static final String updateDateField = "updateDate";
	public static final String jobFunctionField = "jobFunction";
	public static final String answerField = "answer";
	public static final String answerTitleField = "answerTitle";
	public static final String folderField = "folder";
	public static final String sendTypeField = "sendType";
	public static final String initialsField = "initials";
	public static final String letterField = "letter";
	public static final String notTransmittedField = "notTransmitted";

	public static final String nameField = "name";
	public static final String contentField = "content";
	public static final String hideField = "hide";

	public final static DateTimeFormatter dateFormFormatter = DateTimeFormat.forPattern("dd.MM.yyyy");
	public final static DateTimeFormatter dateLatexFormatter = DateTimeFormat.forPattern("d MMMM yyyy");
	public final static DateTimeFormatter dateShortFormatter = DateTimeFormat.forPattern("dd.MM.yy");
	public final static DateTimeFormatter dateTimeShortFormatter = DateTimeFormat.forPattern("dd.MM.yy à HH:mm:ss");
	public final static DateTimeFormatter sqlDateFormatter = DateTimeFormat.forPattern("yyyy-MM-dd");
	public final static DateTimeFormatter sqlDateTimeFormatter = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");

	Map<String, String> map;

	/**
	 * Return the list of fields that are usable in answers creation/edition.
	 * Example : title, requestDate, etc.
	 * 
	 * @return
	 */
	public static List<String> getFields() {
		List<String> list = new ArrayList<>();
		list.add(formatField(titleField));
		list.add(formatField(lastNameField));
		list.add(formatField(firstNameField)); 
		list.add(formatField(emailField));
		list.add(formatField(livesAtField));
		list.add(formatField(streetField));
		list.add(formatField(numStreetField));
		list.add(formatField(postCodeField));
		list.add(formatField(localityField));
		list.add(formatField(countryField));
		list.add(formatField(requestDateField));
		list.add(formatField(updateDateField));
		list.add(formatField(jobFunctionField));
		list.add(formatField(answerField));
		list.add(formatField(answerTitleField));
		list.add(formatField(folderField));
		list.add(formatField(initialsField));
		return list;
	}

	public static String formatField(String field) {
		return "<" + field + ">";
	}

	public Map<String, String> getMap() {
		return map;
	}

	/**
	 * Change the word str singular to his same in plural.
	 * 
	 * @param str
	 * @return
	 */
	public static String plural(String str) {
		if (str.charAt(str.length() - 1) == 'y') {
			return str.substring(0, str.length() - 1) + "ies";
		}
		else {
			return str.substring(0, str.length()) + "s";
		}
	}
}
