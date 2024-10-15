package com.suraj.blog_api.surajblogapi.Filter;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.suraj.blog_api.surajblogapi.Services.JWTServices;
import com.suraj.blog_api.surajblogapi.Services.UserServiceImpl.CustomUserDetailsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private JWTServices jwtServices;

    @Autowired
    private ApplicationContext applicationContext;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        // We will get Token like that Bearer tokens
        System.out.println("JWT Validation  IN GAME");
        String authHeader = request.getHeader("Authorization");
        String token = null;
        String userName = null;
        System.out.println(authHeader);

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            token = authHeader.substring(7);
            userName = jwtServices.extractUserName(token);
            System.out.println(userName);
            System.out.println(token);
        } else {
            System.out.println("Failed");
        }
        if (userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            UserDetails userDetails = applicationContext.getBean(CustomUserDetailsService.class)
                    .loadUserByUsername(userName);
            System.out.println("UserDetails" + userDetails);
            if (jwtServices.validateToken(token, userDetails)) {
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails,
                        null, userDetails.getAuthorities());
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
                System.out.println("UsernamePasswordAuthenticationToken" + authToken);
            }

        }
        filterChain.doFilter(request, response);

    }

}
