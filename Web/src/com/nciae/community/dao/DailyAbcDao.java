package com.nciae.community.dao;

import java.util.ArrayList;

import com.nciae.community.domain.DailyAbc;

public interface DailyAbcDao {
	
	/**
	 * 添加每日常识
	 * @param dailyAbc
	 * @return
	 * @throws Exception
	 */
	public boolean add(DailyAbc dailyAbc) throws Exception;
	/**
	 * 删除每日常识
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public boolean delete(Integer id) throws Exception;
	/**
	 * 查询每日常识（获取最新的）
	 * @return
	 * @throws Exception
	 */
	public DailyAbc queryDailyAbc() throws Exception;
	/**
	 * 查询全部每日常识
	 * @return
	 * @throws Exception
	 */
	public ArrayList<DailyAbc> queryAll() throws Exception;
	/**
	 * 查询每日常识（随机）
	 * @return
	 * @throws Exception
	 */
	public DailyAbc queryRandomDailyAbc() throws Exception;
}
