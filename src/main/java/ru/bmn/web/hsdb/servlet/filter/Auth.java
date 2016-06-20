package ru.bmn.web.hsdb.servlet.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class Auth implements Filter {
	private ServletContext context;

	public void init(FilterConfig filterConfig) throws ServletException {
		this.context = filterConfig.getServletContext();

	}

	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
		HttpServletRequest req  = (HttpServletRequest)  servletRequest;
		HttpServletResponse res = (HttpServletResponse) servletResponse;

		HttpSession session = req.getSession(false);
		String uri          = req.getRequestURI();

		Boolean isLoggedUserArea = !(uri.endsWith("/login/") || uri.endsWith("/register/"));

		if (session != null) {
			if (!isLoggedUserArea) {
				res.sendRedirect("/collection/out/");
			}
		}
		else {
			if (isLoggedUserArea) {
				res.sendRedirect("/login/");
			}
		}
		// pass the request along the filter chain
		filterChain.doFilter(servletRequest, servletResponse);
	}

	public void destroy() {

	}
}
