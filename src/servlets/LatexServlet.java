package servlets;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import beans.Candidate;
import core.Letter;
import core.Paths;
import dao.AnswerDAO;
import dao.AutoCompleteDAO;
import dao.CandidateDAO;
import dao.DAOFactory;

@SuppressWarnings("serial")
public abstract class LatexServlet extends HttpServlet {
	private static final String outputPath = Paths.getInstance().getOutputPath();
	private static final String generatedFileName = Paths.getInstance().getGeneratedFileName();
	private static final String latexPath = Paths.getInstance().getLatexPath();
	private static final boolean DEBUGMODE = false;
	
	CandidateDAO candidateDAO;
	AnswerDAO answerDAO;
	AutoCompleteDAO autoCompleteDAO;

	public void init() throws ServletException {
		this.candidateDAO = ((DAOFactory) getServletContext().getAttribute("daofactory")).getCandidateDao();
		this.answerDAO = ((DAOFactory) getServletContext().getAttribute("daofactory")).getAnswerDao();
		this.autoCompleteDAO = ((DAOFactory) getServletContext().getAttribute("daofactory")).getAutoCompleteDAO();
	}
	
	static void generateLetters(HttpServletRequest request, List<Candidate> candidatesPDF, List<Candidate> candidatesEmail) throws IllegalArgumentException, IOException {
		String pathAndFile = latexPath + "/" + generatedFileName + ".tex";
		Letter.writeLatexLetters(candidatesPDF, pathAndFile);
		
		try {
			Process p = Runtime.getRuntime().exec("pdflatex -output-directory " + outputPath + " " + pathAndFile);
			if (DEBUGMODE) {
				Scanner sc = new Scanner(p.getInputStream());    		
				while (sc.hasNext()) System.out.println(sc.nextLine());
			}
		}
		catch (IOException e) {
			System.err.println(e.getMessage());
		}
		
		request.setAttribute("candidatesEmail", candidatesEmail);
		request.setAttribute("generatedFileName", generatedFileName);
	}
}
