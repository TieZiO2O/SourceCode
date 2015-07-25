package com.nciae.community.domain;

public class SessionContainer extends Users {
	
	private String sessionId;

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	
	/*
	 * 这个类主要记录登录成功后，将用户信息放入该对象保存，
	 * 并保存在session中“SessionContainer”属性中，
	 * 该类主要属性有private String sessionId;
		private String userId;
		private String loginname;
		private String username;
		private String deptId;
		private String deptName;
		private String deptNo;
		private String deptCode;
		private Integer roleId;
		private String roleName;
	 * 
	 * 
	 * 
	 * 
	 */
}
