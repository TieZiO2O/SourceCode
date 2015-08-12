package com.nciae.community.domain;

import org.springframework.beans.factory.annotation.Value;

/*
 * 积分Model
 * */
public class Integral {
	/*
	 *积分Id 
	 * */
	private int id;
	/*
	 *所得分数
	 * */
	private int fraction;
	/*
	 *用户Id
	 * */
	private int userId;
	/*
	 *该积分使用在用
	 * */
	private boolean isUsed=true;
	/*
	 * 判断该分数是要加还是要减
	 * true,代表加分数
	 * false,代表减分数
	 * */
	private boolean addOrFract;
	
	@Override
	public String toString() {
		return "Integral [id=" + id + ", fraction=" + fraction + ", userId="
				+ userId + ", isUsed=" + isUsed + ", addOrFract=" + addOrFract
				+ "]";
	}
	public boolean isAddOrFract() {
		return addOrFract;
	}
	public void setAddOrFract(boolean addOrFract) {
		this.addOrFract = addOrFract;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getFraction() {
		return fraction;
	}
	public void setFraction(int fraction) {
		this.fraction = fraction;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public boolean isUsed() {
		return isUsed;
	}
	public void setUsed(boolean isUsed) {
		this.isUsed = isUsed;
	}
	
}
