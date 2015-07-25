package com.nciae.community.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.nciae.community.dao.impl.DailyAbcDaoImpl;
import com.nciae.community.dao.impl.HappyMomentDaoImpl;
import com.nciae.community.dao.impl.MemberDaoImpl;
import com.nciae.community.domain.DailyAbc;
import com.nciae.community.domain.HappyMoment;
import com.nciae.community.domain.Merchants;
import com.nciae.community.domain.Property;
import com.nciae.community.utils.HTMLDecoder;
import com.nciae.community.utils.JsonUtil;
//import com.nciae.community.dao.impl.Member;
import com.nciae.community.utils.MD5;

@Controller
@RequestMapping("/member")
public class MemberController {
	private MemberDaoImpl memberDaoImpl;
	private DailyAbcDaoImpl dailyAbcDaoImpl;
	private HappyMomentDaoImpl happyMomentDaoImpl;

	public MemberDaoImpl getMemberDaoImpl() {
		return memberDaoImpl;
	}

	public void setMemberDaoImpl(MemberDaoImpl memberDaoImpl) {
		this.memberDaoImpl = memberDaoImpl;
	}

	public DailyAbcDaoImpl getDailyAbcDaoImpl() {
		return dailyAbcDaoImpl;
	}

	public void setDailyAbcDaoImpl(DailyAbcDaoImpl dailyAbcDaoImpl) {
		this.dailyAbcDaoImpl = dailyAbcDaoImpl;
	}

	public HappyMomentDaoImpl getHappyMomentDaoImpl() {
		return happyMomentDaoImpl;
	}

	public void setHappyMomentDaoImpl(HappyMomentDaoImpl happyMomentDaoImpl) {
		this.happyMomentDaoImpl = happyMomentDaoImpl;
	}

	// 注册
	@RequestMapping(value = "member_regist", method = RequestMethod.POST)
	public void regist(HttpServletRequest request, HttpServletResponse response) {
		try {
			String mempassword = request.getParameter("password").trim();
			String memname = request.getParameter("username").trim();
			////
			////mempassword = MD5.Instance().RMD5(request.getParameter("password"));
			////
			String result = null;
			PrintWriter out = null;
			if (memberDaoImpl.chkMemberName(memname)) {

				if (memberDaoImpl.regist(memname, mempassword)
						.equals("success")) {
					result = "success";
				} else {
					result = "error";
				}

			} else {
				result = "failed";
			}
			out = response.getWriter();
			out.write(result);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// 修改密码
	@RequestMapping(value = "member_changepwd", method = RequestMethod.POST)
	public void changepassword(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			String id = request.getParameter("id").trim();
			String newPassword = request.getParameter("newPassword").trim();
			String oldPassword = request.getParameter("oldPassword").trim();

			String result = null;
			PrintWriter out = null;
			if (memberDaoImpl.chkMemberPwd(id, oldPassword)) {
				if (memberDaoImpl.modifyPwd(id, newPassword).equals("success")) {

					result = "success";
				} else {
					result = "failed";
				}

			} else {
				result = "failed";
			}
			out = response.getWriter();
			out.write(result);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// 修改用户名
	@RequestMapping(value = "member_modifyname", method = RequestMethod.POST)
	public void modifyname(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			String name = request.getParameter("name").trim();
			String id = request.getParameter("id").trim();
			String result = null;
			PrintWriter out = null;
			if (memberDaoImpl.chkMemberName(name)) {
				if (memberDaoImpl.modifyName(name, id).equals("success")) {
					result = "success";
				} else {
					result = "failed";
				}
			} else {
				result = "failed";
			}

			out = response.getWriter();
			out.write(result);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// 登录
	@RequestMapping(value = "member_login", method = RequestMethod.POST)
	public void memlogin(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			String memname = request.getParameter("username").trim();
			String mempassword = request.getParameter("password").trim();
			////
			//mempassword = MD5.Instance().RMD5(request.getParameter("password"));
			////
			int memberid = memberDaoImpl.selectMemberId(memname);
			String result = null;
			PrintWriter out = null;

			/*
			 * if (memberDaoImpl.selectLoginStatus(memname)) { result =
			 * "already"; } else {
			 */
			if (memberDaoImpl.memberLogin(memname, mempassword)) {
				// memberDaoImpl.changeLoginStatus(memname);
				result = "success" + " " + memberid;
			} else {
				result = "failed";
			}
			// }
			out = response.getWriter();
			out.write(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 查找商家
	@RequestMapping(value = "mem_selectshop", method = RequestMethod.POST)
	public void mem_selectshopinfo(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			String contentname = request.getParameter("contentname").trim();
			String signl = request.getParameter("signl").trim();
			Integer communityid = Integer.parseInt(request.getParameter(
					"communityid").trim());

			String result = null;
			PrintWriter out = null;
			if (memberDaoImpl.getShopInfo(contentname, communityid, signl) != null) {
				ArrayList<Object> shopinfolist = new ArrayList<Object>();
				shopinfolist = memberDaoImpl.getShopInfo(contentname,
						communityid, signl);
				result = JsonUtil.toJsonString(shopinfolist);
			} else {

				result = "null";
			}
			out = response.getWriter();
			out.write(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 退出
	@RequestMapping(value = "member_logout", method = RequestMethod.POST)
	public void memberLogOut(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		int memberid = Integer
				.parseInt(request.getParameter("memberid").trim());
		String result = null;
		PrintWriter out = null;
		try {
			if (memberDaoImpl.changeLoginStatus1(memberid)) {
				result = "success";
			} else {
				result = "failed";
			}
			out = response.getWriter();
			out.write(result);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	// 查询公告
	@RequestMapping(value = "mem_selectbulletin", method = RequestMethod.POST)
	public void mem_selectBulletin(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			Integer communityid = Integer.parseInt(request.getParameter(
					"communityid").trim());
			String result = null;
			PrintWriter out = null;
			if (memberDaoImpl.selectBulletin(communityid) != null) {
				ArrayList<Object> bulletinlist = new ArrayList<Object>();
				bulletinlist = memberDaoImpl.selectBulletin(communityid);
				result = JsonUtil.toJsonString(bulletinlist);

			} else {

				result = "null";
			}
			out = response.getWriter();
			out.write(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 检测小区开通
	@RequestMapping(value = "mem_chkopened", method = RequestMethod.POST)
	public void mem_chkOpened(HttpServletRequest request,
			HttpServletResponse response) {
		try {

			// String(request.getParameter("communityname").getBytes("ISO8859-1"),"UTF-8");
			String communityname = request.getParameter("communityname").trim();
			String result = null;
			PrintWriter out = null;
			System.out.println("communityname----" + communityname);
			if (!memberDaoImpl.chkCommunityOpened(communityname).equals("-1")) {
				String results = memberDaoImpl
						.chkCommunityOpened(communityname).toString();
				String log[] = results.split(",");
				if (log[0].equals("success")) {
					result = "success" + log[1];
					// System.out.println("result-----------" + result);
				} else if (log[0].equals("failed")) {
					result = "failed" + log[1];
					// System.out.println("result-----------" + result);
				}
			} else {
				result = "error";
			}
			out = response.getWriter();
			out.write(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 点赞
	@RequestMapping(value = "mem_setlikecount", method = RequestMethod.POST)
	public void mem_setLikeCount(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			int communityid = Integer.parseInt(request.getParameter(
					"communityid").trim());
			String result = null;
			PrintWriter out = null;
			result = memberDaoImpl.setLikeCount(communityid);
			out = response.getWriter();
			out.write(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 查询物业信息
	@SuppressWarnings("unused")
	@RequestMapping(value = "mem_selectpropertyinfo", method = RequestMethod.POST)
	public void mem_selectPropertyInfo(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			int communityid = Integer.parseInt(request.getParameter(
					"propertyid").trim());
			Property property = new Property();
			String result = null;
			PrintWriter out = null;
			property = memberDaoImpl.selectProertyInfo(communityid);

			if (property != null) {
				result = JsonUtil.ToJsonStringProperty(property);

				out = response.getWriter();
				out.write(result);
			} else {
				result = "null";
				out = response.getWriter();
				out.write(result);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 添加收藏
	@RequestMapping(value = "addcollection", method = RequestMethod.POST)
	public void mem_addCollection(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			int userid = Integer
					.parseInt(request.getParameter("userid").trim());
			int memberid = Integer.parseInt(request.getParameter("memberid")
					.trim());
			String result = null;
			PrintWriter out = null;
			if (memberDaoImpl.chkAlreadyCollection(userid, memberid)) {
				if (memberDaoImpl.insertCollection(userid, memberid)) {
					result = "success";
				} else {
					result = "failed";
				}
			} else {
				result = "already";
			}
			out = response.getWriter();
			out.write(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 删除收藏
	@RequestMapping(value = "delcollection", method = RequestMethod.POST)
	public void mem_deleteCollection(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			int userid = Integer
					.parseInt(request.getParameter("userid").trim());
			int memberid = Integer.parseInt(request.getParameter("memberid")
					.trim());
			String result = null;
			PrintWriter out = null;
			if (memberDaoImpl.deleteCollection(userid, memberid)) {
				result = "success";

			} else {

				result = "failed";
			}
			out = response.getWriter();
			out.write(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 查询收藏
	@RequestMapping(value = "selectcollection", method = RequestMethod.POST)
	public void mem_selectCollection(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			int memberid = Integer.parseInt(request.getParameter("memberid")
					.trim());
			String result = null;
			PrintWriter out = null;
			if (memberDaoImpl.selectCollection(memberid) != null) {
				ArrayList<Object> collectionlist = new ArrayList<Object>();
				collectionlist = memberDaoImpl.selectCollection(memberid);
				result = JsonUtil.toJsonString(collectionlist);
			} else {
				result = "null";
			}
			out = response.getWriter();
			out.write(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 添加反馈信息
	@RequestMapping(value = "insertreply", method = RequestMethod.POST)
	public void mem_insertReply(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			int memid = Integer.parseInt(request.getParameter("memid").trim());
			int communityid = Integer.parseInt(request.getParameter(
					"communityid").trim());
			String msg = request.getParameter("msg");

			System.out.println("获取反馈的信息 " + memid + "    " + communityid
					+ "    " + msg);
			String result = null;
			PrintWriter out = null;
			if (memberDaoImpl.insertReply(communityid, msg, memid)) {
				result = "success";
			} else {
				result = "failed";
			}
			out = response.getWriter();
			out.write(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 添加软件反馈信息
	@RequestMapping(value = "insertcomment", method = RequestMethod.POST)
	public void mem_insertComment(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			int memid = Integer.parseInt(request.getParameter("memid").trim());
			String msg = request.getParameter("msg");
			String result = null;
			PrintWriter out = null;
			if (memberDaoImpl.insertComment(msg, memid)) {
				result = "success";
			} else {
				result = "failed";
			}
			out = response.getWriter();
			out.write(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*////////////0629
	// 查询单个商家信息
	@RequestMapping(value = "mem_selectsingleshopinfo", method = RequestMethod.POST)
	public void mem_selectSingleShopInfo(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			Integer shopid = Integer.parseInt(request.getParameter("shopid")
					.trim());
			String result = null;
			PrintWriter out = null;
			if (memberDaoImpl.selectSingleShopInfo(shopid) != null) {
				Merchants mer = new Merchants();
				mer = memberDaoImpl.selectSingleShopInfo(shopid);
				result = JsonUtil.ToJsonStringMerchants(mer);

			} else {
				result = "null";
			}
			out = response.getWriter();
			out.write(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	*/
	// 查询单个商家信息
		@RequestMapping(value = "mem_selectsingleshopinfo", method = RequestMethod.POST)
		public void mem_selectSingleShopInfo(HttpServletRequest request,
				HttpServletResponse response) {
			try {
				Integer shopid = Integer.parseInt(request.getParameter("shopid")
						.trim());
				String result = null;
				PrintWriter out = null;
				if (memberDaoImpl.selectSingleShopInfo(shopid) != null) {
					Merchants mer = new Merchants();
					mer = memberDaoImpl.selectSingleShopInfo(shopid);
					//result = JsonUtil.ToJsonStringMerchants(mer);
					result=JsonUtil.toJsonStringObject(mer);
					
				} else {
					result = "null";
				}
				out = response.getWriter();
				out.write(result);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}


	// 申请开店
	@RequestMapping(value = "mem_applyopenshop", method = RequestMethod.GET)
	public void mem_applyopenshop(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			String result = null;
			PrintWriter out = null;
			if (memberDaoImpl.selectAdminPhone() != null) {
				String phone = memberDaoImpl.selectAdminPhone();
				result = JsonUtil.toJsonStringObject(phone);
			} else {
				result = "null";
			}
			out = response.getWriter();
			out.write(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 收藏的商户ID
	@RequestMapping(value = "mem_selectshopid", method = RequestMethod.POST)
	public void mem_test_selectshopid(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			Integer memberid = Integer.parseInt(request
					.getParameter("memberid").trim());
			String result = null;
			PrintWriter out = null;
			if (memberDaoImpl.selectMenberCollectMerchantId(memberid) != null) {
				ArrayList<Integer> shopidlist = new ArrayList<Integer>();
				shopidlist = memberDaoImpl
						.selectMenberCollectMerchantId(memberid);
				result = JsonUtil.toJsonString1(shopidlist);
			} else {

				result = "null";
			}
			out = response.getWriter();
			out.write(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 查询广告信息
	@RequestMapping(value = "mem_selectads", method = RequestMethod.POST)
	public void mem_selectADs(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			Integer communityid = Integer.parseInt(request.getParameter(
					"communityid").trim());
			String result = null;
			PrintWriter out = null;
			ArrayList<Object> adlist = new ArrayList<Object>();
			adlist = memberDaoImpl.selectAdvertisements(communityid);
			if (adlist != null) {
				result = JsonUtil.toJsonString(adlist);
			} else {

				result = "null";
			}
			out = response.getWriter();
			out.write(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//获取开心一刻信息
	@RequestMapping(value = "mem_getANewHappyMoment", method = RequestMethod.POST)
	public void getANewHappyMoment(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			ArrayList<Object> list = new ArrayList<Object>();
			HappyMoment happyMoment = new HappyMoment();
			String result = null;
			PrintWriter out = null;
			// happyMoment=happyMomentDaoImpl.queryHappyMoment();

			ServletContext sc = request.getSession().getServletContext();
			happyMoment = (HappyMoment) sc.getAttribute("happyMoment");
			if (happyMoment == null) {// 服务器刚启动时，happyMoment为空，先随机取一条
				happyMoment = happyMomentDaoImpl.queryRandomHappyMoment();
			}
			////---------0623--------------
			happyMoment.setContent(HTMLDecoder.decode(happyMoment.getContent()));
			////
			list.add(happyMoment);
			if (list.size() > 0) {
				result = JsonUtil.toJsonString(list);
				// result = happyMoment.getContent();
				out = response.getWriter();
				out.write(result);
			} else {// 手机端注意对空值的处理
				result = "null";
				out = response.getWriter();
				out.write(result);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//获取每日常识信息
	@RequestMapping(value = "mem_getANewDailyAbc", method = RequestMethod.POST)
	public void getgetANewDailyAbc(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			ArrayList<Object> list = new ArrayList<Object>();
			DailyAbc dailyAbc = new DailyAbc();
			String result = null;
			PrintWriter out = null;
			// dailyAbc=dailyAbcDaoImpl.queryDailyAbc();
			// dailyAbc=dailyAbcDaoImpl.queryRandomDailyAbc();
			ServletContext sc = request.getSession().getServletContext();
			dailyAbc = (DailyAbc) sc.getAttribute("dailyAbc");
			if (dailyAbc == null) {// 服务器刚启动时，dailyAbc为空，先随机取一条
				dailyAbc = dailyAbcDaoImpl.queryRandomDailyAbc();
			}
			list.add(dailyAbc);
			if (list.size() > 0) {
				result = JsonUtil.toJsonString(list);
				// result = dailyAbc.getContent();
				out = response.getWriter();
				out.write(result);
			} else {// 手机端注意对空值的处理
				result = "null";
				out = response.getWriter();
				out.write(result);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/*
	//获取城市小区
	@RequestMapping(value = "mem_selectcommunity", method = RequestMethod.POST)
	public void mem_selectCommunity(HttpServletRequest request,
			HttpServletResponse response) {
		String city=request.getParameter("city");
		//String city="廊坊";
		//String tmpcity="廊坊";
		Integer cityId=0;
		try {
			String result = null;
			PrintWriter out = null;
			ArrayList<Object> adlist = new ArrayList<Object>();
			String cityid=memberDaoImpl.selectCityId(city);
			if (cityid.length()!=0) {
				cityId=Integer.parseInt(cityid);
				adlist = memberDaoImpl.selectCommunity(cityId);
				System.out.println(adlist);
				if (adlist != null) {
					result = JsonUtil.toJsonString(adlist);
					System.out.println(result);
				} else {
					result = "null";
				}
			}
			else {
				result = "null";
			}
			out = response.getWriter();
			out.write(result);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/
	//获取城市小区
			@RequestMapping(value = "mem_selectcommunity", method = RequestMethod.POST)
			public void mem_selectCommunity(HttpServletRequest request,
					HttpServletResponse response) {
				String city=request.getParameter("city");
				//String city="廊坊";
				//String tmpcity="廊坊";
				Integer cityId=0;
				try {
					String result = null;
					PrintWriter out = null;
					ArrayList<Object> adlist = new ArrayList<Object>();
					String cityid=memberDaoImpl.selectCityId(city);
					if (cityid.length()!=0) {
						cityId=Integer.parseInt(cityid);
						adlist = memberDaoImpl.selectCommunity(cityId);
						//System.out.println(adlist);
						if (adlist != null) {
							//result = JsonUtil.toJsonString(adlist);
							result="";
							for(int i=0;i<adlist.size();i++){
								result+=adlist.get(i);
								if(i<(adlist.size()-1)){
									result+=",";
								}
							}
							System.out.println(result);
						} else {
							result = "";
						}
					}
					else {
						result = "";
					}
					out = response.getWriter();
					out.write(result);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
	
}
