package com.nciae.community.dao;

import java.util.ArrayList;

import com.nciae.community.domain.Bulletin;
import com.nciae.community.domain.Property;
import com.nciae.community.domain.Users;

public interface PropertyDao {
	/**
	 * 物业用户登录
	 * @param name   用户名
	 * @param password  密码
	 * @return   是否登录成功  登录成功就返回用户的role   
	 */
	public String property_login(String name,String password) throws Exception;
	/**
	 * 查询商户信息
	 * @param id
	 * @return
	 */
	public Users selectPropertyInfo(Integer id) throws Exception;
	
	/**
	 * 修改信息
	 * @param property
	 * @return
	 */
	public boolean updatePropertyInfo(Users property,Integer id) throws Exception;
	/**
	 * 查询商户ID
	 * @param propertyname
	 * @return
	 * @throws Exception 
	 */
	public int selectPropertyId(String propertyname) throws Exception;
	/**
	 * 修改用户密码
	 * @param propertypwd
	 * @param id
	 * @return
	 * @throws Exception 
	 */
	public boolean property_changePwd(Integer id,String propertypwd) throws Exception;
	/**
	 * 修改商户图片
	 * @param photopath
	 * @param id
	 * @return
	 */
	public boolean property_uploadPhoto(Integer id,String photopath)throws Exception;
	/**
	 * 查询所有公告信息
	 * @param communityid;
	 * @return
	 */
	public ArrayList<Object> selectBulletin(int communityid)throws Exception;
	/**
	 * 添加公告
	 * @param bulletin
	 * @return
	 * @throws Exception
	 */
	public boolean addBulletin(Bulletin bulletin)throws Exception;
	/**
	 * 删除公告
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public boolean deleteBulletin(int bulletinid)throws Exception;
	/**
	 *查询小区id
	 *@param communityName
	 *@return
	 *
	 */
	public int selectCommunityId(String propertyname)throws Exception;
	/**
	 * 查询用户评论
	 * @param userid
	 * @return 
	 */
	public ArrayList<Object> selectComments(int userid)throws Exception;
	/**
	 * 根据用户ID查询用户名
	 * @param memid
	 * @return 
	 */
	public String selectMemName(int memid)throws Exception;
	/////////////////////////////////////////////////////////////////lx
	/**
	 * 删除物业
	 * @param id
	 * @return
	 */
	public boolean deleteProperty(Integer id) throws Exception;
	
	/**
	 * 添加物业
	 * @param user
	 * @return
	 */
	public boolean addProperty(Users user) throws Exception;
	/**
	 * 通过city,community查询
	 * @param city
	 * @param community
	 * @return
	 * @throws Exception
	 */
	public ArrayList<Users> queryPropertyByCC(String city,String community) throws Exception;
	/**
	 * 查询数据库中users表中的物业相关的小区
	 * @return
	 * @throws Exception
	 */
	public ArrayList<Object> queryMyCommunity() throws Exception;
	////////////////////0627
	/**
	 * 删除评论
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public boolean deleteComments(int bulletinid)throws Exception;

}
