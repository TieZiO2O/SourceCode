package com.nciae.community.utils;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.nciae.community.dao.impl.DailyAbcDaoImpl;

public class CommunityServletContextListener implements ServletContextListener {

	// 时间间隔(一天)
	private static final long PERIOD_DAY = 24 * 60 * 60 * 1000;

	private ApplicationContext context = null;

	//private DailyAbcDaoImpl dailyAbcDaoImpl = null;

	private DailyAbcTimerTask dailyAbcTimerTask = null;
	
	private HappyMomentTimerTask happyMomentTimerTask = null;

	@Override
	public void contextDestroyed(ServletContextEvent se) {
		// TODO Auto-generated method stub

	}

	@Override
	public void contextInitialized(ServletContextEvent se) {
		System.out
				.println("..........CommunityServletContextListener.contextInitialized().......");
		context = WebApplicationContextUtils.getWebApplicationContext(se
				.getServletContext());
		//dailyAbcDaoImpl = (DailyAbcDaoImpl) context.getBean("dailyAbcDaoImpl");

		// 通过Spring配置文件,获取线程任务
		dailyAbcTimerTask = (DailyAbcTimerTask) context
				.getBean("dailyAbcTimerTask");
		dailyAbcTimerTask.setApplication(se.getServletContext());
		
		happyMomentTimerTask = (HappyMomentTimerTask) context
				.getBean("happyMomentTimerTask");
		happyMomentTimerTask.setApplication(se.getServletContext());

		// 设置执行时间
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, 6); //凌晨6点
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		Date date=calendar.getTime(); //第一次执行定时任务的时间
		//如果第一次执行定时任务的时间 小于当前的时间
		//此时要在 第一次执行定时任务的时间加一天，以便此任务在下个时间点执行。如果不加一天，任务会立即执行。
		//0611屏蔽
		/*if (date.before(new Date())) {
			date = this.addDay(date, 1);
		}*/

		//new Timer().schedule(dailyAbcTimerTask, 0, 24 * 3600 * 1000);
		new Timer().schedule(dailyAbcTimerTask, date, PERIOD_DAY);//yes
		//new Timer().schedule(dailyAbcTimerTask, 0, 1000*30);//30s
		//new Timer().schedule(dailyAbcTimerTask, date, 1000*30);//30s
		
		
		new Timer().schedule(happyMomentTimerTask, date, PERIOD_DAY);//yes

	}

	// 增加或减少天数
	private Date addDay(Date date, int num) {
		Calendar startDT = Calendar.getInstance();
		startDT.setTime(date);
		startDT.add(Calendar.DAY_OF_MONTH, num);
		return startDT.getTime();
	}

}
