package com.breakidea.lotus.common.base;

public interface IterableLookup<T> extends Lookup<T> {

    Iterable<String> keySet();

}
