package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Answer;
import beans.Candidate;

@SuppressWarnings("serial")
public class ListCandidatesServlet extends CandidaciesServlet {
	private static final String listCandidatesView = "/WEB-INF/listCandidates.jsp";
	private static final String lettersView = "/WEB-INF/letters.jsp";

	private void statsAndStuff(HttpServletRequest request) {
		List<Answer> answers = answerDAO.readAllVisible();
		List<String> jobs = autoCompleteDAO.readAll("jobFunctions");
		List<String> localities = autoCompleteDAO.readAll("localities");
		List<String> countries = autoCompleteDAO.readAll("countries");
		int countPDF = candidateDAO.countCandidatesOfDay("pdf");
		int countEmail = candidateDAO.countCandidatesOfDay("email");
		int countAll = candidateDAO.countAll();

		request.setAttribute("answers", answers);
		request.setAttribute("jobs", jobs);
		request.setAttribute("localities", localities);
		request.setAttribute("countries", countries);
		request.setAttribute("countPDF", countPDF);
		request.setAttribute("countEmail", countEmail);
		request.setAttribute("countAll", countAll);
	}

	private boolean checkStr(String str) {
		return str == null || str.isEmpty();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Candidate> candidates = candidateDAO.listCandidates(100);
		statsAndStuff(request);
		
		request.setAttribute("candidates", candidates);
		this.getServletContext().getRequestDispatcher(listCandidatesView).forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		statsAndStuff(request);

		String all = "all";
		String[] ids = request.getParameterValues("ids");
		String search = request.getParameter("search");
		String number = request.getParameter("number");
		String answer = request.getParameter("answer");
		String jobFunction = request.getParameter("jobFunction");
		String locality = request.getParameter("locality");
		String country = request.getParameter("country");

		if ((ids == null || ids.length == 0) && checkStr(search) && checkStr(number) && checkStr(answer) 
				&& checkStr(jobFunction) && checkStr(locality) && checkStr(country)) {
			response.sendRedirect(request.getContextPath() + "/candidates");
		}
		else {
			if (!search.isEmpty()) {
				List<Candidate> candidates = candidateDAO.searchByName(search);
				request.setAttribute("candidates", candidates);
				this.getServletContext().getRequestDispatcher(listCandidatesView).forward(request, response);
			}
			else if (!number.isEmpty()) {
				int num = 0;
				try {
					num = Integer.parseInt(number);
				} catch (NumberFormatException e) {
					HttpSession session = request.getSession();
					session.setAttribute("message", "Le nombre fourni est incorrect.");
					response.sendRedirect(request.getContextPath() + "/candidates");
				}

				List<Candidate> candidates = candidateDAO.listCandidates(Math.abs(num));
				request.setAttribute("candidates", candidates);
				this.getServletContext().getRequestDispatcher(listCandidatesView).forward(request, response);
			}
			else if (answer.equals(all) && jobFunction.equals(all) && locality.equals(all) && country.equals(all)) {
				response.sendRedirect(request.getContextPath() + "/candidates");
			}
			else if (!answer.equals(all) || !jobFunction.equals(all) || !locality.equals(all) || !country.equals(all)) {
				List<Candidate> candidates = candidateDAO.listCandidates(answer, jobFunction, locality, country);
				request.setAttribute("candidates", candidates);
				this.getServletContext().getRequestDispatcher(listCandidatesView).forward(request, response);
			}
			else {
				List<Candidate> candidatesPDF = candidateDAO.listCandidates(ids, "pdf");
				List<Candidate> candidatesEmail = candidateDAO.listCandidates(ids, "email");

				generateLetters(request, candidatesPDF, candidatesEmail);

				this.getServletContext().getRequestDispatcher(lettersView).forward(request, response);
			}
		}
	}
}
