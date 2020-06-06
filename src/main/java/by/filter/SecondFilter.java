package by.filter;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class SecondFilter implements Filter {

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
        System.out.println("In second filter!");
        if (StringUtils.isNotBlank(((HttpServletRequest) request).getHeader("Custom-Header"))) {
            System.out.println("Header was found!");
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}

