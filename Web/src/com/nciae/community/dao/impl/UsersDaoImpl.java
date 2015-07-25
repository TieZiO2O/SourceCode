package com.nciae.community.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.nciae.community.dao.UsersDao;
import com.nciae.community.domain.Bulletin;
import com.nciae.community.domain.Community;
import com.nciae.community.domain.ShopImg;
import com.nciae.community.domain.Users;
import com.nciae.community.service.impl.DatabaseServiceImpl;

//import com.nciae.ebookshop.dao.DBClass;

public class UsersDaoImpl implements UsersDao {

	private DatabaseServiceImpl dbServiceImpl;

	public DatabaseServiceImpl getDbServiceImpl() {
		return dbServiceImpl;
	}

	public void setDbServiceImpl(DatabaseServiceImpl dbServiceImpl) {
		this.dbServiceImpl = dbServiceImpl;
	}

	@Override
	public String login(String name, String password) throws Exception {
		ResultSet rs = null;
		PreparedStatement ps = null;
		try {
			String sql = "select * from users where userName=? and userPwd= ?";
			ps = dbServiceImpl.connect().prepareStatement(sql);
			ps.setString(1, name);
			ps.setString(2, password);
			rs = ps.executeQuery();
			if (rs.next()) {
				if (rs.getString("role").equals(Users.ROLE_ADMIN)) {
					return Users.ROLE_ADMIN;
				} else if (rs.getString("role").equals(Users.ROLE_SHOP)) {
					return Users.ROLE_SHOP;
				} else {
					return Users.ROLE_PROPERTY;
				}
			} else {
				return "sorry";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "sorry";
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

	@Override
	public boolean deleteShopUser(Integer id) throws Exception {
		PreparedStatement ps = null;
		try {
			String sql = "delete from users where id = ?";
			ps = dbServiceImpl.connect().prepareStatement(sql);
			ps.setInt(1, id);
			ps.executeUpdate();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			try {
				dbServiceImpl.close();
				if (ps != null) {
					ps.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		}
	}

	@Override
	public boolean addShopUser(Users user) throws Exception {
		PreparedStatement ps = null;
		try {
			// String
			// sql="insert into users
			// (userName,userPwd,realName,sex,linkman,phone,email,emailPassword,postCode,province,city,district,community,address,role,contentName)
			// values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			String sql = "insert into users (userName,userPwd,realName,sex,linkman,phone,email,postCode,province,city,district,community,address,role,contentName,shopType) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			// String
			// sql="insert into users
			// (userName,userPwd,realName,sex,linkman,phone,email,postCode,province,city,district,community,address,role,contentName,shopLogo)
			// values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			ps = dbServiceImpl.connect().prepareStatement(sql);
			ps.setString(1, user.getUserName());
			// ps.setString(2, "000000");//初始密码000000
			ps.setString(2, user.getUserPwd());// 初始密码000000nciae/////0626加密
			ps.setString(3, user.getRealName());
			ps.setString(4, user.getSex());
			ps.setString(5, user.getLinkman());
			ps.setString(6, user.getPhone());
			ps.setString(7, user.getEmail());
			// ps.setString(8, user.getEmailPassword());
			ps.setString(8, user.getPostCode());
			ps.setString(9, user.getProvince());
			ps.setString(10, user.getCity());
			ps.setString(11, user.getDistrict());
			ps.setString(12, user.getCommunity());
			ps.setString(13, user.getAddress());
			// ps.setString(14, user.getRole());//
			ps.setString(14, "shop");//
			ps.setString(15, user.getContentName());
			ps.setByte(16, user.getShopType());
			// ps.setString(16, user.getShopLogo());
			ps.executeUpdate();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			try {
				dbServiceImpl.close();
				if (ps != null) {
					ps.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		}
	}

	@Override
	public ArrayList<Users> queryAllShopUser() throws Exception {
		ResultSet rs = null;
		PreparedStatement ps = null;
		ArrayList<Users> list = null;
		try {
			String sql = "select * from users";
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
				u.setContentName(rs.getString("content"));

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

	@Override
	public boolean deleteCommunity(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addCommunity(Community community) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ArrayList<Community> queryAllCommunity() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean changePwd(Integer id, String userpwd) throws Exception {
		// TODO Auto-generated method stub
		ResultSet rs = null;
		PreparedStatement pstm = null;
		try {
			String sql = "update users set userPwd=? where id=?";
			pstm = dbServiceImpl.connect().prepareStatement(sql);
			pstm.setString(1, userpwd);
			pstm.setInt(2, id);
			int i = pstm.executeUpdate();
			if (i >= 1) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			try {
				dbServiceImpl.close();
				if (rs != null) {
					rs.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
				return false;

			}
		}
	}

	@Override
	public Users selectShopUser(Integer id) throws Exception {
		// TODO Auto-generated method stub
		Users user = new Users();
		ResultSet rs = null;
		PreparedStatement ps = null;
		try {
			String sql = "select * from users where id=?";
			ps = dbServiceImpl.connect().prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if (rs.next()) {
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
				user.setShopLogo(rs.getString("shopLogo"));
				user.setAboutUs(rs.getString("aboutUs"));
				user.setCustomerNotice(rs.getString("customerNotice"));
				user.setShopInfo(rs.getString("shopInfo"));
				user.setShopType(rs.getByte("shopType"));
				return user;
			} else {
				return null;

			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			try {
				dbServiceImpl.close();
				if (rs != null) {
					rs.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
				return null;

			}
		}
	}

	@Override
	public boolean updateShopUser(Users user, Integer id) throws Exception {
		// TODO Auto-generated method stub
		ResultSet rs = null;
		PreparedStatement pstm = null;
		try {
			String sql = "update users set phone=?,linkman=?,address=?,realName=?,shopType=?,shopInfo=?,customerNotice=?,aboutUs=? where id=?";
			pstm = dbServiceImpl.connect().prepareStatement(sql);
			// pstm=conn.prepareStatement(sql);
			pstm.setString(1, user.getPhone());
			pstm.setString(2, user.getLinkman());
			pstm.setString(3, user.getAddress());
			pstm.setString(4, user.getRealName());
			pstm.setByte(5, user.getShopType());
			pstm.setString(6, user.getShopInfo());
			pstm.setString(7, user.getCustomerNotice());
			pstm.setString(8, user.getAboutUs());
			pstm.setInt(9, id);
			int i = pstm.executeUpdate();
			// DBClass.dbClose(conn, pstm, rs);
			if (i >= 1) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			try {
				dbServiceImpl.close();
				if (rs != null) {
					rs.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
				return false;

			}
		}
	}

	@Override
	public int selectShopUserId(String username) throws Exception {
		ResultSet rs = null;
		PreparedStatement ps = null;
		try {
			String sql = "select * from users where userName=?";
			ps = dbServiceImpl.connect().prepareStatement(sql);
			ps.setString(1, username);
			// ps.setString(2, password);
			rs = ps.executeQuery();
			if (rs.next()) {
				return rs.getInt("id");
			} else {
				return -1;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		} finally {
			try {
				dbServiceImpl.close();
				if (rs != null) {
					rs.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
				return -1;

			}
		}

	}

	@Override
	public boolean uploadPhoto(Integer id, String photopath) throws Exception {
		// TODO Auto-generated method stub
		ResultSet rs = null;
		PreparedStatement pstm = null;
		// conn=new DBClass().getconn();
		try {
			String sql = "update users set shopLogo=? where id=?";
			pstm = dbServiceImpl.connect().prepareStatement(sql);
			// pstm=conn.prepareStatement(sql);
			pstm.setString(1, photopath);
			pstm.setInt(2, id);
			int i = pstm.executeUpdate();
			// DBClass.dbClose(conn, pstm, rs);
			if (i >= 1) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			try {
				dbServiceImpl.close();
				if (rs != null) {
					rs.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
				return false;

			}
		}
	}

	@Override
	public ArrayList<ShopImg> selectShopImg(int userid) throws Exception {
		// TODO Auto-generated method stub
		ResultSet rs = null;
		PreparedStatement ps = null;
		ArrayList<ShopImg> shopimglist = null;
		try {
			String sql = "select * from shop_img where userId=?";
			ps = dbServiceImpl.connect().prepareStatement(sql);
			ps.setInt(1, userid);
			rs = ps.executeQuery();
			shopimglist = new ArrayList<ShopImg>();
			while (rs.next()) {
				ShopImg shopimg = new ShopImg();
				shopimg.setId(rs.getInt("Id"));
				shopimg.setImgUrl(rs.getString("imgUrl"));
				shopimg.setUserId(userid);
				shopimglist.add(shopimg);
			}
		} catch (Exception e) {
			e.printStackTrace();
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
		return shopimglist;
	}

	@Override
	public boolean deleteShopImg(int photoid) throws Exception {
		// TODO Auto-generated method stub
		ResultSet rs = null;
		PreparedStatement ps = null;
		try {
			String sql = "delete from shop_img where id=?";
			ps = dbServiceImpl.connect().prepareStatement(sql);
			ps.setInt(1, photoid);
			int i = ps.executeUpdate();
			if (i > 0) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
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

	@Override
	public boolean addShopImg(ShopImg shopimg) throws Exception {
		// TODO Auto-generated method stub
		ResultSet rs = null;
		PreparedStatement ps = null;
		try {
			String sql = "insert into shop_img(imgUrl,userId) values(?,?)";
			ps = dbServiceImpl.connect().prepareStatement(sql);
			ps.setString(1, shopimg.getImgUrl());
			ps.setInt(2, shopimg.getUserId());
			int i = ps.executeUpdate();
			if (i > 0) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
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

	@Override
	public int limitUploadTime(int userid) throws Exception {
		// TODO Auto-generated method stub
		ResultSet rs = null;
		PreparedStatement ps = null;
		ArrayList<ShopImg> shopimglist = null;
		int i = 0;
		try {
			String sql = "select * from shop_img where userId=?";
			ps = dbServiceImpl.connect().prepareStatement(sql);
			ps.setInt(1, userid);
			rs = ps.executeQuery();
			shopimglist = new ArrayList<ShopImg>();
			while (rs.next()) {
				i++;
			}
		} catch (Exception e) {
			e.printStackTrace();
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
		return i;
	}

	@Override
	public String logout(String name) throws Exception {
		ResultSet rs = null;
		PreparedStatement ps = null;
		PreparedStatement psupd = null;
		try {
			String sql = "select * from users where userName=?";

			String sqlupd = "update users set isLogin=? where userName=?";

			ps = dbServiceImpl.connect().prepareStatement(sql);
			ps.setString(1, name);
			rs = ps.executeQuery();
			if (rs.next()) {
				psupd = dbServiceImpl.connect().prepareStatement(sqlupd);
				psupd.setByte(1, (byte) 0);
				psupd.setString(2, name);
				psupd.executeUpdate();
				return "ok";
			} else {
				return "sorry";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "sorry";
		} finally {
			try {
				dbServiceImpl.close();
				if (rs != null) {
					rs.close();
				}
				ps.close();
				psupd.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public boolean addShopLogo(Integer id, String path) throws Exception {
		PreparedStatement ps = null;
		try {
			String sql = "update users set shopLogo=? where id = ?";
			ps = dbServiceImpl.connect().prepareStatement(sql);
			ps.setString(1, path);
			ps.setInt(2, id);
			ps.executeUpdate();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			try {
				dbServiceImpl.close();
				if (ps != null) {
					ps.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		}
	}

	@Override
	public ArrayList<Users> queryShopUserByCityAndCommunity(String city, String community) throws Exception {
		ResultSet rs = null;
		PreparedStatement ps = null;
		ArrayList<Users> list = null;
		try {
			if (city != null && city != "" && community != null && community != "") {
				String sql = "select * from users where city=? and community=? and role=?";
				ps = dbServiceImpl.connect().prepareStatement(sql);
				ps.setString(1, city);
				ps.setString(2, community);
				ps.setString(3, "shop");// "shop"
			} else if (city != null && city != "") {
				String sql = "select * from users where city=? and role=?";
				ps = dbServiceImpl.connect().prepareStatement(sql);
				ps.setString(1, city);
				ps.setString(2, "shop");// "shop"
			} else if (community != null && community != "") {
				String sql = "select * from users where community=? and role=?";
				ps = dbServiceImpl.connect().prepareStatement(sql);
				ps.setString(1, community);
				ps.setString(2, "shop");// "shop"
			} else {
				String sql = "select * from users where role=?";
				ps = dbServiceImpl.connect().prepareStatement(sql);
				ps.setString(1, "shop");// "shop"
			}
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
				u.setShopType(rs.getByte("shopType"));
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

	@Override
	public ArrayList<Users> queryUserByCCR(String city, String community, String role) {
		ResultSet rs = null;
		PreparedStatement ps = null;
		ArrayList<Users> list = null;
		try {
			if (city != null && city != "" && community != null && community != "") {
				String sql = "select * from users where city=? and community=? and role=?";
				ps = dbServiceImpl.connect().prepareStatement(sql);
				ps.setString(1, city);
				ps.setString(2, community);
				ps.setString(3, role);// "shop"
			} else if (city != null && city != "") {
				String sql = "select * from users where city=? and role=?";
				ps = dbServiceImpl.connect().prepareStatement(sql);
				ps.setString(1, city);
				ps.setString(2, role);// "shop"
			} else if (community != null && community != "") {
				String sql = "select * from users where community=? and role=?";
				ps = dbServiceImpl.connect().prepareStatement(sql);
				ps.setString(1, community);
				ps.setString(2, role);// "shop"
			} else {
				String sql = "select * from users where role=?";
				ps = dbServiceImpl.connect().prepareStatement(sql);
				ps.setString(1, role);// "shop"
			}
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

	@Override
	public ArrayList<Object> queryMyCommunity() throws Exception {
		ResultSet rs = null;
		PreparedStatement ps = null;
		ArrayList<Object> list = null;
		try {
			String sql = "select distinct community from users where role=?";
			ps = dbServiceImpl.connect().prepareStatement(sql);
			ps.setString(1, "shop");
			rs = ps.executeQuery();
			list = new ArrayList<Object>();
			while (rs.next()) {
				list.add(rs.getString("community"));
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

	@Override
	public ArrayList<Users> queryUserByCCSC(String city, String community, String shopType, String contentName)
			throws Exception {
		ResultSet rs = null;
		PreparedStatement ps = null;
		ArrayList<Users> list = null;
		try {
			if (!city.equals("不限") && community != null && community != "" && !shopType.equals("请选择")) {
				String sql = "select * from users where city=? and community=? and role=? and shopType=? and contentName=?";
				ps = dbServiceImpl.connect().prepareStatement(sql);
				ps.setString(1, city);
				ps.setString(2, community);
				ps.setString(3, "shop");
				ps.setByte(4, Byte.parseByte(shopType));
				ps.setString(5, contentName);
			} else if (!city.equals("不限") && community != null && community != "") {
				String sql = "select * from users where city=? and community=? and role=?";
				ps = dbServiceImpl.connect().prepareStatement(sql);
				ps.setString(1, city);
				ps.setString(2, community);
				ps.setString(3, "shop");
			} else if (!city.equals("不限") && !shopType.equals("请选择")) {
				String sql = "select * from users where role=? and city=? and shopType=? and contentName=?";
				ps = dbServiceImpl.connect().prepareStatement(sql);
				ps.setString(1, "shop");
				ps.setString(2, city);
				ps.setByte(3, Byte.parseByte(shopType));
				ps.setString(4, contentName);
			} else if (community != null && community != "" && !shopType.equals("请选择")) {
				String sql = "select * from users where role=? and community=? and shopType=? and contentName=?";
				ps = dbServiceImpl.connect().prepareStatement(sql);
				ps.setString(1, "shop");
				ps.setString(2, community);
				ps.setByte(3, Byte.parseByte(shopType));
				ps.setString(4, contentName);// 4
			} else if (!city.equals("不限")) {
				String sql = "select * from users where role=? and city=?";
				ps = dbServiceImpl.connect().prepareStatement(sql);
				ps.setString(1, "shop");
				ps.setString(2, city);
			} else if (community != null && community != "") {
				String sql = "select * from users where role=? and community=?";
				ps = dbServiceImpl.connect().prepareStatement(sql);
				ps.setString(1, "shop");
				ps.setString(2, community);
			} else if (!shopType.equals("请选择")) {
				String sql = "select * from users where role=? and shopType=? and contentName=?";
				ps = dbServiceImpl.connect().prepareStatement(sql);
				ps.setString(1, "shop");
				ps.setByte(2, Byte.parseByte(shopType));
				ps.setString(3, contentName);// 7
			} else {
				String sql = "select * from users where role=?";
				ps = dbServiceImpl.connect().prepareStatement(sql);
				ps.setString(1, "shop");// 8
			}
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
				u.setShopType(rs.getByte("shopType"));
				// ////////0626
				u.setImgNumber(rs.getInt("imgNumber"));
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

	// ///////////////////////////////////////0601
	@Override
	public ArrayList<Users> queryUserByPhone(String phone) throws Exception {
		ResultSet rs = null;
		PreparedStatement ps = null;
		ArrayList<Users> list = null;
		try {
			if (phone != null && !phone.equals("")) {
				String sql = "select * from users where phone=?";
				ps = dbServiceImpl.connect().prepareStatement(sql);
				ps.setString(1, phone);
			} else {
				return list;
			}
			rs = ps.executeQuery();
			list = new ArrayList<Users>();
			if (rs.next()) {
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
				u.setShopType(rs.getByte("shopType"));
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

	@Override
	public boolean setImgNum(int id, int number) throws Exception {
		PreparedStatement ps = null;
		try {
			String sql = "update users set imgNumber = ? where id = ?";
			ps = dbServiceImpl.connect().prepareStatement(sql);
			ps.setInt(1, number);
			ps.setInt(2, id);
			ps.executeUpdate();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			try {
				dbServiceImpl.close();
				if (ps != null) {
					ps.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		}
	}

	@Override
	public boolean lxDelImgNum(int userid, int delnum) throws Exception {
		ResultSet rs = null;
		PreparedStatement ps = null;
		PreparedStatement psdel = null;
		try {
			String sql = "select id from shop_img where userId=? limit 0,?";
			ps = dbServiceImpl.connect().prepareStatement(sql);
			ps.setInt(1, userid);
			rs = ps.executeQuery();
			while (rs.next()) {
				String sqldel = "delete from shop_img where id=?";
				psdel = dbServiceImpl.connect().prepareStatement(sqldel);
				psdel.setInt(1, rs.getInt("id"));
				psdel.executeUpdate();
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			try {
				dbServiceImpl.close();
				if (rs != null) {
					rs.close();
				}
				if (ps != null) {
					ps.close();
				}
				if (psdel != null) {
					psdel.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public int searchTotalNumlx(int id) throws Exception {
		ResultSet rs = null;
		PreparedStatement ps = null;
		try {
			String sql = "select imgNumber from users where id=?";
			ps = dbServiceImpl.connect().prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if (rs.next()) {
				return rs.getInt("imgNumber");
			}
			return -1;
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
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

	@Override
	public boolean lxResetPwd(int id, String pwd) throws Exception {
		PreparedStatement ps = null;
		try {
			String sql = "update users set userPwd = ? where id = ?";
			ps = dbServiceImpl.connect().prepareStatement(sql);
			ps.setString(1, pwd);
			ps.setInt(2, id);
			ps.executeUpdate();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			try {
				dbServiceImpl.close();
				if (ps != null) {
					ps.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		}
	}

	// ///////////////////////////////////////0628
	@Override
	public boolean lxExistsPhone(String phone) throws Exception {
		ResultSet rs = null;
		PreparedStatement ps = null;
		ArrayList<Users> list = null;
		try {
			String sql = "select * from users where phone=?";
			ps = dbServiceImpl.connect().prepareStatement(sql);
			ps.setString(1, phone);
			rs = ps.executeQuery();
			if (rs.next()) {
				return true;
			}
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
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

	// ///////////////////////////////////////0629
	@Override
	public String lxFindShopImgUrl(int photoid) throws Exception {
		ResultSet rs = null;
		PreparedStatement ps = null;
		try {
			String sql = "select imgUrl from shop_img where id=?";
			ps = dbServiceImpl.connect().prepareStatement(sql);
			ps.setInt(1, photoid);
			rs = ps.executeQuery();
			if (rs.next()) {
				return rs.getString("imgUrl");
			}
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
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

	/////////////////////// 0703
	@Override
	public boolean lxChangePwd(Integer id, String pwd) throws Exception {
		ResultSet rs = null;
		PreparedStatement pstm = null;
		try {
			String sql = "update users set userPwd=? where id=?";
			pstm = dbServiceImpl.connect().prepareStatement(sql);
			pstm.setString(1, pwd);
			pstm.setInt(2, id);
			int i = pstm.executeUpdate();
			if (i > 0) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			try {
				dbServiceImpl.close();
				if (rs != null) {
					rs.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
				return false;

			}
		}
	}
	
	/////////////////0703
	@Override
	public boolean chkAdminPwd(Integer id, String Pwd) throws Exception {
		ResultSet rs = null;
		PreparedStatement ps = null;
		try {
			String sql = "select userPwd from users where id=? and userPwd=?";
			ps = dbServiceImpl.connect().prepareStatement(sql);
			ps.setInt(1, id);
			ps.setString(2, Pwd);
			rs = ps.executeQuery();
			if (rs.next()) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
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
	
	/////////////////0703-----lxExistsUserName
	@Override
	public boolean lxExistsUserName(String userName) throws Exception {
		ResultSet rs = null;
		PreparedStatement ps = null;
		ArrayList<Users> list = null;
		try {
			String sql = "select * from users where userName=?";
			ps = dbServiceImpl.connect().prepareStatement(sql);
			ps.setString(1, userName);
			rs = ps.executeQuery();
			if (rs.next()) {
				return true;
			}
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
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
