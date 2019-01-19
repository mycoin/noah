package com.breakidea.noah.common.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class UserModel extends GenaralModel {

    private static final long serialVersionUID = 1L;

    private String password;

    private String status;

    private String userName;
}
