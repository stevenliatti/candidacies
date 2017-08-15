package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Answer;
import beans.Bean;
import forms.AnswerForm;

@SuppressWarnings("serial")
public class AnswerCreateServlet extends CandidaciesServlet {
	private static final String view = "/WEB-INF/answerCreate.jsp";
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (!checkAdmin(request)) {
			response.sendRedirect(request.getContextPath() + "/candidates");
			return;
		}
		request.setAttribute("fields", Bean.getFields());
		this.getServletContext().getRequestDispatcher(view).forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (!checkAdmin(request)) {
			response.sendRedirect(request.getContextPath() + "/candidates");
			return;
		}
		
		AnswerForm form = new AnswerForm(answerDAO);
		Answer answer = form.createAnswer(request);
		
		request.setAttribute("fields", Bean.getFields());
		request.setAttribute("form", form);
		request.setAttribute("answer", answer);
		
		this.getServletContext().getRequestDispatcher(view).forward(request, response);
	}
}
