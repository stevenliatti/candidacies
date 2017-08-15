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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Answer;
import beans.Candidate;
import forms.CandidateForm;

@SuppressWarnings("serial")
public class CandidateCreateServlet extends CandidaciesServlet {
	private static final String view = "/WEB-INF/candidateCreate.jsp";
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Answer> answers = answerDAO.readAllVisible();
		request.setAttribute("answers", answers);
		this.getServletContext().getRequestDispatcher(view).forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CandidateForm form = new CandidateForm(candidateDAO, answerDAO);
		Candidate candidate = form.createCandidate(request);
		autoCompleteDAO
			.checkOrCreate(plural(lastNameField), candidate.getLastName())
			.checkOrCreate(plural(firstNameField), candidate.getFirstName())
			.checkOrCreate(plural(streetField), candidate.getStreet())
			.checkOrCreate(plural(localityField), candidate.getLocality())
			.checkOrCreate(plural(countryField), candidate.getCountry())
			.checkOrCreate(plural(jobFunctionField), candidate.getJobFunction())
			;
		List<Answer> answers = answerDAO.readAllVisible();
		
		request.setAttribute("answers", answers);
		request.setAttribute("form", form);
		request.setAttribute("candidate", candidate);
		
		this.getServletContext().getRequestDispatcher(view).forward(request, response);
	}
}
