package com.codecika.usermanagement.filter;

import org.keycloak.KeycloakPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by yukibuwana on 1/26/17.
 */

@Configuration
public class CORSFilter extends OncePerRequestFilter {

    @Autowired
    UserService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                                    FilterChain filterChain) throws ServletException, IOException {

        if (httpServletRequest.getHeader("Origin") != null) {
            // CORS "pre-flight" request
            httpServletResponse.addHeader("Access-Control-Allow-Origin", "*");
            httpServletResponse.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
            httpServletResponse.addHeader("Access-Control-Allow-Headers", "Authorization, X-Requested-With, " +
                    "Content-Type, Accept");
            httpServletResponse.addHeader("Access-Control-Max-Age", "1800");    // 30 min
        }

        if (httpServletRequest.getUserPrincipal() != null) {
            KeycloakPrincipal principal = (KeycloakPrincipal) httpServletRequest.getUserPrincipal();
            userService.setUsername(principal.getKeycloakSecurityContext().getToken().getPreferredUsername());
        }

        filterChain.doFilter(httpServletRequest, httpServletResponse);

    }
}
