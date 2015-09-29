package org.ionnic.core.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExceptionResolverFilter implements Filter {

	private Logger logger = LoggerFactory.getLogger(ExceptionResolverFilter.class);

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		try {
			chain.doFilter(request, response);
		} catch (Throwable e) {

			logger.error("Error Cautch", e);
			HttpServletResponse resp = (HttpServletResponse) response;
			resp.sendError(403);
		}
	}

	@Override
	public void destroy() {

	}

}
