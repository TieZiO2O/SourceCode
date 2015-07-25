package com.nciae.community.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;

import com.nciae.community.dao.MemberDao;
import com.nciae.community.domain.Advertisement;
import com.nciae.community.domain.Bulletin;
import com.nciae.community.domain.Merchants;
import com.nciae.community.domain.Property;
import com.nciae.community.domain.ShopImg;
import com.nciae.community.service.impl.DatabaseServiceImpl;

public class MemberDaoImpl implements MemberDao {
	private DatabaseServiceImpl dbServiceImpl;

	public DatabaseServiceImpl getDbServiceImpl() {
		return dbServiceImpl;
	}

	public void setDbServiceImpl(DatabaseServiceImpl dbServiceImpl) {
		this.dbServiceImpl = dbServiceImpl;
	}

	@Override
	public String regist(String memname, String mempassword) throws Exception {
		ResultSet rs = null;
		PreparedStatement ps = null;
		try {
			String sql = "insert into member(memLogName,memPassword) values(?,?)";
			ps = dbServiceImpl.connect().prepareStatement(sql);
			ps.setString(1, memname);
			ps.setString(2, mempassword);
			int i = ps.executeUpdate();
			if (i > 0) {
				return "success";
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

	public String modifyPwd(String id, String mempassword) throws Exception {
		ResultSet rs = null;
		PreparedStatement ps = null;
		try {
			String sql = "update member set memPassword=? where id=?";
			ps = dbServiceImpl.connect().prepareStatement(sql);
			ps.setString(1, mempassword);
			ps.setString(2, id);
			int i = ps.executeUpdate();
			if (i > 0) {
				return "success";
			} else {
				return "failed";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "failed";
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

	public String modifyName(String name, String id) throws Exception {
		ResultSet rs = null;
		PreparedStatement ps = null;
		try {
			String sql = "update member set memLogName=? where id=?";
			ps = dbServiceImpl.connect().prepareStatement(sql);
			ps.setString(1, name);
			ps.setString(2, id);
			int i = ps.executeUpdate();
			if (i > 0) {
				return "success";
			} else {
				return "failed";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "failed";
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


	// 检测用户名是否重复/注册用
	@Override
	public boolean chkMemberName(String membername) throws Exception {
		ResultSet rs = null;
		PreparedStatement ps = null;
		try {
			String sql = "select memLogName from member where memLogName=?";
			ps = dbServiceImpl.connect().prepareStatement(sql);
			ps.setString(1, membername);
			rs = ps.executeQuery();
			if (rs.next()) {
				return false;
			} else {
				return true;
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
	public boolean chkMemberPwd(String id, String Pwd) throws Exception {
		ResultSet rs = null;
		PreparedStatement ps = null;
		try {
			String sql = "select memPassword from member where id=? and memPassword=?";
			ps = dbServiceImpl.connect().prepareStatement(sql);
			ps.setString(1, id);
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

	@Override
	public boolean memberLogin(String membername, String memberpwd)
			throws Exception {
		ResultSet rs = null;
		PreparedStatement ps = null;
		try {
			String sql = "select * from member where memLogName=? and memPassword=?";
			ps = dbServiceImpl.connect().prepareStatement(sql);
			ps.setString(1, membername);
			ps.setString(2, memberpwd);
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

	/*
	@Override
	public ArrayList<Object> getShopInfo(String contentName,
			Integer communityid, String signl) throws Exception {

		ResultSet rs = null;
		ResultSet rs1 = null;
		PreparedStatement ps = null;
		ArrayList<Object> shopinfolist = new ArrayList<Object>();
		ArrayList<ShopImg> shop_img = new ArrayList<ShopImg>();

		try {

			String sql2 = "select * from shop_img where userId=?";
			String sql = "select * from users where contentName=? and community=(select communityName from community where id=?)";
			String sql1 = "select * from users where contentName=? and shopType=1";
			if (signl.equals("0")) {
				ps = dbServiceImpl.connect().prepareStatement(sql);
				ps.setString(1, contentName);
				ps.setInt(2, communityid);
			} else if (signl.equals("1")) {
				ps = dbServiceImpl.connect().prepareStatement(sql1);
				ps.setString(1, contentName);
				// ps.setInt(2,communityid);
			} else {
				return null;
			}
			rs = ps.executeQuery();
			while (rs.next()) {
				Merchants shopinfo = new Merchants();
				ps = dbServiceImpl.connect().prepareStatement(sql2);
				ps.setInt(1, rs.getInt("id"));
				rs1 = ps.executeQuery();
				while (rs1.next()) {
					ShopImg shopimg = new ShopImg();
					shopimg.setId(rs1.getInt("id"));
					shopimg.setImgUrl(rs1.getString("imgUrl"));
					shopimg.setUserId(rs1.getInt("userId"));
					shop_img.add(shopimg);
				}

				shopinfo.setId(rs.getInt("id"));
				// shopinfo.setUserName(rs.getString("userName"));
				shopinfo.setImgUrl(shop_img);
				shopinfo.setRealName(rs.getString("realName"));
				shopinfo.setPhone(rs.getString("phone"));
				shopinfo.setAddress(rs.getString("address"));
				shopinfo.setShopInfo(rs.getString("shopInfo"));
				shopinfo.setCustomerNotice(rs.getString("customerNotice"));
				shopinfo.setShopLogo(rs.getString("shopLogo"));
				shopinfolist.add(shopinfo);
			}
			return shopinfolist;
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
	}*/
	@Override
	public ArrayList<Object> getShopInfo(String contentName,
			Integer communityid, String signl) throws Exception {

		ResultSet rs = null;
		ResultSet rs1 = null;
		PreparedStatement ps = null;
		ArrayList<Object> shopinfolist = new ArrayList<Object>();
		ArrayList<ShopImg> shop_img = new ArrayList<ShopImg>();
        
		try {

			String sql = "select * from users where contentName=? and community=(select communityName from community where id=?)";
			String sql1 = "select * from users where contentName=? and shopType=1";
			if (signl.equals("0")) {
				ps = dbServiceImpl.connect().prepareStatement(sql);
				ps.setString(1, contentName);
				ps.setInt(2, communityid);
			} else if (signl.equals("1")) {
				ps = dbServiceImpl.connect().prepareStatement(sql1);
				ps.setString(1, contentName);
			} else {
				return null;
			}
			
			rs = ps.executeQuery();
			while (rs.next()) {
				
				Merchants shopinfo = new Merchants();
				shopinfo.setId(rs.getInt("id"));
				//shopinfo.setImgUrl(shop_img);
				shopinfo.setRealName(rs.getString("realName"));
				//shopinfo.setPhone(rs.getString("phone"));
				shopinfo.setAddress(rs.getString("address"));
				//shopinfo.setShopInfo(rs.getString("shopInfo"));
				//shopinfo.setCustomerNotice(rs.getString("customerNotice"));
				shopinfo.setShopLogo(rs.getString("shopLogo"));
				shopinfolist.add(shopinfo);
			}
			return shopinfolist;
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


	@Override
	public boolean selectLoginStatus(String membername) throws Exception {
		ResultSet rs = null;
		PreparedStatement ps = null;
		try {
			String sql = "select loginStatus from member where memLogName=?";
			ps = dbServiceImpl.connect().prepareStatement(sql);
			ps.setString(1, membername);
			rs = ps.executeQuery();
			if (rs.next()) {
				if (rs.getBoolean("loginStatus") == true) {
					return true;
				} else {
					return false;
				}
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
	public boolean changeLoginStatus(String membername) throws Exception {
		ResultSet rs = null;
		PreparedStatement ps = null;
		try {
			String sql = "update member set loginStatus=1 where memLogName=?";
			ps = dbServiceImpl.connect().prepareStatement(sql);
			ps.setString(1, membername);
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
	public boolean changeLoginStatus1(Integer memberid) throws Exception {
		ResultSet rs = null;
		PreparedStatement ps = null;
		try {
			String sql = "update member set loginStatus=0 where id=?";
			ps = dbServiceImpl.connect().prepareStatement(sql);
			ps.setInt(1, memberid);
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
	public int selectMemberId(String membername) throws Exception {
		ResultSet rs = null;
		PreparedStatement ps = null;
		try {
			String sql = "select id from member where memLogName=?";
			ps = dbServiceImpl.connect().prepareStatement(sql);
			ps.setString(1, membername);
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
	public ArrayList<Object> selectBulletin(int communityId) throws Exception {
		ResultSet rs = null;

		PreparedStatement ps = null;
		ArrayList<Object> list = null;
		try {
			String sql = "select * from bulletin where communityId=?";
			ps = dbServiceImpl.connect().prepareStatement(sql);
			ps.setInt(1, communityId);
			rs = ps.executeQuery();
			list = new ArrayList<Object>();
			while (rs.next()) {
				Bulletin bulletin = new Bulletin();
				bulletin.setId(rs.getInt("id"));
				bulletin.setTitle(rs.getString("title"));
				bulletin.setBulletinInfo(rs.getString("bulletinInfo"));
				bulletin.setCommitTime(rs.getString("commitTime"));
				list.add(bulletin);
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
		return list;
	}

	@Override
	public String chkCommunityOpened(String communityname) throws Exception {
		ResultSet rs = null;
		PreparedStatement ps = null;

		try {
			String sql = "select id,opened from community where communityName=?";
			ps = dbServiceImpl.connect().prepareStatement(sql);
			ps.setString(1, communityname);
			rs = ps.executeQuery();
			if (rs.next()) {
				String opened = String.valueOf(rs.getByte("opened"));
				if (opened.equals("1")) {
					return "success" + "," + rs.getInt("id");
				} else {
					return "failed" + "," + rs.getInt("id");
				}
			} else {
				return "-1";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "-1";
		} finally {
			try {
				dbServiceImpl.close();
				if (rs != null) {
					rs.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
				return "-1";

			}
		}
	}

	@Override
	public String setLikeCount(int communityid) throws Exception {
		ResultSet rs = null;
		PreparedStatement ps = null;

		int likecount = (Integer) 0;

		try {

			String sql = "select likeCount from community where id=?";
			ps = dbServiceImpl.connect().prepareStatement(sql);
			ps.setInt(1, communityid);
			rs = ps.executeQuery();
			if (rs.next()) {
				likecount = rs.getByte("likeCount");
				if (likecount == 5000) {
					return "full";
				} else {
					likecount = likecount + 1;
					String sql1 = "update community set likeCount=? where id=?";
					ps = dbServiceImpl.connect().prepareStatement(sql1);
					ps.setInt(1, likecount);
					ps.setInt(2, communityid);
					int i = ps.executeUpdate();
					if (i > 0) {
						return "true";
					} else {
						return "false";
					}
				}
			} else {
				return "false";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "false";
		} finally {
			try {
				dbServiceImpl.close();
				if (rs != null) {
					rs.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
				return "false";

			}
		}
	}

	@Override
	public Property selectProertyInfo(int propertyid)
			throws Exception {
		Property property = new Property();
		//ArrayList<Object> propertylist = new ArrayList<Object>();
		ResultSet rs = null;
		PreparedStatement ps = null;
		try {
			String sql = "select * from users where community=(select communityName from community where id=?) and role='property'";
			ps = dbServiceImpl.connect().prepareStatement(sql);
			ps.setInt(1, propertyid);
			rs = ps.executeQuery();
			if (rs.next()) {
				property.setId(String.valueOf(propertyid));
				property.setRealName(rs.getString("realName"));
				property.setPhone(rs.getString("phone"));
				property.setAddress(rs.getString("address"));
				property.setShopLogo(rs.getString("shopLogo"));//
				property.setAboutUs(rs.getString("aboutUs").trim());
				//propertylist.add(property);
				return property;
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
	public boolean insertCollection(int userid, int memberid) throws Exception {
		ResultSet rs = null;
		PreparedStatement ps = null;
		try {
			String sql = "insert into collection(memId,shopperId) values(?,?)";
			ps = dbServiceImpl.connect().prepareStatement(sql);
			ps.setInt(1, memberid);
			ps.setInt(2, userid);
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
	public boolean deleteCollection(int userid, int memberid) throws Exception {
		// TODO Auto-generated method stub
		ResultSet rs = null;
		PreparedStatement ps = null;
		try {
			String sql = "delete from collection where memId=? and shopperId=?";
			ps = dbServiceImpl.connect().prepareStatement(sql);
			ps.setInt(1, memberid);
			ps.setInt(2, userid);
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
	public ArrayList<Object> selectCollection(int memberid) throws Exception {
		// TODO Auto-generated method stub
		ResultSet rs = null;
		PreparedStatement ps = null;
		ArrayList<Object> list = new ArrayList<Object>();
		;
		try {

			String sql = "select realName,shopLogo from users where id=?";

			int shopidcount = (selectMenberCollectMerchantId(memberid).size()) - 1;

			for (int i = 0; i <= shopidcount; i++) {
				int shopid = selectMenberCollectMerchantId(memberid).get(i);
				ps = dbServiceImpl.connect().prepareStatement(sql);
				ps.setInt(1, shopid);
				rs = ps.executeQuery();

				while (rs.next()) {
					Merchants merchants = new Merchants();
					merchants.setId(shopid);
					merchants.setShopLogo(rs.getString("shopLogo"));
					merchants.setRealName(rs.getString("realName"));
					list.add(merchants);
				}
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
		return list;
	}

	@Override
	public boolean insertReply(int communityid, String msg, int memid)
			throws Exception {
		ResultSet rs = null;
		PreparedStatement ps = null;
		try {
			String sql = "insert into reply(communityid,msg,commitTime,memId) values(?,?,?,?)";
			ps = dbServiceImpl.connect().prepareStatement(sql);
			Date date = new Date();
			String committime = date.toLocaleString();
			ps.setInt(1, communityid);
			ps.setString(2, msg);
			ps.setString(3, committime);
			ps.setInt(4, memid);
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
	public boolean chkAlreadyCollection(int userid, int memberid)
			throws Exception {
		ResultSet rs = null;
		PreparedStatement ps = null;
		try {
			String sql = "select shopperId from collection where memId=? and shopperId=?";
			ps = dbServiceImpl.connect().prepareStatement(sql);
			ps.setInt(1, memberid);
			ps.setInt(2,userid);
			rs = ps.executeQuery();
			if (rs.next()) {
				return false;
			} else {
				return true;
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

	/*////////0629
	@Override
	public Merchants selectSingleShopInfo(int shopid) throws Exception {
		ResultSet rs = null;
		ResultSet rs1 = null;
		PreparedStatement ps = null;
		PreparedStatement ps1 = null;
		ArrayList<ShopImg> shop_img = new ArrayList<ShopImg>();

		try {
			String sql1 = "select * from shop_img where userId=?";
			String sql = "select * from users where id=?";
			ps1 = dbServiceImpl.connect().prepareStatement(sql1);
			ps1.setInt(1, shopid);
			rs1 = ps1.executeQuery();
			ShopImg shopimg = new ShopImg();
			Merchants shopinfo = new Merchants();
			
			while (rs1.next()) {
				shopimg.setImgUrl(rs1.getString("imgUrl"));
				//shop_img.add(shopimg.getImgUrl())
				//shopinfo.setImgUrl();
			}
			ps = dbServiceImpl.connect().prepareStatement(sql);
			ps.setInt(1, shopid);
			rs = ps.executeQuery();
			while (rs.next()) {
		
				shopinfo.setId(shopid);
				shopinfo.setRealName(rs.getString("realName"));
				shopinfo.setPhone(rs.getString("phone"));
				shopinfo.setAddress(rs.getString("address"));
				shopinfo.setShopInfo(rs.getString("shopInfo"));
				shopinfo.setCustomerNotice(rs.getString("customerNotice"));
				shopinfo.setShopLogo(rs.getString("shopLogo"));
			}
			return shopinfo;
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
	*/
	@Override
	public Merchants selectSingleShopInfo(int shopid) throws Exception {
		ResultSet rs = null;
		ResultSet rs1 = null;
		PreparedStatement ps = null;
		PreparedStatement ps1 = null;
		ArrayList<ShopImg> shop_img = new ArrayList<ShopImg>();

		try {
			String sql1 = "select * from shop_img where userId=?";
			String sql = "select * from users where id=?";
			Merchants shopinfo = new Merchants();
			ps = dbServiceImpl.connect().prepareStatement(sql);
			ps.setInt(1, shopid);
			rs = ps.executeQuery();
			while (rs.next()) {
				ps1 = dbServiceImpl.connect().prepareStatement(sql1);
				ps1.setInt(1, shopid);
				rs1 = ps1.executeQuery();

				while (rs1.next()) {
					ShopImg shopimg = new ShopImg();
					shopimg.setId(rs1.getInt("id"));
					shopimg.setImgUrl(rs1.getString("imgUrl"));
					shopimg.setUserId(rs1.getInt("userId"));
					shop_img.add(shopimg);
				}
				shopinfo.setId(shopid);
				shopinfo.setImgUrl(shop_img);
				shopinfo.setRealName(rs.getString("realName"));
				shopinfo.setPhone(rs.getString("phone"));
				shopinfo.setAddress(rs.getString("address"));
				shopinfo.setShopInfo(rs.getString("shopInfo"));
				shopinfo.setCustomerNotice(rs.getString("customerNotice"));
				shopinfo.setShopLogo(rs.getString("shopLogo"));
			}
			return shopinfo;
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


	@Override
	public boolean insertComment(String msg, int memid) throws Exception {
		// TODO Auto-generated method stub
		ResultSet rs = null;
		PreparedStatement ps = null;
		try {
			String sql = "insert into comment(memId,msg,commitTime) values(?,?,?)";
			ps = dbServiceImpl.connect().prepareStatement(sql);
			Date date = new Date();
			String committime = date.toLocaleString();
			ps.setInt(1, memid);
			ps.setString(2, msg);
			ps.setString(3, committime);
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
	public ArrayList<Integer> selectMenberCollectMerchantId(int memberid)
			throws Exception {
		// TODO Auto-generated method stub
		ResultSet rs = null;
		PreparedStatement ps = null;
		ArrayList<Integer> merchantsidlist = null;
		try {

			String sql = "select shopperId from collection where memId=?";
			ps = dbServiceImpl.connect().prepareStatement(sql);
			ps.setInt(1, memberid);
			rs = ps.executeQuery();
			merchantsidlist = new ArrayList<Integer>();
			while (rs.next()) {
				merchantsidlist.add(rs.getInt("shopperid"));
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
		return merchantsidlist;
	}
	
	// 申请开店
	@Override
	public String selectAdminPhone() throws Exception {
		ResultSet rs = null;
		PreparedStatement ps = null;

		try {
			String sql = "select phone from users where role='admin'";
			ps = dbServiceImpl.connect().prepareStatement(sql);
			rs = ps.executeQuery();
			if (rs.next()) {
				return rs.getString("phone");
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
	
	
	//查询广告
	@Override
	public ArrayList<Object> selectAdvertisements(int communityid)
			throws Exception {
		ResultSet rs = null;
		String communitystringid;
		PreparedStatement ps = null;
		ArrayList<Object> list = null;
		try {
			String sql = "select * from Advertisement";
			ps = dbServiceImpl.connect().prepareStatement(sql);
			rs = ps.executeQuery();
			list = new ArrayList<Object>();
			while (rs.next()) {
				communitystringid = rs.getString("communityList");
				if (communitystringid == null) {
					Advertisement ad = new Advertisement();
					ad.setShopperId(rs.getInt("shopperId"));
					ad.setAdImagURL(rs.getString("adImagURL"));
					ad.setAdTitle(rs.getString("adTitle"));
					ad.setAdContent(rs.getString("adContent"));
					list.add(ad);
				} else {
					String communitystring[] = communitystringid.split(",");
					for (int i = 0; i < communitystring.length; i++) {
						String community = Integer.toString(communityid);
						if (community.equals(communitystring[i])) {
							Advertisement ad = new Advertisement();
							ad.setShopperId(rs.getInt("shopperId"));
							ad.setAdImagURL(rs.getString("adImagURL"));
							ad.setAdTitle(rs.getString("adTitle"));
							ad.setAdContent(rs.getString("adContent"));
							list.add(ad);
						}
					}
				}

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
		return list;
	}
	
    //查询城市Id
	public String selectCityId(String city) throws Exception{
		ResultSet rs = null;
		PreparedStatement ps = null;
		try {
			String sql = "select id from city where city=?";
			ps = dbServiceImpl.connect().prepareStatement(sql);
			ps.setString(1, city);
			rs = ps.executeQuery();
			String cityid=null;
			if (rs.next()) {
				cityid=String.valueOf(rs.getInt("id"));
				
				return cityid;
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
 	
	// 查询小区
		@Override
		public ArrayList<Object> selectCommunity(int cityid) throws Exception {
			ResultSet rs = null;
			String communityname=null;
			//int cityId=Integer.parseInt(cityid);
			PreparedStatement ps = null;
			ArrayList<Object> list = null;
			try {
				String sql = "select communityName from community where cityId=?";
				ps = dbServiceImpl.connect().prepareStatement(sql);
				ps.setInt(1, cityid);
				rs = ps.executeQuery();
				list = new ArrayList<Object>();
				while (rs.next()) {
					communityname = rs.getString("communityName");
					list.add(communityname);
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
			return list;
		}


}
