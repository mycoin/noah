package com.breakidea.noah.service.post;

import java.util.List;

import com.breakidea.noah.framework.support.post.ActionProcessor;

public class FormActionProcessorConfigBean {

	private List<ActionProcessor> actionProcessors = null;

	public List<ActionProcessor> getActionProcessors() {
		return actionProcessors;
	}

	public void setActionProcessors(List<ActionProcessor> actionProcessors) {
		this.actionProcessors = actionProcessors;
	}

}
