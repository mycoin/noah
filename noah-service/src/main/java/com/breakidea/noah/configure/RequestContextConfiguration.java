package com.breakidea.noah.configure;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnNotWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.OncePerRequestFilter;

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

        public void resetRequest() {
        }
 
        @Override
        protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                FilterChain filterChain) throws ServletException, IOException {
            
            try {
                filterChain.doFilter(request, response);
            } finally {
                resetRequest();
                if (logger.isTraceEnabled()) {
                    logger.trace("Cleared thread-bound request context: " + request);
                }
            }
        }

    }
}
