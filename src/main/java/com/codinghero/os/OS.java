package com.codinghero.os;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.Writer;
import java.net.MalformedURLException;
import java.net.URL;

import com.codinghero.util.FileUtils;

public abstract class OS {
	public boolean wget(String origPath, String destPath) {
		try {
			Reader reader = new BufferedReader(new InputStreamReader(new URL(origPath).openStream()));
			Writer writer = new BufferedWriter(new FileWriter(destPath));
			FileUtils.write(reader, writer);
			return true;
		} catch (MalformedURLException e) {
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
}
