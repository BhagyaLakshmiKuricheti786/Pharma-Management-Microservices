
package com.ctel.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.ctel.config.JwtUtil;
import com.ctel.entity.Customer;

@Component
public class SecurityFilter extends OncePerRequestFilter {

	@Autowired
	JwtUtil util;

	@Autowired
	UserDetailsService userdetailsservice;

	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterchain)
			throws IOException, ServletException {
		String token = request.getHeader("Authorization");

		if (token != null) {
			System.out.println("Filter");
			String userEmail = util.getEmailId(token);
			if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
				UserDetails user = userdetailsservice.loadUserByUsername(userEmail);
				boolean isValid = util.validateToken(token, user.getUsername());
				if (isValid) {
					UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userEmail,
							user.getPassword(), user.getAuthorities());
					authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
					SecurityContextHolder.getContext().setAuthentication(authToken);
				}
			}
		}
		filterchain.doFilter(request, response);
	}
}
