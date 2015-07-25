package com.nciae.community.domain;

public class Category {

	private Integer id;
	private String contentName;
	private Integer parentId;
	private Byte isDisplay;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getContentName() {
		return contentName;
	}
	public void setContentName(String contentName) {
		this.contentName = contentName;
	}
	public Integer getParentId() {
		return parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	public Byte getIsDisplay() {
		return isDisplay;
	}
	public void setIsDisplay(Byte isDisplay) {
		this.isDisplay = isDisplay;
	}
}
