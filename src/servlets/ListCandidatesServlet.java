package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Candidate;
import dao.CandidateDAO;
import dao.DAOFactory;

@SuppressWarnings("serial")
public class ListCandidatesServlet extends HttpServlet {
	private static final String view = "/WEB-INF/listAllCandidates.jsp";

	private CandidateDAO candidateDAO;

	public void init() throws ServletException {
		this.candidateDAO = ((DAOFactory) getServletContext().getAttribute("daofactory")).getCandidateDao();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Candidate> candidates = candidateDAO.readAll();
		request.setAttribute("candidates", candidates);
		this.getServletContext().getRequestDispatcher(view).forward(request, response);
	}
}
