package com.nciae.community.dao.impl;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import org.springframework.format.datetime.DateFormatter;

import com.nciae.community.dao.DailyLivesDao;
import com.nciae.community.domain.DailyLives;
import com.nciae.community.domain.DailyLivesType;
import com.nciae.community.domain.DailyLives_SeperateTypes;
import com.nciae.community.domain.Users;
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
		String sql="select dl.id,dl.isUsed,dl.user_id,dl.guid,dl.customerNotice,dl.address,dl.phone,dl.title,"
					+"dl.content,dl.createDate,dl.updateDate,di.img_path"
					+" from dailylives dl" 
					+" left join dailylives_img di on dl.guid=di.dailylives_guid where dl.guid=? and dl.isUsed<>0";
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
				ft.setUid(rs.getString("user_id"));
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
	public ArrayList<DailyLives> queryAll() {
		// TODO Auto-generated method stub
		
		String sql="select dt.serviceType,dt.style,dl.id,dl.guid,dl.customerNotice,dl.address,"
				+ "dl.phone,dl.title,dl.content,dl.createDate,dl.updateDate,di.img_path"
				 +" from dailylives dl LEFT join dailylives_type dt on dt.id=dl.dailylives_type_id "
				 + "INNER join dailylives_img di on dl.guid=di.dailylives_guid where dl.isUsed=true order by dt.id";
		org.apache.tomcat.util.codec.binary.Base64 base64=new org.apache.tomcat.util.codec.binary.Base64();
		
		PreparedStatement ps=null;
		ResultSet rs=null;
		ArrayList<DailyLives> fts=null;
		try {
			ps=this.dbServiceImpl.connect().prepareStatement(sql);
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
					DailyLivesType dlt=new DailyLivesType();
					dlt.setServiceType(rs.getString("serviceType"));
					dlt.setStyle(rs.getString("style"));
					ft.setDailyLivesType(dlt);
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
			/*String sql="SELECT ft.id,ft.guid,"
				+ "ft.title,ft.content,ft.createDate,ft.phone from "
				+ "users_DailyLives uf inner join "
				+ "DailyLives ft on uf.DailyLivesGuid=ft.guid ";
				
		
		org.apache.tomcat.util.codec.binary.Base64 base64=new org.apache.tomcat.util.codec.binary.Base64();
		
		PreparedStatement ps=null;
		ResultSet rs=null;
		ArrayList<DailyLives> fts=null;
		try {
			ps=this.dbServiceImpl.connect().prepareStatement(sql);
			if(!uid.equals("")){
				sql=sql+"where uf.userId=?";
				ps.setString(1, uid);
			}
			
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
		return fts;*/
	}

	@Override
	public ArrayList<DailyLives> query_By_titleorphone(String title,String phone,String typeId) {
		// TODO Auto-generated method stub
			
		String sql="select dt.serviceType,dt.style,dl.id,dl.guid,dl.customerNotice,dl.address,"
					+ "dl.phone,dl.title,dl.content,dl.createDate,dl.updateDate,di.img_path"
					 +" from dailylives dl LEFT join dailylives_type dt on dt.id=dl.dailylives_type_id "
					 + "left join dailylives_img di on dl.guid=di.dailylives_guid";
	
		if(title.equals("")){
			if(!phone.equals("")){
				sql+=" where dl.isUsed=true and dl.phone=? order by dt.id";
			}else{
				sql+=" where dl.isUsed=true order by dt.id";
			}
		}else{
			if(phone.equals("")){
				sql+=" where dl.isUsed=true and dl.title=? order by dt.id";
			}else{
				sql+=" where dl.isUsed=true and dl.phone=? and dl.title=? order by dt.id";
			}
		}
		org.apache.tomcat.util.codec.binary.Base64 base64=new org.apache.tomcat.util.codec.binary.Base64();
		
		PreparedStatement ps=null;
		ResultSet rs=null;
		ArrayList<DailyLives> fts=null;
		try {
			ps=this.dbServiceImpl.connect().prepareStatement(sql);
			if(title.equals("")){
				if(!phone.equals("")){
					ps.setString(1, phone);
				}
			}else{
				ps.setString(1, new String(base64.encode(title.getBytes("utf-8"))));
				if(!phone.equals("")){
					ps.setString(2, phone);
				}
			}
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
					DailyLivesType dlt=new DailyLivesType();
					dlt.setServiceType(rs.getString("serviceType"));
					dlt.setStyle(rs.getString("style"));
					ft.setDailyLivesType(dlt);
					ArrayList<String> imgs=new ArrayList<String>();
					imgs.add(this.webUrl+rs.getString("img_path"));
					ft.setImages(imgs);
					ft.setId(rs.getInt("id"));
					ft.setCreateDate(new SimpleDateFormat("yyyy-MM-dd").parse(rs.getString("createDate")));
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
	public ArrayList<DailyLives> query_By_titleorphone_notAdmin(String title,String phone,String uid,String typeId) {
		// TODO Auto-generated method stub
			
		String sql="select dt.serviceType,dt.style,dl.id,dl.guid,dl.customerNotice,dl.address,"
					+ "dl.phone,dl.title,dl.content,dl.createDate,dl.updateDate,di.img_path"
					 +" from dailylives dl LEFT join dailylives_type dt on dt.id=dl.dailylives_type_id "
					 + "left join dailylives_img di on dl.guid=di.dailylives_guid";
	
		if(title.equals("")){
			if(!phone.equals("")){
				sql+=" where dl.isUsed=true and dl.phone=? and dl.user_id=? order by dt.id";
			}else{
				sql+=" where dl.isUsed=true and dl.user_id=? order by dt.id";
			}
		}else{
			if(phone.equals("")){
				sql+=" where dl.isUsed=true and dl.title=? and dl.user_id=? order by dt.id";
			}else{
				sql+=" where dl.isUsed=true and dl.phone=? and dl.title=? and dl.user_id=? order by dt.id";
			}
		}
		org.apache.tomcat.util.codec.binary.Base64 base64=new org.apache.tomcat.util.codec.binary.Base64();
		
		PreparedStatement ps=null;
		ResultSet rs=null;
		ArrayList<DailyLives> fts=null;
		try {
			ps=this.dbServiceImpl.connect().prepareStatement(sql);
			if(title.equals("")){
				if(!phone.equals("")){
					ps.setString(1, phone);
					ps.setString(2, uid);
				}else{
					ps.setString(1, uid);
				}
			}else{
				ps.setString(1, new String(base64.encode(title.getBytes("utf-8"))));
				if(!phone.equals("")){
					ps.setString(2, phone);
					ps.setString(3, uid);
				}else{
					ps.setString(2, uid);
				}
			}
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
					DailyLivesType dlt=new DailyLivesType();
					dlt.setServiceType(rs.getString("serviceType"));
					dlt.setStyle(rs.getString("style"));
					ft.setDailyLivesType(dlt);
					ArrayList<String> imgs=new ArrayList<String>();
					imgs.add(this.webUrl+rs.getString("img_path"));
					ft.setImages(imgs);
					ft.setId(rs.getInt("id"));
					ft.setCreateDate(new SimpleDateFormat("yyyy-MM-dd").parse(rs.getString("createDate")));
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
	public ArrayList<DailyLives> queryAllByServiceType(int typeId,int comId) {
		// TODO Auto-generated method stub
		
		String sql="";
		boolean isSurround=false;
		if(comId>-1){
			isSurround=true;
		}
		
		if(isSurround){
			sql="select dl.id,dl.communitys,dl.guid,dl.customerNotice,dl.address,dl.phone,dl.title,dt.style,dt.id as typeId,"
					+"dl.content,dl.createDate,dl.updateDate,di.img_path"
					+" from dailylives_type dt"
					+" LEFT join dailylives dl on dt.id=dl.dailylives_type_id"
					+" inner join dailylives_img di on dl.guid=di.dailylives_guid "
					+ "where dt.id=? and dl.isUsed=true and (communitys like (?) "
					+ "OR communitys LIKE (?) or communitys is NULL) order by "
					+ "dl.id";
		}else{
			sql="select dl.id,dl.communitys,dl.guid,dl.customerNotice,dl.address,"
					+ "dl.phone,dl.title,dt.style,dt.id as typeId,"
					+"dl.content,dl.createDate,dl.updateDate,di.img_path"
					+" from dailylives_type dt"
					+" LEFT join dailylives dl on dt.id=dl.dailylives_type_id"
					+" inner join dailylives_img di on dl.guid=di.dailylives_guid "
					+ "where dt.id=? and dl.isUsed=true order by dl.id";
		}
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
			if(isSurround){
				ps.setString(2,"%"+comId+",%");
				ps.setString(3,"%,"+comId+"%");
			}
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
					DailyLivesType type=new DailyLivesType();
					type.setStyle(rs.getString("style"));
					type.setId(rs.getInt("typeId"));
					ArrayList<String> imgs=new ArrayList<String>();
					imgs.add(this.webUrl+rs.getString("img_path"));
					ft.setImages(imgs);
					ft.setId(rs.getInt("id"));
					ft.setGuid(rs.getString("guid"));				
					ft.setCreateDate(new SimpleDateFormat("yyyy-MM-dd").parse(rs.getString("createDate")));
					ft.setPhone(rs.getString("phone"));
					ft.setAddress(rs.getString("address"));
					ft.setCustomerNotice(rs.getString("customerNotice"));
					ft.setCommunitys(rs.getString("communitys"));
					ft.setDailyLivesType(type);
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
	public ArrayList<DailyLives> queryAllByServiceType_Paging(int typeId,int comId,int pindex,int psize) {
		// TODO Auto-generated method stub
		
		String sql="";
		boolean isSurround=false;
		if(comId>-1){
			isSurround=true;
		}
		
		if(isSurround){
			sql="select dl.id,dl.communitys,dl.guid,dl.customerNotice,dl.address,dl.phone,dl.title,dt.style,dt.id as typeId,"
					+"dl.content,dl.createDate,dl.updateDate,di.img_path"
					+" from dailylives_type dt"
					+" LEFT join dailylives dl on dt.id=dl.dailylives_type_id"
					+" inner join dailylives_img di on dl.guid=di.dailylives_guid "
					+ "where dt.id=? and dl.isUsed=true and (communitys like (?) "
					+ "OR communitys LIKE (?) or communitys is NULL) order by "
					+ "dl.createDate desc limit ?,?";
		}else{
			sql="select dl.id,dl.communitys,dl.guid,dl.customerNotice,dl.address,"
					+ "dl.phone,dl.title,dt.style,dt.id as typeId,"
					+"dl.content,dl.createDate,dl.updateDate,di.img_path"
					+" from dailylives_type dt"
					+" LEFT join dailylives dl on dt.id=dl.dailylives_type_id"
					+" inner join dailylives_img di on dl.guid=di.dailylives_guid "
					+ "where dt.id=? and dl.isUsed=true order by dl.createDate desc limit ?,?";
		}
		org.apache.tomcat.util.codec.binary.Base64 base64=new org.apache.tomcat.util.codec.binary.Base64();
		
		PreparedStatement ps=null;
		ResultSet rs=null;
		ArrayList<DailyLives> fts=null;
		try {
			ps=this.dbServiceImpl.connect().prepareStatement(sql);
			ps.setInt(1, typeId);
			if(isSurround){
				StringBuilder str=new StringBuilder();
				ps.setString(2,"%"+comId+",%");
				ps.setString(3,"%,"+comId+"%");
				ps.setInt(4, pindex);
				ps.setInt(5, psize);
			}else{
				ps.setInt(2, pindex);
				ps.setInt(3, psize);
			}
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
					DailyLivesType type=new DailyLivesType();
					type.setStyle(rs.getString("style"));
					type.setId(rs.getInt("typeId"));
					ArrayList<String> imgs=new ArrayList<String>();
					imgs.add(this.webUrl+rs.getString("img_path"));
					ft.setImages(imgs);
					ft.setId(rs.getInt("id"));
					ft.setGuid(rs.getString("guid"));				
					ft.setCreateDate(new SimpleDateFormat("yyyy-MM-dd").parse(rs.getString("createDate")));
					ft.setPhone(rs.getString("phone"));
					ft.setAddress(rs.getString("address"));
					ft.setCustomerNotice(rs.getString("customerNotice"));
					ft.setCommunitys(rs.getString("communitys"));
					ft.setDailyLivesType(type);
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

	public int queryAllByServiceType_Count(int typeId,int comId) {
		// TODO Auto-generated method stub
		
		String sql="";
		boolean isSurround=false;
		if(comId>-1){
			isSurround=true;
		}
		
		if(isSurround){
			sql="select count(*) as totalCount from (select count(*)"
					+" from dailylives_type dt"
					+" LEFT join dailylives dl on dt.id=dl.dailylives_type_id"
					+" inner join dailylives_img di on dl.guid=di.dailylives_guid"
					+ " where dt.id=? and dl.isUsed=true and (communitys like (?)"
					+ " OR communitys LIKE (?) or communitys is NULL) group by dl.id) dd";
		}else{
			sql="select count(*) as totalCount from (select count(*)"
					+" from dailylives_type dt"
					+" LEFT join dailylives dl on dt.id=dl.dailylives_type_id"
					+" inner join dailylives_img di on dl.guid=di.dailylives_guid "
					+ "where dt.id=? and dl.isUsed=true group by dl.id) dd";
		}
		org.apache.tomcat.util.codec.binary.Base64 base64=new org.apache.tomcat.util.codec.binary.Base64();
		
		PreparedStatement ps=null;
		ResultSet rs=null;
		int fts=-1;
		try {
			ps=this.dbServiceImpl.connect().prepareStatement(sql);
			ps.setInt(1, typeId);
			if(isSurround){
				StringBuilder str=new StringBuilder();
				ps.setString(2,"%"+comId+",%");
				ps.setString(3,"%,"+comId+"%");
			}
			rs=ps.executeQuery();
			
			fts=rs.getInt("totalCount");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			fts=-1;
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
			String sql="insert into DailyLives(guid,dailylives_type_id,customerNotice,address,"
					+ "title,content,phone,createDate,user_id,isMainList,communitys) values(?,?,?,?,?,?,?,?,?,?,?)";
			ps=dbServiceImpl.connect().prepareStatement(sql);
			ps.setString(1, DailyLives.getGuid());
			ps.setInt(2, DailyLives.getDailyLivesId());
			ps.setString(3, DailyLives.getCustomerNotice());
			ps.setString(4, DailyLives.getAddress());
			ps.setString(5, DailyLives.getTitle());
			ps.setString(6, DailyLives.getContent());
			ps.setString(7, DailyLives.getPhone());
			ps.setDate(8, new java.sql.Date(new Date().getTime()));
			ps.setInt(9, Integer.parseInt(DailyLives.getUid()));
			ps.setBoolean(10, DailyLives.isMainList());
			ps.setString(11, DailyLives.getCommunitys());
			
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

	/**
	 * @param id，服务的id
	 * @return true,删除成功；false，删除失败
	 * */
	@Override
	public boolean delete_DailyLivesType_ById(int id){
		boolean returnData=true;
		String sql="delete from dailylives_type where id=?";
		PreparedStatement ps=null;
		try {
			ps=this.dbServiceImpl.connect().prepareStatement(sql);
			ps.setInt(1, id);
			int result=ps.executeUpdate();
			if(result>0){
				returnData=true;
			}else{
				returnData=false;
			}
		} catch (Exception e) {
			returnData=false;
			// TODO: handle exception
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
		
		return returnData;
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

	/**
	 * @return 获取所有的服务类型
	 * */
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
	
	/**
	 * @see 获取所有的服务类型，已经分类好的
	 * @return DailyLives_SeperateTypes
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
	
	/**
	 * @param guid，服务的guid
	 * @return true,删除成功；false，删除失败
	 * */
	@Override
	public boolean delete_DailyLives_ById(String guid){
		boolean returnData=true;
		String sql="update dailylives set isUsed=false where guid=?";
		PreparedStatement ps=null;
		try {
			ps=this.dbServiceImpl.connect().prepareStatement(sql);
			ps.setString(1, guid);
			int result=ps.executeUpdate();
			if(result>0){
				returnData=true;
			}else{
				returnData=false;
			}
		} catch (Exception e) {
			returnData=false;
			// TODO: handle exception
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
		
		return returnData;
	}

	@Override
	public boolean update(DailyLives dl) {
		boolean returnData=true;
		String sql="update dailylives set title=?,content=?,phone=?,address=?,customerNotice=?,updateDate=? where guid=?";
		PreparedStatement ps=null;
		try {
			String dateNow=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
			ps=this.dbServiceImpl.connect().prepareStatement(sql);
			ps.setString(1, dl.getTitle());
			ps.setString(2,dl.getContent());
			ps.setString(3, dl.getPhone());
			ps.setString(4, dl.getAddress());
			ps.setString(5, dl.getCustomerNotice());
			ps.setString(6,dateNow);
			ps.setString(7, dl.getGuid());
			int result=ps.executeUpdate();
			if(result>0){
				returnData=true;
			}else{
				returnData=false;
			}
		} catch (Exception e) {
			returnData=false;
			// TODO: handle exception
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
		
		return returnData;
	}

	@Override
	public boolean deleteImgs(String guid){
		boolean returnData=true;
		String sql="delete from dailylives_img where dailylives_guid=?";
		PreparedStatement ps=null;
		try {
			ps=this.dbServiceImpl.connect().prepareStatement(sql);
			ps.setString(1, guid);
			int result=ps.executeUpdate();
			if(result>0){
				returnData=true;
			}else{
				returnData=false;
			}
		} catch (Exception e) {
			returnData=false;
			// TODO: handle exception
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
		
		return returnData;
	}
	
	@Override
	public ArrayList<Users> queryAllShopUsers(){
		ResultSet rs = null;
		PreparedStatement ps = null;
		ArrayList<Users> list = null;
		try {
			String sql = "select * from users where role='shop'";
			ps = dbServiceImpl.connect().prepareStatement(sql);
			rs = ps.executeQuery();
			list = new ArrayList<Users>();
			while (rs.next()) {
				Users u = new Users();
				u.setId(rs.getInt("id"));
				u.setAddress(rs.getString("address"));
				u.setCity(rs.getString("city"));
				u.setCommunity(rs.getString("community"));
				u.setDistrict(rs.getString("district"));
				u.setEmail(rs.getString("email"));
				u.setEmailPassword(rs.getString("emailPassword"));
				u.setLinkman(rs.getString("linkman"));
				u.setPhone(rs.getString("phone"));
				u.setPostCode(rs.getString("postCode"));
				u.setProvince(rs.getString("province"));
				u.setRealName(rs.getString("realName"));
				u.setSex(rs.getString("sex"));
				u.setUserName(rs.getString("userName"));
				u.setUserPwd(rs.getString("userPwd"));
				u.setRole(rs.getString("role"));
				u.setContentName(rs.getString("contentName"));

				list.add(u);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return list;
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
