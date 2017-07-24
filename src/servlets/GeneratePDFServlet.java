package servlets;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Candidate;
import core.ListLetter;
import core.Paths;
import dao.CandidateDAO;
import dao.DAOFactory;

@SuppressWarnings("serial")
public class GeneratePDFServlet extends HttpServlet {
	private static final String outputPath = Paths.getInstance().getOutputPath();
	private static final String generatedFileName = Paths.getInstance().getGeneratedFileName();

	private CandidateDAO candidateDAO;

	public void init() throws ServletException {
		this.candidateDAO = ((DAOFactory) getServletContext().getAttribute("daofactory")).getCandidateDao();
	}

	@SuppressWarnings({ "unused", "resource" })
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Candidate> candidates = candidateDAO.candidatesOfDay();
		if (candidates == null || candidates.isEmpty()) {
			HttpSession session = request.getSession();
			session.setAttribute("message", "Pas de candidats inscrits aujourd'hui");
			response.sendRedirect(request.getContextPath());
			return;
		}
		
		candidates = candidateDAO.applySendDate(candidates);
		String pathAndFile = outputPath + "/" + generatedFileName + ".tex";
		
		ListLetter lt = new ListLetter(candidates, pathAndFile);
		
		try {
			Process p = Runtime.getRuntime().exec("pdflatex -output-directory " + outputPath + " " + pathAndFile);
			if (core.Debug.DEBUGMODE) {
				Scanner sc = new Scanner(p.getInputStream());    		
				while (sc.hasNext()) System.out.println(sc.nextLine());
			}
		}
		catch (IOException e) {
			System.out.println(e.getMessage());
		}
		
		response.sendRedirect("download/" + generatedFileName + ".pdf");
	}
}
