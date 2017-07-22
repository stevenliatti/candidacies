package core;

import static beans.Bean.initialsField;
import static beans.Bean.livesAtField;
import static beans.Candidate.formatField;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;

import beans.Candidate;
import beans.User;

public class Letter {
	private static final String latexPath = "data/latex/";
	public static final String modelPreamble = read(latexPath + "preamble.tex");
	public static final String modelNegative = read(latexPath + "negatif.tex");
	public static final String modelNegativeSixMonths = read(latexPath + "negatifSixMois.tex");
	public static final String modelSuspendSixMonths = read(latexPath + "suspensSixMois.tex");

	private Candidate candidate;
	private User user;
	private String finalContent;

	public static void main(String[] args) throws IllegalArgumentException, IOException {
		Candidate candidate = new Candidate(null, "M.", "Dupont", "Jean", "jean@mail.com", "Bob", "Rue de Lyon", 
				"4", "1202", "Genève", "Suisse", LocalDate.now(), LocalDateTime.now(), LocalDateTime.now(), 
				LocalDateTime.now(), "sl", "infirmier", "negative", "yes");
		User user = new User(null, "steven", "abc", "Liatti", "Steven", "sl");
		Letter letter = new Letter(candidate, user, modelNegative, "data/latex/test.tex");
		System.out.println(letter);
	}
	
	public Letter(Candidate candidate, User user, String model) throws IllegalArgumentException, IOException {
		this.candidate = candidate;
		this.user = user;
		parse(model);
	}
	
	public Letter(Candidate candidate, User user, String model, String filename) throws IllegalArgumentException, IOException {
		this.candidate = candidate;
		this.user = user;
		parse(model);
		write(filename);
	}

	private static String read(String filename) {
		String model = "";
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(filename));
			String line;
			while ((line = br.readLine()) != null) {
				// match string like : "\input{file.tex} and line return "file.tex"
				if (line.matches("\\\\input\\{[a-zA-Z0-9_]*.tex\\}")) {
					line = read(latexPath + line.substring(7, line.length() - 1));
				}
				model += line + "\n";
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		} 
		return model;
	}
	
	private String processField(Matcher matcher) {
		String group = matcher.group(0);
		String candidateData = candidate.getMap().get(group);
		candidateData = candidateData == null ? "" : candidateData;
		if (group.equals(formatField(livesAtField))) {
			return candidate.getMap().get(group) + " \\\\\\\\"; //all this shit for " \\" ...
		}
		else if (group.equals(formatField(initialsField))) {
			return user.getInitials();
		}
		return candidateData;
	}

	private void parse(String model) {
		Pattern pattern = Pattern.compile("<[a-zA-Z]*>");
		Matcher matcher = pattern.matcher(model);
		StringBuffer sb = new StringBuffer();
		while (matcher.find()) {
			matcher.appendReplacement(sb, processField(matcher));				
		}
		matcher.appendTail(sb);
		finalContent = sb.toString();
	}

	private void write(String filename) throws IOException {
		BufferedWriter bw = new BufferedWriter(new FileWriter(filename));
		bw.write(finalContent);
		bw.close();
	}

	@Override
	public String toString() {
		return finalContent;
	}
}
