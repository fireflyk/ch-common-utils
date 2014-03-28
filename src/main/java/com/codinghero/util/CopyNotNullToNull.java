package com.codinghero.util;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.PropertyUtils;

public class CopyNotNullToNull extends BeanUtilsBean {

	public void copyProperty(Object dest, String name, Object value) throws IllegalAccessException, InvocationTargetException {
		try {
			if (value != null && PropertyUtils.getProperty(dest, name) == null)
				super.copyProperty(dest, name, value);
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
	}
}
