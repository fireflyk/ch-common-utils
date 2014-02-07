package com.codinghero.util;

import java.util.List;

import org.junit.Test;

import com.codinghero.util.ArrayUtils;

import static org.junit.Assert.*;

public class ArrayUtilsTest {

	@Test
	public void testIsEmpty() {
		assertTrue(ArrayUtils.isEmpty(null));
		assertTrue(ArrayUtils.isEmpty(new Object[] {}));
		assertFalse(ArrayUtils.isEmpty(new String[] { "" }));
	}

	@Test
	public void testToList() {
		assertNull(ArrayUtils.toList((Object[]) null));
		
		String[] strArr1 = { "a" };
		List<String> strList = ArrayUtils.toList(strArr1);
		assertEquals(strList.size(), 1);
		assertEquals(strList.get(0), "a");

		String[] strArr2 = { "a", "b" };
		strList = ArrayUtils.toList(strArr2);
		assertEquals(strList.size(), 2);
		assertEquals(strList.get(0), "a");
		assertEquals(strList.get(1), "b");
	}
}
