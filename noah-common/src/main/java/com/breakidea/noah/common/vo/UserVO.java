package com.breakidea.noah.common.vo;

import java.util.Date;

import com.breakidea.noah.common.Description;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString
@EqualsAndHashCode(callSuper = false)
public class UserVO extends Description {

	private static final long serialVersionUID = 1L;

	private Long id;

	private String status;

	private String userName;

	private Date gmtCreated;

	private Date gmtModified;
}
