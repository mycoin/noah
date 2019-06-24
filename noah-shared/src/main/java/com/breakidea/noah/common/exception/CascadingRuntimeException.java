package com.breakidea.noah.common.exception;

public class CascadingRuntimeException extends RuntimeException implements CascadingThrowable {

    private static final long serialVersionUID = 1L;

    public CascadingRuntimeException(final String message, final Throwable throwable) {
        super(message, throwable);
    }

    @Override
    public Integer getCode() {
        return 500;
    }

}
