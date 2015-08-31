package com.nciae.community.dao;

import java.util.ArrayList;

import com.nciae.community.domain.Merchants;
import com.nciae.community.domain.Property;
import com.nciae.community.domain.Users;

public interface MemberDao {
/**
 * 小区用户注册
 * @param Member member
 * @return 
 */
public String regist(String membername,String mempassword)throws Exception;
/**
 * 检查小区用户名的唯一
 * @param membername
 * @return
 */
public boolean chkMemberName(String membername)throws Exception;
/**
 * 小区用户登录
 * @param membername
 * @param memberpwd
 * @ return
 */
public boolean memberLogin(String membername,String memberpwd)throws Exception;
/**
 * 小区用户查询商户分类信息
 * @param contentName
 * @return
 */
public  ArrayList<Object> getShopInfo(String contentName,Integer communityid,String signl)throws Exception;
/**
 * 判断小区用户是否登录
 * @param membername
 * @return
 */
public boolean selectLoginStatus(String membername)throws Exception;
/**
 * 修改小区用户的登录状态
 * @param membername
 * @return
 */
public boolean changeLoginStatus(String membername)throws Exception;
/**
 * 修改小区用户的登录状态
 * @param membername
 * @return
 */
public boolean changeLoginStatus1(Integer memberid)throws Exception;
/**
 * 查询商户ID
 * @param username
 * @return
 * @throws Exception 
 */
public int selectMemberId(String membername) throws Exception;
/**
 * 用户查询物业公告
 * @param communityId
 * @return
 */
public ArrayList<Object> selectBulletin(int communityId) throws Exception;
/**
 * 用户查询小区开通状态
 * @param communityName
 * @return
 */
public String chkCommunityOpened(String communityname)throws Exception;
/**
 * 用户为未开通小区点赞
 * @param communityid
 * @return
 */
public String setLikeCount(int communityid)throws Exception;
/**
 * 用户查询当前小区的物业信息
 * @param communityid
 * @return
 */
public Property selectProertyInfo(int communityid)throws Exception;
/**
 * 存储用户收藏的商家
 * @param userID
 * @param memberID
 * @return
 */
public boolean insertCollection(String guid, int memberid) throws Exception;
/**
 * 删除用户收藏的商家
 * @param userID
 * @param memberID
 * @return
 */
public boolean deleteCollection(String guid, int memberid) throws Exception;
/**
 * 查询用户收藏
 * @param memid
 * @return
 */
public ArrayList<Object> selectCollection(int memberid)throws Exception;
/**
 * 保存用户留言
 * @param communityid
 * @param msg
 * @return
 */
public boolean insertReply(int communityid,String msg,int memid)throws Exception;
/**
 * 验证用户是否已经收藏了该商家
 * @param userID
 * @param memberID
 * @return
 */
public boolean chkAlreadyCollection(String guid, int memberid) throws Exception;
/**
 * 为客户端返回广告
 * @return
 */
//public ArrayList<Object> selectAdvertisements()throws Exception;
public ArrayList<Object> selectAdvertisements(int communityid) throws Exception;
/**
 * 查询特定ID的商户信息
 * @param shopid
 * @return
 */
public Merchants selectSingleShopInfo(int shopid)throws Exception;
/**
 * 为悠然小区软件反馈意见
 * @param msg
 * @param memid
 * @return
 */
public boolean insertComment(String msg,int memid)throws Exception;
/**
 *查询当前用户所收藏的商户ID
 *@param
 * @return 
 *@return
 * 
 */
public ArrayList<Integer> selectMenberCollectMerchantId(int memberid)throws Exception;
/**
 * 检查旧密码是否正确
 * @param id
 * @param Pwd
 * @return
 * @throws Exception
 */
public boolean chkMemberPwd(String id, String Pwd) throws Exception;
/**
 * 查询系统管理员电话
 * @return
 * @throws Exception
 */
String selectAdminPhone() throws Exception;

/**
 * 查询小区信息
 * @param cityid
 * @return
 * @throws Exception
 */
ArrayList<Object> selectCommunity(int cityid) throws Exception;

/**
 * 获取城市Id
 * @param city
 * @return
 * @throws Exception
 */
String selectCityId(String city) throws Exception;



}

