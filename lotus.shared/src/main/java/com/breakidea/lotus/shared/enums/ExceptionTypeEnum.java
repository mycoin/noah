package com.breakidea.lotus.shared.enums;

public enum ExceptionTypeEnum {

	SYSTEM_WARN(1),

	SYSTEM_ERROR(2),

	SYSTEM_FATAL(3),

	APPLICATION_BUSY(4);

	ExceptionTypeEnum(int level) {

	}
}
