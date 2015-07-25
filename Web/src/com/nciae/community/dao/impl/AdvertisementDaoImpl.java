package com.nciae.community.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.nciae.community.dao.AdvertisementDao;
import com.nciae.community.domain.Advertisement;
import com.nciae.community.service.impl.DatabaseServiceImpl;

public class AdvertisementDaoImpl implements AdvertisementDao {

	private DatabaseServiceImpl dbServiceImpl;

	public DatabaseServiceImpl getDbServiceImpl() {
		return dbServiceImpl;
	}

	public void setDbServiceImpl(DatabaseServiceImpl dbServiceImpl) {
		this.dbServiceImpl = dbServiceImpl;
	}
	
	@Override
	public boolean add(Advertisement advertisement) throws Exception {
		PreparedStatement ps = null;
		try {
			String sql="insert into advertisement (adTitle,adContent,adImagURL,shopperId,communityList) values(?,?,?,?,?)";
			ps=dbServiceImpl.connect().prepareStatement(sql);
			ps.setString(1, advertisement.getAdTitle());
			ps.setString(2, advertisement.getAdContent());
			ps.setString(3, advertisement.getAdImagURL());
			ps.setInt(4, advertisement.getShopperId());
			ps.setString(5, advertisement.getCommunityList());
			//ps.setString(4, advertisement.getAdUrl());////////////////////////0602
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
	public boolean delete(Integer id) throws Exception {
		PreparedStatement ps = null;
		try {
			String sql="delete from advertisement where id=?";
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
	public ArrayList<Advertisement> queryAdvertisements() throws Exception {
		ResultSet rs=null;
		PreparedStatement ps = null;
		ArrayList<Advertisement> list = null;
		try {
			String sql="select * from advertisement order by id desc";
			ps=dbServiceImpl.connect().prepareStatement(sql);
			rs=ps.executeQuery();
			list = new ArrayList<Advertisement>();
			while(rs.next()){
				Advertisement ad = new Advertisement();
				ad.setId(rs.getInt("id"));
				ad.setAdTitle(rs.getString("adTitle"));
				ad.setAdContent(rs.getString("adContent"));
				ad.setAdImagURL(rs.getString("adImagURL"));
				//ad.setAdUrl(rs.getString("adUrl"));//////////////////0602
				list.add(ad);
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
	public Advertisement queryOneAdvertisement() throws Exception {
		ResultSet rs=null;
		PreparedStatement ps = null;
		Advertisement ad = null;
		try {
			String sql="select * from advertisement order by id desc limit 0,1";
			ps=dbServiceImpl.connect().prepareStatement(sql);
			rs=ps.executeQuery();
			if(rs.next()){
				ad = new Advertisement();
				ad.setId(rs.getInt("id"));
				ad.setAdTitle(rs.getString("adTitle"));
				ad.setAdContent(rs.getString("adContent"));
				ad.setAdImagURL(rs.getString("adImagURL"));
				//ad.setAdUrl(rs.getString("adUrl"));//////////////////0602
			}
			return ad;
		} catch (Exception e) {
			e.printStackTrace();
			return ad;
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
	public ArrayList<Object> queryNewThreeAd() throws Exception {
		ResultSet rs=null;
		PreparedStatement ps = null;
		ArrayList<Object> list = null;
		try {
			String sql="select * from advertisement order by id desc limit 0,3";
			ps=dbServiceImpl.connect().prepareStatement(sql);
			rs=ps.executeQuery();
			list = new ArrayList<Object>();
			if(rs.next()){
				Advertisement ad = new Advertisement();
				ad.setId(rs.getInt("id"));
				ad.setAdTitle(rs.getString("adTitle"));
				ad.setAdContent(rs.getString("adContent"));
				ad.setAdImagURL(rs.getString("adImagURL"));
				//ad.setAdUrl(rs.getString("adUrl"));//////////////////0602
				list.add(ad);
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
	public boolean addAdImg(Integer id, String path) throws Exception {
		PreparedStatement ps = null;
		try {
			String sql="update advertisement set adImagURL=? where id = ?";
			ps=dbServiceImpl.connect().prepareStatement(sql);
			ps.setString(1, path);
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
	// ///////////////////////////////////////0629
	@Override
	public Integer lxQueryShoperIDofAD(Integer id) throws Exception {
		ResultSet rs=null;
		PreparedStatement ps = null;
		try {
			String sql="select shopperId from advertisement where id=?";
			ps=dbServiceImpl.connect().prepareStatement(sql);
			ps.setInt(1, id);
			rs=ps.executeQuery();
			if(rs.next()){
				return rs.getInt("shopperId");
			}
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
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
	
	
	// ///////////////////////////////////////0629
			@Override
			public String lxFindAdImgUrl(int id) throws Exception {
				ResultSet rs = null;
				PreparedStatement ps = null;
				try {
					String sql = "select adImagURL from advertisement where id=?";
					ps = dbServiceImpl.connect().prepareStatement(sql);
					ps.setInt(1, id);
					rs = ps.executeQuery();
					if (rs.next()) {
						return rs.getString("adImagURL");
					}
					return null;
				} catch (Exception e) {
					e.printStackTrace();
					return null;
				} finally {
					try {
						dbServiceImpl.close();
						if (rs != null) {
							rs.close();
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}

}
