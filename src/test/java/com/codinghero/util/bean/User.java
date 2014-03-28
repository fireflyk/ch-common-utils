package com.codinghero.util.bean;

import java.util.Map;

public class User {
	private String name;
	private Role role;
	
	private Integer[] friendIds;
	
	private Map<Integer, String> friends;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Integer[] getFriendIds() {
		return friendIds;
	}

	public void setFriendIds(Integer[] friendIds) {
		this.friendIds = friendIds;
	}

	public Map<Integer, String> getFriends() {
		return friends;
	}

	public void setFriends(Map<Integer, String> friends) {
		this.friends = friends;
	}

}