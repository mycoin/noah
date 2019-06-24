package com.breakidea.noah.common.vo;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class UserVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String status;

    private String userName;

    private Date gmtCreated;

    private Date gmtModified;
}
