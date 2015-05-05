package org.developers.monitor.app.config.filter;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by sebastian.alberski on 2015-05-05.
 */
@Component
public class SimpleCORSFilter implements Filter {
    
    @Override
    public void init(FilterConfig arg0) throws ServletException {}
    
    @Override
    public void doFilter(ServletRequest req, ServletResponse resp,
                         FilterChain chain) throws IOException, ServletException {
        HttpServletResponse response=(HttpServletResponse) resp;
    
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
        
        chain.doFilter(req, resp);
    }
    
    @Override
    public void destroy() {}
    
}
