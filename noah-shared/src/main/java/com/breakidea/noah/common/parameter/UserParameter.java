package com.breakidea.noah.common.parameter;

import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class UserParameter extends BaseParameter {

    private static final long serialVersionUID = 1L;

    private Date gmtCreated;

    private Date gmtModified;

    private Long id;

    private String password;

    private String status;

    private String userName;
}
