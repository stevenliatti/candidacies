package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CandidateDAO;
import dao.DAOFactory;

@SuppressWarnings("serial")
public class IndexServlet extends HttpServlet {
	private CandidateDAO candidateDAO;

	public void init() throws ServletException {
		this.candidateDAO = ((DAOFactory) getServletContext().getAttribute("daofactory")).getCandidateDao();
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int countPDF = candidateDAO.countCandidatesOfDay("pdf");
		int countEmail = candidateDAO.countCandidatesOfDay("email");
		request.setAttribute("countPDF", countPDF);
		request.setAttribute("countEmail", countEmail);
		this.getServletContext().getRequestDispatcher("/WEB-INF/index.jsp").forward(request, response);
	}
}
