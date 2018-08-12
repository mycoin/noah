package com.breakidea.noah.framework.support.post;

public abstract class ActionProcessor {

	protected abstract boolean accepted(Parameter parameter);

	protected abstract void execute(Parameter parameter, ActionProcessorContext context);

	/**
	 * 流程的执行入口
	 * 
	 * @param parameter
	 * @param context
	 */
	public void process(Parameter parameter, ActionProcessorContext context) {
		if (accepted(parameter)) {
			execute(parameter, context);
		}
	}
}
