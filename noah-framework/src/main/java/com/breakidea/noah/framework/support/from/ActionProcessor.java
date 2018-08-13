package com.breakidea.noah.framework.support.from;

public abstract class ActionProcessor {

	protected abstract boolean accepted(Parameter parameter);

	protected abstract void execute(ActionProcessorContext context, Parameter parameter);

	public void process(Parameter parameter, ActionProcessorContext context) {
		if (accepted(parameter)) {
			execute(context, parameter);
		}
	}
}
