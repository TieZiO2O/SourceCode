package com.nciae.community.domain;

public class Bulletin {

	private Integer id;
	private String bulletinInfo;
	private String title;
	private String commitTime;
	private Integer communityId;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getBulletinInfo() {
		return bulletinInfo;
	}
	public void setBulletinInfo(String bulletinInfo) {
		this.bulletinInfo = bulletinInfo;
	}
	public Integer getCommunityId() {
		return communityId;
	}
	public void setCommunityId(Integer communityId) {
		this.communityId = communityId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getCommitTime() {
		return commitTime;
	}
	public void setCommitTime(String commitTime) {
		this.commitTime = commitTime;
	}
}
