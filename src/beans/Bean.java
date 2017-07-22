package beans;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public interface Bean {
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
	public static final String sendDateField = "sendDate";
	public static final String jobTypeField = "jobType";
	public static final String jobFunctionField = "jobFunction";
	public static final String answerField = "answer";
	public static final String folderField = "folder";
	
	public static final String userNameField = "userName";
	public static final String passwordField = "password";
	public static final String initialsField = "initials";
	
	public static final String nameField = "name";
	public static final String contentField = "content";
	
	public static final String negative = "negative";
	public static final String negativeSixMonths = "negativeSixMonths";
	public static final String suspendSixMonths = "suspendSixMonths";
	
	public final static DateTimeFormatter dateFormFormatter = DateTimeFormat.forPattern("dd.MM.yyyy");
	public final static DateTimeFormatter dateShowFormatter = DateTimeFormat.forPattern("d MMMM yyyy");
	public final static DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("d MMMM yyyy à HH:mm:ss");
	public final static DateTimeFormatter sqlDateFormatter = DateTimeFormat.forPattern("yyyy-MM-dd");
	public final static DateTimeFormatter sqlDateTimeFormatter = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
}
