package com.nciae.community.utils;

import java.util.ArrayList;
import java.util.TimerTask;

import javax.servlet.ServletContext;

import com.nciae.community.dao.impl.DailyAbcDaoImpl;
import com.nciae.community.domain.DailyAbc;

public class DailyAbcTimerTask extends TimerTask {

	private DailyAbcDaoImpl dailyAbcDaoImpl = null;
	
	private ServletContext application=null;
	
	public void setDailyAbcDaoImpl(DailyAbcDaoImpl dailyAbcDaoImpl) {
		this.dailyAbcDaoImpl = dailyAbcDaoImpl;
	}

	public void setApplication(ServletContext application) {
		this.application = application;
	}

	@Override
	public void run() {
		
		//ArrayList<Object> dailyAbcList=new ArrayList<Object>();
		DailyAbc dailyAbc = new DailyAbc();
		
		try {
			dailyAbc=dailyAbcDaoImpl.queryRandomDailyAbc();
		} catch (Exception e) {
			e.printStackTrace();
		}
		//dailyAbcList.add(dailyAbc);
		//application.setAttribute("dailyAbcList", dailyAbcList);
		application.setAttribute("dailyAbc", dailyAbc);
	}

}
