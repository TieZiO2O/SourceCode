package com.nciae.community.domain;

public class Advertisement {

	private Integer id;
	private String adImagURL;//广告图片链接
	private String adTitle;//广告标题
	private String adContent;//广告内容
	private Integer shopperId;//广告所属商家id
	private String communityList;//商家要发布的小区id列表（空代表全城小区）
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getAdImagURL() {
		return adImagURL;
	}
	public void setAdImagURL(String adImagURL) {
		this.adImagURL = adImagURL;
	}
	
	public String getAdTitle() {
		return adTitle;
	}
	public void setAdTitle(String adTitle) {
		this.adTitle = adTitle;
	}
	
	public String getAdContent() {
		return adContent;
	}
	public void setAdContent(String adContent) {
		this.adContent = adContent;
	}
	public Integer getShopperId() {
		return shopperId;
	}
	public void setShopperId(Integer shopperId) {
		this.shopperId = shopperId;
	}
	public String getCommunityList() {
		return communityList;
	}
	public void setCommunityList(String communityList) {
		this.communityList = communityList;
	}

}
