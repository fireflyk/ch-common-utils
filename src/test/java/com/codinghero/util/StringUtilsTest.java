package com.codinghero.util;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.codinghero.util.StringUtils;

public class StringUtilsTest {
	
	@Test
	public void testIsEmpty() {
		assertTrue(StringUtils.isEmpty(""));
		assertTrue(StringUtils.isEmpty(" "));
		assertFalse(StringUtils.isEmpty(" hello "));
	}
	
	@Test
	public void testContainsChinese() {
		assertTrue(StringUtils.containsChinese("a我b"));
		assertFalse(StringUtils.containsChinese("abc"));
		assertFalse(StringUtils.containsChinese(""));
	}
	
	@Test
	public void testToPinyin() {
		assertEquals(StringUtils.toPinyin("a我b123"), "awo3b123");
	}
	
	@Test
	public void testFirstLetterUpper() {
		assertEquals(StringUtils.firstLetterUpper("abc"), "Abc");
		assertEquals(StringUtils.firstLetterUpper("aBc"), "ABc");
	}
	
	@Test
	public void testFirstLetterLower() {
		assertEquals(StringUtils.firstLetterLower("Abc"), "abc");
		assertEquals(StringUtils.firstLetterLower("ABc"), "aBc");
	}
	
	@Test
	public void testJoin() {
		List<String> list = new ArrayList<String>();
		assertEquals(StringUtils.join(list, ","), "");
		list.add("a");
		assertEquals(StringUtils.join(list, ","), "a");
		assertEquals(StringUtils.join(list, ",", 0, 0), "a");
		list.add("b");
		list.add("c");
		assertEquals(StringUtils.join(list, ","), "a,b,c");
		assertEquals(StringUtils.join(list, ",", 0, 1), "a,b");
		assertEquals(StringUtils.join(list, ",", 0, 2), "a,b,c");
	}
}
