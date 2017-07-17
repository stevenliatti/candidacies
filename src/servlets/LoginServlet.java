package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.User;
import dao.DAOFactory;
import dao.UserDAO;
import forms.LoginForm;

@SuppressWarnings("serial")
public class LoginServlet extends HttpServlet {
	private static final String view = "/WEB-INF/login.jsp";
	private static final String sessionUser = "sessionUser";

	private UserDAO userDAO;

	public void init() throws ServletException {
		this.userDAO = ((DAOFactory) getServletContext().getAttribute("daofactory")).getUserDao();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher(view).forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LoginForm form = new LoginForm(userDAO);
		User user = form.loginUser(request);
		HttpSession session = request.getSession();
		
		if (form.getErrors().isEmpty()) {
			session.setAttribute(sessionUser, user);
		} 
		else {
			session.setAttribute(sessionUser, null);
		}
		
		request.setAttribute("form", form);
		request.setAttribute("user", user);
		
		response.sendRedirect(request.getContextPath());
	}
}
