package com.packt.carddatabase;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.packt.carddatabase.domain.AccountCredentials;
import com.packt.carddatabase.service.AuthenticationService;
 


public class LoginFilter extends AbstractAuthenticationProcessingFilter {
	public LoginFilter(String url, AuthenticationManager authManager) {
	    super(new AntPathRequestMatcher(url));
	    logger.info("LoginFilter!!!");
	    setAuthenticationManager(authManager);
	  }
	
 
  @Override
  public Authentication attemptAuthentication(
	HttpServletRequest req, HttpServletResponse res)
			throws AuthenticationException, IOException, ServletException {
	  
    logger.info("attemptAuthentication!!!" + req.getParameter("username"));
    
    
   String username = req.getParameter("username");
   String password = req.getParameter("password");
 
	AccountCredentials creds = new ObjectMapper() 
	 		.readValue(req.getInputStream(), AccountCredentials.class);
	/*
    AccountCredentials creds = new AccountCredentials();
    creds.setUsername(username);
    creds.setPassword(password);
	*/
	logger.info("creds.getUsername=" + creds.getUsername());
	logger.info("creds.getPassword=" + creds.getPassword());
	
	return getAuthenticationManager().authenticate(
        new UsernamePasswordAuthenticationToken(
            creds.getUsername(),
            creds.getPassword(),
            Collections.emptyList()
        )
    );
  }
  String httpServletRequestToString(HttpServletRequest request) throws Exception {

	    ServletInputStream mServletInputStream = request.getInputStream();
	    byte[] httpInData = new byte[request.getContentLength()];
	    int retVal = -1;
	    StringBuilder stringBuilder = new StringBuilder();

	    while ((retVal = mServletInputStream.read(httpInData)) != -1) {
	        for (int i = 0; i < retVal; i++) {
	            stringBuilder.append(Character.toString((char) httpInData[i]));
	        }
	    }

	    return stringBuilder.toString();
	}

  @Override
  protected void successfulAuthentication(
      HttpServletRequest req,
      HttpServletResponse res, FilterChain chain,
      Authentication auth) throws IOException, ServletException {
	  logger.info("successfulAuthentication!!!");
	  AuthenticationService.addToken(res, auth.getName());
  }
 

}
