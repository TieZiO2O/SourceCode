package com.nciae.community.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.nciae.community.dao.impl.UsersDaoImpl;
import com.nciae.community.domain.ShopImg;
import com.nciae.community.domain.Users;
//import com.nciae.ebookshop.servlet.HttpServletResponse;
import com.nciae.community.utils.MD5;

@Controller
@RequestMapping("/user")
public class UsersController {

	private UsersDaoImpl usersDaoImpl;

	public UsersDaoImpl getUsersDaoImpl() {
		return usersDaoImpl;
	}

	public void setUsersDaoImpl(UsersDaoImpl usersDaoImpl) {
		this.usersDaoImpl = usersDaoImpl;
	}
	
	@RequestMapping(value="login",method=RequestMethod.POST)
	public String login(HttpServletRequest request){
		try {
			HttpSession session =request.getSession();
			String username=request.getParameter("username").trim();
			String password=request.getParameter("password").trim();
			
			////0626
			password = MD5.Instance().RMD5(password);
			//System.out.println(password);
			String role=usersDaoImpl.login(username, password);
			String loginresult=null;
			if(role.equals(Users.ROLE_ADMIN)){
				
				session.setAttribute("userid",usersDaoImpl.selectShopUserId(username));
				session.setAttribute("username",username);
				//System.out.print("1111111111111111111");
				return "main_admin";
				
			}else if(role.equals(Users.ROLE_SHOP)){
				
				session.setAttribute("userid",usersDaoImpl.selectShopUserId(username));
				session.setAttribute("username",username);
				//System.out.print("222222222222222222");
				return "main_shop";
			}
			else if(role.equals(Users.ROLE_PROPERTY)){
				session.setAttribute("userid",usersDaoImpl.selectShopUserId(username));
				session.setAttribute("username",username);
				return "main_property";
			}
			else
			{
				request.setAttribute("error", "密码错误或用户不存在!请重试...");
				return "index";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}
	
	@RequestMapping("admin_top")
	public String goToAdminTop(){
		return "adminusers/top";
	}
	
	@RequestMapping("admin_left")
	public String goToAdminLeft(){
		return "adminusers/left";
	}
	
	@RequestMapping("admin_main")
	public String goToAdminMain(){
		return "adminusers/main";
	}
	
	@RequestMapping("shop_top")
	public String goToShopTop(){
		return "shopusers/top";
	}
	
	@RequestMapping("shop_left")
	public String goToShopLeft(){
		return "shopusers/left";
	}
	
	@RequestMapping("shop_main")
	public String goToShopMain(){
		
		return "shopusers/main";
	}
	@RequestMapping("goaddshopimg")
	public String goAddShopImg(HttpServletRequest request, HttpServletResponse resp) throws NumberFormatException, Exception{
		HttpSession session =request.getSession();
		PrintWriter out = resp.getWriter();
		//usersDaoImpl.limitUploadTime(Integer.parseInt(session.getAttribute("userid").toString()))<3
		///////////////0627------lx
		/////logo+展示店面
		int imgNumber = usersDaoImpl.searchTotalNumlx(Integer.parseInt(session.getAttribute("userid").toString()));
		if(usersDaoImpl.limitUploadTime(Integer.parseInt(session.getAttribute("userid").toString()))<imgNumber-1)
		{
			return "shopusers/uploadshopimg";
		}
		else
		{
			out.println("<script>window.alert('图片已达上限！')</script>");
			//return "shopusers/shop_img";
		}
		return "shopusers/shop_img";
	}
	@RequestMapping("gochangephoto")
	public String goTocChangePhoto(){
		
		return "shopusers/upload5";
	}
	@RequestMapping("gochangepwd")
	public String goChangePwd(){
		return "shopusers/changepwd";
	}
	@RequestMapping("changepwdnow")
	public String changePwdNow(HttpServletRequest request){
		String userpwd = request.getParameter("password");
		HttpSession session =request.getSession();
		/////////////////0627
		userpwd = MD5.Instance().RMD5(userpwd);
		/////////////////
		try {
			String username=session.getAttribute("username").toString();
			String password2=request.getParameter("password2").trim();
			//////////////////////0627
			password2 = MD5.Instance().RMD5(password2);
			//////////////////////
			String role=usersDaoImpl.login(username, password2);
			if(role.equals(Users.ROLE_ADMIN)||role.equals(Users.ROLE_SHOP)){
				if(usersDaoImpl.changePwd(Integer.parseInt(session.getAttribute("userid").toString()), userpwd))
				{
					String result="修改成功!";
					request.setAttribute("result1", result);
					//out.println("<script>window.alert('修改成功！')</script>");
					return "shopusers/changepwd";
					
					
				}
				else
				{
					String result1="修改失败!";
					request.setAttribute("result1", result1);
					return "shopusers/changepwd";
				}
				
			}else{
				String result2="旧密码不正确!";
				request.setAttribute("result1", result2);
				return "shopusers/changepwd";
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}
	
	@RequestMapping(value="update",method=RequestMethod.GET)
	public String goToUpdate(HttpServletRequest request){
		HttpSession session =request.getSession();
		try {
			session.setAttribute("shopuser", usersDaoImpl.selectShopUser(Integer.parseInt(session.getAttribute("userid").toString())));
			String filePath=usersDaoImpl.selectShopUser(Integer.parseInt(session.getAttribute("userid").toString())).getShopLogo();
			//filePath=new String(filePath.getBytes("ISO8859-1"),"UTF-8");
			//0621-----修改了server.xml配置文件
			session.setAttribute("photopath", filePath);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "shopusers/update";
	}
	
	@RequestMapping(value="updatenow",method=RequestMethod.POST)
	public String updateNow(HttpServletRequest request, HttpServletResponse resp){
		HttpSession session =request.getSession();
		PrintWriter out = null;
		try {
			out = resp.getWriter();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Users user=new Users();
		////////////////////////0627
		if(request.getParameter("address")!=null&&!request.getParameter("address").equals("")){
			user.setAddress(request.getParameter("address"));
		}
		if(request.getParameter("realname")!=null&&!request.getParameter("realname").equals("")){
			user.setRealName(request.getParameter("realname"));
		}
		if(request.getParameter("linkman")!=null&&!request.getParameter("linkman").equals("")){
			user.setLinkman(request.getParameter("linkman"));
		}
		if(request.getParameter("phone")!=null&&!request.getParameter("phone").equals("")){
			user.setPhone(request.getParameter("phone"));
		}
		if(request.getParameter("aboutus")!=null&&!request.getParameter("aboutus").equals("")){
			user.setAboutUs(request.getParameter("aboutus"));
		}
		if(request.getParameter("shopinfo")!=null&&!request.getParameter("shopinfo").equals("")){
			user.setShopInfo(request.getParameter("shopinfo"));
		}
		if(request.getParameter("customerNotice")!=null&&!request.getParameter("customerNotice").equals("")){
			user.setCustomerNotice(request.getParameter("customerNotice"));
		}
		/*
		user.setAddress(request.getParameter("address"));
		user.setRealName(request.getParameter("realname"));
		user.setLinkman(request.getParameter("linkman"));
		user.setPhone(request.getParameter("phone"));
		user.setAboutUs(request.getParameter("aboutus"));
		user.setShopInfo(request.getParameter("shopinfo"));
		user.setCustomerNotice(request.getParameter("customerNotice"));
		*/
		int shoptype;
		if(request.getParameter("shoptype").equals("全城商家")){
		 shoptype=1;
		}else
		{
			shoptype=0;
		}
		user.setShopType((byte) shoptype);
		try {
			if(usersDaoImpl.updateShopUser(user,Integer.valueOf(session.getAttribute("userid").toString())))
			{
				String result="修改成功!";
				request.setAttribute("result", result);
				return goToUpdate(request);
				
				
			}
			else
			{
				String result1="修改失败!";
				request.setAttribute("result", result1);
				return goToUpdate(request);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
		
		
	}
	@RequestMapping(value="showshopimg")
	public String showShopImg(HttpServletRequest request) throws NumberFormatException, Exception
	{
		HttpSession session=request.getSession();
		ArrayList<ShopImg>  shopimglist =new ArrayList<ShopImg>();
		shopimglist=usersDaoImpl.selectShopImg(Integer.parseInt(session.getAttribute("userid").toString()));
		session.setAttribute("shopimglist", shopimglist);
		return "shopusers/shop_img";
	}
	
	/*
	 *------------0629---------1947修改
	@RequestMapping(value="deleteshopimg")
	public String deleteShopImg(HttpServletRequest request) throws Exception
	{
		int photoid=Integer.parseInt(request.getParameter("photoid"));
		if(usersDaoImpl.deleteShopImg(photoid))
		{
			return showShopImg(request);
		}
		else
		{
			return "error";
		}
	}*/
	@RequestMapping(value="deleteshopimg")
	public String deleteShopImg(HttpServletRequest request) throws Exception
	{
		int photoid=Integer.parseInt(request.getParameter("photoid"));
		String imgUrl = usersDaoImpl.lxFindShopImgUrl(photoid);
		if(usersDaoImpl.deleteShopImg(photoid))
		{
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
			
			return showShopImg(request);
		}
		else
		{
			return "error";
		}
	}

	@RequestMapping("start")
	public String start(HttpServletRequest req, HttpServletResponse resp) throws IOException{
		//HttpSession session=req.getSession();
		//session.invalidate();  //清空session
		return "index";
	}
	@RequestMapping("end")
	public String end(HttpServletRequest req, HttpServletResponse resp) throws IOException{
		HttpSession session=req.getSession();
		session.invalidate();  //清空session
		return "index";
	}
	@RequestMapping(value="test_post",method=RequestMethod.POST)
	public String start_post(){
		System.out.println("post");
		return "start_post";
	}
}
