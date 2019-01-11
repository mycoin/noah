package com.breakidea.noah.common;

public abstract class ActionProcessor {

	protected abstract void execute(Parameter parameter);

	protected boolean accepted(Parameter parameter) {
		return true;
	}

	public void process(Parameter parameter) {
		if (accepted(parameter)) {
			execute(parameter);
		}
	}
}
