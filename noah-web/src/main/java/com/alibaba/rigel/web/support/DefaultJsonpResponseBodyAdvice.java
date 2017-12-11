package com.alibaba.rigel.web.support;

import javax.servlet.http.HttpServletRequest;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.AbstractJsonpResponseBodyAdvice;

@Component
@ControllerAdvice(basePackages = "com.alibaba.rigel.web.module")
public class DefaultJsonpResponseBodyAdvice extends AbstractJsonpResponseBodyAdvice {

	private final String[] callbackNames;

	public DefaultJsonpResponseBodyAdvice() {
		super("callback", "jsonp");
		this.callbackNames = new String[] { "callback" };
	}

	@Override
	protected void beforeBodyWriteInternal(MappingJacksonValue bodyContainer, MediaType contentType,
			MethodParameter returnType, ServerHttpRequest request, ServerHttpResponse response) {

		HttpServletRequest servletRequest = ((ServletServerHttpRequest) request).getServletRequest();
		if (ObjectUtils.isEmpty(servletRequest.getParameter("callback"))) {
			return;
		}

		for (String name : this.callbackNames) {
			String value = servletRequest.getParameter(name);
			if (value != null) {
				MediaType contentTypeToUse = getContentType(contentType, request, response);
				response.getHeaders().setContentType(contentTypeToUse);
				bodyContainer.setJsonpFunction(value);
				return;
			}
		}
	}

}
