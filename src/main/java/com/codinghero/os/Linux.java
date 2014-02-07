package com.codinghero.os;

import java.io.IOException;

public class Linux extends OS {
	public boolean wget(String origPath, String destPath) {
		try {
			Runtime.getRuntime().exec(
					"wget -c -T 15 -t 5 " + origPath + " -O " + destPath);
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
}
