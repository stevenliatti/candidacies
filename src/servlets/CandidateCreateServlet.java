package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Candidate;
import dao.CandidateDAO;
import dao.DAOFactory;
import forms.CandidateCreateForm;

@SuppressWarnings("serial")
public class CandidateCreateServlet extends HttpServlet {
	private static final String view = "/WEB-INF/candidate_create.jsp";
	
	private CandidateDAO candidateDAO;
	
	public void init() throws ServletException {
        this.candidateDAO = ((DAOFactory) getServletContext().getAttribute("daofactory")).getCandidateDao();
    }
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher(view).forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CandidateCreateForm form = new CandidateCreateForm(candidateDAO);
		Candidate candidate = form.createCandidate(request);
		
		request.setAttribute("form", form);
		request.setAttribute("candidate", candidate);
		
		this.getServletContext().getRequestDispatcher(view).forward(request, response);
	}
}
