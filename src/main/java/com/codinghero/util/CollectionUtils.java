package com.codinghero.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public final class CollectionUtils {
	
	public static <T> boolean isEmpty(Collection<T> coll) {
		if (coll == null || coll.size() == 0)
			return true;
		else
			return false;
	}
	
	public static <T> boolean contains(Collection<T> father, T son) {
		if (father == null)
			return false;
		for (T f : father) {
			if (f == null) {
				if (son != null)
					continue;
				else
					return true;
			}
			if (f.equals(son))
				return true;
		}
		return false;
	}
	
	public static <T> int getSameElementQuantity(Collection<T> coll1, Collection<T> coll2) {
		if (isEmpty(coll1) || isEmpty(coll2))
			return 0;
		
		int quantity = 0;
		for (T t1 : coll1) {
			if (t1 == null)
				continue;
			for (T t2 : coll2) {
				if (t1.equals(t2))
					quantity++;
			}
		}
		return quantity;
	}

	public static <T> void removeDuplicate(Collection<T> coll) {
		Map<T, Object> map = new HashMap<T, Object>();
		Iterator<T> iter = coll.iterator();
		while (iter.hasNext()) {
			T t = iter.next();
			if(!map.containsKey(t)) {
				map.put(t, null);
			} else {
				iter.remove();
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	public static <T> Collection<T> union(final Collection<T> coll1, final Collection<T> coll2, final Class<?> resultClazz) {
		if (!resultClazz.isAssignableFrom(Collection.class))
			throw new IllegalArgumentException();
		try {
			Collection<T> result = (Collection<T>) resultClazz.newInstance();
			result.addAll(coll1);
			for(T t : coll2) {
				if(!result.contains(t)) {
					result.add(t);
				}
			}
			return result;
		} catch (InstantiationException e) {
			throw new IllegalArgumentException(e);
		} catch (IllegalAccessException e) {
			throw new IllegalArgumentException(e);
		}
	}
	
	@SuppressWarnings("unchecked")
	public static <T> Collection<T> unionAll(final Collection<T> coll1, final Collection<T> coll2, final Class<?> resultClazz) {
		if (!resultClazz.isAssignableFrom(Collection.class))
			throw new IllegalArgumentException();
		try {
			Collection<T> result = (Collection<T>) resultClazz.newInstance();
			result.addAll(coll1);
			result.addAll(coll2);
			return result;
		} catch (InstantiationException e) {
			throw new IllegalArgumentException(e);
		} catch (IllegalAccessException e) {
			throw new IllegalArgumentException(e);
		}
	}
	
	@SuppressWarnings("unchecked")
	public static <T> Collection<T> intersect(final Collection<T> coll1, final Collection<T> coll2, final Class<?> resultClazz) {
		if (!resultClazz.isAssignableFrom(Collection.class))
			throw new IllegalArgumentException();
		try {
			Collection<T> result = (Collection<T>) resultClazz.newInstance();
			for(T t : coll1) {
				if(coll2.contains(t)) {
					result.add(t);
				}
			}
			return result;
		} catch (InstantiationException e) {
			throw new IllegalArgumentException(e);
		} catch (IllegalAccessException e) {
			throw new IllegalArgumentException(e);
		}
	}
	
	public static <T extends HasKey> Map<String, T> toMap(List<T> list) {
		Map<String, T> map = new HashMap<String, T>();
		for (T t : list) {
			map.put(t.getKey(), t);
		}
		return map;
	}
	
	public static <K extends Object, V extends HasKey> List<V> toList(Map<K, V> map) {
		List<V> list = new ArrayList<V>();
		for (Entry<K, V> e : map.entrySet()) {
			list.add(e.getValue());
		}
		return list;
	}

	public static <T extends HasKey> List<T> removeEmptyItem(List<T> list, Class<T> clazz) {
		if (list == null)
			return null;
		T emptyItem = BeanUtils.newInstance(clazz);
		Iterator<T> iter = list.iterator();
		while (iter.hasNext()) {
			T t = iter.next();
			if (emptyItem != null && t != null)
				// both key null
				if (emptyItem.getKey() == null && t.getKey() == null)
					iter.remove();
				// key not null & equal
				else if (emptyItem.getKey() != null && t.getKey() != null 
						&& emptyItem.getKey().equals(t.getKey()))
					iter.remove();
		}
		return list;
	}
}
