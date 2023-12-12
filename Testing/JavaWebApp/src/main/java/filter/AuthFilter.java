package filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/*")
public class AuthFilter implements Filter {
    private static final String USERNAME = "admin";
    private static final String PASSWORD = "admin";

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        // Check if the request is for a resource that requires authentication
        String path = httpRequest.getRequestURI().substring(httpRequest.getContextPath().length());

        if (path.startsWith("/secure")) {
            // Authentication is required for resources under /secure
            String providedUsername = httpRequest.getParameter("username");
            String providedPassword = httpRequest.getParameter("password");

            if (USERNAME.equals(providedUsername) && PASSWORD.equals(providedPassword)) {
                // Authentication successful, continue with the request
                chain.doFilter(request, response);
            } else {
                // Authentication failed, send unauthorized response
                httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                httpResponse.getWriter().write("Authentication failed");
            }
        } else {
            // No authentication required for other resources, continue with the request
            chain.doFilter(request, response);
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Initialization code if needed
    }

    @Override
    public void destroy() {
        // Cleanup code if needed
    }
}