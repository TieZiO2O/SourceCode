package com.nciae.community.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import com.nciae.community.dao.IntegralDao;
import com.nciae.community.domain.DailyLives;
import com.nciae.community.domain.Integral;
import com.nciae.community.domain.IntegralCommodity;
import com.nciae.community.domain.IntegralType;
import com.nciae.community.service.impl.DatabaseServiceImpl;

public class IntegralDaoImpl implements IntegralDao {

	private DatabaseServiceImpl dbServiceImpl;
	
	public DatabaseServiceImpl getdbServiceImpl() {
		return dbServiceImpl;
	}

	public void setdbServiceImpl(DatabaseServiceImpl dbServiceImpl) {
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
	public boolean AddIntegral(Integral integral) {
		// TODO Auto-generated method stub
		boolean returnData=true;
		
		String sql="update Integral set fraction=fraction+? where userId=?";
		PreparedStatement ps=null;
		try {
			ps=dbServiceImpl.connect().prepareStatement(sql);
			ps.setInt(1, integral.getFraction());
			ps.setInt(2, integral.getUserId());
			
			int result=ps.executeUpdate();
			
			if (result>0) {
				returnData=true;
			}
		} catch (Exception e) {
			returnData=false;
			// TODO: handle exception
		}finally{
			try {
				dbServiceImpl.close();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			if(ps!=null){
				try {
					ps.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		return returnData;
	}
	
	/*
	 * 减去分数
	 * */
	@Override
	public boolean SubtractIntegral(Integral integral) {
		// TODO Auto-generated method stub
		
		boolean returnData=true;
		String sql="update Integral set fraction=fraction-? where userId=?";
		PreparedStatement ps=null;
		try {
			ps=dbServiceImpl.connect().prepareStatement(sql);
			ps.setInt(1, integral.getFraction());
			ps.setInt(2, integral.getUserId());
			
			int result=ps.executeUpdate();
			
			if (result>0) {
				returnData=true;
			}
		} catch (Exception e) {
			returnData=false;
			// TODO: handle exception
		}finally{
			try {
				dbServiceImpl.close();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			if(ps!=null){
				try {
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
	public Integral QueryIntegral(String userId){
		Integral returnData=null;
		String sql="select * from Integral where userId=?";
		PreparedStatement ps=null;
		try {
			ps=dbServiceImpl.connect().prepareStatement(sql);
			ps.setInt(1,Integer.parseInt(userId));
			
			ResultSet result=ps.executeQuery();
			
			if (result!=null) {
				
				while(result.next()){
					returnData=new Integral();
					returnData.setId(result.getInt("id"));
					returnData.setFraction(result.getInt("fraction"));
					returnData.setUserId(result.getInt("userId"));
					returnData.setUsed(result.getBoolean("isUsed"));
				}
			}
		} catch (Exception e) {
			returnData=null;
			// TODO: handle exception
		}finally{
			try {
				dbServiceImpl.close();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			if(ps!=null){
				try {
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
	public boolean InsertIntegral(Integral integral){
		boolean returnData=true;
		String sql="insert into Integral(userId,fraction,isUsed) values(?,?,?)";
		PreparedStatement ps=null;
		try {
			ps=dbServiceImpl.connect().prepareStatement(sql);
			ps.setInt(2, integral.getFraction());
			ps.setInt(1, integral.getUserId());
			ps.setBoolean(3, true);
			
			int result=ps.executeUpdate();
			
			if (result>0) {
				returnData=true;
			}
		} catch (Exception e) {
			returnData=false;
			// TODO: handle exception
		}finally{
			try {
				dbServiceImpl.close();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			if(ps!=null){
				try {
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
	public IntegralType QueryOneByType(String type){
		
		IntegralType Itype=null;
		PreparedStatement ps=null;
		
		try {
			String sql="select * from integral_type where operateType=?";
			ps=this.dbServiceImpl.connect().prepareCall(sql);
			ps.setString(1, type);
			
			ResultSet set=ps.executeQuery();
			Itype=new IntegralType();
			while(set.next()){
				Itype.setId(set.getInt("id"));
				Itype.setOperateType(set.getString("operatetype"));
				Itype.setFraction(set.getInt("fraction"));
				Itype.setAddOrdecrease(set.getBoolean("addOrdecrease"));
				Itype.setExtened(set.getString("extened"));
			}
			set.close();
			
		} catch (Exception e) {
			Itype=null;
			// TODO: handle exception
		}finally{
			if(ps!=null){
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
		
		return Itype;
	}
	
	public ArrayList<IntegralCommodity> queryAllByCommodity() {
		// TODO Auto-generated method stub
		String sql="select ic.id,ic.guid,ic.title,ic.content,ic.limitAmount,ic.phone,ic.fraction,"
				+ "ic.startTime,ic.endTime,ic.isUsed,ici.img_path "
				+ "from integral_commodity_detail ic left join "
				+ "integral_commodity_img ici on ic.guid=ici.commodity_guid where ic.isUsed=1 group by ic.guid";
		org.apache.tomcat.util.codec.binary.Base64 base64=new org.apache.tomcat.util.codec.binary.Base64();
		
		PreparedStatement ps=null;
		ResultSet rs=null;
		ArrayList<IntegralCommodity> fts=null;
		try {
			ps=this.dbServiceImpl.connect().prepareStatement(sql);
			rs=ps.executeQuery();
			
			fts=new ArrayList<IntegralCommodity>();
			while(rs.next()){

				IntegralCommodity ft=new IntegralCommodity();
				ft.setId(rs.getInt("id"));
				ft.setGuid(rs.getString("guid"));
				ft.setPhone(rs.getString("phone"));
				ft.setImg(rs.getString("img_path"));
				ft.setFraction(rs.getInt("fraction"));
				ft.setLimitAmount(rs.getInt("limitAmount"));
				ft.setStartTime(rs.getString("startTime"));
				ft.setEndTime(rs.getString("endTime"));
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

	public IntegralCommodity queryByGuid(String guid) {
		// TODO Auto-generated method stub
		String sql="select ic.id,ic.guid,ic.title,ic.content,ic.limitAmount,ic.phone,"
				+ "ic.startTime,ic.endTime,ic.isUsed,ici.img_path "
				+ "from integral_commodity_detail ic left join "
				+ "integral_commodity_img ici on ic.guid=ici.commodity_guid where ic.isUsed=1 and ic.guid=?";
		org.apache.tomcat.util.codec.binary.Base64 base64=new org.apache.tomcat.util.codec.binary.Base64();
		PreparedStatement ps=null;
		ResultSet rs=null;
		IntegralCommodity ft=null;
		try {
			ps=this.dbServiceImpl.connect().prepareStatement(sql);
			ps.setString(1, guid);
			rs=ps.executeQuery();
		
			
			ft=new IntegralCommodity();
			ArrayList<String> imgs=new ArrayList<String>();
			while(rs.next()){
				imgs.add(this.webUrl+rs.getString("img_path"));
				ft.setId(rs.getInt("id"));
				ft.setLimitAmount(rs.getInt("limitAmount"));
				ft.setGuid(rs.getString("guid"));
				ft.setStartTime(rs.getString("startTime"));
				ft.setEndTime(rs.getString("endTime"));
				ft.setPhone(rs.getString("phone"));
				ft.setTitle(new String(base64.decode(rs.getString("title")),"utf-8"));
				String content=rs.getString("content");
				ft.setContent(content!=null?new String(base64.decode(rs.getString("content")),"utf-8"):"");
			}
			ft.setImgs(imgs);
			
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

}
