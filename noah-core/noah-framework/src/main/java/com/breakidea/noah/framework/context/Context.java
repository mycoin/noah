package com.breakidea.noah.framework.context;

import java.io.Serializable;

public interface Context extends Serializable {

	public static final String REQUEST_ID = null;

	Object get(Object key) throws ContextException;

	Context getParent();

	boolean isLeaf();

	void put(Object key, Object value) throws ContextException;

}
