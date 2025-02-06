package com.civic.security;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthFilter extends OncePerRequestFilter{
	
	//DI here is JwtUtils
	@Autowired
	private JwtUtils utils;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		//get Authorization header form req
		String authHeader = request.getHeader("Authorization");
		
		if(authHeader != null && authHeader.startsWith("Bearer ")) {
			//means jwt present
			String jwt = authHeader.substring(7);
			//validate jwt
			Claims payloadclaims = utils.validateJwtToken(jwt);
			//get username/email 
			String email = utils.getUserNameFromJwtToken(payloadclaims);
			//get granted authorities as a custom claim
			List<GrantedAuthority> authorities = utils.getAuthoritiesFromClaims(payloadclaims);
			//add username n auths in authObj
			UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(email, null, authorities);
			
			//save this auth token in spring sec context, so next filters(servlets)will not retry auth again
			SecurityContextHolder.getContext().setAuthentication(token);
			System.out.println("saved token in se context: ");
			
		}
		
		filterChain.doFilter(request, response); //continue with next filters in chain
		
	}
	
	
	

}
