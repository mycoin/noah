package com.breakidea.noah.service.post.processor;

import com.breakidea.noah.framework.support.post.ActionProcessor;
import com.breakidea.noah.framework.support.post.Parameter;

public class TokenProcessor extends ActionProcessor {

	@Override
	protected void execute(Parameter parameter) {
		System.out.println(parameter);
	}
}
