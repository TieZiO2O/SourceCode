package com.nciae.community.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.nciae.community.dao.ReplyDao;
import com.nciae.community.domain.Reply;
import com.nciae.community.service.impl.DatabaseServiceImpl;

public class ReplyDaoImpl implements ReplyDao {

	private DatabaseServiceImpl dbServiceImpl;

	public DatabaseServiceImpl getDbServiceImpl() {
		return dbServiceImpl;
	}

	public void setDbServiceImpl(DatabaseServiceImpl dbServiceImpl) {
		this.dbServiceImpl = dbServiceImpl;
	}
	
	@Override
	public ArrayList<Reply> querySoftwareReply() throws Exception {
		ResultSet rs=null;
		PreparedStatement ps = null;
		ArrayList<Reply> list = null;
		try {
			String sql="select * from reply where communityId=?";
			ps=dbServiceImpl.connect().prepareStatement(sql);
			ps.setInt(1, 0);
			rs=ps.executeQuery();
			list = new ArrayList<Reply>();
			while(rs.next()){
				Reply r = new Reply();
				r.setCommitTime(rs.getString("commitTime"));
				r.setId(rs.getInt("id"));
				r.setMemId(rs.getInt("memId"));
				r.setCommunityId(rs.getInt("communityId"));
				r.setMsg(rs.getString("msg"));
				r.setUserId(rs.getInt("userId"));
				list.add(r);
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
	public boolean deleteSoftwareReply(Integer id) throws Exception {
		PreparedStatement ps = null;
		try {
			String sql="delete from reply where id = ?";
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

}
