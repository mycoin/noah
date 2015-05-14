package org.ionnic.test.misc;

public class SystemPath {

	public static String SEPARATOR = System.getProperty("file.separator");

	public static String getClassPath() {
		String path = Thread.currentThread().getContextClassLoader().getResource("").toString();
		return path.replaceFirst("file:", "");
	}

	public static String getSystempPath() {
		return System.getProperty("java.io.tmpdir");
	}

	public static void main(String[] args) {
		System.out.println(System.getProperty("java.io.tmpdir"));
		System.out.println(SEPARATOR);
		System.out.println(getClassPath());
	}
}
