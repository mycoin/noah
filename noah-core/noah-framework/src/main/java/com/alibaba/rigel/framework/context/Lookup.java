package com.alibaba.rigel.framework.context;

public interface Lookup<T> {

	T get(Object name);

}