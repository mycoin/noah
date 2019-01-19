package com.breakidea.noah.common.exception;

public interface CascadingThrowable {

    public static final String INTERNAL = "<INTERNAL>";

    public static final String EXTERNAL = "<EXTERNAL>";

    public static final String UNKNOWN = "<UNKNOWN>";

    Integer getCode();

}
