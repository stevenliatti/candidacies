package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class Index extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String param = request.getParameter( "author" );
		String message = "Transmission variables : OK !" + param;
	    request.setAttribute("index", message);
		this.getServletContext().getRequestDispatcher("/WEB-INF/index.jsp").forward(request, response);
	}
}
