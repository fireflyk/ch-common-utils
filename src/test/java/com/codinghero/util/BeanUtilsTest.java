package com.codinghero.util;

import static org.junit.Assert.assertEquals;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import com.codinghero.util.bean.Customer;
import com.codinghero.util.bean.NoGetSetUser;
import com.codinghero.util.bean.Role;
import com.codinghero.util.bean.User;
import com.codinghero.util.bean.UserForm;

public class BeanUtilsTest {

	private User origUser;
	private Map<String, Object> userMap;

	public BeanUtilsTest() {

	}

	@Before
	public void before() {
		origUser = new User();
		origUser.setName("userName");
		Role role = new Role();
		role.setName("roleName");
		origUser.setRole(role);
		origUser.setFriendIds(new Integer[] { 1, 3, 5 });
		
		Map<Integer, String> friends = new HashMap<Integer, String>();
		friends.put(1, "friend1");
		origUser.setFriends(friends);

		userMap = new HashMap<String, Object>();
		userMap.put("name", "userName");
		Map<String, Object> roleMap =  new HashMap<String, Object>();
		roleMap.put("name", "roleName");
		userMap.put("role", roleMap);
	}

	@Test
	public void testCopySameBean() throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		User destUser = new User();
		org.apache.commons.beanutils.BeanUtils.copyProperties(destUser, origUser);
		assertEquals("userName", destUser.getName());
		assertEquals("roleName", destUser.getRole().getName());
	}

	@Test
	public void testCopySameFieldName() throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		UserForm destUser = new UserForm();
		try {
			org.apache.commons.beanutils.BeanUtils.copyProperties(destUser, origUser);
			Assert.fail();
			assertEquals("userName", destUser.getName());
			assertEquals("roleName", destUser.getRole().getName());
		} catch (IllegalArgumentException e) {
		}
	}
	


	@Test
	public void testCopyNoGetSetBean() throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		NoGetSetUser origUser = new NoGetSetUser("userName");
		NoGetSetUser destUser = new NoGetSetUser();
		org.apache.commons.beanutils.BeanUtils.copyProperties(destUser, origUser);
		assertEquals(null, destUser.specialGet());
	}
	

	@Test
	public void testCopyNullField() throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		User origUser = new User();
		origUser.setName(null);
		
		User destUser = new User();
		destUser.setName("userName");
		
		org.apache.commons.beanutils.BeanUtils.copyProperties(destUser, origUser);
		assertEquals(null, destUser.getName());
	}

	@Test
	public void testCopyBeanToMap() {
		Map<?, ?> map = BeanUtils.copyProperties(origUser);
		Role role = (Role) map.get("role");
		assertEquals("userName", map.get("name"));
		assertEquals("roleName", role.getName());
	}
	
	@Test
	public void testGetIndexedProperty() throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		Assert.assertEquals("3", org.apache.commons.beanutils.BeanUtils.getIndexedProperty(origUser, "friendIds[1]"));
	}
	
	//@Test
	public void testGetMappedProperty() throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		Assert.assertEquals("friend1", org.apache.commons.beanutils.BeanUtils.getMappedProperty(origUser, "friends('1')"));
	}

	//@Test
	public void testCopyMapToBean() {
		User user = BeanUtils.copyProperties(userMap, User.class);
		assertEquals(user.getName(), "userName");
		assertEquals(user.getRole().getName(), "roleName");
	}
	
	public void testNewInstance() {
		Customer customer = BeanUtils.newInstance(Customer.class);
		Assert.assertEquals(new Integer(1), customer.getAge());
	}
}
