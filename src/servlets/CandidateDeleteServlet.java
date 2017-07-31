package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CandidateDAO;
import dao.DAOFactory;

@SuppressWarnings("serial")
public class CandidateDeleteServlet extends HttpServlet {
	private CandidateDAO candidateDAO;
	
	public void init() throws ServletException {
        this.candidateDAO = ((DAOFactory) getServletContext().getAttribute("daofactory")).getCandidateDao();
    }
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		if (id != null) {
			candidateDAO.delete(Long.parseLong(id));
		}
		response.sendRedirect(request.getContextPath() + "/candidates");
	}
}
