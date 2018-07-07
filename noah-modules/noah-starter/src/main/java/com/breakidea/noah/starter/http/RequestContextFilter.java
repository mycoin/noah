package com.breakidea.noah.starter.http;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.OncePerRequestFilter;

import com.breakidea.noah.framework.FrameworkConstants;

public class RequestContextFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		if (request.getAttribute(FrameworkConstants.REQUEST_ID) == null) {
			request.setAttribute(FrameworkConstants.REQUEST_ID, UUID.randomUUID());
		}

		filterChain.doFilter(request, response);
	}
}
