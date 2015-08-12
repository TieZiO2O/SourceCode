package com.nciae.community.domain;

import java.util.ArrayList;

public class IntegralCommodity {
	private int id;
	private String guid;
	private String title;
	private String content;
	private int limitAmount;
	private int fraction;
	private String phone;
	private String startTime;
	private String endTime;
	private boolean isUsed;
	private ArrayList<String> imgs;
	private String img;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "IntegralCommodity [id=" + id + ", guid=" + guid + ", title="
				+ title + ", content=" + content + ", limitAmount="
				+ limitAmount + ", fraction=" + fraction + ", phone=" + phone
				+ ", startTime=" + startTime + ", endTime=" + endTime
				+ ", isUsed=" + isUsed + ", imgs=" + imgs + ", img=" + img
				+ "]";
	}
	public int getFraction() {
		return fraction;
	}
	public void setFraction(int fraction) {
		this.fraction = fraction;
	}
	public String getGuid() {
		return guid;
	}
	public void setGuid(String guid) {
		this.guid = guid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getLimitAmount() {
		return limitAmount;
	}
	public void setLimitAmount(int limitAmount) {
		this.limitAmount = limitAmount;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public boolean isUsed() {
		return isUsed;
	}
	public void setUsed(boolean isUsed) {
		this.isUsed = isUsed;
	}
	public ArrayList<String> getImgs() {
		return imgs;
	}
	public void setImgs(ArrayList<String> imgs) {
		this.imgs = imgs;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
}
