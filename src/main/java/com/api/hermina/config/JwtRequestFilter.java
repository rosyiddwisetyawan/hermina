package com.api.hermina.config;

import com.api.hermina.service.JwtUserDetailsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

	private static final Logger LOGGER = LoggerFactory.getLogger(JwtRequestFilter.class);

	@Autowired
	private JwtUserDetailsService jwtUserDetailsService;

	@Autowired
	private JwtTokenService jwtTokenService;

	@Override
	protected void doFilterInternal(final HttpServletRequest request, final HttpServletResponse response,
									final FilterChain chain) throws ServletException, IOException {

		// look for Bearer auth header
		final String header = request.getHeader(HttpHeaders.AUTHORIZATION);
		if (header == null || !header.startsWith("Bearer ")) {
			LOGGER.info("Authorization");
			chain.doFilter(request, response);

			return;
		}

		final String token = header.substring(7);
		final String username = jwtTokenService.validateTokenAndGetUsername(token);
		if (username == null) {
			LOGGER.info("Token Not Valid");
			// validation failed or token expired
			chain.doFilter(request, response);
			return;
		}

		// set user details on spring security context
		final UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(username);
		final UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
				userDetails, null, userDetails.getAuthorities());
		authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
		SecurityContextHolder.getContext().setAuthentication(authentication);

		// continue with authenticated user
		chain.doFilter(request, response);
	}
}

