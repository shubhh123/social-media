package com.social.spring.socialmedia.config;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/*
Class responsible for getting the JWT token and extracting email and using this email, creates authentication which then sets the Authentication in the SecurityContextHolder...
Once it is set in the SecurityContextHolder, control flows to CustomUserDetailService, where there it loads the user by the username, if username found then he will be validated.
 */
public class JwtValidator extends OncePerRequestFilter {


    //EXECUTED WHEN WE ALREADY HAVE THE TOKEN GENERATED..
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //EXTRACT THE TOKEN COMING FROM THE HEADER
        String jwt = request.getHeader(JwtConstants.JWT_HEADER);
        if(jwt != null) {
            try {
                //TO GET EMAIL
                String email = JwtProvider.getEmailFromJwtToken(jwt);
                List<GrantedAuthority> authorities = new ArrayList<>();

                Authentication authentication = new UsernamePasswordAuthenticationToken(email, null, authorities);//UsernamePasswordAuthenticationToken used to perform authentication
                SecurityContextHolder.getContext().setAuthentication(authentication);

            } catch (Exception e) {
                throw new BadCredentialsException("Invalid token");
            }
        }
        filterChain.doFilter(request, response);
    }
}
