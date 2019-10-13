package main.java.com.huynguyen.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
/**
 * Servlet Filter implementation class LogFilter
 */
@WebFilter(filterName = "LogFilter", urlPatterns = {"/DemoServlet"})
public class LogFilter implements Filter {

	HashMap<String, Integer> register;

    
    public void  init(FilterConfig config) throws ServletException {

        System.out.println("LogFilter init!");
        register = new HashMap<String, Integer>();

    }

    
    public void  doFilter(ServletRequest request, ServletResponse response,
                          FilterChain chain) throws IOException, ServletException {
        System.out.println("Entering LogFilter.doFilter()");
        String IpAddr = request.getRemoteAddr();
        int count = 1;
        if (register.containsKey(IpAddr)) {
            count += register.get(IpAddr);
            register.replace(IpAddr, count);
        } else {
            register.put(IpAddr, count);
        }

        System.out.print("\n");
        System.out.println("IP: " + IpAddr + "; count: " + count + "; Date/time: "
                + new Date());
        System.out.print("\n");
        // pass the request along the filter chain
        chain.doFilter(request, response);


    }

    
    public void destroy( ) {
        /* Called before the Filter instance is removed from service by the web container*/
        System.out.println("LogFilter destroy!");

    }

}
