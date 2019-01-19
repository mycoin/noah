package com.breakidea.noah.common.param;

import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class UserParam extends BaseParam {

    private static final long serialVersionUID = -2859563426021667683L;

    private Date gmtCreated;

    private Date gmtModified;

    private Long id;

    private String password;

    private String status;

    private String userName;
}
