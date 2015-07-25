package com.nciae.community.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.nciae.community.dao.PropertyDao;
import com.nciae.community.domain.Bulletin;
import com.nciae.community.domain.Property;
import com.nciae.community.domain.Reply;
import com.nciae.community.domain.Reply1;
import com.nciae.community.domain.Users;
import com.nciae.community.service.impl.DatabaseServiceImpl;

public class PropertyDaoImpl implements PropertyDao{
	private DatabaseServiceImpl dbServiceImpl;
	public DatabaseServiceImpl getDbServiceImpl() {
		return dbServiceImpl;
	}

	public void setDbServiceImpl(DatabaseServiceImpl dbServiceImpl) {
		this.dbServiceImpl = dbServiceImpl;
	}

	@Override
	public String property_login(String name, String password) throws Exception {
		// TODO Auto-generated method stub
		ResultSet rs=null;
		PreparedStatement ps = null;
		try {
			String sql="select * from users where userName=? and userPwd= ?";
			ps=dbServiceImpl.connect().prepareStatement(sql);
			ps.setString(1, name);
			ps.setString(2, password);
			rs=ps.executeQuery();
			if(rs.next()){
				if(rs.getString("role").equals(Users.ROLE_ADMIN)){
					return Users.ROLE_ADMIN;
				}
				else if(rs.getString("role").equals(Users.ROLE_SHOP)){
					return Users.ROLE_SHOP;
				}
				else{
					return Users.ROLE_PROPERTY;
				}
			}
			else{
				return "sorry";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "sorry";
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
	public Users selectPropertyInfo(Integer id) throws Exception {
		// TODO Auto-generated method stub
		Users user = new Users();
		ResultSet rs=null;
		PreparedStatement ps = null;
		try {
			String sql="select * from users where id=?";
			ps=dbServiceImpl.connect().prepareStatement(sql);
			ps.setInt(1, id);
			//ps.setString(2, password);
			rs=ps.executeQuery();
			if(rs.next()){
				user.setUserName(rs.getString("userName"));
				user.setSex(rs.getString("sex"));
				user.setRealName(rs.getString("realName"));
				user.setProvince(rs.getString("province"));
				user.setPostCode(rs.getString("postCode"));
				user.setPhone(rs.getString("phone"));
				user.setLinkman(rs.getString("linkman"));
				user.setEmail(rs.getString("email"));
				user.setDistrict(rs.getString("district"));
				user.setCommunity(rs.getString("community"));
				user.setCity(rs.getString("city"));
				user.setAddress(rs.getString("address"));
				user.setContentName(rs.getString("contentName"));
				user.setShopType(rs.getByte("shopType"));
				user.setShopLogo(rs.getString("shopLogo"));
				//user.setAboutUs(rs.getString("aboutUs").trim());
				user.setAboutUs(rs.getString("aboutUs"));
				return user;
			}
			else{
				return null;
				
			}
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
				return null;
				
			}
		}
	}

	@Override
	public boolean updatePropertyInfo(Users property, Integer id) throws Exception {
		// TODO Auto-generated method stub
		ResultSet rs=null;
		PreparedStatement pstm = null;
		//conn=new DBClass().getconn();
		try{
		String sql="update users set phone=?,linkman=?,address=?,realName=?,aboutUs=? where id=?";
		pstm=dbServiceImpl.connect().prepareStatement(sql);
		//pstm=conn.prepareStatement(sql);
		pstm.setString(1, property.getPhone());
		pstm.setString(2,property.getLinkman() );
		pstm.setString(3, property.getAddress());
		pstm.setString(4, property.getRealName());
		pstm.setString(5, property.getAboutUs());
		pstm.setInt(6, id);
		int i=pstm.executeUpdate();
		//DBClass.dbClose(conn, pstm, rs);
		if(i>=1)
		{
			return true;
		}
		else
		{
			return false;
		}
	} catch (Exception e) {
		e.printStackTrace();
		return false;
	} finally{
		try {
			dbServiceImpl.close();
			if(rs!=null){
				rs.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
			
		}
	}
	}

	@Override
	public int selectPropertyId(String propertyname) throws Exception {
		// TODO Auto-generated method stub
		ResultSet rs=null;
		PreparedStatement ps = null;
		try {
			String sql="select * from users where userName=?";
			ps=dbServiceImpl.connect().prepareStatement(sql);
			ps.setString(1, propertyname);
			//ps.setString(2, password);
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
				return -1;
				
			}
		}
	}

	@Override
	public boolean property_changePwd(Integer id, String propertypwd)
			throws Exception {
		// TODO Auto-generated method stub
		ResultSet rs=null;
		PreparedStatement pstm = null;
		//conn=new DBClass().getconn();
		try{
		String sql="update users set userPwd=? where id=?";
		pstm=dbServiceImpl.connect().prepareStatement(sql);
		//pstm=conn.prepareStatement(sql);
		pstm.setString(1, propertypwd);
		pstm.setInt(2, id);
		int i=pstm.executeUpdate();
		//DBClass.dbClose(conn, pstm, rs);
		if(i>=1)
		{
			return true;
		}
		else
		{
			return false;
		}
	} catch (Exception e) {
		e.printStackTrace();
		return false;
	} finally{
		try {
			dbServiceImpl.close();
			if(rs!=null){
				rs.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
			
		}
	}
	}

	@Override
	public boolean property_uploadPhoto(Integer id, String photopath)
			throws Exception {
		ResultSet rs=null;
		PreparedStatement pstm = null;
		//conn=new DBClass().getconn();
		try{
		String sql="update users set shopLogo=? where id=?";
		pstm=dbServiceImpl.connect().prepareStatement(sql);
		//pstm=conn.prepareStatement(sql);
		pstm.setString(1, photopath);
		pstm.setInt(2, id);
		int i=pstm.executeUpdate();
		//DBClass.dbClose(conn, pstm, rs);
		if(i>=1)
		{
			return true;
		}
		else
		{
			return false;
		}
	} catch (Exception e) {
		e.printStackTrace();
		return false;
	} finally{
		try {
			dbServiceImpl.close();
			if(rs!=null){
				rs.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
			
		}
	}
	}

	@Override
	public ArrayList<Object> selectBulletin(int communityid) throws Exception {
		// TODO Auto-generated method stub
		ResultSet rs=null;
		PreparedStatement ps = null;
		ArrayList<Object> list = null;
		try {
			String sql="select * from bulletin where communityId=?";
			ps=dbServiceImpl.connect().prepareStatement(sql);
			ps.setInt(1, communityid);
			rs=ps.executeQuery();
			list = new ArrayList<Object>();
			while(rs.next()){
				Bulletin bulletin=new Bulletin();
				bulletin.setId(rs.getInt("id"));
				bulletin.setTitle(rs.getString("title"));
				bulletin.setBulletinInfo(rs.getString("bulletinInfo"));
				bulletin.setCommitTime(rs.getString("commitTime"));
				list.add(bulletin);
			}

		} catch (Exception e) {
			e.printStackTrace();
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
		return list;
	}

	@Override
	public boolean addBulletin(Bulletin bulletin) throws Exception {
		// TODO Auto-generated method stub
		ResultSet rs=null;
		PreparedStatement ps = null;
		try {
			String sql="insert into bulletin(bulletinInfo,title,commitTime,communityId) values(?,?,?,?)";
			ps=dbServiceImpl.connect().prepareStatement(sql);
			ps.setString(1, bulletin.getBulletinInfo());
			ps.setString(2, bulletin.getTitle());
			ps.setString(3, bulletin.getCommitTime());
			ps.setInt(4, bulletin.getCommunityId());
			int i=ps.executeUpdate();
			if(i>0){
				return true;
			}
			else{
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
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
	public boolean deleteBulletin(int bulletinid) throws Exception {
		// TODO Auto-generated method stub
		ResultSet rs=null;
		PreparedStatement ps = null;
		try {
			String sql="delete from bulletin where id=?";
			ps=dbServiceImpl.connect().prepareStatement(sql);
			ps.setInt(1, bulletinid);
			int i=ps.executeUpdate();
			if(i>0){
				return true;
			}
			else{
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
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
	public int selectCommunityId(String propertyname) throws Exception {
		// TODO Auto-generated method stub
		ResultSet rs=null;
		PreparedStatement ps = null;
		try {
			String sql="select id from community where communityName=(select community from users where userName=?)";
			ps=dbServiceImpl.connect().prepareStatement(sql);
			ps.setString(1, propertyname);
			//ps.setString(2, password);
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
				return -1;
				
			}
		}
	}

	@Override
	public ArrayList<Object> selectComments(int userid) throws Exception {
		// TODO Auto-generated method stub
		ResultSet rs=null;
		ResultSet rs1=null;
		PreparedStatement ps = null;
		PreparedStatement ps1 = null;
		ArrayList<Object> list = null;
		try {
			String sql="select * from reply where communityId=(select id from community where communityName=(select community from users where id=?))";
			String sql1="select memLogName from member where id=?";
			ps=dbServiceImpl.connect().prepareStatement(sql);
			
			ps.setInt(1, userid);
			rs=ps.executeQuery();
			list = new ArrayList<Object>();
			while(rs.next()){
				Reply1 reply=new Reply1();
				reply.setId(rs.getInt("id"));
				reply.setMemId(rs.getInt("memId"));
				reply.setCommitTime(rs.getString("commitTime"));
				reply.setMsg(rs.getString("msg"));
				ps1=dbServiceImpl.connect().prepareStatement(sql1);
				ps1.setInt(1, rs.getInt("memId"));
				rs1=ps1.executeQuery();
				while(rs1.next())
				{
					reply.setMemName(rs1.getString("memLogName"));
				}
				list.add(reply);
			}

		} catch (Exception e) {
			e.printStackTrace();
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
		return list;
	}

	@Override
	public String selectMemName(int memid) throws Exception {
		// TODO Auto-generated method stub
		ResultSet rs=null;
		PreparedStatement ps = null;
		try {
			String sql="select memLogName from member where id=?";
			ps=dbServiceImpl.connect().prepareStatement(sql);
			ps.setInt(1, memid);
			//ps.setString(2, password);
			rs=ps.executeQuery();
			if(rs.next()){
				return rs.getString("memLogName");
			}
			else{
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally{
			try {
				dbServiceImpl.close();
//				if(rs!=null){
//					rs.close();
//				}
			} catch (Exception e) {
				e.printStackTrace();
				return null;
				
			}
		}
	}
	
	@Override
	public boolean deleteProperty(Integer id) throws Exception {
		PreparedStatement ps = null;
		try {
			String sql="delete from users where id = ?";
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
	public boolean addProperty(Users user) throws Exception {
		PreparedStatement ps = null;
		try {
			//String sql="insert into users (userName,userPwd,realName,sex,linkman,phone,email,emailPassword,postCode,province,city,district,community,address,role,contentName) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			//String sql="insert into users (userName,userPwd,realName,sex,linkman,phone,email,postCode,province,city,district,community,address,role,contentName) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			//String sql="insert into users (userName,userPwd,realName,sex,linkman,phone,email,postCode,province,city,district,community,address,role,contentName,shopLogo) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			String sql="insert into users (userName,userPwd,realName,sex,linkman,phone,email,postCode,province,city,district,community,address,role) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			ps=dbServiceImpl.connect().prepareStatement(sql);
			ps.setString(1, user.getUserName());
			//ps.setString(2, "000000");//初始密码000000
			///////////0626
			ps.setString(2, user.getUserPwd());//初始密码000000nciae
			ps.setString(3, user.getRealName());
			ps.setString(4, user.getSex());
			ps.setString(5, user.getLinkman());
			ps.setString(6, user.getPhone());
			ps.setString(7, user.getEmail());
			//ps.setString(8, user.getEmailPassword());
			ps.setString(8, user.getPostCode());
			ps.setString(9, user.getProvince());
			ps.setString(10, user.getCity());
			ps.setString(11, user.getDistrict());
			ps.setString(12, user.getCommunity());
			ps.setString(13, user.getAddress());
			//ps.setString(14, user.getRole());//property
			ps.setString(14, "property");
			//ps.setString(15, user.getContentName());
			//ps.setString(16, user.getShopLogo());
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
	public ArrayList<Users> queryPropertyByCC(String city, String community)
			throws Exception {
		ResultSet rs=null;
		PreparedStatement ps = null;
		ArrayList<Users> list = null;
		try {
			if(city!=null && city!="" && community!=null && community!=""){
				String sql="select * from users where city=? and community=? and role=?";
				ps=dbServiceImpl.connect().prepareStatement(sql);
				ps.setString(1, city);
				ps.setString(2, community);
				ps.setString(3, "property");//"property"
			}else if(city!=null && city!=""){
				String sql="select * from users where city=? and role=?";
				ps=dbServiceImpl.connect().prepareStatement(sql);
				ps.setString(1, city);
				ps.setString(2, "property");//"property"
			}else if(community!=null && community!=""){
				String sql="select * from users where community=? and role=?";
				ps=dbServiceImpl.connect().prepareStatement(sql);
				ps.setString(1, community);
				ps.setString(2, "property");//"property"
			}else{
				String sql="select * from users where role=?";
				ps=dbServiceImpl.connect().prepareStatement(sql);
				ps.setString(1, "property");//"property"
			}
			rs=ps.executeQuery();
			list = new ArrayList<Users>();
			while(rs.next()){
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
	public ArrayList<Object> queryMyCommunity() throws Exception {
		ResultSet rs=null;
		PreparedStatement ps = null;
		ArrayList<Object> list = null;
		try {
			String sql="select distinct community from users where role=?";
			ps=dbServiceImpl.connect().prepareStatement(sql);
			ps.setString(1, "property");
			rs=ps.executeQuery();
			list = new ArrayList<Object>();
			while(rs.next()){
				list.add(rs.getString("community"));
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
	public boolean deleteComments(int commentsid) throws Exception {
		ResultSet rs=null;
		PreparedStatement ps = null;
		try {
			String sql="delete from reply where id=?";
			ps=dbServiceImpl.connect().prepareStatement(sql);
			ps.setInt(1, commentsid);
			int i=ps.executeUpdate();
			if(i>0){
				return true;
			}
			else{
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
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
