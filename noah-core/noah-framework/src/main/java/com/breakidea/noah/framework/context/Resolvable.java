package com.breakidea.noah.framework.context;

public interface Resolvable<T> {

	T resolve(Context context) throws ContextException;
}
