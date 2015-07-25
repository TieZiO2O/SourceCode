package com.nciae.community.utils;

import java.util.TimerTask;

import javax.servlet.ServletContext;

import com.nciae.community.dao.impl.HappyMomentDaoImpl;
import com.nciae.community.domain.HappyMoment;

public class HappyMomentTimerTask extends TimerTask {

	private HappyMomentDaoImpl happyMomentDaoImpl = null;
	
	private ServletContext application=null;
	
	public void setHappyMomentDaoImpl(HappyMomentDaoImpl happyMomentDaoImpl) {
		this.happyMomentDaoImpl = happyMomentDaoImpl;
	}

	public void setApplication(ServletContext application) {
		this.application = application;
	}

	@Override
	public void run() {
		
		//ArrayList<Object> happyMomentList=new ArrayList<Object>();
		HappyMoment happyMoment = new HappyMoment();
		
		try {
			happyMoment=happyMomentDaoImpl.queryRandomHappyMoment();
		} catch (Exception e) {
			e.printStackTrace();
		}
		//happyMomentList.add(happyMoment);
		//application.setAttribute("happyMomentList", happyMomentList);
		application.setAttribute("happyMoment", happyMoment);
	}

}
