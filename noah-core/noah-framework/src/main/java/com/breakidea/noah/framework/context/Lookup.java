package com.breakidea.noah.framework.context;

public interface Lookup<T> {

	T get(Object name);

}