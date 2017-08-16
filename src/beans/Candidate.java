package beans;

import java.util.HashMap;

import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;

public class Candidate extends Bean {
	private Long id;
	private String title;
	private String lastName;
	private String firstName;
	private String email;
	private String livesAt;
	private String street;
	private String numStreet;
	private String postCode;
	private String locality;
	private String country;
	private LocalDate requestDate;
	private LocalDateTime insertDate;
	private LocalDateTime updateDate;
	private String initials;
	private String jobFunction;
	private String answer;
	private String answerTitle;
	private String folder;
	private String sendType;
	private String letter;
	private String notTransmitted;

	public Candidate() {}

	public Candidate(Long id, String title, String lastName, String firstName, String email, String livesAt,
			String street, String numStreet, String postCode, String locality, String country, LocalDate requestDate,
			LocalDateTime insertDate, LocalDateTime updateDate, String initials, String jobFunction, String answer,
			String answerTitle, String folder, String sendType, String letter, String notTransmitted) {
		this.id = id;
		this.title = title;
		this.lastName = lastName;
		this.firstName = firstName;
		this.email = email;
		this.livesAt = livesAt;
		this.street = street;
		this.numStreet = numStreet;
		this.postCode = postCode;
		this.locality = locality;
		this.country = country;
		this.requestDate = requestDate;
		this.insertDate = insertDate;
		this.updateDate = updateDate;
		this.initials = initials;
		this.jobFunction = jobFunction;
		this.answer = answer;
		this.answerTitle = answerTitle;
		this.folder = folder;
		this.sendType = sendType;
		this.letter = letter;
		this.notTransmitted = notTransmitted;
		
		candidateAsMap();
	}

	private void candidateAsMap() {
		map = new HashMap<>();
		map.put(formatField(titleField), title);
		map.put(formatField(lastNameField), lastName);
		map.put(formatField(firstNameField), firstName);
		map.put(formatField(emailField), email);
		map.put(formatField(livesAtField), getFullLivesAtLatex());
		map.put(formatField(streetField), street);
		map.put(formatField(numStreetField), numStreet);
		map.put(formatField(postCodeField), getLatexPostCode());
		map.put(formatField(localityField), locality);
		map.put(formatField(countryField), country);
		map.put(formatField(requestDateField), getRequestDateLetterFormatted());
		map.put(formatField(updateDateField), getUpdateDateLatexFormatted());
		map.put(formatField(initialsField), initials);
		map.put(formatField(jobFunctionField), jobFunction);
		map.put(formatField(answerField), answer);
		map.put(formatField(folderField), getLatexFolder());
	}
	
	public boolean todayCandidate() {
		return insertDate.toLocalDate().equals(LocalDate.now()) || updateDate.toLocalDate().equals(LocalDate.now());
	}
	
	public String getShortTitle() {
		if (title.equals("Monsieur")) {
			return "M.";
		}
		else if (title.equals("Madame")) {
			return "Mme";
		}
		else if (title.equals("Mademoiselle")) {
			return "Mlle";
		}
		return title;
	}

	private String getFullLivesAtLatex() {
		if (livesAt == null || livesAt.isEmpty()) {
			return null;
		}
		else {
			return "Chez " + livesAt + " \\\\\\\\";
		}
	}

	private String getLatexPostCode() {
		if (postCode == null || postCode.isEmpty()) {
			return null;
		}
		else if (country != null && !country.isEmpty() && !country.equalsIgnoreCase("suisse")) {
			return country.toUpperCase().charAt(0) + "-" + postCode;
		}
		return postCode;
	}

	public String getRequestDateFormatted() {
		return requestDate == null ? null : requestDate.toString(dateShortFormatter);
	}

	public String getRequestDateLetterFormatted() {
		return requestDate == null ? null : "du " + requestDate.toString(dateLatexFormatter);
	}

	public String getRequestDateFormFormatted() {
		return requestDate == null ? null : requestDate.toString(dateFormFormatter);
	}

	public String getRequestDateSQLFormatted() {
		return requestDate == null ? null : requestDate.toString(sqlDateFormatter);
	}

	public String getInsertDateFormatted() {
		return insertDate == null ? null : insertDate.toString(dateTimeShortFormatter);
	}

	public String getUpdateDateLatexFormatted() {
		return updateDate == null ? null : updateDate.toString(dateLatexFormatter);
	}
	
	public String getUpdateDateFormatted() {
		return updateDate == null ? null : updateDate.toString(dateTimeShortFormatter);
	}

	private String getLatexFolder() {
		if (folder == null || folder.isEmpty()) {
			return "";
		}
		else if (folder.equals("yes")) {
			return "Annexe : votre dossier";
		}
		return "";
	}
	
	@Override
	public String toString() {
		return "Candidate [id=" + id + ", title=" + title + ", lastName=" + lastName + ", firstName=" + firstName
				+ ", email=" + email + ", livesAt=" + livesAt + ", street=" + street + ", numStreet=" + numStreet
				+ ", postCode=" + postCode + ", locality=" + locality + ", country=" + country + ", requestDate="
				+ requestDate + ", insertDate=" + insertDate + ", updateDate=" + updateDate + ", initials=" + initials
				+ ", jobFunction=" + jobFunction + ", answer=" + answer + ", answerTitle=" + answerTitle + ", folder="
				+ folder + ", sendType=" + sendType + ", letter=" + letter + "]";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLivesAt() {
		return livesAt;
	}

	public void setLivesAt(String livesAt) {
		this.livesAt = livesAt;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getNumStreet() {
		return numStreet;
	}

	public void setNumStreet(String numStreet) {
		this.numStreet = numStreet;
	}

	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	public String getLocality() {
		return locality;
	}

	public void setLocality(String locality) {
		this.locality = locality;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public LocalDate getRequestDate() {
		return requestDate;
	}

	public void setRequestDate(LocalDate requestDate) {
		this.requestDate = requestDate;
	}

	public LocalDateTime getInsertDate() {
		return insertDate;
	}

	public void setInsertDate(LocalDateTime insertDate) {
		this.insertDate = insertDate;
	}

	public LocalDateTime getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(LocalDateTime updateDate) {
		this.updateDate = updateDate;
	}

	public String getInitials() {
		return initials;
	}

	public void setInitials(String initials) {
		this.initials = initials;
	}

	public String getJobFunction() {
		return jobFunction;
	}

	public void setJobFunction(String jobFunction) {
		this.jobFunction = jobFunction;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String getFolder() {
		return folder;
	}

	public void setFolder(String folder) {
		this.folder = folder;
	}

	public String getSendType() {
		return sendType;
	}

	public void setSendType(String sendType) {
		this.sendType = sendType;
	}

	public String getLetter() {
		return letter;
	}

	public void setLetter(String letter) {
		this.letter = letter;
	}
	
	public String getAnswerTitle() {
		return answerTitle;
	}

	public void setAnswerTitle(String answerTitle) {
		this.answerTitle = answerTitle;
	}

	public String getNotTransmitted() {
		return notTransmitted;
	}

	public void setNotTransmitted(String notTransmitted) {
		this.notTransmitted = notTransmitted;
	}
}
