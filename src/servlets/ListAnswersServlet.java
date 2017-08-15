package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Answer;

@SuppressWarnings("serial")
public class ListAnswersServlet extends CandidaciesServlet {
	private static final String view = "/WEB-INF/listAnswers.jsp";

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Answer> answers = answerDAO.readAll();
		request.setAttribute("answers", answers);
		this.getServletContext().getRequestDispatcher(view).forward(request, response);
	}
}
