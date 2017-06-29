package servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class CandidateCreate extends HttpServlet {
	private static final String view = "/WEB-INF/candidate_create.jsp";
	private static final String titleField = "title";
	private static final String lastNameField = "last_name";
	private static final String firstNameField = "first_name";
	private static final String emailField = "email";
	private static final String livesAtField = "lives_at";
	private static final String streetField = "street";
	private static final String numStreetField = "num_street";
	private static final String postCodeField = "post_code";
	private static final String localityField = "locality";
	private static final String countryField = "country";
	private static final String requestDateField = "request_date";
	private static final String jobTypeField = "job_type";
	private static final String jobFunctionField = "job_function";
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher(view).forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String result;
		Map<String, String> errors = new HashMap<>();
		
		try {
			validateLastName(request.getParameter(lastNameField));
		} catch (Exception e) {
			errors.put(lastNameField, e.getMessage());
		}

		try {
			validateFirstName(request.getParameter(firstNameField));
		} catch (Exception e) {
			errors.put(firstNameField, e.getMessage());
		}

		try {
			validateEmail(request.getParameter(emailField));
		} catch (Exception e) {
			errors.put(emailField, e.getMessage());
		}

		try {
			validateRequestDate(request.getParameter(requestDateField));
		} catch (Exception e) {
			errors.put(requestDateField, e.getMessage());
		}
		
		if (errors.isEmpty()) {
			result = "success";
		}
		else {
			result = "errors";
		}
		
		request.setAttribute("errors", errors);
		request.setAttribute("result", result);
		
		this.getServletContext().getRequestDispatcher(view).forward(request, response);
	}
	
	private void validateLastName(String lastName) throws Exception {
		
	}
	
	private void validateFirstName(String lastName) throws Exception {
		
	}
	
	private void validateEmail(String lastName) throws Exception {
		
	}

	private void validateRequestDate(String lastName) throws Exception {
		
	}
}
