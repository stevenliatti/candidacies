package core;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import beans.Candidate;

public class Letter {
	private static final String latexFullPath = Paths.getInstance().getLatexPath();
	public static final String modelPreamble = read(latexFullPath + "/preamble.tex");
	public static final String modelNegative = read(latexFullPath + "/negatif.tex");
	public static final String modelNegativeSixMonths = read(latexFullPath + "/negatifSixMois.tex");
	public static final String modelSuspendSixMonths = read(latexFullPath + "/suspensSixMois.tex");

	private Candidate candidate;
	private String finalContent;
	
	public Letter(Candidate candidate, String model) throws IllegalArgumentException, IOException {
		this.candidate = candidate;
		parse(model);
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
					line = read(latexFullPath + "/" + line.substring(7, line.length() - 1));
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

	@Override
	public String toString() {
		return finalContent;
	}
}
