package com.codinghero.util;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import net.sourceforge.pinyin4j.PinyinHelper;

public final class StringUtils {
	
	public static boolean isChinese(char c) {
		if (c >= 0x4e00 && c <= 0x9fbb)
			return true;
		else
			return false;
	}
	
	public static boolean isEngLetter(char c) {
		if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z'))
			return true;
		else
			return false;
	}
	
	public static boolean isUpperLetter(char c) {
		if (c >= 'A' && c <= 'Z')
			return true;
		else
			return false;
	}
	
	public static boolean isLowerLetter(char c) {
		if (c >= 'a' && c <= 'z')
			return true;
		else
			return false;
	}
	
	/**
	 * Use NumberUtils.isNumber(c) instead.
	 * 
	 * @param c
	 * @return
	 */
	@Deprecated
	public static boolean isNumber(char c) {
		return NumberUtils.isNumber(c);
	}
	
	public static boolean containsChinese(String str) {
		for (int i = 0; i < str.length(); i++) {
			if (isChinese(str.charAt(i)))
				return true;
		}
		return false;
	}
	
	public static String toPinyin(String str) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);
			// transform chinese
			if (isChinese(c)) {
				String[] pinyin = PinyinHelper.toHanyuPinyinStringArray(c);
				if (pinyin != null && pinyin.length > 0) {
					char[] arr = pinyin[0].toCharArray();
					for (char ch : arr)
						// if ((ch >= '0' && ch <= '9') || (ch >= 'a' && ch <=
						// 'z')
						// || (ch >= 'A' && ch <= 'Z'))
						sb.append(ch);
				}
			} else {
				sb.append(c);
			}
		}
		return sb.toString();
	}
	
	/**
	 * Declare the string is empty or not<br/>
	 * true: the string is an empty string<br/>
	 * false: the string is not an empty string<br/>
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str) {
		if (str == null || str.trim().equals("")) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean isNotEmpty(String str) {
		return !isEmpty(str);
	}

	@Deprecated
	public static boolean isTrue(String str) {
		return !isEmpty(str) && str.equals("true");
	}

	@Deprecated
	public static boolean isFalse(String str) {
		return !isEmpty(str) && str.equals("false");
	}

	@Deprecated
	public static boolean isEmptyOrTrue(String str){
		return isEmpty(str) || isTrue(str);
	}

	@Deprecated
	public static boolean isEmptyOrFalse(String str){
		return isEmpty(str) || isFalse(str);
	}
	

	public static List<String> split(String str, String join) {
		String[] arr = str.split(join);
		return ArrayUtils.toList(arr);
	}
	
	/**
	 * 'join' is a opposite action of 'split'
	 * @param list
	 * @param join
	 * @return
	 */
	public static <T> String join(Collection<T> list, String join) {
		if (list == null || list.size() == 0) {
			return "";
		}
		return join(list, join, 0, list.size() - 1);
	}
	
	/**
	 * 'join' is a opposite action of 'split'
	 * 
	 * @param list
	 * @param join
	 * @param start	start index(include)
	 * @param end	end index(include)
	 * @return
	 */
	public static <T> String join(Collection<T> c, String join, int start, int end) {
		if (c == null || c.size() == 0) {
			return "";
		}
		if (start < 0 || start > end || end >= c.size())
			throw new IllegalArgumentException();
		StringBuilder sb = new StringBuilder();
		
		Iterator<T> iter = c.iterator();
		int i = 0;
		for (; i < start; i++) {
			iter.next();
		}
		for (; i < end; i++) {
			sb.append(iter.next());
			sb.append(join);
		}
		sb.append(iter.next());
		return sb.toString();
	}
	
	/**
	 * use <code>StringUtils.join(StringUtils.toStringList(arr), join, start, end)</code> replaced
	 * @param arr
	 * @param join
	 * @param start
	 * @param end
	 * @return
	 */
	@Deprecated
	public static String join(String[] arr, String join, int start, int end) {
		if (arr == null || arr.length == 0) {
			return "";
		}
		return join(ArrayUtils.toList(arr), join, start, end);
	}

	/**
	 * The first letter of the 'String' will be upper
	 * 
	 * @param str
	 * @return
	 */
	public static String firstLetterUpper(String str) {
		if (isEmpty(str))
			throw new NullPointerException();
		StringBuilder sb = new StringBuilder();
		sb.append(Character.toUpperCase(str.charAt(0)));
		if (str.length() > 1)
			sb.append(str.substring(1));
		return sb.toString();
	}
	
	/**
	 * The first letter of the 'String' will be lower
	 * @param str
	 * @return
	 */
	public static String firstLetterLower(String str) {
		if (isEmpty(str))
			throw new NullPointerException();
		else {
			StringBuilder sb = new StringBuilder();
			sb.append(Character.toLowerCase(str.charAt(0)));
			if (str.length() > 1)
				sb.append(str.substring(1));
			return sb.toString();
		}
	}
	
	@Deprecated
	public static boolean isFormula(String formula){
		
		if (formula == null) {
			return false;
		}
		
		int state = -1;
		for(int i=0; i<formula.length(); i++){
			char c = formula.charAt(i);
			if (state == -1) {
				if (c >= '0' && c <= '9') {
					state = 0;
					continue;
				} else {
					return false;
				}
			} else if (state == 0) {
				if (c >= '0' && c <= '9') {
					state = 0;
					continue;
				} else if(c=='+' || c=='-' || c=='*' || c=='/'){
					state = 1;
					continue;
				} else if(c == '.'){
					state = 2;
					continue;
				} else {
					return false;
				}
			} else if (state == 1){
				if (c >= '0' && c <= '9') {
					state = 0;
					continue;
				} else {
					return false;
				}
			} else if (state == 2) {
				if (c >= '0' && c <= '9') {
					state = 3;
					continue;
				} else {
					return false;
				}
			} else if (state == 3) {
				if (c >= '0' && c <= '9') {
					state = 3;
					continue;
				} else if(c=='+' || c=='-' || c=='*' || c=='/'){
					state = 1;
					continue;
				} else {
					return false;
				}
			}
		}
		
		if (state == 0 || state == 3) {
			return true;
		} else {
			return false;
		}
	}
}
