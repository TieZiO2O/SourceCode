package com.nciae.community.domain;

import java.util.ArrayList;

public class DailyLives_SeperateTypes {
	private ArrayList<DailyLivesType> localservice;
	private ArrayList<DailyLivesType> allcityservice;
	public ArrayList<DailyLivesType> getLocalservice() {
		return localservice;
	}
	public void setLocalservice(ArrayList<DailyLivesType> localservice) {
		this.localservice = localservice;
	}
	public ArrayList<DailyLivesType> getAllcityservice() {
		return allcityservice;
	}
	
	public void setAllcityservice(ArrayList<DailyLivesType> allcityservice) {
		this.allcityservice = allcityservice;
	}
	@Override
	public String toString() {
		return "Integral_SeperateTypes [localservice=" + localservice
				+ ", allcityservice=" + allcityservice + "]";
	}
}
