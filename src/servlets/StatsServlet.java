package servlets;

import static beans.Bean.*;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class StatsServlet extends LatexServlet {
	private static final String view = "/WEB-INF/stats.jsp";
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("countAll", candidateDAO.countAll());
		request.setAttribute(plural(titleField), candidateDAO.countBy(titleField));
		request.setAttribute(plural(answerField), candidateDAO.countBy(answerField));
		request.setAttribute(plural(localityField), candidateDAO.countBy(localityField));
		request.setAttribute(plural(countryField), candidateDAO.countBy(countryField));
		request.setAttribute(plural(jobFunctionField), candidateDAO.countBy(jobFunctionField));
		request.setAttribute(plural(sendTypeField), candidateDAO.countBy(sendTypeField));
		this.getServletContext().getRequestDispatcher(view).forward(request, response);
	}
}
