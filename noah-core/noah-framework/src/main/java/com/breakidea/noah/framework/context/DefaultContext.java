package com.breakidea.noah.framework.context;

import java.util.Hashtable;
import java.util.Map;

public class DefaultContext implements Context {

	private static final long serialVersionUID = 1L;

	private final Map<Object, Object> context;

	private final Context parent;

	public DefaultContext() {
		this((Context) null);
	}

	public DefaultContext(final Context parent) {
		this(new Hashtable<Object, Object>(), parent);
	}

	public DefaultContext(final Map<Object, Object> context) {
		this(context, null);
	}

	public DefaultContext(final Map<Object, Object> context, final Context parent) {
		this.parent = parent;
		this.context = context;
	}

	@Override
	public Object get(final Object key) throws ContextException {
		final Object data = context.get(key);
		if (null != data) {
			if (data instanceof Resolvable) {
				return ((Resolvable) data).resolve(this);
			}
			return data;
		}
		if (parent == null) {
			throw new ContextException("Unable to resolve context key: " + key);
		}

		return parent.get(key);
	}

	@Override
	public final Context getParent() {
		return parent;
	}

	@Override
	public boolean isLeaf() {
		return parent == null;
	}

	@Override
	public void put(final Object key, final Object value) throws IllegalStateException {
		if (null == value) {
			context.remove(key);
		} else {
			context.put(key, value);
		}
	}
}
