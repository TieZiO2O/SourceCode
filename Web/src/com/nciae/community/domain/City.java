package com.nciae.community.domain;

public class City {

	private Integer id;
	private String code;
	private String city;
	private Byte isHot;//是否热门搜索城市
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public Byte getIsHot() {
		return isHot;
	}
	public void setIsHot(Byte isHot) {
		this.isHot = isHot;
	}
}
