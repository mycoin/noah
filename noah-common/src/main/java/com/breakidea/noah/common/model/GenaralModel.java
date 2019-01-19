package com.breakidea.noah.common.model;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public abstract class GenaralModel implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private Date gmtCreated;

    private Date gmtModified;
}
