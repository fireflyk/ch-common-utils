package com.codinghero.util;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

import com.codinghero.util.CodecUtils;
import com.codinghero.util.FileUtils;

public class CodecUtilsTest {

	@Test
	public void testForceGenMd5File() {
		String basePath = FileUtilsTest.getBasePath();
		String filePath = basePath + "test.txt";
		String md5FilePath = basePath + "test.txt.md5";
		if (new File(filePath).exists()) {
			throw new RuntimeException("Remove the file '" + filePath
					+ "' firstly, and then start test.");
		}
		
		FileUtils.newFile(filePath, "hello world!你好，世界！");
		CodecUtils.forceGenMd5File(filePath);
		assertEquals(FileUtils.getContent(md5FilePath), "7e41fd23f45be710a8998fba6f50e7eb");
		FileUtils.deleteFile(filePath);
		FileUtils.deleteFile(filePath + ".md5");
	}
}
