package com.codinghero.util;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class MapUtils {
	public static <K, V> Map<V, K> reverse(Map<K, V> map) {
		if (map == null)
			return null;
		@SuppressWarnings("unchecked")
		Map<V, K> result = BeanUtils.newInstance(map.getClass());
		Iterator<Entry<K, V>> iter = map.entrySet().iterator();
		while (iter.hasNext()) {
			Entry<K, V> e = iter.next();
			result.put(e.getValue(), e.getKey());
		}
		return result;
	}
}
