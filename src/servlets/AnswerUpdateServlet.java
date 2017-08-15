package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Answer;
import forms.AnswerForm;

@SuppressWarnings("serial")
public class AnswerUpdateServlet extends CandidaciesServlet {
	private static final String view = "/WEB-INF/answerUpdate.jsp";
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		if (id != null) {
			Answer answer = answerDAO.read(Long.parseLong(id));
			request.setAttribute("answer", answer);
			this.getServletContext().getRequestDispatcher(view).forward(request, response);
		}
		else {
			response.sendRedirect(request.getContextPath() + "/admin/answers");
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AnswerForm form = new AnswerForm(answerDAO);
		Answer answer = form.updateAnswer(request);
		
		request.setAttribute("form", form);
		request.setAttribute("answer", answer);
		
		this.getServletContext().getRequestDispatcher(view).forward(request, response);
	}
}
