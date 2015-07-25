package com.nciae.community.domain;

import java.io.Serializable;

public class Users implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String ROLE_ADMIN="admin";
	public static final String ROLE_SHOP="shop";
	public static final String ROLE_PROPERTY="property";//物业
	
	private Integer id;
	private String userName;
	private String userPwd;
	private String realName;//商户点名/物业名
	private String sex;
	private String linkman;
	private String phone;
	private String email;
	private String emailPassword;
	private String postCode;
	private String province;
	private String city;
	private String district;
	private String community;
	private String address;
	private String role;
	private String contentName;//子级目录名称（服务类型)
	private String shopLogo;//物业头像(物业只有一张图片)   或  店面头像(1张店面+3张 其他 )
	private Byte isLogin;
	private Byte shopType;//商户类型
	private String shopInfo;//店面介绍
	private String customerNotice;//顾客须知
	private String aboutUs;//物业总部简介或商户简介
	private Integer imgNumber;//控制上传图片数（是算上logo是总数）默认4
	
	public Integer getImgNumber() {
		return imgNumber;
	}
	public void setImgNumber(Integer imgNumber) {
		this.imgNumber = imgNumber;
	}
	public String getAboutUs() {
		return aboutUs;
	}
	public void setAboutUs(String aboutUs) {
		this.aboutUs = aboutUs;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPwd() {
		return userPwd;
	}
	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getLinkman() {
		return linkman;
	}
	public void setLinkman(String linkman) {
		this.linkman = linkman;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getEmailPassword() {
		return emailPassword;
	}
	public void setEmailPassword(String emailPassword) {
		this.emailPassword = emailPassword;
	}
	public String getPostCode() {
		return postCode;
	}
	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getCommunity() {
		return community;
	}
	public void setCommunity(String community) {
		this.community = community;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getContentName() {
		return contentName;
	}
	public void setContentName(String contentName) {
		this.contentName = contentName;
	}
	public String getShopLogo() {
		return shopLogo;
	}
	public void setShopLogo(String shopLogo) {
		this.shopLogo = shopLogo;
	}
	public Byte getIsLogin() {
		return isLogin;
	}
	public void setIsLogin(Byte isLogin) {
		this.isLogin = isLogin;
	}
	public Byte getShopType() {
		return shopType;
	}
	public void setShopType(Byte shopType) {
		this.shopType = shopType;
	}
	public String getShopInfo() {
		return shopInfo;
	}
	public void setShopInfo(String shopInfo) {
		this.shopInfo = shopInfo;
	}
	public String getCustomerNotice() {
		return customerNotice;
	}
	public void setCustomerNotice(String customerNotice) {
		this.customerNotice = customerNotice;
	}
}
