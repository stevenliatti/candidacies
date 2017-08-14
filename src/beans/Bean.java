package beans;

import java.util.Map;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

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
	public static final String jobTypeField = "jobType";
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
	
	public final static DateTimeFormatter dateFormFormatter = DateTimeFormat.forPattern("dd.MM.yyyy");
	public final static DateTimeFormatter dateLatexFormatter = DateTimeFormat.forPattern("d MMMM yyyy");
	public final static DateTimeFormatter dateShortFormatter = DateTimeFormat.forPattern("dd.MM.yy");
	public final static DateTimeFormatter dateTimeShortFormatter = DateTimeFormat.forPattern("dd.MM.yy à HH:mm:ss");
	public final static DateTimeFormatter sqlDateFormatter = DateTimeFormat.forPattern("yyyy-MM-dd");
	public final static DateTimeFormatter sqlDateTimeFormatter = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
	
	Map<String, String> map;
	
	public static String formatField(String field) {
		return "<" + field + ">";
	}
	
	public Map<String, String> getMap() {
		return map;
	}
	
	public static String plural(String str) {
		if (str.charAt(str.length() - 1) == 'y') {
			return str.substring(0, str.length() - 1) + "ies";
		}
		else {
			return str.substring(0, str.length()) + "s";
		}
	}
}
