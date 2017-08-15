package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Answer;
import forms.AnswerForm;

@SuppressWarnings("serial")
public class AnswerCreateServlet extends CandidaciesServlet {
	private static final String view = "/WEB-INF/answerCreate.jsp";
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher(view).forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AnswerForm form = new AnswerForm(answerDAO);
		Answer answer = form.createAnswer(request);
		
		request.setAttribute("form", form);
		request.setAttribute("answer", answer);
		
		this.getServletContext().getRequestDispatcher(view).forward(request, response);
	}
}
