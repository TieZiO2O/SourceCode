package com.nciae.community.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.nciae.community.dao.CommunityDao;
import com.nciae.community.domain.Community;
import com.nciae.community.service.impl.DatabaseServiceImpl;

public class CommunityDaoImpl implements CommunityDao {

	private DatabaseServiceImpl dbServiceImpl;

	public DatabaseServiceImpl getDbServiceImpl() {
		return dbServiceImpl;
	}

	public void setDbServiceImpl(DatabaseServiceImpl dbServiceImpl) {
		this.dbServiceImpl = dbServiceImpl;
	}
	
	@Override
	public ArrayList<Community> queryAllCommunity() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Community queryCommunityById() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Community> queryCommunityByCityId(int cityId) throws Exception {
		ResultSet rs=null;
		PreparedStatement ps = null;
		ArrayList<Community> list = null;
		try {
			String sql="select * from community where cityId=? and isActive=?";
			ps=dbServiceImpl.connect().prepareStatement(sql);
			ps.setInt(1, cityId);
			ps.setInt(2, 1);
			rs=ps.executeQuery();
			list = new ArrayList<Community>();
			while(rs.next()){
				Community c = new Community();
				c.setCityId(cityId);
				c.setId(rs.getInt("id"));
				c.setCommunityName(rs.getString("communityName"));
				c.setRemark(rs.getString("remark"));
				c.setIsActive(rs.getByte("isActive"));
				//0625
				c.setLikeCount(rs.getInt("likeCount"));
				c.setOpened(rs.getByte("opened"));
				list.add(c);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return list;
		} finally{
			try {
				dbServiceImpl.close();
				if(rs!=null){
					rs.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	
	public int getTotalRecord(int cityId){
		ResultSet rs=null;
		PreparedStatement ps = null;
		ArrayList<Community> list = null;
		try {
			String sql="select * from community where cityId=? and isActive=?";
			ps=dbServiceImpl.connect().prepareStatement(sql);
			ps.setInt(1, cityId);
			ps.setInt(2, 1);
			rs=ps.executeQuery();
			list = new ArrayList<Community>();
			while(rs.next()){
				Community c = new Community();
				c.setCityId(cityId);
				c.setId(rs.getInt("id"));
				c.setCommunityName(rs.getString("communityName"));
				c.setRemark(rs.getString("remark"));
				c.setIsActive(rs.getByte("isActive"));
				list.add(c);
			}
			return list.size();
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		} finally{
			try {
				dbServiceImpl.close();
				if(rs!=null){
					rs.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public List<Community> findPageCommunity(int startIndex, int pagesize,int cityId) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Community> list = null;
		String sql="select * from community where cityId=? and isActive=? limit ?,?";
		try {
			ps=dbServiceImpl.connect().prepareStatement(sql);
			ps.setInt(1, cityId);
			ps.setInt(2, 1);
			ps.setInt(3, startIndex);
			ps.setInt(4, pagesize);
			rs=ps.executeQuery();
			list = new ArrayList<Community>();
			while(rs.next()){
				Community c = new Community();
				c.setCityId(cityId);
				c.setId(rs.getInt("id"));
				c.setCommunityName(rs.getString("communityName"));
				c.setRemark(rs.getString("remark"));
				c.setIsActive(rs.getByte("isActive"));
				list.add(c);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} 
	}
	@Override
	public ArrayList<Community> queryCommunity(int cityId) throws Exception {
		ResultSet rs=null;
		PreparedStatement ps = null;
		ArrayList<Community> list = null;
		try {
			if(cityId!=-1){
				String sql="select * from community where cityId=? and isActive=?";
				ps=dbServiceImpl.connect().prepareStatement(sql);
				ps.setInt(1, cityId);
				ps.setInt(2, 1);
			}else{
				String sql="select * from community where isActive=?";
				ps=dbServiceImpl.connect().prepareStatement(sql);
				ps.setInt(1, 1);
			}
			rs=ps.executeQuery();
			list = new ArrayList<Community>();
			while(rs.next()){
				Community c = new Community();
				c.setCityId(cityId);
				c.setId(rs.getInt("id"));
				c.setCommunityName(rs.getString("communityName"));
				c.setRemark(rs.getString("remark"));
				c.setIsActive(rs.getByte("isActive"));
				list.add(c);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return list;
		} finally{
			try {
				dbServiceImpl.close();
				if(rs!=null){
					rs.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public boolean insert(Community community) throws Exception {
		PreparedStatement ps = null;
		try {
			String sql="insert into community (communityName,remark,cityId) values(?,?,?)";
			ps=dbServiceImpl.connect().prepareStatement(sql);
			ps.setString(1, community.getCommunityName());
			ps.setString(2, community.getRemark());
			ps.setInt(3, community.getCityId());
			ps.executeUpdate();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally{
			try {
				dbServiceImpl.close();
				if(ps!=null){
					ps.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		}
	}

	@Override
	public boolean delete(int id) throws Exception {
		PreparedStatement ps = null;
		try {
			String sql="delete from community where id = ?";
			ps=dbServiceImpl.connect().prepareStatement(sql);
			ps.setInt(1, id);
			ps.executeUpdate();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally{
			try {
				dbServiceImpl.close();
				if(ps!=null){
					ps.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		}
	}

	@Override
	public boolean deleteByLogical(int id) throws Exception {
		PreparedStatement ps = null;
		try {
			String sql="update community set isActive = ? where id = ?";
			ps=dbServiceImpl.connect().prepareStatement(sql);
			ps.setBoolean(1, false);
			ps.setInt(2, id);
			ps.executeUpdate();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally{
			try {
				dbServiceImpl.close();
				if(ps!=null){
					ps.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		}
	}
	//////////////////////0601
	public ArrayList<Object> queryMyCommunity(int cityId) throws Exception {
		ResultSet rs=null;
		PreparedStatement ps = null;
		ArrayList<Object> list = null;
		try {
			if(cityId!=-1){
				String sql="select * from community where cityId=? and isActive=?";
				ps=dbServiceImpl.connect().prepareStatement(sql);
				ps.setInt(1, cityId);
				ps.setInt(2, 1);
			}else{
				String sql="select * from community where isActive=?";
				ps=dbServiceImpl.connect().prepareStatement(sql);
				ps.setInt(1, 1);
			}
			rs=ps.executeQuery();
			list = new ArrayList<Object>();
			while(rs.next()){
				Community c = new Community();
				c.setCityId(cityId);
				c.setId(rs.getInt("id"));
				c.setCommunityName(rs.getString("communityName"));
				c.setRemark(rs.getString("remark"));
				c.setIsActive(rs.getByte("isActive"));
				list.add(c);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return list;
		} finally{
			try {
				dbServiceImpl.close();
				if(rs!=null){
					rs.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public boolean updateToOpen(int id) throws Exception {
		PreparedStatement ps = null;
		try {
			String sql="update community set opened = ? where id = ?";
			ps=dbServiceImpl.connect().prepareStatement(sql);
			ps.setByte(1, (byte) 1);
			ps.setInt(2, id);
			ps.executeUpdate();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally{
			try {
				dbServiceImpl.close();
				if(ps!=null){
					ps.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		}
	}
	
	@Override
	public boolean updateToClose(int id) throws Exception {
		PreparedStatement ps = null;
		try {
			String sql="update community set opened = ? where id = ?";
			ps=dbServiceImpl.connect().prepareStatement(sql);
			ps.setByte(1, (byte) 0);
			ps.setInt(2, id);
			ps.executeUpdate();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally{
			try {
				dbServiceImpl.close();
				if(ps!=null){
					ps.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		}
	}

}
