package com.breakidea.noah.web.velocity;

import javax.servlet.http.HttpServletRequest;

import org.apache.velocity.context.Context;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.Gson;

public class VelocityInternalUtils {

	@Autowired
	private HttpServletRequest request;
	
	private Context velocityContext;

	public String stringify(Object jsonElement) {
		return new Gson().toJson(jsonElement);
	}

	public void setHttpServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public void setVelocityContext(Context velocityContext) {
		this.velocityContext = velocityContext;
	}

}
