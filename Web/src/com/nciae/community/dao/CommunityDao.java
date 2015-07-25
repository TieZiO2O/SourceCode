package com.nciae.community.dao;

import java.util.ArrayList;

import com.nciae.community.domain.Community;

public interface CommunityDao {
	
	public ArrayList<Community> queryAllCommunity() throws Exception;
	public Community queryCommunityById() throws Exception;
	public ArrayList<Community> queryCommunityByCityId(int cityId) throws Exception;
	public ArrayList<Community> queryCommunity(int cityId) throws Exception;
	public boolean insert(Community community) throws Exception;
	//物理删除
	public boolean delete(int id) throws Exception;
	//逻辑删除
	public boolean deleteByLogical(int id) throws Exception;
	//0625
	public boolean updateToOpen(int id) throws Exception;
	public boolean updateToClose(int id) throws Exception;
}
