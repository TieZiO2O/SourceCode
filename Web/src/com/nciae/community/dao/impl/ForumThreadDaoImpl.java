package com.nciae.community.dao.impl;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.nciae.community.dao.*;
import com.nciae.community.domain.ForumThreads;
import com.nciae.community.service.impl.DatabaseServiceImpl;

public class ForumThreadDaoImpl implements ForumThreadDao {
	private DatabaseServiceImpl dbServiceImpl;
	public DatabaseServiceImpl getDbServiceImpl() {
		return dbServiceImpl;
	}
	
	public void setDbServiceImpl(DatabaseServiceImpl dbServiceImpl) {
		this.dbServiceImpl = dbServiceImpl;
	}
	
	@Override
	public ForumThreads queryByGuid(String guid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<ForumThreads> queryAllByUid(String uid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean addNewForumThread(ForumThreads forumThread) throws Exception {
		// TODO Auto-generated method stub
		PreparedStatement ps=null;
		try {
			String dateNow=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
			String sql="insert into forumthreads(guid,title,content,phone,createDate) values(?,?,?,?,?)";
			ps=dbServiceImpl.connect().prepareStatement(sql);
			ps.setString(1, forumThread.getGuid());
			ps.setString(2, forumThread.getTitle());
			ps.setString(3, forumThread.getContent());
			ps.setString(4, forumThread.getPhone());
			//ps.setDate(5, new Date());
			ps.setDate(5, new java.sql.Date(new Date().getTime()));
			
			int reuslt=ps.executeUpdate();
			
			if(reuslt>0){
				return true;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			dbServiceImpl.close();
			if(ps!=null){
				ps.close();
			}
		}
		return false;
	}

	@Override
	public boolean addForumImgs(ArrayList<String> imgPaths,String guid) {
		// TODO Auto-generated method stub
		PreparedStatement ps=null;
		Connection con=null;
		boolean returnData=true;
		try{
			String sql="insert into forumtheads_img(forumthreadsguid,imgpath) values(?,?)";
			
			con=dbServiceImpl.connect();
			ps=con.prepareStatement(sql);
			con.setAutoCommit(false);
			for (String str : imgPaths) {
				ps.setString(1, guid);
				ps.setString(2, str);
				ps.addBatch();
			}
			ps.executeBatch();
			con.commit();
			con.setAutoCommit(true);
			
		}catch(Exception e){
			e.printStackTrace();
			returnData=false;
			try{
				con.rollback();
				con.setAutoCommit(true);
			}catch(Exception ex){
				ex.printStackTrace();
			}
		}finally{
			try {
				if(con!=null && !con.isClosed()){
					con.close();
				}
				if(ps!=null){
					ps.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return returnData;
	}

}
