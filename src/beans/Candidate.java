package beans;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Candidate {
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
	private LocalDateTime sendDate;
	private String writer;
	private String jobType;
	private String jobFunction;
	private String answer;
	
	public Candidate() {}
	
	public Candidate(Long id, String title, String lastName, String firstName, String email, String livesAt,
			String street, String numStreet, String postCode, String locality, String country, LocalDate requestDate,
			LocalDateTime insertDate, LocalDateTime updateDate, LocalDateTime sendDate, String writer, String jobType,
			String jobFunction, String answer) {
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
		this.sendDate = sendDate;
		this.writer = writer;
		this.jobType = jobType;
		this.jobFunction = jobFunction;
		this.answer = answer;
	}
	
	@Override
	public String toString() {
		return "Candidate [id=" + id + ", title=" + title + ", lastName=" + lastName + ", firstName=" + firstName
				+ ", email=" + email + ", livesAt=" + livesAt + ", street=" + street + ", numStreet=" + numStreet
				+ ", postCode=" + postCode + ", locality=" + locality + ", country=" + country + ", requestDate="
				+ requestDate + ", insertDate=" + insertDate + ", updateDate=" + updateDate + ", sendDate=" + sendDate
				+ ", writer=" + writer + ", jobType=" + jobType + ", jobFunction=" + jobFunction + ", answer=" + answer
				+ "]";
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

	public LocalDateTime getSendDate() {
		return sendDate;
	}

	public void setSendDate(LocalDateTime sendDate) {
		this.sendDate = sendDate;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getJobType() {
		return jobType;
	}

	public void setJobType(String jobType) {
		this.jobType = jobType;
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
}
