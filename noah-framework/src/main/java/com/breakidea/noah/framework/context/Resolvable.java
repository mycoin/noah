package com.breakidea.noah.framework.context;

public interface Resolvable {

	Object resolve(Context context) throws ContextException;
}
