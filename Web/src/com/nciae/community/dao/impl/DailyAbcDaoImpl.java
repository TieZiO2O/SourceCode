package com.nciae.community.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.nciae.community.dao.DailyAbcDao;
import com.nciae.community.domain.DailyAbc;
import com.nciae.community.service.impl.DatabaseServiceImpl;

public class DailyAbcDaoImpl implements DailyAbcDao {

	private DatabaseServiceImpl dbServiceImpl;

	public DatabaseServiceImpl getDbServiceImpl() {
		return dbServiceImpl;
	}

	public void setDbServiceImpl(DatabaseServiceImpl dbServiceImpl) {
		this.dbServiceImpl = dbServiceImpl;
	}
	
	@Override
	public boolean add(DailyAbc dailyAbc) throws Exception {
		PreparedStatement ps = null;
		try {
			String sql="insert into dailyabc (title,content) values(?,?)";
			ps=dbServiceImpl.connect().prepareStatement(sql);
			ps.setString(1, dailyAbc.getTitle());
			ps.setString(2, dailyAbc.getContent());
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
	public DailyAbc queryDailyAbc() throws Exception {
		ResultSet rs=null;
		PreparedStatement ps = null;
		DailyAbc d = null;
		try {
			String sql="select * from dailyabc order by id desc limit 0,1";
			ps=dbServiceImpl.connect().prepareStatement(sql);
			rs=ps.executeQuery();
			if(rs.next()){
				d = new DailyAbc();
				d.setId(rs.getInt("id"));
				d.setTitle(rs.getString("title"));
				d.setContent(rs.getString("content"));
			}
			return d;
		} catch (Exception e) {
			e.printStackTrace();
			return d;
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
	public boolean delete(Integer id) throws Exception {
		PreparedStatement ps = null;
		try {
			String sql="delete from dailyabc where id=?";
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
	public ArrayList<DailyAbc> queryAll() throws Exception {
		ResultSet rs=null;
		PreparedStatement ps = null;
		ArrayList<DailyAbc> list = null;
		try {
			String sql="select * from dailyabc order by id desc";
			ps=dbServiceImpl.connect().prepareStatement(sql);
			rs=ps.executeQuery();
			list = new ArrayList<DailyAbc>();
			while(rs.next()){
				DailyAbc d = new DailyAbc();
				d.setId(rs.getInt("id"));
				d.setTitle(rs.getString("title"));
				d.setContent(rs.getString("content"));
				list.add(d);
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
	public DailyAbc queryRandomDailyAbc() throws Exception {
		ResultSet rs=null;
		PreparedStatement ps = null;
		DailyAbc d = null;
		try {
			String sql="select * from dailyabc as t1 "+
						"join (select round(rand() * (select max(id) from dailyabc)) as id) as t2 "+
						"where t1.id >= t2.id "+
						"order by t1.id asc limit 1";
			ps=dbServiceImpl.connect().prepareStatement(sql);
			rs=ps.executeQuery();
			if(rs.next()){
				d = new DailyAbc();
				d.setId(rs.getInt("id"));
				d.setTitle(rs.getString("title"));
				d.setContent(rs.getString("content"));
			}
			return d;
		} catch (Exception e) {
			e.printStackTrace();
			return d;
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
