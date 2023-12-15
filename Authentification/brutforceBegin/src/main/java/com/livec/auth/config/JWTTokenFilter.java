package com.livec.auth.config;

import java.io.IOException;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

public class JWTTokenFilter extends OncePerRequestFilter{
private JWTTokenProvider provider;
	
	public JWTTokenFilter(JWTTokenProvider p) {
		this.provider = p;
	}
	
	@Override
	protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain filter) throws ServletException, IOException{
		String token = provider.resolveToken(req);	
		try {
				if(token != null&& provider.validatToken(token)) {
					Authentication auth = provider.getAuthentication(token);
					SecurityContextHolder.getContext().setAuthentication(auth);

					// Juste pour vérifier pas anonymous user
					Authentication a = SecurityContextHolder.getContext().getAuthentication();
					String currentPrincipalName = a.getName();
					System.out.println("currentPrincipalName filter "+  currentPrincipalName);
				}
				
			} catch (Exception e) {
				SecurityContextHolder.clearContext();
				res.sendError(403, "bad token");
			}
		filter.doFilter(req, res);
	}
}
