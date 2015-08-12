package com.nciae.community.domain;

public class IntegralResult {
	private int uid;
	private int fraction;
	private String operateinfo;
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public int getFraction() {
		return fraction;
	}
	public void setFraction(int fraction) {
		this.fraction = fraction;
	}
	public String getOperateinfo() {
		return operateinfo;
	}
	@Override
	public String toString() {
		return "IntegralResult [uid=" + uid + ", fraction=" + fraction
				+ ", operateinfo=" + operateinfo + "]";
	}
	public void setOperateinfo(String operateinfo) {
		this.operateinfo = operateinfo;
	}
}
