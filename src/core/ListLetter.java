package core;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import beans.Bean;
import beans.Candidate;

public class ListLetter implements Bean {
	private List<Letter> letters;
	
	public ListLetter(List<Candidate> candidates, String filename) throws IllegalArgumentException, IOException {
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
			letters.add(new Letter(candidate, model));
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
