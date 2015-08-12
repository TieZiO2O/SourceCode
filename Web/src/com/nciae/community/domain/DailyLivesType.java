package com.nciae.community.domain;

import java.util.ArrayList;

public class DailyLivesType {
	private int id;
	private String style;
	private String logoPath;
	@Override
	public String toString() {
		return "DailyLivesType [id=" + id + ", style=" + style + ", logoPath="
				+ logoPath + ", dailyLivesLists=" + dailyLivesLists
				+ ", serviceType=" + serviceType + "]";
	}
	public String getLogoPath() {
		return logoPath;
	}
	public void setLogoPath(String logoPath) {
		this.logoPath = logoPath;
	}
	private ArrayList<DailyLives> dailyLivesLists;
	private String serviceType;
	
	public String getStyle() {
		return style;
	}
	public void setStyle(String style) {
		this.style = style;
	}
	public String getServiceType() {
		return serviceType;
	}
	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public ArrayList<DailyLives> getDailyLivesLists() {
		return dailyLivesLists;
	}
	public void setDailyLivesLists(ArrayList<DailyLives> dailyLivesLists) {
		this.dailyLivesLists = dailyLivesLists;
	}
}
