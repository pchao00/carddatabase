package com.packt.carddatabase;

import java.io.IOException;



import javax.servlet.FilterChain;

import javax.servlet.ServletException;

import javax.servlet.ServletRequest;

import javax.servlet.ServletResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;

import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.web.filter.GenericFilterBean;

import com.packt.carddatabase.service.AuthenticationService;

  

public class AuthenticationFilter extends GenericFilterBean {
	@Override 
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
				throws IOException, ServletException {
		logger.info("AuthenticationFilter!!!");
		
		Authentication authentication = AuthenticationService.getAuthentication((HttpServletRequest)request);
	    
	    SecurityContextHolder.getContext().setAuthentication(authentication);
	    filterChain.doFilter(request, response);
	  }
	
	 private String resolveToken(HttpServletRequest request){
		 String bearerToken = request.getHeader("Authorization");         //从HTTP头部获取TOKEN
		 
		 return null;
	 }
	 
}
