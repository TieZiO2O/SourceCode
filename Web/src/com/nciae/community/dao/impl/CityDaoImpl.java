package com.nciae.community.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.nciae.community.dao.CityDao;
import com.nciae.community.domain.City;
import com.nciae.community.service.impl.DatabaseServiceImpl;

public class CityDaoImpl implements CityDao{

	private DatabaseServiceImpl dbServiceImpl;

	public DatabaseServiceImpl getDbServiceImpl() {
		return dbServiceImpl;
	}

	public void setDbServiceImpl(DatabaseServiceImpl dbServiceImpl) {
		this.dbServiceImpl = dbServiceImpl;
	}
	
	@Override
	public ArrayList<City> queryAllCity() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer queryIdByName(String city) throws Exception {
		ResultSet rs=null;
		PreparedStatement ps = null;
		try {
			String sql="select id from city where city=?";
			ps=dbServiceImpl.connect().prepareStatement(sql);
			ps.setString(1, city);
			rs=ps.executeQuery();
			if(rs.next()){
				return rs.getInt("id");
			}
			else{
				return -1;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
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
