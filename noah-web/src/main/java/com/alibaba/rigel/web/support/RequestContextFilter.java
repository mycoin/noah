package com.alibaba.rigel.web.support;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.OncePerRequestFilter;

import com.alibaba.rigel.framework.FrameworkConstants;

public class RequestContextFilter extends OncePerRequestFilter implements FrameworkConstants {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		if (request.getAttribute(REQUEST_ID) == null) {
			request.setAttribute(REQUEST_ID, UUID.randomUUID());
		}
		filterChain.doFilter(request, response);
	}
}
