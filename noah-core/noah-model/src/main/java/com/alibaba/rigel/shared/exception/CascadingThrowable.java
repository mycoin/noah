package com.alibaba.rigel.shared.exception;

public interface CascadingThrowable {

	Throwable getCause();

	String getName();

}
