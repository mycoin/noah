package com.breakidea.noah.web.velocity;

import javax.servlet.http.HttpServletRequest;

import org.apache.velocity.context.Context;

public class VelocityInternalUtils {

	private HttpServletRequest request;

	private Context velocityContext;

	public void setHttpServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public void setVelocityContext(Context velocityContext) {
		this.velocityContext = velocityContext;
	}

}
