package com.gts.users.security;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.gts.users.entities.UserEntity;
import com.gts.users.repositories.UserRepository;

import io.jsonwebtoken.Jwts;

public class AuthorizationFilter extends BasicAuthenticationFilter {

	                                    
	
	public AuthorizationFilter(AuthenticationManager authenticationManager ) {
		super(authenticationManager);

	}

	@Override
	protected void doFilterInternal(HttpServletRequest httpReq,
			                              HttpServletResponse httpRes, 
			                                       FilterChain chain)  throws IOException, ServletException {

		String header = httpReq.getHeader(SecurityConstants.HEADER_STRING);

		if (header == null || !header.startsWith(SecurityConstants.TOKEN_PREFIX)) {
			chain.doFilter(httpReq, httpRes);
			return;
		}

		UsernamePasswordAuthenticationToken authentication = getAuthentication22(httpReq);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		chain.doFilter(httpReq, httpRes);

	}

	
	private UsernamePasswordAuthenticationToken getAuthentication22(HttpServletRequest request) {

		String token = request.getHeader(SecurityConstants.HEADER_STRING);

		if (token != null) {
			token = token.replace(SecurityConstants.TOKEN_PREFIX, "");

			String user = Jwts.parser()
					          .setSigningKey(SecurityConstants.getTokenSecret())
					          .parseClaimsJws(token)
					          .getBody()
					          .getSubject();

			if (user != null) {
//				UserEntity userEntity = uRepo.findByEmail(user);
//				UserPrincipal userPrincipal = new UserPrincipal(userEntity);

//				return new UsernamePasswordAuthenticationToken(user, null, userPrincipal.getAuthorities());
				
				return new UsernamePasswordAuthenticationToken(user, null, new ArrayList<>());
			}                                
			                                  
                                             
			return null;               

		}
		return null;

	}

}
