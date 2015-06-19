package huiswerknakijken.hu.Filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class PaginaFilters implements Filter{
	private ServletContext context;
	
	@Override
	public void init(FilterConfig config) throws ServletException {
		this.context = config.getServletContext();
		
	}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        String uri = req.getRequestURI();
        String file = req.getServletPath();
        HttpSession session = req.getSession(false);

        if(file.equals("/loginpage.jsp"))
        	chain.doFilter(request, response);
        
        if(file.equals("/LogoutServlet.do"))
        	chain.doFilter(request, response);
        
        else if(session == null && (uri.endsWith(".do")|| uri.endsWith(".jsp"))){
        	req.setAttribute("msgs", "U moet eerst inloggen");
        	res.sendRedirect("/loginpage.jsp");
        	chain.doFilter(request, response);
        }
        else
        	chain.doFilter(request,response);
	}
		
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	


}
