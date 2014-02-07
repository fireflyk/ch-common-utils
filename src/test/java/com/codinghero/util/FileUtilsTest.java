package com.codinghero.util;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

import com.codinghero.os.OSContext;
import com.codinghero.util.FileUtils;

public class FileUtilsTest {
	
	public static String WIN_BASE_PATH = "F:\\liutongutils\\";
	public static String LINUX_BASE_PATH = "~/liutongutils/";
	
	@Test
	public void testGetFileType() {
		assertEquals(FileUtils.getFileType("F:\\test.txt"), "txt");
		assertEquals(FileUtils.getFileType("/home/test.txt"), "txt");
		assertEquals(FileUtils.getFileType("/home/.classpath"), "classpath");
		assertEquals(FileUtils.getFileType("/home/test."), "");
		assertEquals(FileUtils.getFileType("/home/work"), "");
	}

	@Test
	public void testGetFileName() {
		assertEquals(FileUtils.getFileName("F:\\test.txt"), "test.txt");
		assertEquals(FileUtils.getFileName("/home/test.txt"), "test.txt");
		assertEquals(FileUtils.getFileName("/home/.classpath"), ".classpath");
		assertEquals(FileUtils.getFileName("/home/test."), "test.");
		assertEquals(FileUtils.getFileName("/home/work"), "");
	}

	@Test
	public void testGetFileNameWithoutPostfix() {
		assertEquals(FileUtils.getFileNameWithoutPostfix("F:\\test.txt"), "test");
		assertEquals(FileUtils.getFileNameWithoutPostfix("/home/test.txt"), "test");
		assertEquals(FileUtils.getFileNameWithoutPostfix("/home/.classpath"), "");
		assertEquals(FileUtils.getFileNameWithoutPostfix("/home/test."), "test");
		assertEquals(FileUtils.getFileNameWithoutPostfix("/home/work"), "");
	}

	@Test
	public void testNewFolder() {
		String folderPath = getBasePath() + "test";
		if (new File(folderPath).exists()) {
			throw new RuntimeException("Remove the folder '" + folderPath
					+ "' firstly, and then start test.");
		}
		FileUtils.newFolder(folderPath);
		assertTrue(new File(folderPath).exists());
		FileUtils.deleteEmptyFolder(folderPath);
	}

	@Test
	public void testNewFile() {
		// newFile(String)
		String filePath = getBasePath() + "test.txt";
		if (new File(filePath).exists()) {
			throw new RuntimeException("Remove the file '" + filePath
					+ "' firstly, and then start test.");
		}
		FileUtils.newFile(filePath);
		assertTrue(new File(filePath).exists());
		FileUtils.deleteFile(filePath);
		
		filePath = getBasePath() + ".." + File.separator + "test.txt";
		if (new File(filePath).exists()) {
			throw new RuntimeException("Remove the file '" + filePath
					+ "' firstly, and then start test.");
		}
		FileUtils.newFile(filePath);
		assertTrue(new File(filePath).exists());
		FileUtils.deleteFile(filePath);
		
		// newFile(String,String)
		filePath = getBasePath() + "test.txt";
		String content = "Hello World!";
		FileUtils.newFile(filePath, content);
		assertTrue(new File(filePath).exists());
		assertEquals(FileUtils.getContent(filePath), content);
		assertTrue(FileUtils.deleteFile(filePath));
	}

	@Test
	public void testDeleteFolder() {
		String folderPath = getBasePath() + "test" + File.separator;
		FileUtils.newFile(folderPath + "test.txt");
		FileUtils.newFile(folderPath + "sub" + File.separator + "test.txt");
		FileUtils.deleteFolder(folderPath);
		assertFalse(new File(folderPath).exists());
		
		FileUtils.newFile(folderPath + "test.txt");
		FileUtils.newFile(folderPath + "sub" + File.separator + "test.txt");
		FileUtils.deleteFolder(folderPath, ".*u.*");
		assertFalse(new File(folderPath + File.separator + "sub").exists());
		assertTrue(new File(folderPath).exists());
		FileUtils.deleteFolder(folderPath);
	}

	@Test
	public void testCleanFolder() {
		// test->sub,test.txt;sub->test.txt
		String folderPath = getBasePath() + "test" + File.separator;
		FileUtils.newFolder(folderPath + "sub");
		FileUtils.newFile(folderPath + "test.txt");
		FileUtils.newFile(folderPath + "sub" + File.separator + "sub.txt");
		FileUtils.cleanFolder(folderPath);
		assertTrue(new File(folderPath).exists());
		assertTrue(new File(folderPath + "sub").exists());
		assertFalse(new File(folderPath + "test.txt").exists());
		assertFalse(new File(folderPath + "sub" + File.separator + "sub.txt").exists());
		FileUtils.deleteFolder(folderPath);
	}
	
	@Test
	public void testUnzip(){
		// zip
		String folderPath = getBasePath() + "test" + File.separator;
		FileUtils.newFile(folderPath + "test.txt");
		FileUtils.newFile(folderPath + "sub" + File.separator + "sub.txt");
		FileUtils.zip(folderPath, getBasePath() + "test.zip");
		assertTrue(new File(getBasePath() + "test.zip").exists());
		
		// unzip
		FileUtils.deleteFolder(folderPath);
		FileUtils.unzip(getBasePath() + "test.zip", getBasePath());
		assertTrue(new File(folderPath + "test.txt").exists());
		assertTrue(new File(folderPath + "sub" + File.separator + "sub.txt").exists());
		FileUtils.deleteFolder(folderPath);
		FileUtils.deleteFile(getBasePath() + "test.zip");
	}
	
	public static String getBasePath() {
		String os = OSContext.getOSName();
		// ex. Windows XP
		if (os.startsWith("Win")) {
			return WIN_BASE_PATH;
		} else {
			return LINUX_BASE_PATH;
		}
	}
}
