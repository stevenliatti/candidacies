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
		String search = request.getParameter("search");
		String type = request.getParameter("type");

		if ((ids == null || ids.length == 0) && (search == null || search.isEmpty())) {
			System.out.println("first if");
			response.sendRedirect(request.getContextPath() + "/candidates");
		}
		else {
			System.out.println("first else");
			if (!search.isEmpty()) {
				System.out.println("second if");
				List<Candidate> candidates = null;
				if (type.equals("name")) {
					System.out.println("third if");
					candidates = candidateDAO.searchByName(search);
				}
				else if (type.equals("job")) {
					System.out.println("else if");
					candidates = candidateDAO.searchByJob(search);
				}
				request.setAttribute("candidates", candidates);
				this.getServletContext().getRequestDispatcher(listCandidatesView).forward(request, response);
			}
			else {
				System.out.println("second else");
				List<Candidate> candidatesPDF = candidateDAO.listCandidates(ids, "pdf");
				List<Candidate> candidatesEmail = candidateDAO.listCandidates(ids, "email");
				
				generateLetters(request, candidatesPDF, candidatesEmail);
				
				this.getServletContext().getRequestDispatcher(lettersView).forward(request, response);
			}
		}
	}
}
