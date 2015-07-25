package com.nciae.community.dao;

import java.util.ArrayList;

import com.nciae.community.domain.Community;
import com.nciae.community.domain.ShopImg;
import com.nciae.community.domain.Users;

public interface UsersDao {

	/**
	 * 管理员(或商户)用户登录
	 * @param name   用户名
	 * @param password  密码
	 * @return   是否登录成功  登录成功就返回用户的role   
	 */
	public String login(String name,String password) throws Exception;
	/**
	 * 管理员退出登录
	 * @param name
	 * @return
	 * @throws Exception
	 */
	public String logout(String name) throws Exception;
	/**
	 * 删除商户
	 * @param id
	 * @return
	 */
	public boolean deleteShopUser(Integer id) throws Exception;
	
	/**
	 * 添加商户
	 * @param user
	 * @return
	 */
	public boolean addShopUser(Users user) throws Exception;
	/**
	 * 
	 * @param id
	 * @param path
	 * @return
	 * @throws Exception
	 */
	public boolean addShopLogo(Integer id,String path) throws Exception;
	/**
	 * 查询所有商户
	 * @return
	 */
	public ArrayList<Users> queryAllShopUser() throws Exception;
	/**
	 * 通过所在城市查询所有商户
	 * @return
	 */
	public ArrayList<Users> queryShopUserByCityAndCommunity(String city,String community) throws Exception;
	
	public ArrayList<Users> queryUserByCCR(String city,String community, String role) throws Exception;
	/**
	 * 删除小区
	 * @param id
	 * @return
	 */
	public boolean deleteCommunity(Integer id) throws Exception;
	
	/**
	 * 添加小区
	 * @param community
	 * @return
	 */
	public boolean addCommunity(Community community) throws Exception;
	/**
	 * 查询所有小区
	 * @return
	 */
	public ArrayList<Community> queryAllCommunity() throws Exception;
	
	
	/**
	 * 查询商户信息
	 * @param id
	 * @return
	 */
	public Users selectShopUser(Integer id) throws Exception;
	
	/**
	 * 修改信息
	 * @param user
	 * @return
	 */
	public boolean updateShopUser(Users user,Integer id) throws Exception;
	/**
	 * 查询商户ID
	 * @param username
	 * @return
	 * @throws Exception 
	 */
	public int selectShopUserId(String username) throws Exception;
	/**
	 * 修改用户密码
	 * @param userpwd
	 * @param id
	 * @return
	 * @throws Exception 
	 */
	public boolean changePwd(Integer id,String userpwd) throws Exception;
	/**
	 * 修改商户图片
	 * @param photopath
	 * @param id
	 * @return
	 */
	public boolean uploadPhoto(Integer id,String photopath)throws Exception;
	/**
	 * 后台显示所有商家图片信息
	 * @param userid
	 * @return 
	 */
	public ArrayList<ShopImg> selectShopImg(int userid)throws Exception;
	/**
	 * 商家删除指定的图片
	 * @param photoid
	 * @return
	 */
	public boolean deleteShopImg(int photoid)throws Exception;
	/**
	 * 商家添加新的图片
	 * @param shopImg
	 * @return
	 */
	public boolean addShopImg(ShopImg shopimg)throws Exception;
	/**
	 * 限制商家上传图片张数
	 * @param userid
	 * @return
	 */
	public int limitUploadTime(int userid)throws Exception;
	
	/**
	 * 查询数据库中users表中的商户相关的小区
	 * @return
	 * @throws Exception
	 */
	public ArrayList<Object> queryMyCommunity() throws Exception;
	
	/**
	 * 通过city,community,shopType,contentName查询
	 * @param city
	 * @param community
	 * @param shopType
	 * @param contentName
	 * @return
	 * @throws Exception
	 */
	public ArrayList<Users> queryUserByCCSC(String city,String community, String shopType,String contentName) throws Exception;
	/**
	 * 通过手机号查询商户信息
	 * @param phone
	 * @return
	 * @throws Exception
	 */
	////////////////////////////////////////0601
	ArrayList<Users> queryUserByPhone(String phone) throws Exception;
	/////////////////////////0627
	/**
	 * 设置图片数量（LOGO+展示图片总数）
	 * @param id
	 * @param number
	 * @return
	 * @throws Exception
	 */
	public boolean setImgNum(int id,int number)throws Exception;
	/**
	 * 当用户图片数多于设置数是，删除shopimg表中的多余记录（从id小号开始删）
	 * @param userid
	 * @param number
	 * @return
	 * @throws Exception
	 */
	public boolean lxDelImgNum(int userid,int delnum) throws Exception;
	/**
	 * 返回users表中的imgNumber
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public int searchTotalNumlx(int id) throws Exception;
	////////////////////////0628
	/**
	 * 管理员重置密码
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public boolean lxResetPwd(int id,String pwd) throws Exception;
	/**
	 * 
	 * @param phone
	 * @return
	 * @throws Exception
	 */
	public boolean lxExistsPhone(String phone) throws Exception;
	/**
	 *0629
	 * @param photoid
	 * @return
	 * @throws Exception
	 */
	public String lxFindShopImgUrl(int photoid) throws Exception;
	/**
	 * 0703
	 * @param id
	 * @param pwd
	 * @return
	 * @throws Exception
	 */
	public boolean lxChangePwd(Integer id, String pwd) throws Exception;
	/**
	 * 0703
	 * @param id
	 * @param Pwd
	 * @return
	 * @throws Exception
	 */
	public boolean chkAdminPwd(Integer id, String Pwd) throws Exception;
	/**
	 * 0703
	 * @param userName
	 * @return
	 * @throws Exception
	 */
	public boolean lxExistsUserName(String userName) throws Exception;
}
