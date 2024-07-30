package org.uvt.uvtgaseste.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {
    @Autowired
    private JwtService jwtService;
    @Autowired
    private CustomUserDetailsService customUserDetailsService;
    @Override
    public void doFilterInternal (HttpServletRequest req, HttpServletResponse res, FilterChain filterChain) throws ServletException, IOException {

    }
}
