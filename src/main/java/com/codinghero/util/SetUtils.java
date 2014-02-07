package com.codinghero.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SetUtils {
	public static <T> Set<T> toSet(Collection<T> c) {
		Set<T> set = new HashSet<T>();
		for (T t : c) {
			set.add(t);
		}
		return set;
	}

	public static <T> List<T> toList(Set<T> set) {
		List<T> list = new ArrayList<T>();
		for (T t : set) {
			list.add(t);
		}
		return list;
	}
}
