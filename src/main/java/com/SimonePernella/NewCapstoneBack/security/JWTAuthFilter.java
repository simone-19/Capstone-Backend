package com.SimonePernella.NewCapstoneBack.security;


import com.SimonePernella.NewCapstoneBack.entities.User;
import com.SimonePernella.NewCapstoneBack.exception.UnauthorizedException;
import com.SimonePernella.NewCapstoneBack.service.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JWTAuthFilter extends OncePerRequestFilter {
    @Autowired
    private JWTTools jwtTools;

    @Autowired
    private UserService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//        String authHeader = request.getHeader("Authorization");
//        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
//            throw new UnauthorizedException("Athorization Bearer token not found.");
//        } else {
//            String token = authHeader.substring(7);
//            jwtTools.verifyToken(token);
//            String id = jwtTools.extractIdFroToken(token);
//            User currentUser = userService.getById(Long.valueOf(id));
//
//            Authentication authentication = new UsernamePasswordAuthenticationToken(currentUser, null, currentUser.getAuthorities());
//            SecurityContextHolder.getContext().setAuthentication(authentication);
//
//            filterChain.doFilter(request, response);
//        }
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return new AntPathMatcher().match("/auth/**", request.getServletPath());
    }
}

