package com.alibaba.rigel.web.support;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.AbstractJsonpResponseBodyAdvice;

@ControllerAdvice(basePackages = "com.alibaba.rigel.web.module")
public class DefaultJsonpResponseBodyAdvice extends AbstractJsonpResponseBodyAdvice {
	public DefaultJsonpResponseBodyAdvice() {
		super("callback");
	}
}
