package core;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import beans.Answer;
import beans.Candidate;

public class Letter {
	private static final String latexPath = Paths.getInstance().getLatexPath();
	private static final String emsName = Paths.getInstance().getEmsName();
	private static final String rhWriter = Paths.getInstance().getRhWriter();
	private static final String modelLatexPreamble = read(latexPath + "/preamble.tex");
	private static final String modelLatexHeader = read(latexPath + "/header.tex");
	private static final String modelLatexFooter = read(latexPath + "/footer.tex");

	private Candidate candidate;
	private Answer answer;
	private String model;
	
	public static void makeLetter(Candidate candidate, Answer answer) {
		new Letter(candidate, answer);
	}

	private Letter(Candidate candidate, Answer answer) {
		this.candidate = candidate;
		this.answer = answer;
		switch (candidate.getSendType()) {
			case "pdf":
				model = modelLatexHeader + "\n" + answer.getContent() + "\n" + modelLatexFooter;
				break;
			case "email":
				model = answer.getContent() +"\n"+ emsName +"\n"+ rhWriter;
				break;
			default:
				break;
		}
		parse();
	}

	private static String read(String filename) {
		String model = "";
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(filename));
			String line;
			while ((line = br.readLine()) != null) {
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
		if (group.equals("<emsName>")) {
			return emsName;
		}
		if (group.equals("<latexPath>")) {
			return latexPath;
		}
		if (group.equals("<rhWriter>")) {
			return rhWriter;
		}
		String candidateData = candidate.getMap().get(group);
		candidateData = candidateData == null ? answer.getMap().get(group) : candidateData;
		candidateData = candidateData == null ? "" : candidateData;
		return candidateData;
	}

	private void parse() {
		Pattern pattern = Pattern.compile("<[a-zA-Z0-9]*>");
		Matcher matcher = pattern.matcher(model);
		StringBuffer sb = new StringBuffer();
		while (matcher.find()) {
			matcher.appendReplacement(sb, processField(matcher));				
		}
		matcher.appendTail(sb);
		candidate.setAnswerTitle(answer.getTitle());
		candidate.setLetter(sb.toString());
	}

	public static void writeLatexLetters(List<Candidate> candidates, String filename) throws IllegalArgumentException, IOException {
		StringBuilder sb = new StringBuilder();
		String newPage = "\\newpage\n";

		sb.append(modelLatexPreamble);
		for (Candidate candidate : candidates) {
			sb.append(candidate.getLetter());
			sb.append(newPage);
		}
		sb.delete(sb.length() - newPage.length(), sb.length());
		sb.append("\\end{document}");

		BufferedWriter bw = new BufferedWriter(new FileWriter(filename));
		bw.write(sb.toString());
		bw.close();
	}
}
