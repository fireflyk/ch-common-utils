package com.codinghero.util;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;

import com.codinghero.util.DateUtils;

public class DateUtilsTest {

	@Test
	public void testGetDateStr() {
		assertEquals(DateUtils.getDateStr(DateUtils.getDate("1986-06-28")),
				"1986-06-28");
		assertEquals(DateUtils.getDateStr(DateUtils.getDate("1986-06-28"), "yyyyMMdd"),
				"19860628");
	}

	@Test
	public void testGetTimeStr() {
		assertEquals(DateUtils.getTimeStr(DateUtils.getTime("1986-06-28 17:55:00")),
				"1986-06-28 17:55:00");
		assertEquals(DateUtils.getTimeStr(DateUtils.getTime("1986-06-28 17:55:00"), "yyyyMMdd HHmmss"),
				"19860628 175500");
	}

	@Test
	public void testLastMonth() {
		Date date = DateUtils.getTime("1986-06-28 17:55:00");
		assertEquals(DateUtils.getTimeStr(DateUtils.lastMonth(date)), "1986-05-01 00:00:00");
	}

	@Test
	public void testThisMonth() {
		Date date = DateUtils.getTime("1986-06-28 17:55:00");
		assertEquals(DateUtils.getTimeStr(DateUtils.thisMonth(date)), "1986-06-01 00:00:00");
	}
}
