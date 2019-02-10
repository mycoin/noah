package com.breakidea.noah.service.impl;

import com.breakidea.noah.support.Encoder;

public class PasswordGenerator {

	public static void main(String[] args) throws Exception {
		String code = "www.1688.com";
		String result = Encoder.encode(code).toUpperCase();

		System.out.println(result);
	}
}
