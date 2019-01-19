package com.breakidea.noah.common.param;

import java.io.Serializable;

import lombok.Data;

@Data
public abstract class BaseParam implements Serializable {

    private static final long serialVersionUID = 1L;

    private String namespace = "DEFAULT";
}
