package main.java.com.huynguyen.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import eu.bitwalker.useragentutils.UserAgent;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterConfig;
import javax.servlet.FilterChain;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * Servlet Filter implementation class BlockingFilter
 */
@WebFilter(filterName = "BlockingFilter", urlPatterns = { "/DemoServlet" })
public class BlockingFilter implements Filter {

	public void init(FilterConfig config) throws ServletException {
		System.out.println("BlockingFilter init!");
	}

	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws ServletException, IOException {
		System.out.println("Entering BlockingFilter.doFilter()");

		HttpServletRequest request = (HttpServletRequest) req;

		UserAgent userAgent = UserAgent.parseUserAgentString(request.getHeader("User-Agent"));

		if (userAgent.getBrowser().getName().equals("Microsoft Edge")) {
			System.out.println("user has just accessed this site using Microsoft Edge browser");
			PrintWriter out = resp.getWriter();
			out.println("<font color=red>You are not authorized to access</font>");
		} else {
			chain.doFilter(request, resp);

		}
		System.out.print("\n");

	}

	public void destroy() {
		// we can close resources here
		System.out.println("BlockingFilter destroy!");
	}

}
