package com.codinghero.util;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtilsBean;

public class CopyNotNullToDest extends BeanUtilsBean {

	public void copyProperty(Object dest, String name, Object value) throws IllegalAccessException, InvocationTargetException {
		if (value != null)
			super.copyProperty(dest, name, value);
	}

}
