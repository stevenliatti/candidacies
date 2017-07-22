package core;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;

import beans.Bean;
import beans.Candidate;
import beans.User;

public class ListLetter implements Bean {
	private List<Letter> letters;
	
	@SuppressWarnings("unused")
	public static void main(String[] args) throws IllegalArgumentException, IOException {
		Candidate candidate1 = new Candidate(null, "M.", "Dupont", "Jean", "jean@mail.com", "Bob", "Rue de Lyon", 
				"4", "1202", "Genève", "Suisse", LocalDate.now(), LocalDateTime.now(), LocalDateTime.now(), 
				LocalDateTime.now(), "sl", "infirmier", negative, "yes");
		Candidate candidate2 = new Candidate(null, "M.", "Dupuis", "Jean", "jean@mail.com", "Bob", "Rue de Lyon", 
				"4", "1202", "Genève", "Suisse", LocalDate.now(), LocalDateTime.now(), LocalDateTime.now(), 
				LocalDateTime.now(), "sl", "infirmier", negativeSixMonths, "yes");
		Candidate candidate3 = new Candidate(null, "M.", "Dupassage", "Jean", "jean@mail.com", "Bob", "Rue de Lyon", 
				"4", "1202", "Genève", "Suisse", LocalDate.now(), LocalDateTime.now(), LocalDateTime.now(), 
				LocalDateTime.now(), "sl", "infirmier", suspendSixMonths, "yes");
		User user = new User(null, "steven", "abc", "Liatti", "Steven", "sl");
		List<Candidate> candidates = new ArrayList<>();
		candidates.add(candidate1);
		candidates.add(candidate2);
		candidates.add(candidate3);
		
		try {
			ListLetter lt = new ListLetter(candidates, user, "data/latex/letters.tex");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public ListLetter(List<Candidate> candidates, User user, String filename) throws IllegalArgumentException, IOException {
		letters = new ArrayList<>();
		String model = "";
		for (Candidate candidate : candidates) {
			if (candidate.getAnswer().equals(negative)) {
				model = Letter.modelNegative;
			}
			else if (candidate.getAnswer().equals(negativeSixMonths)) {
				model = Letter.modelNegativeSixMonths;
			}
			else if (candidate.getAnswer().equals(suspendSixMonths)) {
				model = Letter.modelSuspendSixMonths;
			}
			letters.add(new Letter(candidate, user, model));
		}
		
		StringBuilder sb = new StringBuilder();
		String newPage = "\\newpage\n";
		
		sb.append(Letter.modelPreamble);
		for (Letter letter : letters) {
			sb.append(letter);
			sb.append(newPage);
		}
		sb.delete(sb.length() - newPage.length(), sb.length());
		sb.append("\\end{document}");
		
		BufferedWriter bw = new BufferedWriter(new FileWriter(filename));
		bw.write(sb.toString());
		bw.close();
	}
}
