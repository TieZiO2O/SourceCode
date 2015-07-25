package com.nciae.community.dao;

import java.util.ArrayList;

import com.nciae.community.domain.HappyMoment;

public interface HappyMomentDao {
	
	/**
	 * 添加开心一刻
	 * @param dailyAbc
	 * @return
	 * @throws Exception
	 */
	public boolean add(HappyMoment happyMoment) throws Exception;
	/**
	 * 删除开心一刻
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public boolean delete(Integer id) throws Exception;
	/**
	 * 查询开心一刻（获取最新的）
	 * @return
	 * @throws Exception
	 */
	public HappyMoment queryHappyMoment() throws Exception;
	/**
	 * 查询全部开心一刻
	 * @return
	 * @throws Exception
	 */
	public ArrayList<HappyMoment> queryAll() throws Exception;
	/**
	 * 查询开心一刻（随机）
	 * @return
	 * @throws Exception
	 */
	public HappyMoment queryRandomHappyMoment() throws Exception;
}
