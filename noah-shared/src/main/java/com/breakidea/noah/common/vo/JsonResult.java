package com.breakidea.noah.common.vo;

import java.io.Serializable;

import lombok.Data;

@Data
public class JsonResult<T extends Object> implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer status = 0;

    private T data = null;

    private String statusInfo = "OK";
}
