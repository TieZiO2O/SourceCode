package com.nciae.community.domain;

public class IntegralType {
	private int id;
	private String operateType;
	private int fraction;
	@Override
	public String toString() {
		return "IntegralType [id=" + id + ", operateType=" + operateType
				+ ", fraction=" + fraction + ", addOrdecrease=" + addOrdecrease
				+ ", extened=" + extened + "]";
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getOperateType() {
		return operateType;
	}
	public void setOperateType(String operateType) {
		this.operateType = operateType;
	}
	public int getFraction() {
		return fraction;
	}
	public void setFraction(int fraction) {
		this.fraction = fraction;
	}
	public boolean isAddOrdecrease() {
		return addOrdecrease;
	}
	public void setAddOrdecrease(boolean addOrdecrease) {
		this.addOrdecrease = addOrdecrease;
	}
	public String getExtened() {
		return extened;
	}
	public void setExtened(String extened) {
		this.extened = extened;
	}
	private boolean addOrdecrease;
	private String extened;
}
