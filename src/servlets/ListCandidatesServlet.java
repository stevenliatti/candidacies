package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Candidate;

@SuppressWarnings("serial")
public class ListCandidatesServlet extends LatexServlet {
	private static final String listCandidatesView = "/WEB-INF/listAllCandidates.jsp";
	private static final String lettersView = "/WEB-INF/letters.jsp";

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int countPDF = candidateDAO.countCandidatesOfDay("pdf");
		int countEmail = candidateDAO.countCandidatesOfDay("email");
		List<Candidate> candidates = candidateDAO.listCandidates(100);
		
		request.setAttribute("countPDF", countPDF);
		request.setAttribute("countEmail", countEmail);
		request.setAttribute("candidates", candidates);
		this.getServletContext().getRequestDispatcher(listCandidatesView).forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String[] ids = request.getParameterValues("ids");
		String search = request.getParameter("search");
		String type = request.getParameter("type");
		String number = request.getParameter("number");

		if ((ids == null || ids.length == 0) && (search == null || search.isEmpty()) && (number == null || number.isEmpty())) {
			response.sendRedirect(request.getContextPath());
		}
		else {
			if (!search.isEmpty()) {
				List<Candidate> candidates = null;
				if (type.equals("name")) {
					candidates = candidateDAO.searchByName(search);
				}
				else if (type.equals("job")) {
					candidates = candidateDAO.searchByJob(search);
				}
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
					response.sendRedirect(request.getContextPath());
				}
				
				List<Candidate> candidates = candidateDAO.listCandidates(Math.abs(num));
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
