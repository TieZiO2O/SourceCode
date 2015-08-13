package com.nciae.community.dao.impl;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.apache.commons.codec.binary.Base64;

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
	
	private String webUrl;

	public String getWebUrl() {
		return webUrl;
	}

	public void setWebUrl(String webUrl) {
		this.webUrl = webUrl;
	}
	
	@Override
	public ForumThreads queryByGuid(String guid) {
		// TODO Auto-generated method stub
		String sql="SELECT ft.id,ft.guid,"
				+ "ft.title,ft.content,"
				+ "ft.phone,fti.imgpath"
				+ " from forumthreads ft "
				+ "inner join forumtheads_img fti "
				+ "on ft.guid=fti.forumthreadsguid where ft.guid=? and ft.isUsed=true";
		org.apache.tomcat.util.codec.binary.Base64 base64=new org.apache.tomcat.util.codec.binary.Base64();
		PreparedStatement ps=null;
		ResultSet rs=null;
		ForumThreads ft=null;
		try {
			ps=this.dbServiceImpl.connect().prepareStatement(sql);
			ps.setString(1, guid);
			rs=ps.executeQuery();
		
			ft=new ForumThreads();
			ArrayList<String> imgs=new ArrayList<String>();
			while(rs.next()){
				ft.setId(rs.getInt("id"));
				ft.setGuid(rs.getString("guid"));
				ft.setTitle(new String(base64.decode(rs.getString("title")),"utf-8"));
				ft.setPhone(rs.getString("phone"));
				String content=rs.getString("content");
				ft.setContent(content!=null?new String(base64.decode(rs.getString("content")),"utf-8"):"");
				imgs.add(this.webUrl+rs.getString("imgpath"));
			}
			ft.setImages(imgs);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			ft=null;
		}finally{
			if(ps!=null){
				try {
					ps.close();
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return ft;
	}

	@Override
	public boolean query_IsBelong(String uid,String guid) {
		// TODO Auto-generated method stub
		String sql="select uf.userId from users_forumthreads uf "
				+ "INNER JOIN forumthreads f on uf.forumThreadsGuid=f.guid "
				+ "where f.guid=? and uf.userId=? and f.isUsed=true";

		PreparedStatement ps=null;
		ResultSet rs=null;
		boolean result=true;
		try {
			ps=this.dbServiceImpl.connect().prepareStatement(sql);
			ps.setString(1, guid);
			ps.setInt(2, Integer.parseInt(uid));
			rs=ps.executeQuery();
			
			if(!rs.next()){
				result=false;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			if(ps!=null){
				try {
					ps.close();
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return result;
	}

	@Override
	public ArrayList<ForumThreads> queryAllByUid(String uid) {
		// TODO Auto-generated method stub
		String sql="SELECT ft.id,ft.guid,uf.userId,"
				+ "ft.title,ft.content,ft.createDate,ft.phone from "
				+ "users_forumthreads uf inner join "
				+ "forumthreads ft on uf.forumThreadsGuid=ft.guid "
				+ "where uf.userId=?";
		
		org.apache.tomcat.util.codec.binary.Base64 base64=new org.apache.tomcat.util.codec.binary.Base64();
		
		PreparedStatement ps=null;
		ResultSet rs=null;
		ArrayList<ForumThreads> fts=null;
		try {
			ps=this.dbServiceImpl.connect().prepareStatement(sql);
			ps.setString(1, uid);
			rs=ps.executeQuery();
		
			fts=new ArrayList<ForumThreads>();
			while(rs.next()){
				ForumThreads ft=new ForumThreads();
				ft.setId(rs.getInt("id"));
				ft.setUid(rs.getString("userId"));
				ft.setGuid(rs.getString("guid"));
				ft.setPhone(rs.getString("phone"));
				ft.setCreateDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(rs.getString("createDate")));
				ft.setTitle(new String(base64.decode(rs.getString("title")),"utf-8"));
				String content=rs.getString("content");
				ft.setContent(content!=null?new String(base64.decode(rs.getString("content")),"utf-8"):"");
				fts.add(ft);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			fts=null;
		}finally{
			if(ps!=null){
				try {
					ps.close();
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return fts;
	}

	public ArrayList<ForumThreads> queryAll() {
		// TODO Auto-generated method stub
		String sql="SELECT ft.id,ut.userId,ft.guid,ft.title,ft.content,ft.phone,ft.createDate,"
				+" fti.imgpath from users_forumthreads ut LEFT JOIN forumthreads ft on ut.forumThreadsGuid=ft.guid"
				+" LEFT join forumtheads_img fti on ft.guid=fti.forumthreadsguid where ft.isUsed=true"
				+" GROUP BY ft.guid ORDER BY ft.id";

		org.apache.tomcat.util.codec.binary.Base64 base64=new org.apache.tomcat.util.codec.binary.Base64();
		
		PreparedStatement ps=null;
		ResultSet rs=null;
		ArrayList<ForumThreads> fts=null;
		try {
			ps=this.dbServiceImpl.connect().prepareStatement(sql);
			rs=ps.executeQuery();
		
			fts=new ArrayList<ForumThreads>();
			while(rs.next()){
				ForumThreads ft=new ForumThreads();
				ArrayList<String> strs=new ArrayList<String>();
				strs.add(this.webUrl+rs.getString("imgpath"));
				
				ft.setImages(strs);
				ft.setId(rs.getInt("id"));
				ft.setUid(rs.getString("userId"));
				ft.setGuid(rs.getString("guid"));
				ft.setPhone(rs.getString("phone"));
				ft.setCreateDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(rs.getString("createDate")));
				ft.setTitle(new String(base64.decode(rs.getString("title")),"utf-8"));
				String content=rs.getString("content");
				ft.setContent(content!=null?new String(base64.decode(rs.getString("content")),"utf-8"):"");
				fts.add(ft);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			fts=null;
		}finally{
			if(ps!=null){
				try {
					ps.close();
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return fts;
	}

	
	@Override
	public boolean addNewForumThread(ForumThreads forumThread) throws Exception {
		// TODO Auto-generated method stub
		PreparedStatement ps=null;
		boolean isTrue=true;
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
				isTrue=true;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			isTrue=false;
		}finally{
			dbServiceImpl.close();
			if(ps!=null){
				ps.close();
			}
		}
		return isTrue;
	}

	public boolean addNewUser_ForumThread(ForumThreads forumThread) throws Exception {
		// TODO Auto-generated method stub
		PreparedStatement ps=null;
		boolean isTrue=true;
		try {
			String dateNow=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
			String sql="insert into users_forumthreads(userid,forumThreadsGuid,createDate) values(?,?,?)";
			ps=dbServiceImpl.connect().prepareStatement(sql);
			ps.setString(2, forumThread.getGuid());
			ps.setString(1, forumThread.getUid());
			ps.setDate(3, new java.sql.Date(new Date().getTime()));
			
			int reuslt=ps.executeUpdate();
			
			if(reuslt>0){
				isTrue=true;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			isTrue=false;
		}finally{
			dbServiceImpl.close();
			if(ps!=null){
				ps.close();
			}
		}
		return isTrue;
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
				dbServiceImpl.close();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
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

	@Override
	public boolean delete_By_Guid(String guid){
		PreparedStatement ps=null;
		String sql="update forumthreads set isUsed=false where guid=?";
		boolean result=true;
		try {
			ps=this.dbServiceImpl.connect().prepareStatement(sql);
			ps.setString(1, guid);
			int count=ps.executeUpdate();
			
			if(count>0){
				result=true;
			}else{
				result=false;
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			result=false;
		}finally{
			if(ps!=null){
				try {
					
					try {
						this.dbServiceImpl.close();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					ps.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		return result;
	}

}
