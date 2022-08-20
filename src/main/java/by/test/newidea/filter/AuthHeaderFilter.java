package by.test.newidea.filter;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter(filterName = "AuthHeaderFilter")
public class AuthHeaderFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
   /*     System.out.println("In Auth filter!");
        HttpServletRequest castedRequest=(HttpServletRequest) request;
//        String authHeader= castedRequest.getHeader("X-Auth-Token");
        String authHeader= castedRequest.getHeader("Secret-Key");
        if (StringUtils.isNotBlank(authHeader)){
            System.out.println("Header was found with payload:" + authHeader);
        } else {
            System.out.println("Header was not found!");
        }

*/
        chain.doFilter(request, response);
    }
}
