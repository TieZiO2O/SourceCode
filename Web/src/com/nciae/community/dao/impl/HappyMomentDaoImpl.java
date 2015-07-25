package com.nciae.community.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.nciae.community.dao.HappyMomentDao;
import com.nciae.community.domain.HappyMoment;
import com.nciae.community.service.impl.DatabaseServiceImpl;

public class HappyMomentDaoImpl implements HappyMomentDao {

	private DatabaseServiceImpl dbServiceImpl;

	public DatabaseServiceImpl getDbServiceImpl() {
		return dbServiceImpl;
	}

	public void setDbServiceImpl(DatabaseServiceImpl dbServiceImpl) {
		this.dbServiceImpl = dbServiceImpl;
	}
	
	@Override
	public boolean add(HappyMoment happyMoment) throws Exception {
		PreparedStatement ps = null;
		try {
			String sql="insert into happymoment (title,content) values(?,?)";
			ps=dbServiceImpl.connect().prepareStatement(sql);
			ps.setString(1, happyMoment.getTitle());
			ps.setString(2, happyMoment.getContent());
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
			String sql="delete from happymoment where id=?";
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
	public HappyMoment queryHappyMoment() throws Exception {
		ResultSet rs=null;
		PreparedStatement ps = null;
		HappyMoment h = null;
		try {
			String sql="select * from happymoment order by id desc limit 0,1";
			ps=dbServiceImpl.connect().prepareStatement(sql);
			rs=ps.executeQuery();
			if(rs.next()){
				h = new HappyMoment();
				h.setId(rs.getInt("id"));
				h.setTitle(rs.getString("title"));
				h.setContent(rs.getString("content"));
			}
			return h;
		} catch (Exception e) {
			e.printStackTrace();
			return h;
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
	public ArrayList<HappyMoment> queryAll() throws Exception {
		ResultSet rs=null;
		PreparedStatement ps = null;
		ArrayList<HappyMoment> list = null;
		try {
			String sql="select * from happymoment order by id desc";
			ps=dbServiceImpl.connect().prepareStatement(sql);
			rs=ps.executeQuery();
			list = new ArrayList<HappyMoment>();
			while(rs.next()){
				HappyMoment h = new HappyMoment();
				h.setId(rs.getInt("id"));
				h.setTitle(rs.getString("title"));
				h.setContent(rs.getString("content"));
				list.add(h);
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
	public HappyMoment queryRandomHappyMoment() throws Exception {
		ResultSet rs=null;
		PreparedStatement ps = null;
		HappyMoment h = null;
		try {
			String sql="select * from happymoment as t1 "+
					"join (select round(rand() * (select max(id) from happymoment)) as id) as t2 "+
					"where t1.id >= t2.id "+
					"order by t1.id asc limit 1";
			ps=dbServiceImpl.connect().prepareStatement(sql);
			rs=ps.executeQuery();
			if(rs.next()){
				h = new HappyMoment();
				h.setId(rs.getInt("id"));
				h.setTitle(rs.getString("title"));
				h.setContent(rs.getString("content"));
			}
			return h;
		} catch (Exception e) {
			e.printStackTrace();
			return h;
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

}
