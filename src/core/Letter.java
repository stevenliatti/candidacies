package core;

import static beans.Bean.initialsField;
import static beans.Bean.livesAtField;
import static beans.Candidate.formatField;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import beans.Candidate;
import beans.User;

public class Letter {
	private static final String latexPath = "data/latex/";
	private static final String modelNegative = read(latexPath + "negatif.tex");
	private static final String modelNegativeSixMonths = read(latexPath + "negatifSixMois.tex");
	private static final String modelSuspendSixMonths = read(latexPath + "suspensSixMois.tex");

	private Candidate candidate;
	private User user;
	private String finalContent;

	public static void main(String[] args) throws IllegalArgumentException, IOException {
		Candidate candidate = new Candidate(null, "M.", "Dupont", "Jean", "jean@mail.com", "Bob", "Rue de Lyon", 
				"4", "1202", "Genève", "Suisse", LocalDate.now(), LocalDateTime.now(), LocalDateTime.now(), 
				LocalDateTime.now(), "sl", "infirmier", "Négatif");
		User user = new User(null, "steven", "abc", "Liatti", "Steven", "sl");
		Letter letter = new Letter(candidate, user);
		System.out.println("----- parse -----");
		letter.parse(modelNegative);
		System.out.println(letter.finalContent);
		letter.write("data/latex/test.tex");
	}

	public Letter(Candidate candidate, User user) throws IllegalArgumentException, IOException {
		this.candidate = candidate;
		this.user = user;
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
}
