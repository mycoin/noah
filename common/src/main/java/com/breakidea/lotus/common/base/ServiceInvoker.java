package com.breakidea.lotus.common.base;

public interface ServiceInvoker<T> {

    T invoke( String methodName, Object... params );

}
