package com.breakidea.noah.support;

public class Desensitization {
	public static String cardId(String cardId) {
		int len = cardId.length();
		int desenLen = len - 4;
		StringBuffer stringBuffer = new StringBuffer();

		String deSecCardId = "";
		for (int i = 0; i < len; i++) {
			if (i < desenLen) {
				stringBuffer.append("*");
			} else {
				stringBuffer.append(cardId.charAt(i));
			}
		}
		deSecCardId = stringBuffer.toString();
		return deSecCardId;
	}

	public static String chineseName(String personalName) {
		int len = personalName.length();
		StringBuffer stringBuffer = new StringBuffer();

		String deSecPersonalName = "";
		for (int i = 0; i < len; i++) {
			if (i == 0) {
				stringBuffer.append("*");
			} else {
				stringBuffer.append(personalName.charAt(i));
			}
		}
		deSecPersonalName = stringBuffer.toString();
		return deSecPersonalName;
	}

	public static String emailAddr(String emailAddr) {
		String emailPrefix = emailAddr.substring(0, emailAddr.indexOf("@"));
		String emailPostfix = emailAddr.substring(emailAddr.indexOf("@") + 1, emailAddr.length());
		int len = emailPrefix.length();
		int desenLen = len - 2;
		StringBuffer stringBuffer = new StringBuffer();
		String deSecphoneNum = "";
		if (len <= 2) {
			stringBuffer.append(emailPrefix.charAt(0) + "**");
		} else {
			for (int i = 0; i < len; i++) {
				if (i < desenLen) {
					stringBuffer.append(emailAddr.charAt(i));
				} else {
					stringBuffer.append("*");
				}
			}
		}
		stringBuffer.append("@");
		stringBuffer.append(emailPostfix);

		deSecphoneNum = stringBuffer.toString();
		return deSecphoneNum;
	}

	public static String personalId(String personalId) {
		int len = personalId.length();
		StringBuffer stringBuffer = new StringBuffer();

		String deSecPersonalId = "";
		for (int i = 0; i < len; i++) {
			if (i == 0 || i == len - 1) {
				stringBuffer.append(personalId.charAt(i));
			} else {
				stringBuffer.append("*");
			}
		}
		deSecPersonalId = stringBuffer.toString();
		return deSecPersonalId;
	}

	public static String phoneNum(String phoneNum) {
		int len = phoneNum.length();
		int desenLen = len - 4;
		StringBuffer stringBuffer = new StringBuffer();
		String deSecphoneNum = "";
		for (int i = 0; i < len; i++) {
			if (i < desenLen) {
				stringBuffer.append("*");
			} else {
				stringBuffer.append(phoneNum.charAt(i));
			}
		}
		deSecphoneNum = stringBuffer.toString();
		return deSecphoneNum;
	}
}
