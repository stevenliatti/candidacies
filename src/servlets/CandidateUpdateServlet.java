package servlets;

import static beans.Bean.countryField;
import static beans.Bean.firstNameField;
import static beans.Bean.jobFunctionField;
import static beans.Bean.lastNameField;
import static beans.Bean.localityField;
import static beans.Bean.plural;
import static beans.Bean.streetField;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Answer;
import beans.Candidate;
import dao.AnswerDAO;
import dao.AutoCompleteDAO;
import dao.CandidateDAO;
import dao.DAOFactory;
import forms.CandidateForm;

@SuppressWarnings("serial")
public class CandidateUpdateServlet extends HttpServlet {
	private static final String view = "/WEB-INF/candidateUpdate.jsp";
	
	private CandidateDAO candidateDAO;
	private AnswerDAO answerDAO;
	private AutoCompleteDAO autoCompleteDAO;
	
	public void init() throws ServletException {
        this.candidateDAO = ((DAOFactory) getServletContext().getAttribute("daofactory")).getCandidateDao();
        this.answerDAO = ((DAOFactory) getServletContext().getAttribute("daofactory")).getAnswerDao();
        this.autoCompleteDAO = ((DAOFactory) getServletContext().getAttribute("daofactory")).getAutoCompleteDAO();
    }
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		if (id != null) {
			Candidate candidate = candidateDAO.read(Long.parseLong(id));
			List<Answer> answers = answerDAO.readAll();
			request.setAttribute("candidate", candidate);
			request.setAttribute("answers", answers);
			this.getServletContext().getRequestDispatcher(view).forward(request, response);
		}
		else {
			response.sendRedirect(request.getContextPath() + "/candidates");
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CandidateForm form = new CandidateForm(candidateDAO, answerDAO);
		Candidate candidate = form.updateCandidate(request);
		autoCompleteDAO
			.checkOrCreate(plural(lastNameField), candidate.getLastName())
			.checkOrCreate(plural(firstNameField), candidate.getFirstName())
			.checkOrCreate(plural(streetField), candidate.getStreet())
			.checkOrCreate(plural(localityField), candidate.getLocality())
			.checkOrCreate(plural(countryField), candidate.getCountry())
			.checkOrCreate(plural(jobFunctionField), candidate.getJobFunction())
			;
		List<Answer> answers = answerDAO.readAll();
		
		request.setAttribute("answers", answers);
		request.setAttribute("form", form);
		request.setAttribute("candidate", candidate);
		
		this.getServletContext().getRequestDispatcher(view).forward(request, response);
	}
}
