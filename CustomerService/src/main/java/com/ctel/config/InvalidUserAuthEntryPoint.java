package com.ctel.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

//Login Avvakundaa API ni hit chesthe "UNAUTHORIZED" ane exception vasthundi.
// So aa exception raakundaa "msg" ni print cheyyadaaniki ee  InvalidUserAuthEntryPoint class ni use chesthaamu.

@Component
public class InvalidUserAuthEntryPoint implements AuthenticationEntryPoint {

	@Override
//	@Bean
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		// TODO Auto-generated method stub
		response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "UnAuthorized User!!!!");
		
	}

}

