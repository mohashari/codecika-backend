package com.codecika.usermanagement.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.servlet.*;
import java.io.IOException;
import java.util.List;

/**
 * Created by yukibuwana on 1/26/17.
 */

@Configuration
public class IpFilter implements Filter {

    public static final Logger logger = LoggerFactory.getLogger(IpFilter.class);

    @Value("#{'${ip.whitelist}'.split(',')}")
    List<String> allowedIp;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {

//        if(!allowedIp.contains(servletRequest.getRemoteAddr())){
//            servletResponse.getWriter().write("An unauthorized client hostname, please contact your Administrator");
//        }else {
            filterChain.doFilter(servletRequest, servletResponse);
 //       }
    }

    @Override
    public void destroy() {

    }
}
