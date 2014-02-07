package com.codinghero.util;

import static junit.framework.Assert.*;

import org.junit.Test;

import com.codinghero.util.NumberUtils;

public class NumberUtilsTest {

	@Test
	public void testIsInteger() {
		assertTrue(NumberUtils.isInteger("0"));
		assertTrue(NumberUtils.isInteger("12"));
		assertTrue(NumberUtils.isInteger("-1"));
		
		assertFalse(NumberUtils.isInteger("-0"));
		assertFalse(NumberUtils.isInteger(" 12"));
	}
}
