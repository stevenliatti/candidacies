package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Candidate;

@SuppressWarnings("serial")
public class GenerateLettersOfDayServlet extends LatexServlet {
	private static final String lettersView = "/WEB-INF/letters.jsp";
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Candidate> candidatesPDF = candidateDAO.candidatesOfDay("pdf");
		List<Candidate> candidatesEmail = candidateDAO.candidatesOfDay("email");
		
		if ((candidatesPDF == null || candidatesPDF.isEmpty()) && (candidatesEmail == null || candidatesEmail.isEmpty())) {
			HttpSession session = request.getSession();
			session.setAttribute("message", "Pas de candidats inscrits aujourd'hui");
			response.sendRedirect(request.getContextPath());
			return;
		}
		
		generateLetters(request, candidatesPDF, candidatesEmail);
		
		this.getServletContext().getRequestDispatcher(lettersView).forward(request, response);
	}
}
