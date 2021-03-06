package com.breakidea.noah.common.parameter;

import java.io.Serializable;

import lombok.Data;

@Data
public abstract class BaseParameter implements Serializable {

    private static final long serialVersionUID = 1L;

    private String namespace = "DEFAULT";

    private String version = "1.0.0";

    private String ie = "utf-8";
}
