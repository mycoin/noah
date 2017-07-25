package com.breakidea.lotus.web.support.beans;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.OncePerRequestFilter;

public class RequestContextFilter extends OncePerRequestFilter {

	public static final String REQUEST_ID = "request_id";

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		if (request.getAttribute(REQUEST_ID) == null) {
			request.setAttribute(REQUEST_ID, UUID.randomUUID());
		}
		filterChain.doFilter(request, response);
	}
}
