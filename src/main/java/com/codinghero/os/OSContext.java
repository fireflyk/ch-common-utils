package com.codinghero.os;

public class OSContext {
	
	public static String getOSName() {
		return System.getProperty("os.name");
	}
	
	public static OS getOS() {
		String os = getOSName();
		if (os.startsWith("Linux")) {
			return new Linux();
		} else if (os.startsWith("Win")) {
			return new Windows();
		} else {
			throw new RuntimeException("Not support this operating system:" + os);
		}
	}
}
