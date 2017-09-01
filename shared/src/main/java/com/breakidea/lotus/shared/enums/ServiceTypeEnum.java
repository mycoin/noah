package com.breakidea.lotus.shared.enums;

public enum ServiceTypeEnum {

	FORMAT_SERVICE("formal"),

	INTERNAL_SERVICE("interval");

	private String type;

	public static ServiceTypeEnum getValue(String type) {
		ServiceTypeEnum result = null;
		for (ServiceTypeEnum sourceType : ServiceTypeEnum.values()) {
			if (sourceType.getType().equals(type)) {
				result = sourceType;
				break;
			}
		}
		return result;
	}

	private ServiceTypeEnum(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}
}
