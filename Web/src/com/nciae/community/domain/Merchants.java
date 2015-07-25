package com.nciae.community.domain;

import java.util.ArrayList;

/**
 * 商家类
 * @author Mr Rui
 */
public class Merchants {
	private int id;//商家id
	private String realName;//商家名称
	private String shopLogo;//商家图片
	private ArrayList<ShopImg> imgUrl;//商家的另外3张图片
	//image[] 数组存放URL时注意事项
	//默认第一张为商家头像，若没有，请赋值null，如果还有其他照片往后放,没有其他的就不放，返回null就可以了

	private String address;//商家地址（请勿过长）
	private String shopInfo;//商品信息（介绍自己卖什么，提供什么服务）
	private String customerNotice;//顾客须知（不论商家填什么，默认追加一句“联系时，请告诉我们是在悠然社区看到的”）
	private String phone;//商家电话
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
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
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getShopLogo() {
		return shopLogo;
	}
	public void setShopLogo(String shopLogo) {
		this.shopLogo = shopLogo;
	}
	public ArrayList<ShopImg> getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(ArrayList<ShopImg> imgUrl) {
		this.imgUrl = imgUrl;
	}
	
}
