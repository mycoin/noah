package com.breakidea.noah.configure;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.AsyncContext;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnNotWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.filter.OncePerRequestFilter;

import com.breakidea.noah.support.core.ThreadLocalContext;

@Configuration
@ConditionalOnClass({ OncePerRequestFilter.class })
@EnableConfigurationProperties(VelocityProperties.class)
public class RequestContextConfiguration {

	@Bean
	public RequestContextFilterBean requestContextListener() {
		return new RequestContextFilterBean();
	}

	@Configuration
	@ConditionalOnNotWebApplication
	public static class RequestContextFilterBean extends OncePerRequestFilter {

		public void resetContextHolders() {
			LocaleContextHolder.resetLocaleContext();
			RequestContextHolder.resetRequestAttributes();

			ThreadLocalContext.clear();
		}

		public void initContextHolders(HttpServletRequest request, ServletRequestAttributes requestAttributes) {
			LocaleContextHolder.setLocale(request.getLocale(), true);
			RequestContextHolder.setRequestAttributes(requestAttributes, true);

			if (logger.isInfoEnabled()) {
				logger.info("Bound request context to thread: " + request.getRequestURI());
			}
		}

		@Override
		protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
				FilterChain filterChain) throws ServletException, IOException {
			ServletRequestAttributes attributes = new ServletRequestAttributes(request, response);
			initContextHolders(request, attributes);

			try {
				HttpServletRequestWrapper httpRequestWrapper = new HttpServletRequestWrapper(request) {
					public String getParameter(String name) {
						return super.getParameter(name);
					}

					public Enumeration<String> getParameterNames() {
						return super.getParameterNames();
					}

					public AsyncContext getAsyncContext() {
						return super.getAsyncContext();
					}

					public String getMethod() {
						return super.getMethod();
					}
				};

				filterChain.doFilter(httpRequestWrapper, response);
			} finally {
				resetContextHolders();
				attributes.requestCompleted();
				if (logger.isInfoEnabled()) {
					logger.info("Cleared thread-bound request context: " + request.getRequestURI());
				}
			}
		}

	}
}
