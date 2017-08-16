package filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class RestrictionFilter implements Filter {
	
	public void init(FilterConfig config) throws ServletException {

    }

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		request.setCharacterEncoding("UTF-8");
		HttpServletResponse response = (HttpServletResponse) res;
		String path = request.getRequestURI().substring(request.getContextPath().length());

        if (path.startsWith("/inc")) {
            chain.doFilter(request, response);
            return;
        }
		
		HttpSession session = request.getSession();
		
		if (session.getAttribute("sessionUser") == null) {
			request.getRequestDispatcher("/login").forward(request, response);
		}
		else {
			chain.doFilter(request, response);
		}
	}
	
	public void destroy() {

    }

}
