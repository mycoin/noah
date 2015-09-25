package org.ionnic.core.handler;

import java.io.Serializable;

import org.springframework.core.MethodParameter;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;

public class JsonResultReturnValueHandler implements HandlerMethodReturnValueHandler, Serializable {

	private static final long serialVersionUID = 1L;

	@Override
	public void handleReturnValue(Object returnValue, MethodParameter returnType, ModelAndViewContainer mavContainer, NativeWebRequest webRequest)
	        throws Exception {
		// Access-Control-Allow-Credentials:true
		// Access-Control-Allow-Origin:*
		// Access-Control-Expose-Headers:ETag, Link, X-GitHub-OTP,
		// X-RateLimit-Limit, X-RateLimit-Remaining,
		// X-RateLimit-Reset,X-OAuth-Scopes, X-Accepted-OAuth-Scopes,
		// X-Poll-Interval
	}

	@Override
	public boolean supportsReturnType(MethodParameter returnType) {
		Class<?> paramType = returnType.getParameterType();
		return JsonResultReturnValueHandler.class.isAssignableFrom(paramType);
	}

}
