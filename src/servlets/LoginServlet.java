package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import core.Paths;

@SuppressWarnings("serial")
public class LoginServlet extends HttpServlet {
	private static final String view = "/WEB-INF/login.jsp";
	private static final String sessionUser = "sessionUser";
	
	private static final String user = Paths.getInstance().getUser();
	private static final String admin = Paths.getInstance().getAdmin();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher(view).forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String password = request.getParameter("password");
		
		if (!password.isEmpty()) {
			if (password.equals(user)) {
				session.setAttribute(sessionUser, user);
			}
			if (password.equals(admin)) {
				session.setAttribute(sessionUser, admin);
			}
		}
		else {
			session.setAttribute(sessionUser, null);
		}
		
		response.sendRedirect(request.getContextPath() + "/candidates");
	}
}
