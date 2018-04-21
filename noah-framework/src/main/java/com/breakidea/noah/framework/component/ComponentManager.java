package com.breakidea.noah.framework.component;

public interface ComponentManager {

	boolean hasComponent(String key);

	Component lookup(String key) throws ComponentException;

	void release(Component component);
}
