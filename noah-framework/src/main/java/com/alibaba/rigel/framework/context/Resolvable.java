package com.alibaba.rigel.framework.context;

public interface Resolvable {

	Object resolve(Context context) throws ContextException;
}
