package org.ionnic.core.filter;

import java.io.IOException;
import java.nio.file.AccessDeniedException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GlobalAccessFilter implements Filter {

	private Logger logger = LoggerFactory.getLogger(GlobalAccessFilter.class);

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		try {
			chain.doFilter(request, response);
		} catch (AccessDeniedException e) {

			logger.error("Error Cautch", e);
			request.setAttribute("exception", e);

			HttpServletResponse resp = (HttpServletResponse) response;
			resp.sendError(405, "Global-Access-Filter Cautch.");
		}
	}

	@Override
	public void destroy() {

	}

}
