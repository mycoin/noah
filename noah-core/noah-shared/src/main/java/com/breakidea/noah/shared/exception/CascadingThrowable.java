package com.breakidea.noah.shared.exception;

public interface CascadingThrowable {

	Throwable getCause();

	String getName();

}
