package com.nciae.community.dao.impl;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import org.apache.commons.codec.binary.Base64;

import com.nciae.community.dao.*;
import com.nciae.community.domain.DailyLives;
import com.nciae.community.domain.DailyLivesType;
import com.nciae.community.domain.DailyLives;
import com.nciae.community.domain.DailyLives_SeperateTypes;
import com.nciae.community.service.impl.DatabaseServiceImpl;

public class DailyLivesDaoImpl implements DailyLivesDao {
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
	public DailyLives queryByGuid(String guid) {
		// TODO Auto-generated method stub
		String sql="select dl.isUsed,dl.guid,dl.customerNotice,dl.address,dl.phone,dl.title,"
					+"dl.content,dl.createDate,dl.updateDate,di.img_path"
					+" from dailylives dl" 
					+" LEFT join dailylives_img di on dl.guid=di.dailylives_guid and dl.isUsed<>0 where dl.guid=?";
		org.apache.tomcat.util.codec.binary.Base64 base64=new org.apache.tomcat.util.codec.binary.Base64();
		PreparedStatement ps=null;
		ResultSet rs=null;
		DailyLives ft=null;
		try {
			ps=this.dbServiceImpl.connect().prepareStatement(sql);
			ps.setString(1, guid);
			rs=ps.executeQuery();
		
			
			ft=new DailyLives();
			ArrayList<String> imgs=new ArrayList<String>();
			while(rs.next()){
				imgs.add(this.webUrl+rs.getString("img_path"));
				ft.setId(rs.getInt("id"));
				ft.setGuid(rs.getString("guid"));
				ft.setAddress(rs.getString("address"));
				ft.setCustomerNotice(rs.getString("customerNotice"));
				ft.setPhone(rs.getString("phone"));
				ft.setTitle(new String(base64.decode(rs.getString("title")),"utf-8"));
				String content=rs.getString("content");
				ft.setContent(content!=null?new String(base64.decode(rs.getString("content")),"utf-8"):"");
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
	public ArrayList<DailyLives> queryAllByUid(String uid) {
		// TODO Auto-generated method stub
		String sql="SELECT ft.id,ft.guid,"
				+ "ft.title,ft.content,ft.createDate,ft.phone from "
				+ "users_DailyLives uf inner join "
				+ "DailyLives ft on uf.DailyLivesGuid=ft.guid "
				+ "where uf.userId=?";
		
		org.apache.tomcat.util.codec.binary.Base64 base64=new org.apache.tomcat.util.codec.binary.Base64();
		
		PreparedStatement ps=null;
		ResultSet rs=null;
		ArrayList<DailyLives> fts=null;
		try {
			ps=this.dbServiceImpl.connect().prepareStatement(sql);
			ps.setString(1, uid);
			rs=ps.executeQuery();
			
			fts=new ArrayList<DailyLives>();
			while(rs.next()){
				DailyLives ft=new DailyLives();
				ft.setId(rs.getInt("id"));
				ft.setGuid(rs.getString("guid"));
				ft.setPhone(rs.getString("phone"));
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
	public ArrayList<DailyLives> queryAllByServiceType(int typeId) {
		// TODO Auto-generated method stub
		String sql="select dl.id,dl.guid,dl.customerNotice,dl.address,dl.phone,dl.title,"
					+"dl.content,dl.createDate,dl.updateDate,di.img_path"
					+" from dailylives_type dt"
					+" LEFT join dailylives dl on dt.id=dl.dailylives_type_id and dl.isUsed<>0"
					+" LEFT join dailylives_img di on dl.guid=di.dailylives_guid where dt.id=? order by dt.id;";
				/*"select dt.id,dt.serviceType,dt.style,dt.logoPath,dl.guid,dl.customerNotice,dl.address,dl.phone,dl.title,"
					+"dl.content,dl.createDate,dl.updateDate,di.img_path"
					+" from dailylives_type dt "
					+" LEFT join dailylives dl on dt.id=dl.dailylives_type_id and dl.isUsed<>0"
					+" LEFT join dailylives_img di on dl.guid=di.dailylives_guid order by dt.id";*/
		org.apache.tomcat.util.codec.binary.Base64 base64=new org.apache.tomcat.util.codec.binary.Base64();
		
		PreparedStatement ps=null;
		ResultSet rs=null;
		ArrayList<DailyLives> fts=null;
		try {
			ps=this.dbServiceImpl.connect().prepareStatement(sql);
			ps.setInt(1, typeId);
			rs=ps.executeQuery();
			
			fts=new ArrayList<DailyLives>();
			while(rs.next()){
				Iterator<DailyLives> dlvs=fts.iterator();
				DailyLives ft=null;
				int index=0;
				int count=0;
				while(dlvs.hasNext()){
					DailyLives dl=dlvs.next();
					if(dl.getGuid().equals(rs.getString("guid"))){
						ft=dl;
						index=count;
						break;
					}
					count++;
				}
				if(ft==null){
					ft=new DailyLives();
					ArrayList<String> imgs=new ArrayList<String>();
					imgs.add(this.webUrl+rs.getString("img_path"));
					ft.setImages(imgs);
					ft.setId(rs.getInt("id"));
					ft.setGuid(rs.getString("guid"));
					ft.setPhone(rs.getString("phone"));
					ft.setAddress(rs.getString("address"));
					ft.setCustomerNotice(rs.getString("customerNotice"));
					ft.setTitle(new String(base64.decode(rs.getString("title")),"utf-8"));
					String content=rs.getString("content");
					ft.setContent(content!=null?new String(base64.decode(rs.getString("content")),"utf-8"):"");
					fts.add(ft);
				}else{
					fts.get(index).getImages().add(this.webUrl+rs.getString("img_path"));
				}
				
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
	public boolean addNewDailyLivesType(DailyLivesType dlv){
		boolean result=true;
		String sql="insert into dailylives_type(style,serviceType,logoPath) values(?,?,?)";
		PreparedStatement ps=null;
		try {
			ps=this.dbServiceImpl.connect().prepareStatement(sql);
			ps.setString(1, dlv.getStyle());
			ps.setString(2, dlv.getServiceType());
			ps.setString(3, dlv.getLogoPath());
			
			int returnDt=ps.executeUpdate();
			if(returnDt>0){
				result=true;
			}
		} catch (Exception e) {
			result=false;
			// TODO: handle exception
		}finally{
			if(!ps.equals(null)){
				try {
					this.dbServiceImpl.close();
					ps.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return result;
	}
	
	@Override
	public boolean addNewDailyLives(DailyLives DailyLives) throws Exception {
		// TODO Auto-generated method stub
		PreparedStatement ps=null;
		boolean isTrue=true;
		try {
			String dateNow=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
			String sql="insert into DailyLives(guid,dailylives_type_id,customerNotice,address,title,content,phone,createDate) values(?,?,?,?,?,?,?,?)";
			ps=dbServiceImpl.connect().prepareStatement(sql);
			ps.setString(1, DailyLives.getGuid());
			ps.setInt(2, DailyLives.getDailyLivesId());
			ps.setString(3, DailyLives.getCustomerNotice());
			ps.setString(4, DailyLives.getAddress());
			ps.setString(5, DailyLives.getTitle());
			ps.setString(6, DailyLives.getContent());
			ps.setString(7, DailyLives.getPhone());
			ps.setDate(8, new java.sql.Date(new Date().getTime()));
			
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

	public boolean addNewUser_DailyLives(DailyLives DailyLives) throws Exception {
		// TODO Auto-generated method stub
		PreparedStatement ps=null;
		boolean isTrue=true;
		try {
			String dateNow=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
			String sql="insert into users_DailyLives(userid,DailyLivesGuid,createDate) values(?,?,?)";
			ps=dbServiceImpl.connect().prepareStatement(sql);
			ps.setString(2, DailyLives.getGuid());
			ps.setString(1, DailyLives.getUid());
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
	public boolean addDailyLivesImgs(ArrayList<String> imgPaths,String guid) {
		// TODO Auto-generated method stub
		PreparedStatement ps=null;
		Connection con=null;
		boolean returnData=true;
		try{
			String sql="insert into dailylives_img(dailylives_guid,img_path) values(?,?)";
			
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

	public ArrayList<DailyLivesType> SelectAllDailyLivesTypes(){
		ArrayList<DailyLivesType> dailyTypes=null;
		String sql="select * from dailylives_type";
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			ps=this.dbServiceImpl.connect().prepareStatement(sql);
			rs=ps.executeQuery();
			
			dailyTypes=new ArrayList<DailyLivesType>();
			while(rs.next()){
				DailyLivesType dl=new DailyLivesType();
				dl.setId(rs.getInt("id"));
				dl.setServiceType(rs.getString("serviceType"));
				dl.setStyle(rs.getString("style"));
				dl.setLogoPath(this.webUrl+rs.getString("logoPath"));
				dailyTypes.add(dl);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			try {
				this.dbServiceImpl.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
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
		return dailyTypes;
	} 
	
	/*
	 * 获取所有的服务类型，已经分类好的
	 * */
	public DailyLives_SeperateTypes SelectAllDailyLivesTypsBySerperate(){
		DailyLives_SeperateTypes dailyTypes=null;
		String sql="select * from dailylives_type";
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			ps=this.dbServiceImpl.connect().prepareStatement(sql);
			rs=ps.executeQuery();
			
			dailyTypes=new DailyLives_SeperateTypes();
			
			ArrayList<DailyLivesType> localTyps=new ArrayList<DailyLivesType>();
			ArrayList<DailyLivesType> allCityTyps=new ArrayList<DailyLivesType>();
			while(rs.next()){
				DailyLivesType dl=new DailyLivesType();
				dl.setId(rs.getInt("id"));
				dl.setServiceType(rs.getString("serviceType"));
				dl.setStyle(rs.getString("style"));
				dl.setLogoPath(this.webUrl+rs.getString("logoPath"));
				if(dl.getServiceType().equals("全城服务")){
					allCityTyps.add(dl);
				}else{
					localTyps.add(dl);
				}
			}
			
			dailyTypes.setAllcityservice(allCityTyps);
			dailyTypes.setLocalservice(localTyps);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			try {
				this.dbServiceImpl.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
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
		return dailyTypes;
	} 
	
}
