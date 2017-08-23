package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Bean;
import dao.AutoCompleteDAO;
import dao.DAOFactory;

/**
 * This servlet is used for the ajax call in creation/update of a candidate.
 * @author stevenliatti
 *
 */
@SuppressWarnings("serial")
public class AutoCompleteServlet extends HttpServlet {

	private AutoCompleteDAO autoCompleteDAO;
	
	public void init() throws ServletException {
        this.autoCompleteDAO = ((DAOFactory) getServletContext().getAttribute("daofactory")).getAutoCompleteDAO();
    }
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String field = request.getParameter("field");
		// the next parameter is send by the jquery plugin, it's what the user has typed.
		String term = request.getParameter("term");
		StringBuilder sb = new StringBuilder();
		
		if (field != null) {
			List<String> list = autoCompleteDAO.readAll(Bean.plural(field), term);
			
			sb.append("[");
			for (String str : list) {
				sb.append("\"" + str + "\",");
			}
			sb.deleteCharAt(sb.length() - 1);
			sb.append("]");
			
			// return a list example : ["Berne", "Bâle"]
			
			response.setContentType("text/xml");
			response.getWriter().write(sb.toString());
		}
	}
}
