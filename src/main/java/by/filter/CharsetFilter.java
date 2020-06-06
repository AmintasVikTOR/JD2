package by.filter;

import javax.servlet.*;
import java.io.IOException;

public class CharsetFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    /**
     * Method change the encoding of request and response.
     * Then they will be transferred to a method for performing a next filter in the chain.
     *
     * @param request  - ServletRequest
     * @param response - ServletResponse
     * @param chain    - FilterChain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        System.out.println("In charset filter!");

        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
