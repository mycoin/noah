package org.ionnic.core.http.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.ionnic.core.SecuritySupport;
import org.ionnic.core.utils.RequestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RefererCheckFilter implements Filter {

	private Logger logger = LoggerFactory.getLogger(RefererCheckFilter.class);

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;

		if (RequestUtils.isAjax(req)) {

			if (!SecuritySupport.checkRefererDomain(req)) {
				ServletException exception = new ServletException("Not Allowed Referer");
				logger.error("not acceptable referer.", exception);
				throw exception;
			}
		}
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {

	}

}
