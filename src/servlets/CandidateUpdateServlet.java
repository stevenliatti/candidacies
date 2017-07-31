package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Candidate;
import dao.CandidateDAO;
import dao.DAOFactory;
import forms.CandidateForm;

@SuppressWarnings("serial")
public class CandidateUpdateServlet extends HttpServlet {
	private static final String view = "/WEB-INF/candidateUpdate.jsp";
	
	private CandidateDAO candidateDAO;
	
	public void init() throws ServletException {
        this.candidateDAO = ((DAOFactory) getServletContext().getAttribute("daofactory")).getCandidateDao();
    }
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		if (id != null) {
			Candidate candidate = candidateDAO.read(Long.parseLong(id));
			request.setAttribute("candidate", candidate);
			this.getServletContext().getRequestDispatcher(view).forward(request, response);
		}
		else {
			response.sendRedirect(request.getContextPath() + "/candidates");
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CandidateForm form = new CandidateForm(candidateDAO);
		Candidate candidate = form.updateCandidate(request);
		
		request.setAttribute("form", form);
		request.setAttribute("candidate", candidate);
		
		this.getServletContext().getRequestDispatcher(view).forward(request, response);
	}
}
