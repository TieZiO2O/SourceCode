package com.nciae.community.domain;

public class Community {

	private Integer id;
	private String communityName;
	private String remark;
	private Byte isActive;//小区是否可用      0 不可用 1 可用     默认1
	private Integer cityId;
	//private Integer memId;
	private Integer likeCount;//点赞个数-----同意开通的个数
	private Byte opened;//小区 开通状态      0 未开通 1 已开通
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCommunityName() {
		return communityName;
	}
	public void setCommunityName(String communityName) {
		this.communityName = communityName;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Byte getIsActive() {
		return isActive;
	}
	public void setIsActive(Byte isActive) {
		this.isActive = isActive;
	}
	public Integer getCityId() {
		return cityId;
	}
	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}
	/*public Integer getMemId() {
		return memId;
	}
	public void setMemId(Integer memId) {
		this.memId = memId;
	}*/
	public Integer getLikeCount() {
		return likeCount;
	}
	public void setLikeCount(Integer likeCount) {
		this.likeCount = likeCount;
	}
	public Byte getOpened() {
		return opened;
	}
	public void setOpened(Byte opened) {
		this.opened = opened;
	}
}
