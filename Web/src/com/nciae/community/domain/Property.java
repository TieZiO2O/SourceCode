package com.nciae.community.domain;

/**
 * 物业类
 * @author Mr Rui
 *
 */
public class Property {
	private String id;//物业id
	private String realName;//物业名称
	private String shopLogo;//图片，只有一张，没有就返回null
	private String phone;//物业值班电话
	private String address;//物业在小区的驻扎地址
	private String aboutUs;//物业简介
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getShopLogo() {
		return shopLogo;
	}
	public void setShopLogo(String shopLogo) {
		this.shopLogo = shopLogo;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getAboutUs() {
		return aboutUs;
	}
	public void setAboutUs(String aboutUs) {
		this.aboutUs = aboutUs;
	}
	
	
}
