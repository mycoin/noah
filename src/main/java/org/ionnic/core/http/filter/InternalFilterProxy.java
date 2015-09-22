package org.ionnic.core.http.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.springframework.web.filter.GenericFilterBean;

public class InternalFilterProxy extends GenericFilterBean {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		request.setAttribute("filter", this.getClass().getName());
		chain.doFilter(request, response);
	}

	@Override
	protected void initFilterBean() throws ServletException {
		
	}
}
