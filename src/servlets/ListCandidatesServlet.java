package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Candidate;

@SuppressWarnings("serial")
public class ListCandidatesServlet extends LatexServlet {
	private static final String listCandidatesView = "/WEB-INF/listAllCandidates.jsp";
	private static final String lettersView = "/WEB-INF/letters.jsp";

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int countPDF = candidateDAO.countCandidatesOfDay("pdf");
		int countEmail = candidateDAO.countCandidatesOfDay("email");
		List<Candidate> candidates = candidateDAO.readAll();
		
		request.setAttribute("countPDF", countPDF);
		request.setAttribute("countEmail", countEmail);
		request.setAttribute("candidates", candidates);
		this.getServletContext().getRequestDispatcher(listCandidatesView).forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String[] ids = request.getParameterValues("ids");
		if (ids == null || ids.length == 0) {
			response.sendRedirect(request.getContextPath() + "/candidates");
		}
		else {
			List<Candidate> candidatesPDF = candidateDAO.listCandidates(ids, "pdf");
			List<Candidate> candidatesEmail = candidateDAO.listCandidates(ids, "email");
			
			generateLetters(request, candidatesPDF, candidatesEmail);
			
			this.getServletContext().getRequestDispatcher(lettersView).forward(request, response);
		}
	}
}
