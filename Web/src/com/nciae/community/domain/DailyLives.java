package com.nciae.community.domain;

import java.util.ArrayList;
import java.util.Date;

public class DailyLives {
	private int id;
	/*
	 * 帖子用户的id
	 * */
	private String uid;
	private String guid;
	private String title;
	private String content;
	private String phone;
	private Date createDate;
	private ArrayList<String> images;
	/*private ArrayList<String> styles;*/
	private String customerNotice;
	private String address;
	private int dailyLivesId;
	public ArrayList<String> getImages() {
		return images;
	}
	public void setImages(ArrayList<String> images) {
		this.images = images;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	
	@Override
	public String toString() {
		return "DailyLives [id=" + id + ", uid=" + uid + ", guid=" + guid
				+ ", title=" + title + ", content=" + content + ", phone="
				+ phone + ", createDate=" + createDate + ", images=" + images
				+ ", customerNotice=" + customerNotice + ", address=" + address
				+ ", dailyLivesId=" + dailyLivesId + "]";
	}
	public String getCustomerNotice() {
		return customerNotice;
	}
	public void setCustomerNotice(String customerNotice) {
		this.customerNotice = customerNotice;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getDailyLivesId() {
		return dailyLivesId;
	}
	public void setDailyLivesId(int dailyLivesId) {
		this.dailyLivesId = dailyLivesId;
	}

}
