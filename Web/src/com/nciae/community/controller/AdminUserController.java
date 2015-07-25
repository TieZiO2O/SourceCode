package com.nciae.community.controller;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nciae.community.dao.impl.AdvertisementDaoImpl;
import com.nciae.community.dao.impl.CityDaoImpl;
import com.nciae.community.dao.impl.CommunityDaoImpl;
import com.nciae.community.dao.impl.DailyAbcDaoImpl;
import com.nciae.community.dao.impl.HappyMomentDaoImpl;
import com.nciae.community.dao.impl.PropertyDaoImpl;
import com.nciae.community.dao.impl.ReplyDaoImpl;
import com.nciae.community.dao.impl.UsersDaoImpl;
import com.nciae.community.domain.Advertisement;
import com.nciae.community.domain.Community;
import com.nciae.community.domain.DailyAbc;
import com.nciae.community.domain.HappyMoment;
import com.nciae.community.domain.Reply;
import com.nciae.community.domain.Users;
import com.nciae.community.utils.JsonUtil;
import com.nciae.community.utils.MD5;

@SuppressWarnings("rawtypes")
@Controller
@RequestMapping("/adminusers")
public class AdminUserController {

	private UsersDaoImpl usersDaoImpl;
	private CityDaoImpl cityDaoImpl;
	private CommunityDaoImpl communityDaoImpl;
	private ReplyDaoImpl replyDaoImpl;
	private DailyAbcDaoImpl dailyAbcDaoImpl;
	private AdvertisementDaoImpl advertisementDaoImpl;
	private HappyMomentDaoImpl happyMomentDaoImpl;
	private PropertyDaoImpl propertyDaoImpl;

	public UsersDaoImpl getUsersDaoImpl() {
		return usersDaoImpl;
	}

	public void setUsersDaoImpl(UsersDaoImpl usersDaoImpl) {
		this.usersDaoImpl = usersDaoImpl;
	}

	public CityDaoImpl getCityDaoImpl() {
		return cityDaoImpl;
	}

	public void setCityDaoImpl(CityDaoImpl cityDaoImpl) {
		this.cityDaoImpl = cityDaoImpl;
	}

	public CommunityDaoImpl getCommunityDaoImpl() {
		return communityDaoImpl;
	}

	public void setCommunityDaoImpl(CommunityDaoImpl communityDaoImpl) {
		this.communityDaoImpl = communityDaoImpl;
	}

	public ReplyDaoImpl getReplyDaoImpl() {
		return replyDaoImpl;
	}

	public void setReplyDaoImpl(ReplyDaoImpl replyDaoImpl) {
		this.replyDaoImpl = replyDaoImpl;
	}

	public DailyAbcDaoImpl getDailyAbcDaoImpl() {
		return dailyAbcDaoImpl;
	}

	public void setDailyAbcDaoImpl(DailyAbcDaoImpl dailyAbcDaoImpl) {
		this.dailyAbcDaoImpl = dailyAbcDaoImpl;
	}

	public AdvertisementDaoImpl getAdvertisementDaoImpl() {
		return advertisementDaoImpl;
	}

	public void setAdvertisementDaoImpl(
			AdvertisementDaoImpl advertisementDaoImpl) {
		this.advertisementDaoImpl = advertisementDaoImpl;
	}

	public HappyMomentDaoImpl getHappyMomentDaoImpl() {
		return happyMomentDaoImpl;
	}

	public void setHappyMomentDaoImpl(HappyMomentDaoImpl happyMomentDaoImpl) {
		this.happyMomentDaoImpl = happyMomentDaoImpl;
	}

	public PropertyDaoImpl getPropertyDaoImpl() {
		return propertyDaoImpl;
	}

	public void setPropertyDaoImpl(PropertyDaoImpl propertyDaoImpl) {
		this.propertyDaoImpl = propertyDaoImpl;
	}

	@RequestMapping("community_page")
	public String goToCommunityPage() {
		return "adminusers/community";
	}

	@RequestMapping(value = "community_query", method = RequestMethod.GET)
	public String search(HttpServletRequest request,
			HttpServletResponse response) {
		ArrayList<Community> list = new ArrayList<Community>();
		// Page page = null;
		try {
			//String city = new String(request.getParameter("city").trim().getBytes("ISO-8859-1"), "UTF-8");
			////因为修改了服务器配置----不用转码了
			String city = request.getParameter("city").trim();
			// String pagenum = request.getParameter("pagenum");
			System.out.println(new Date()+city);
			int cityId = cityDaoImpl.queryIdByName(city);
			// System.out.println(cityId);
			if (cityId == -1) {
				request.setAttribute("communityList", null);
				// request.setAttribute("page", page);
				return "adminusers/community";
			}
			list = communityDaoImpl.queryCommunityByCityId(cityId);
			// page =
			// this.communityDaoImpl.queryCommunityByCityId(cityId,pagenum);
			// request.setAttribute("page", page);

			request.setAttribute("communityList", list);
			return "adminusers/community";

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("communityList", null);
			// request.setAttribute("page", null);
			return "adminusers/community";
		}
	}

	@RequestMapping(value = "community_delete", method = RequestMethod.GET)
	public String delete(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			int id = Integer.parseInt(request.getParameter("id").trim());
			boolean boolDel = communityDaoImpl.deleteByLogical(id);
			if (boolDel) {
				request.setAttribute("result",
						"<font color='green'>删除成功</font>");
			}
			request.setAttribute("communityList", null);
			return "adminusers/community";

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("communityList", null);
			request.setAttribute("result", "<font color='red'>删除失败</font>");
			return "adminusers/community";
		}
	}

	@RequestMapping("community_add")
	public String goToCommunityAdd() {
		return "adminusers/community_add";
	}

	@RequestMapping(value = "community_addOpt", method = RequestMethod.POST)
	public String addOpt(HttpServletRequest request,
			HttpServletResponse response) {
		Community c = null;
		try {
			String city = request.getParameter("city").trim();
			String communityName = request.getParameter("communityName").trim();
			String remark = request.getParameter("remark").trim();

			int cityId = cityDaoImpl.queryIdByName(city);
			if (cityId == -1) {
				request.setAttribute("result",
						"<font color='red'>不存在该城市...</font>");
				return "adminusers/community";
			} else {
				c = new Community();
				c.setCommunityName(communityName);
				c.setRemark(remark);
				c.setCityId(cityId);
				if (communityDaoImpl.insert(c)) {
					request.setAttribute("result",
							"<font color='green'>添加成功</font>");
					return "adminusers/community";
				} else {
					request.setAttribute("result",
							"<font color='red'>添加失败</font>");
					return "adminusers/community";
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("result", "<font color='red'>删除失败</font>");
			return "adminusers/community";
		}
	}

	@RequestMapping("shopper_page")
	public String goToShopperPage() {
		return "adminusers/shopper";
	}

	@RequestMapping("shopper_add")
	public String goToShopperAdd() {
		return "adminusers/shopper_add";
	}

	@SuppressWarnings("unchecked")
	// //////////
	@RequestMapping(value = "shopper_getCommunity", method = RequestMethod.POST)
	public void getCommunity(@RequestParam String city, PrintWriter out) {
		String result = "";
		ArrayList<Community> aList = null;
		try {
			int cityId = cityDaoImpl.queryIdByName(city);
			if (cityId == -1) {
				out.write(result);
			} else {
				aList = new ArrayList();
				aList = communityDaoImpl.queryCommunityByCityId(cityId);
				if (aList.size() > 0) {
					ArrayList cList = new ArrayList();
					for (int i = 0; i < aList.size(); i++) {
						Community com = aList.get(i);
						cList.add(com.getCommunityName());
					}
					out.write(JsonUtil.toJsonString(cList));
				} else {
					out.write(result);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			out.write(result);
		}
	}

	@RequestMapping("shopper_query")
	public String searchShopper(HttpServletRequest request,
			HttpServletResponse response) {
		ArrayList<Users> aList = null;
		try {
			// String city=new
			// String(request.getParameter("city").trim().getBytes("ISO-8859-1"),"UTF-8");
			// String community=new
			// String(request.getParameter("community").trim().getBytes("ISO-8859-1"),"UTF-8");
			String city = request.getParameter("city").trim();
			//因为修改了服务器配置----不用转码了
			//city = new String(city.getBytes("ISO-8859-1"), "UTF-8");
			String community = request.getParameter("community").trim();

			if (community != null && !community.equals("")) {
				//因为修改了服务器配置----不用转码了
				//community = new String(community.getBytes("ISO-8859-1"),"UTF-8");
			}
			// String role = request.getParameter("role").trim();

			String shopType = request.getParameter("shopType").trim();
			if (shopType != null && !shopType.equals("")) {
				////因为修改了服务器配置----不用转码了
				//shopType = new String(shopType.getBytes("ISO-8859-1"), "UTF-8");
			}
			String contentName = request.getParameter("contentName").trim();
			if (contentName != null && !contentName.equals("")) {
				//因为修改了服务器配置----不用转码了
				//contentName = new String(contentName.getBytes("ISO-8859-1"),"UTF-8");
			}

			aList = new ArrayList<Users>();
			aList = usersDaoImpl.queryUserByCCSC(city, community, shopType,
					contentName);
			/*
			 * if (city.equals("不限")) { aList = new ArrayList<Users>(); //aList
			 * = usersDaoImpl.queryShopUserByCityAndCommunity(null, null);
			 * //aList = usersDaoImpl.queryUserByCCR(null, null, role); } else
			 * if (!city.equals("不限") && (community == null ||
			 * community.equals(""))) { aList = new ArrayList<Users>(); //aList
			 * = usersDaoImpl.queryShopUserByCityAndCommunity(city, null);
			 * //aList = usersDaoImpl.queryUserByCCR(city, null, role); } else
			 * if (!city.equals("不限") && community != null &&
			 * !community.equals("")) { community = new
			 * String(community.getBytes("ISO-8859-1"), "UTF-8"); aList = new
			 * ArrayList<Users>(); //aList =
			 * usersDaoImpl.queryShopUserByCityAndCommunity(city, community);
			 * //aList = usersDaoImpl.queryUserByCCR(city, community, role); }
			 */

			/*
			 * int cityId=cityDaoImpl.queryIdByName(city);
			 * //System.out.println(cityId); if(cityId==-1){
			 * request.setAttribute("shoppers", null); return
			 * "adminusers/shopper"; }else{ //list =
			 * communityDaoImpl.queryCommunityByCityId(cityId); aList = new
			 * ArrayList<Users>(); aList =
			 * usersDaoImpl.queryShopUserByCityAndCommunity(city, community);
			 * if(aList.size()>0){ request.setAttribute("shoppers", aList);
			 * return "adminusers/shopper"; }else{
			 * request.setAttribute("shoppers", null); return
			 * "adminusers/shopper"; } }
			 */
			if (aList.size() > 0) {
				request.setAttribute("shoppers", aList);
				return "adminusers/shopper";
			} else {
				request.setAttribute("shoppers", null);
				return "adminusers/shopper";
			}

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("shoppers", null);
			return "adminusers/shopper";
		}
	}

	@SuppressWarnings("static-access")
	@RequestMapping(value = "shopper_addOpt2", method = RequestMethod.POST)
	public @ResponseBody
	void addOptShopper2(@RequestBody String msg, PrintWriter out) {
		ArrayList<Users> aList = null;//
		// PrintWriter out = null;
		String result = "";
		JSONObject jo = null;
		try {
			// String msgstr=new
			// String(request.getParameter("msgstr").trim().getBytes("ISO-8859-1"),"UTF-8");
			// String msgstr=request.getParameter("msg");

			ObjectMapper objectMapper = new ObjectMapper();
			Users u = objectMapper.configure(
					DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
					.readValue(msg, Users.class);
			/////////////0626
			//mempassword = MD5.Instance().RMD5(request.getParameter("password"));
			u.setUserPwd(MD5.Instance().RMD5("000000nciae"));
			// Users u = objectMapper.readValue(msg, Users.class);
			// out = response.getWriter();

			// MultipartFile file = multiRequest.getFile(iter.next());
			// String fileName = u.getShopLogo();
			// String path ="H:/tempImg/" +fileName;
			// File localFile = new File(path);
			// MultipartFile.transferTo(localFile);

			if (usersDaoImpl.addShopUser(u)) {
				System.out.println(msg);
				// request.setAttribute("shoppers", null);
				// request.setAttribute("result",
				// "<font color='green'>添加成功</font>");
				// return "adminusers/shopper";
				result = "success";
				jo = new JSONObject();
				jo.put(result, true);
				out.write(jo.toString());
			} else {
				// request.setAttribute("shoppers", null);
				// request.setAttribute("result",
				// "<font color='red'>添加失败</font>");
				// return "adminusers/shopper";
				result = "error";
				jo = new JSONObject();
				jo.put(result, false);
				out.write(jo.toString());
			}

		} catch (Exception e) {
			e.printStackTrace();
			// request.setAttribute("shoppers", null);
			// request.setAttribute("result", "<font color='red'>添加失败</font>");
			// return "adminusers/shopper";
			result = "error";
			jo = new JSONObject();
			jo.put(result, false);
			out.write(jo.toString());
		}
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "shopper_add_getCommunity", method = RequestMethod.POST)
	public void getCommunityAdd(@RequestParam String city, PrintWriter out) {// @RequestParam
																				// String
																				// city,PrintWriter
																				// out
		String result = "";
		ArrayList<Community> aList = null;
		try {
			if (city == "不限") {
				aList = new ArrayList();
				aList = communityDaoImpl.queryCommunity(-1);
				if (aList.size() > 0) {
					ArrayList cList = new ArrayList();
					for (int i = 0; i < aList.size(); i++) {
						Community com = aList.get(i);
						cList.add(com.getCommunityName());
					}
					out.write(JsonUtil.toJsonString(cList));
				} else {
					out.write(result);
				}
			} else {
				int cityId = cityDaoImpl.queryIdByName(city);
				if (cityId == -1) {
					out.write(result);
				} else {
					aList = new ArrayList();
					aList = communityDaoImpl.queryCommunity(cityId);
					if (aList.size() > 0) {
						ArrayList cList = new ArrayList();
						for (int i = 0; i < aList.size(); i++) {
							Community com = aList.get(i);
							cList.add(com.getCommunityName());
						}
						out.write(JsonUtil.toJsonString(cList));
					} else {
						out.write(result);
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			out.write(result);
		}
	}

	@RequestMapping(value = "shopper_delete", method = RequestMethod.GET)
	public String shopperDel(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			int id = Integer.parseInt(request.getParameter("id").trim());
			boolean boolDel = usersDaoImpl.deleteShopUser(id);
			if (boolDel) {
				request.setAttribute("shoppers", null);
				request.setAttribute("result",
						"<font color='green'>删除成功</font>");
			}
			request.setAttribute("shoppers", null);
			return "adminusers/shopper";

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("shoppers", null);
			request.setAttribute("result", "<font color='red'>删除失败</font>");
			return "adminusers/shopper";
		}
	}

	@RequestMapping("soft_reply")
	public String goToSearchSoftReply() {
		return "adminusers/soft_reply";
	}

	@RequestMapping("reply_soft_search")
	public String searchSoftReply(HttpServletRequest request,
			HttpServletResponse response) {
		ArrayList<Reply> aList = null;
		try {

			aList = new ArrayList<Reply>();
			aList = replyDaoImpl.querySoftwareReply();

			if (aList.size() > 0) {
				request.setAttribute("softReplys", aList);
				return "adminusers/soft_reply";
			} else {
				request.setAttribute("softReplys", null);
				return "adminusers/soft_reply";
			}

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("softReplys", null);
			return "adminusers/soft_reply";
		}
	}

	@RequestMapping(value = "reply_soft_del", method = RequestMethod.GET)
	public String softReplyDel(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			int id = Integer.parseInt(request.getParameter("id").trim());
			boolean boolDel = replyDaoImpl.deleteSoftwareReply(id);
			if (boolDel) {
				request.setAttribute("softReplys", null);
				request.setAttribute("result",
						"<font color='green'>删除成功</font>");
			}
			request.setAttribute("softReplys", null);
			return "adminusers/soft_reply";

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("softReplys", null);
			request.setAttribute("result", "<font color='red'>删除失败</font>");
			return "adminusers/soft_reply";
		}
	}

	/*
	 * @RequestMapping(method = RequestMethod.POST) public String
	 * handleThirdUploadProcess(BoUploadFile uploadFile, Model model) throws
	 * Exception{ MultipartFile file = uploadFile.getImageFile();
	 * //这里你可以通过uploadFile.getName()...等等获取用户输入的其他普通信息了。
	 * model.addAttribute("success", "true"); return "adminusers/upload"; }
	 * 
	 * @RequestMapping("upload_page") public String goToUpload(){ return
	 * "adminusers/upload"; }
	 */
	@RequestMapping("dailyAbc_page")
	public String goToDailyAbcPage() {
		return "adminusers/dailyAbc";
	}

	@RequestMapping("dailyAbc_add")
	public String goToDailyAbcAdd() {
		return "adminusers/dailyAbc_add";
	}

	@RequestMapping("dailyAbc_search")
	public String searchDailyAbc(HttpServletRequest request,
			HttpServletResponse response) {
		ArrayList<DailyAbc> aList = null;
		try {
			aList = new ArrayList<DailyAbc>();
			aList = dailyAbcDaoImpl.queryAll();

			if (aList.size() > 0) {
				request.setAttribute("dailyAbcs", aList);
				return "adminusers/dailyAbc";
			} else {
				request.setAttribute("dailyAbcs", null);
				return "adminusers/dailyAbc";
			}

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("dailyAbcs", null);
			return "adminusers/dailyAbc";
		}
	}

	@RequestMapping(value = "dailyAbc_del", method = RequestMethod.GET)
	public String DailyAbcDel(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			int id = Integer.parseInt(request.getParameter("id").trim());
			boolean boolDel = dailyAbcDaoImpl.delete(id);
			if (boolDel) {
				request.setAttribute("dailyAbcs", null);
				request.setAttribute("result",
						"<font color='green'>删除成功</font>");
			}
			request.setAttribute("dailyAbcs", null);
			return "adminusers/dailyAbc";

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("dailyAbcs", null);
			request.setAttribute("result", "<font color='red'>删除失败</font>");
			return "adminusers/dailyAbc";
		}
	}

	@RequestMapping(value = "dailyAbc_addOpt1", method = RequestMethod.POST)
	public @ResponseBody
	void addOptDailyAbc1(@RequestBody String msg, PrintWriter out) {
		ArrayList<DailyAbc> aList = null;//
		String result = "";
		JSONObject jo = null;
		try {

			ObjectMapper objectMapper = new ObjectMapper();
			DailyAbc d = objectMapper.configure(
					DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
					.readValue(msg, DailyAbc.class);

			if (dailyAbcDaoImpl.add(d)) {
				System.out.println(msg);
				result = "success";
				jo = new JSONObject();
				jo.put(result, true);
				out.write(jo.toString());
			} else {
				result = "error";
				jo = new JSONObject();
				jo.put(result, false);
				out.write(jo.toString());
			}

		} catch (Exception e) {
			e.printStackTrace();
			result = "error";
			jo = new JSONObject();
			jo.put(result, false);
			out.write(jo.toString());
		}
	}

	@RequestMapping(value = "dailyAbc_addOpt", method = RequestMethod.POST)
	public String addOptDailyAbc(HttpServletRequest request,
			HttpServletResponse response) {
		DailyAbc dailyAbc = null;
		try {
			String title = request.getParameter("title").trim();
			String content = request.getParameter("content").trim();
			dailyAbc = new DailyAbc();
			dailyAbc.setTitle(title);
			dailyAbc.setContent(content);
			boolean addBool = dailyAbcDaoImpl.add(dailyAbc);
			if (addBool) {
				request.setAttribute("result",
						"<font color='green'>添加成功</font>");
				return "adminusers/dailyAbc";
			} else {
				request.setAttribute("result", "<font color='red'>添加失败</font>");
				return "adminusers/dailyAbc";
			}

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("result", "<font color='red'>添加失败</font>");
			return "adminusers/dailyAbc";
		}
	}

	// //////////////////////////////////happy
	@RequestMapping("happyMoment_page")
	public String goToHappyMomentPage() {
		return "adminusers/happyMoment";
	}

	@RequestMapping("happyMoment_add")
	public String goToHappyMomentAdd() {
		return "adminusers/happyMoment_add";
	}

	@RequestMapping("happyMoment_search")
	public String searchHappyMoment(HttpServletRequest request,
			HttpServletResponse response) {
		ArrayList<HappyMoment> aList = null;
		try {
			aList = new ArrayList<HappyMoment>();
			aList = happyMomentDaoImpl.queryAll();

			if (aList.size() > 0) {
				request.setAttribute("happyMoments", aList);
				return "adminusers/happyMoment";
			} else {
				request.setAttribute("happyMoments", null);
				return "adminusers/happyMoment";
			}

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("happyMoments", null);
			return "adminusers/happyMoment";
		}
	}

	@RequestMapping(value = "happyMoment_del", method = RequestMethod.GET)
	public String HappyMomentDel(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			int id = Integer.parseInt(request.getParameter("id").trim());
			boolean boolDel = happyMomentDaoImpl.delete(id);
			if (boolDel) {
				request.setAttribute("happyMoments", null);
				request.setAttribute("result",
						"<font color='green'>删除成功</font>");
			}
			request.setAttribute("happyMoments", null);
			return "adminusers/happyMoment";

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("happyMoments", null);
			request.setAttribute("result", "<font color='red'>删除失败</font>");
			return "adminusers/happyMoment";
		}
	}

	@RequestMapping(value = "happyMoment_addOpt1", method = RequestMethod.POST)
	public @ResponseBody
	void addOpthappyMoment1(@RequestBody String msg, PrintWriter out) {
		ArrayList<DailyAbc> aList = null;//
		String result = "";
		JSONObject jo = null;
		try {

			ObjectMapper objectMapper = new ObjectMapper();
			HappyMoment d = objectMapper.configure(
					DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
					.readValue(msg, HappyMoment.class);

			if ("".equals(d.getContent()) || "".equals(d.getTitle())) {
				result = "error";
				jo = new JSONObject();
				jo.put(result, false);
				out.write(jo.toString());
				return;
			}
			if (happyMomentDaoImpl.add(d)) {
				System.out.println(msg);
				result = "success";
				jo = new JSONObject();
				jo.put(result, true);
				out.write(jo.toString());
			} else {
				result = "error";
				jo = new JSONObject();
				jo.put(result, false);
				out.write(jo.toString());
			}

		} catch (Exception e) {
			e.printStackTrace();
			result = "error";
			jo = new JSONObject();
			jo.put(result, false);
			out.write(jo.toString());
		}
	}

	@RequestMapping(value = "happyMoment_addOpt", method = RequestMethod.POST)
	public String addOptHappyMoment(HttpServletRequest request,
			HttpServletResponse response) {
		HappyMoment happyMoment = null;
		try {
			String title = request.getParameter("title").trim();
			String content = request.getParameter("content").trim();
			happyMoment = new HappyMoment();
			happyMoment.setTitle(title);
			happyMoment.setContent(content);
			boolean addBool = happyMomentDaoImpl.add(happyMoment);
			if (addBool) {
				request.setAttribute("result",
						"<font color='green'>添加成功</font>");
				return "adminusers/happyMoment";
			} else {
				request.setAttribute("result", "<font color='red'>添加失败</font>");
				return "adminusers/happyMoment";
			}

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("result", "<font color='red'>添加失败</font>");
			return "adminusers/happyMoment";
		}
	}

	// ////advertisement///////////////////ad
	@RequestMapping("ad_page")
	public String goToAdPage() {
		return "adminusers/ad";
	}

	@RequestMapping("ad_add")
	public String goToAdAdd() {
		return "adminusers/ad_add";
	}

	@RequestMapping("ad_search")
	public String searchAd(HttpServletRequest request,
			HttpServletResponse response) {
		ArrayList<Advertisement> aList = null;
		try {
			aList = new ArrayList<Advertisement>();
			aList = advertisementDaoImpl.queryAdvertisements();

			if (aList.size() > 0) {
				request.setAttribute("ads", aList);
				return "adminusers/ad";
			} else {
				request.setAttribute("ads", null);
				return "adminusers/ad";
			}

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("ads", null);
			return "adminusers/ad";
		}
	}

	@RequestMapping(value = "ad_del", method = RequestMethod.GET)
	public String AdDel(HttpServletRequest request, HttpServletResponse response) {
		try {
			int id = Integer.parseInt(request.getParameter("id").trim());
			String imgUrl = advertisementDaoImpl.lxFindAdImgUrl(id);
			boolean boolDel = advertisementDaoImpl.delete(id);
			if (boolDel) {
				
				/////////根据photoid查找图片，删除图片
				if(imgUrl!=null){
					String path = imgUrl.substring(0, imgUrl.lastIndexOf("/"));
					String fileName = imgUrl.substring(path.length()+1, imgUrl.length());
					String logoRealPathDir = request.getSession().getServletContext().getRealPath(path);
					File logoSaveFile = new File(logoRealPathDir);
					if (logoSaveFile.exists()){
						String url = logoRealPathDir + File.separator + fileName;//新文件URL
						new File(url).delete();
					}
				}
				
				
				request.setAttribute("ads", null);
				request.setAttribute("result",
						"<font color='green'>删除成功</font>");
			}
			request.setAttribute("ads", null);
			return "adminusers/ad";

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("ads", null);
			request.setAttribute("result", "<font color='red'>删除失败</font>");
			return "adminusers/ad";
		}
	}

	@RequestMapping(value = "ad_addOpt1", method = RequestMethod.POST)
	public @ResponseBody
	void addOptAd1(@RequestBody String msg, PrintWriter out) {
		ArrayList<Advertisement> aList = null;//
		String result = "";
		JSONObject jo = null;
		try {

			ObjectMapper objectMapper = new ObjectMapper();
			Advertisement d = objectMapper.configure(
					DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
					.readValue(msg, Advertisement.class);
			// d.setAdUrl(d.getAdUrl().toLowerCase());
			// ||"".equals(d.getAdUrl())//////////////////////////////////////////0602
			if ("".equals(d.getAdContent()) || "".equals(d.getAdTitle())) {
				result = "error";
				jo = new JSONObject();
				jo.put(result, false);
				out.write(jo.toString());
				return;
			}
			if (advertisementDaoImpl.add(d)) {
				System.out.println(msg);
				result = "success";
				jo = new JSONObject();
				jo.put(result, true);
				out.write(jo.toString());
			} else {
				result = "error";
				jo = new JSONObject();
				jo.put(result, false);
				out.write(jo.toString());
			}

		} catch (Exception e) {
			e.printStackTrace();
			result = "error";
			jo = new JSONObject();
			jo.put(result, false);
			out.write(jo.toString());
		}
	}

	// //////////////internative////////////////////////////交互

	@RequestMapping(value = "admin_getThreeAd", method = RequestMethod.POST)
	public void getThreeAd(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			ArrayList<Object> adList = new ArrayList<Object>();
			String result = null;
			PrintWriter out = null;
			adList = advertisementDaoImpl.queryNewThreeAd();
			if (adList != null) {
				result = JsonUtil.toJsonString(adList);
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

	@RequestMapping(value = "admin_getANewHappyMoment", method = RequestMethod.POST)
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
			list.add(happyMoment);
			if (list.size() > 0) {
				result = JsonUtil.toJsonString(list);
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

	@RequestMapping(value = "admin_getANewDailyAbc", method = RequestMethod.POST)
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

	// ///////////////////////////////////////////物业和商户分开处理。。。。。0531
	@RequestMapping("property_page")
	public String goToPropertyPage() {
		return "adminusers/property";
	}

	@RequestMapping("property_add")
	public String goToPropertyAdd() {
		return "adminusers/property_add";
	}

	@SuppressWarnings("unchecked")
	// //////////
	@RequestMapping(value = "property_getMyCommunity", method = RequestMethod.POST)
	public void getMyCommunity(@RequestParam String city, PrintWriter out) {
		String result = "";
		ArrayList<Object> aList = null;
		try {
			int cityId = cityDaoImpl.queryIdByName(city);
			if (cityId == -1) {
				out.write(result);
			} else {
				aList = new ArrayList<Object>();
				aList = propertyDaoImpl.queryMyCommunity();
				if (aList.size() > 0) {
					out.write(JsonUtil.toJsonString(aList));
				} else {
					out.write(result);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			out.write(result);
		}
	}

	@RequestMapping(value = "shopper_getMyCommunity", method = RequestMethod.POST)
	public void getMyShopperCommunity(@RequestParam String city, PrintWriter out) {
		String result = "";
		ArrayList<Object> aList = null;
		try {
			int cityId = cityDaoImpl.queryIdByName(city);
			if (cityId == -1) {
				out.write(result);
			} else {
				aList = new ArrayList<Object>();
				aList = usersDaoImpl.queryMyCommunity();
				if (aList.size() > 0) {
					out.write(JsonUtil.toJsonString(aList));
				} else {
					out.write(result);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			out.write(result);
		}
	}

	@RequestMapping("property_query")
	public String searchProperty(HttpServletRequest request,
			HttpServletResponse response) {
		ArrayList<Users> aList = null;
		try {
			String city = request.getParameter("city").trim();
			////因为修改了服务器配置----不用转码了
			//city = new String(city.getBytes("ISO-8859-1"), "UTF-8");
			String community = request.getParameter("community").trim();

			if (city.equals("不限")) {
				aList = new ArrayList<Users>();
				aList = propertyDaoImpl.queryPropertyByCC(null, null);
			} else if (!city.equals("不限")
					&& (community == null || community.equals(""))) {
				aList = new ArrayList<Users>();
				aList = propertyDaoImpl.queryPropertyByCC(city, null);
			} else if (!city.equals("不限") && community != null
					&& !community.equals("")) {
				////因为修改了服务器配置----不用转码了
				//community = new String(community.getBytes("ISO-8859-1"),"UTF-8");
				aList = new ArrayList<Users>();
				aList = propertyDaoImpl.queryPropertyByCC(city, community);
			}
			if (aList.size() > 0) {
				request.setAttribute("properties", aList);
				return "adminusers/property";
			} else {
				request.setAttribute("properties", null);
				return "adminusers/property";
			}

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("properties", null);
			return "adminusers/property";
		}
	}

	@SuppressWarnings("static-access")
	@RequestMapping(value = "property_addOpt", method = RequestMethod.POST)
	public @ResponseBody
	void addOptProperty(@RequestBody String msg, PrintWriter out) {
		ArrayList<Users> aList = null;//
		// PrintWriter out = null;
		String result = "";
		JSONObject jo = null;
		try {

			ObjectMapper objectMapper = new ObjectMapper();
			Users u = objectMapper.configure(
					DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
					.readValue(msg, Users.class);

			
			/////////////0626
			/////////////0626
			//mempassword = MD5.Instance().RMD5(request.getParameter("password"));
			u.setUserPwd(MD5.Instance().RMD5("000000nciae"));
			
			if (propertyDaoImpl.addProperty(u)) {
				System.out.println(msg);
				result = "success";
				jo = new JSONObject();
				jo.put(result, true);
				out.write(jo.toString());
			} else {
				result = "error";
				jo = new JSONObject();
				jo.put(result, false);
				out.write(jo.toString());
			}

		} catch (Exception e) {
			e.printStackTrace();
			result = "error";
			jo = new JSONObject();
			jo.put(result, false);
			out.write(jo.toString());
		}
	}

	@SuppressWarnings("unchecked")
	// /////property_add_getCommunity与shopper_add_getCommunity同
	@RequestMapping(value = "property_add_getCommunity", method = RequestMethod.POST)
	public void getPropertyCommunityAdd(@RequestParam String city,
			PrintWriter out) {// @RequestParam
		// String
		// city,PrintWriter
		// out
		String result = "";
		ArrayList<Community> aList = null;
		try {
			if (city == "不限") {
				aList = new ArrayList();
				aList = communityDaoImpl.queryCommunity(-1);
				if (aList.size() > 0) {
					ArrayList cList = new ArrayList();
					for (int i = 0; i < aList.size(); i++) {
						Community com = aList.get(i);
						cList.add(com.getCommunityName());
					}
					out.write(JsonUtil.toJsonString(cList));
				} else {
					out.write(result);
				}
			} else {
				int cityId = cityDaoImpl.queryIdByName(city);
				if (cityId == -1) {
					out.write(result);
				} else {
					aList = new ArrayList();
					aList = communityDaoImpl.queryCommunity(cityId);
					if (aList.size() > 0) {
						ArrayList cList = new ArrayList();
						for (int i = 0; i < aList.size(); i++) {
							Community com = aList.get(i);
							cList.add(com.getCommunityName());
						}
						out.write(JsonUtil.toJsonString(cList));
					} else {
						out.write(result);
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			out.write(result);
		}
	}

	@RequestMapping(value = "property_delete", method = RequestMethod.GET)
	public String wuyeDel(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			int id = Integer.parseInt(request.getParameter("id").trim());
			boolean boolDel = propertyDaoImpl.deleteProperty(id);
			if (boolDel) {
				request.setAttribute("properties", null);
				request.setAttribute("result",
						"<font color='green'>删除成功</font>");
			}
			request.setAttribute("properties", null);
			return "adminusers/property";

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("properties", null);
			request.setAttribute("result", "<font color='red'>删除失败</font>");
			return "adminusers/property";
		}
	}

	@RequestMapping(value = "admin_logout", method = RequestMethod.GET)
	public String adminLogout(HttpServletRequest request) throws Exception {
		HttpSession session = request.getSession();
		session.invalidate(); // 清空session
		return "index";
	}

	// ///////////////////////0601
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "ad_getMyCommunity", method = RequestMethod.POST)
	public void getMyAdCommunity(@RequestParam String city, PrintWriter out) {
		String result = "";
		ArrayList<Object> aList = null;
		try {
			if (city == "不限") {
				aList = new ArrayList();
				aList = communityDaoImpl.queryMyCommunity(-1);
				if (aList.size() > 0) {
					// ArrayList cList = new ArrayList();
					// for (int i = 0; i < aList.size(); i++) {
					// Community com = aList.get(i);
					// cList.add(com.getCommunityName());
					// }
					// out.write(JsonUtil.toJsonString(cList));
					out.write(JsonUtil.toJsonString(aList));
				} else {
					out.write(result);
				}
			} else {
				int cityId = cityDaoImpl.queryIdByName(city);
				if (cityId == -1) {
					out.write(result);
				} else {
					aList = new ArrayList();
					aList = communityDaoImpl.queryMyCommunity(cityId);
					if (aList.size() > 0) {
						// ArrayList cList = new ArrayList();
						// for (int i = 0; i < aList.size(); i++) {
						// Community com = aList.get(i);
						// cList.add(com.getCommunityName());
						// }
						// out.write(JsonUtil.toJsonString(cList));
						out.write(JsonUtil.toJsonString(aList));
					} else {
						out.write(result);
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			out.write(result);
		}
	}

	@RequestMapping(value = "ad_getShopperInfo", method = RequestMethod.POST)
	public void getMyAdShopper(@RequestParam String phone, PrintWriter out) {
		String result = "";
		ArrayList<Users> user = null;
		try {
			if (phone == null || phone.equals("")) {
				out.write(result);
			} else {
				user = usersDaoImpl.queryUserByPhone(phone);
				if (user.size() > 0) {
					out.write(JsonUtil.toJsonStringUser(user));
				} else {
					out.write(result);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			out.write(result);
		}
	}

	// ///////////////////////////0602
	@SuppressWarnings("static-access")
	@RequestMapping(value = "ad_addOpt", method = RequestMethod.POST)
	public @ResponseBody
	void addOptAd(@RequestBody String msg, PrintWriter out) {
		ArrayList<Advertisement> aList = null;//
		String result = "";
		JSONObject jo = null;
		try {
			JSONObject dataJson = JSONObject.fromObject(msg);
			System.out.println(dataJson);
			System.out.println(dataJson.get("shopType"));
			String shopType = (String) dataJson.get("shopType");
			String role = (String) dataJson.get("role");
			String communityList = (String) dataJson.get("communityList");
			ObjectMapper objectMapper = new ObjectMapper();
			Advertisement d = objectMapper.configure(
					DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
					.readValue(msg, Advertisement.class);
			if (role.equals("shop") && shopType.equals("1")) {
				d.setCommunityList(null);
			} else {
				if (communityList == null || communityList.equals("")) {
					result = "error";
					jo = new JSONObject();
					jo.put(result, false);
					out.write(jo.toString());
					return;
				}
			}
			if ("".equals(d.getAdContent()) || "".equals(d.getAdTitle())) {
				result = "error";
				jo = new JSONObject();
				jo.put(result, false);
				out.write(jo.toString());
				return;
			}
			if (advertisementDaoImpl.add(d)) {
				System.out.println(msg);
				result = "success";
				jo = new JSONObject();
				jo.put(result, true);
				out.write(jo.toString());
			} else {
				result = "error";
				jo = new JSONObject();
				jo.put(result, false);
				out.write(jo.toString());
			}

		} catch (Exception e) {
			e.printStackTrace();
			result = "error";
			jo = new JSONObject();
			jo.put(result, false);
			out.write(jo.toString());
		}
	}
	/////////////////////////////////////////////0625-----小区的开通与关闭
	@RequestMapping(value = "community_Open", method = RequestMethod.GET)
	public String community_open(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			int id = Integer.parseInt(request.getParameter("id").trim());
			boolean boolDel = communityDaoImpl.updateToOpen(id);
			if (boolDel) {
				request.setAttribute("result",
						"<font color='green'>开通成功</font>");
			}
			request.setAttribute("communityList", null);
			return "adminusers/community";

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("communityList", null);
			request.setAttribute("result", "<font color='red'>开通失败</font>");
			return "adminusers/community";
		}
	}
	
	@RequestMapping(value = "community_Close", method = RequestMethod.GET)
	public String community_close(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			int id = Integer.parseInt(request.getParameter("id").trim());
			boolean boolDel = communityDaoImpl.updateToClose(id);
			if (boolDel) {
				request.setAttribute("result",
						"<font color='green'>关闭成功</font>");
			}
			request.setAttribute("communityList", null);
			return "adminusers/community";

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("communityList", null);
			request.setAttribute("result", "<font color='red'>关闭失败</font>");
			return "adminusers/community";
		}
	}
	
	//////////0626
	@RequestMapping("shopper_setImgNum")
    public String handleSetImgNum() { 
        return "adminusers/setImgNum";  
    }
	////////0627
	@RequestMapping(value = "shopper_dealImgNum", method = RequestMethod.POST)
    public String dealSetImgNum(HttpServletRequest request,HttpServletResponse response) { 
		try {
			int id = Integer.parseInt(request.getParameter("id").trim());
			int imgNum = Integer.parseInt(request.getParameter("imgNumber").trim());
			//boolean boolDel = usersDaoImpl.deleteShopUser(id);
			boolean boolSet = usersDaoImpl.setImgNum(id, imgNum);
			int intNum = usersDaoImpl.limitUploadTime(id);
			int delnum = intNum-(imgNum-1);
			if(delnum>0){
				boolean boolDel = usersDaoImpl.lxDelImgNum(id, delnum);
				if (boolDel && boolSet) {
					request.setAttribute("shoppers", null);
					request.setAttribute("result",
							"<font color='green'>设置成功</font>");
				}else{
					request.setAttribute("shoppers", null);
					request.setAttribute("result", "<font color='red'>设置失败</font>");
				}
			}else{
				if (boolSet) {
					request.setAttribute("shoppers", null);
					request.setAttribute("result",
							"<font color='green'>设置成功</font>");
				}else{
					request.setAttribute("shoppers", null);
					request.setAttribute("result", "<font color='red'>设置失败</font>");
				}
			}
			request.setAttribute("shoppers", null);
			return "adminusers/shopper";

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("shoppers", null);
			request.setAttribute("result", "<font color='red'>设置失败</font>");
			return "adminusers/shopper";
		}
    }
	
	/////////0628
	@RequestMapping(value = "shopper_property_setPwd", method = RequestMethod.GET)
	public String shopper_setPwd(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			int id = Integer.parseInt(request.getParameter("id").trim());
			////////加密
			String pwd = MD5.Instance().RMD5("000000nciae");
			boolean boolSetPwd = usersDaoImpl.lxResetPwd(id, pwd);
			if (boolSetPwd) {
				request.setAttribute("result",
						"<font color='green'>密码重置成功(000000nciae)</font>");
			}
			request.setAttribute("communityList", null);
			return "adminusers/community";

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("communityList", null);
			request.setAttribute("result", "<font color='red'>密码重置失败</font>");
			return "adminusers/community";
		}
	}
	
	
		// ///////////////////////0628
		@RequestMapping(value = "shopper_chkPhone", method = RequestMethod.POST)
		public void shopper_chkPhone(@RequestParam String phone, PrintWriter out) {
			String result = "";
			try {
				if (phone != null&&!phone.equals("")) {
					if(usersDaoImpl.lxExistsPhone(phone)){
						result = "exist";
					}else{
						result = "none";
					}
					out.write(result);
				} else {
					out.write(result);
				}

			} catch (Exception e) {
				e.printStackTrace();
				out.write(result);
			}
		}
		// ///////////////////////0703
				@RequestMapping(value = "shopper_chkLoginName", method = RequestMethod.POST)
				public void shopper_chkLoginName(@RequestParam String userName, PrintWriter out) {
					String result = "";
					try {
						if (userName != null&&!userName.equals("")) {
							if(usersDaoImpl.lxExistsUserName(userName)){
								result = "exist";
							}else{
								result = "none";
							}
							out.write(result);
						} else {
							out.write(result);
						}

					} catch (Exception e) {
						e.printStackTrace();
						out.write(result);
					}
				}
		
		/////////////////////0703
		@RequestMapping("changepwdnow")
		public String changePwdNow(HttpServletRequest request){
			String userpwd = request.getParameter("password");
			HttpSession session =request.getSession();
			/////////////////0627
			userpwd = MD5.Instance().RMD5(userpwd);
			/////////////////
			try {
				int id=Integer.parseInt(session.getAttribute("userid").toString());
				/////0703
				String oldPassword=request.getParameter("password2").trim();
				//////////////////////0627
				oldPassword = MD5.Instance().RMD5(oldPassword);
				//////////////////////
				if(usersDaoImpl.chkAdminPwd(id, oldPassword)){
					if(usersDaoImpl.lxChangePwd(id, userpwd))
					{
						String result="修改成功!";
						
						//System.out.print("1111111111111111111");
						request.setAttribute("result1", result);
						//out.println("<script>window.alert('修改成功！')</script>");
						return "adminusers/changepwd";
						
						
					}
					else
					{
						String result1="修改失败!";
						System.out.print((session.getAttribute("userid").toString())+"3333333333333333333");
						//out.println("<script>window.alert('修改失败！')</script>");
						request.setAttribute("result1", result1);
						return "adminusers/changepwd";
					}
				}else{
					String result2="旧密码不正确!";
					request.setAttribute("result1", result2);
					return "adminusers/changepwd";
				}
					
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
			
		}
		/////////////0703
		@RequestMapping("gochangepwd")
		public String goChangePwd(){
			return "adminusers/changepwd";
		}

}
