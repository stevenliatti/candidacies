package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Answer;
import beans.Candidate;
import dao.AnswerDAO;
import dao.CandidateDAO;
import dao.DAOFactory;
import forms.CandidateForm;

@SuppressWarnings("serial")
public class CandidateCreateServlet extends HttpServlet {
	private static final String view = "/WEB-INF/candidateCreate.jsp";
	
	private CandidateDAO candidateDAO;
	private AnswerDAO answerDAO;
	
	public void init() throws ServletException {
        this.candidateDAO = ((DAOFactory) getServletContext().getAttribute("daofactory")).getCandidateDao();
        this.answerDAO = ((DAOFactory) getServletContext().getAttribute("daofactory")).getAnswerDao();
    }
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Answer> answers = answerDAO.readAll();
		request.setAttribute("answers", answers);
		this.getServletContext().getRequestDispatcher(view).forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CandidateForm form = new CandidateForm(candidateDAO, answerDAO);
		Candidate candidate = form.createCandidate(request);
		List<Answer> answers = answerDAO.readAll();
		
		request.setAttribute("answers", answers);
		request.setAttribute("form", form);
		request.setAttribute("candidate", candidate);
		
		this.getServletContext().getRequestDispatcher(view).forward(request, response);
	}
}
