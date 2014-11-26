package com.codinghero.util;

import org.apache.commons.codec.digest.DigestUtils;

public class CodecUtils {
	
	public static String md5(String str){
		return DigestUtils.md5Hex(str);
	}
	
	public static boolean genMd5File(String origFilePath){
		return genMd5File(origFilePath, "md5");
	}
	
	public static boolean forceGenMd5File(String origFilePath){
		return genMd5File(origFilePath, "md5");
	}
	
	public static void forceGenMd5File(String origFilePath, String postfix){
		if (origFilePath == null)
			throw new IllegalArgumentException();
		if (postfix == null)
			throw new IllegalArgumentException();
		// delete firstly
		String md5FilePath = origFilePath + "." + postfix;
		FileUtils.deleteFile(md5FilePath);
		// then generate md5 file
		genMd5File(origFilePath, postfix);
	}
	
	public static boolean genMd5File(String origFilePath, String postfix){
		if (origFilePath == null)
			throw new IllegalArgumentException();
		if (postfix == null)
			throw new IllegalArgumentException();
		String md5FilePath = origFilePath + "." + postfix;
		if (!FileUtils.newFile(md5FilePath))
			return false;
		// get file content
		String fileContent = FileUtils.getContent(origFilePath);
		// append content's md5 to file
		FileUtils.appendFile(md5FilePath, md5(fileContent));
		return true;
	}
}
