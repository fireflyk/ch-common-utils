package com.codinghero.util;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;

import com.codinghero.util.entity.AutoArrayList;

/**
 * Bean utility. Support deep copy.
 * 
 * @author liutong01
 * 
 */
public final class BeanUtils {

	/**
	 * new instance quietly, not throw exception.<br/>
	 * u should test your own program pass on runtime, not only for compile.
	 * 
	 * @param clazz
	 * @return
	 */
	public static <T> T newInstance(Class<T> clazz){
		try {
			return clazz.newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
			return null;
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * new instance quietly, not throw exception.<br/>
	 * u should test your own program pass on runtime, not only for compile.
	 * 
	 * @param className
	 * @return
	 */
	public static Object newInstance(String className){
		try {
			return newInstance(Class.forName(className));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * create an instance, and set null to all non-primitive fields
	 * 
	 * @param clazz
	 * @return
	 */
	public static <T> T newEmptyInstance(Class<T> clazz){
		try {
			T t = newInstance(clazz);
			PropertyDescriptor[] pds = PropertyUtils.getPropertyDescriptors(clazz);
			if (pds != null) {
				for (PropertyDescriptor pd : pds) {
					if (pd.getWriteMethod() != null
							& !pd.getPropertyType().isPrimitive()) {
						PropertyUtils.setProperty(t, pd.getName(), null);
					}
				}
			}
			return t;
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			return null;
		} catch (InvocationTargetException e) {
			e.printStackTrace();
			return null;
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 
	 * 
	 * @param bean
	 * @param field
	 * @return
	 */
	public static Class<?> getPropertyType(Object bean, String field) {
		try {
			return PropertyUtils.getPropertyType(bean, field);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} catch (InvocationTargetException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 
	 * 
	 * @param orig
	 * @param field
	 * @return
	 */
	public static Object getPropertyValue(Object orig, String field) {
		try {
			return PropertyUtils.getSimpleProperty(orig, field);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} catch (InvocationTargetException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	public static void setProperty(Object bean, String name, Object value) {
		try {
			PropertyUtils.setProperty(bean, name, value);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
	}
	
	public static void setSimpleProperty(Object bean, String name, Object value) {
		try {
			PropertyUtils.setSimpleProperty(bean, name, value);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * copy object to object
	 * 
	 * @param dest
	 * @param orig
	 */
	public static void copyProperties(Object orig, Object dest){
		try {
			if (orig != null)
				PropertyUtils.copyProperties(dest, orig);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
	}

	/**
	 * copy an object & return a new object
	 * 
	 * @param orig
	 * @param destClazz
	 * @return
	 */
	public static <T> T copyProperties(Object orig, Class<T> destClazz) {
		T dest = newInstance(destClazz);
		copyProperties(orig, dest);
		return dest;
	}
	
	/**
	 * copy map an object & return a new object
	 * 
	 * @param orig
	 * @param destClazz
	 * @return
	 */
	public static <T> T copyProperties(Map<?, ?> orig, Class<T> destClazz){
		T dest = newInstance(destClazz);
		copyProperties(orig, dest);
		return dest;
	}
	
	/**
	 * copy map to object
	 * 
	 * @param dest
	 * @param orig
	 */
	public static void copyProperties(Map<?, ?> orig, Object dest){
		try {
			org.apache.commons.beanutils.BeanUtils.populate(dest, orig);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}

	/**
	 * copy object to map & return a new map
	 * 
	 * @param orig
	 * @return
	 */
	public static Map<?, ?> copyProperties(Object orig){
		try {
			return PropertyUtils.describe(orig);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			return null;
		} catch (InvocationTargetException e) {
			e.printStackTrace();
			return null;
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static void copyNotNullToNull(Object orig, Object dest) {
		try {
			if (orig != null) {
				new CopyNotNullToNull().copyProperties(dest, orig);
			}
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}

	public static void copyNotNullToDest(Object orig, Object dest) {
		try {
			if (orig != null) {
				new CopyNotNullToDest().copyProperties(dest, orig);
			}
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}

	public static <T1, T2> List<T2> copyList(List<T1> origList, Class<T2> destClazz) {
		if (origList == null)
			return null;
		List<T2> resultList = new ArrayList<T2>();
		for (T1 orig : origList) {
			resultList.add(copyProperties(orig, destClazz));
		}
		return resultList;
	}

	public static <T> List<T> copyAutoArrayList(List<T> list, Class<T> clazz) {
		if (list == null)
			return null;
		List<T> resultList = new AutoArrayList<T>(clazz);
		for (T t : list) {
			resultList.add(t);
		}
		return resultList;
	}
}
