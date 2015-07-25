package com.nciae.community.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.nciae.community.dao.impl.PropertyDaoImpl;
import com.nciae.community.domain.Bulletin;
import com.nciae.community.domain.Users;
import com.nciae.community.utils.MD5;

@Controller
@RequestMapping("/property")
public class PropertyController {
	private PropertyDaoImpl propertyDaoImpl;

	public PropertyDaoImpl getPropertyDaoImpl() {
		return propertyDaoImpl;
	}

	public void setPropertyDaoImpl(PropertyDaoImpl propertyDaoImpl) {
		this.propertyDaoImpl = propertyDaoImpl;
	}
	@RequestMapping("property_top")
	public String goToPropertyTop(){
		return "property/top";
	}
	
	@RequestMapping("property_left")
	public String goToPropertyLeft(){
		return "property/left";
	}
	
	@RequestMapping("property_main")
	public String goToPropertyMain(){
		
		return "property/main";
	}
	@RequestMapping("gochangephoto")
	public String goTocChangePhoto(){
		
		return "property/upload5";
	}
	@RequestMapping("gochangepwd")
	public String goChangePwd(){
		return "property/changepwd";
	}
	@RequestMapping("showbulletin")
	public String goToAnnouncement(HttpServletRequest request){
		HttpSession session =request.getSession();
		String propertyname=session.getAttribute("username").toString();
		try {
			session.setAttribute("bulletinlist", propertyDaoImpl.selectBulletin(propertyDaoImpl.selectCommunityId(propertyname)));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "property/showbulletin";
	}
	@RequestMapping("showcomments")
	public String goToShowComments(HttpServletRequest request){
		HttpSession session =request.getSession();
		int propertyid=Integer.parseInt(session.getAttribute("userid").toString());
		try {
			
			session.setAttribute("commentslist", propertyDaoImpl.selectComments(propertyid));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "property/ShowComments";
	}
	@RequestMapping("gotoaddbulletin")
	public String goToAddannouncement(){
		return "property/addbulletin";
	}
	@RequestMapping("addbulletinnow")
	public String addAnnouncement(HttpServletRequest request){
		String title=request.getParameter("title");
		String bulletininfo=request.getParameter("bulletininfo");
		HttpSession session =request.getSession();
		String propertyname=session.getAttribute("username").toString();
		Bulletin bulletin = new Bulletin();
		String result=null;
		Date date =new Date();
		String committime=date.toLocaleString();
		bulletin.setTitle(title);
		bulletin.setBulletinInfo(bulletininfo);
		bulletin.setCommitTime(committime);
		try {
			///////////////////////////0629判断空值
			if(title==""||bulletininfo=="")
			{
				result="公告标题或内容为空！";
			}else{
				bulletin.setCommunityId(propertyDaoImpl.selectCommunityId(propertyname));
				if(propertyDaoImpl.addBulletin(bulletin))
				{
					result="添加成功！";
				}
				else
				{
					result="添加失败!";
				}
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		session.setAttribute("result1",result );
		return "property/addbulletin";
	}

	
	@RequestMapping("backtoshowbulletinnow")
	public void backToShowAnnouncement(HttpServletRequest request){
		goToAnnouncement(request);
		//return "property/announcement";
	}
	@RequestMapping("deletebulletinnow")
	public String deleteAnnouncement(HttpServletRequest request){
		int bulletinid=Integer.parseInt(request.getParameter("bulletinid"));
		HttpSession session =request.getSession();
		String result=null;
		try {
			if(propertyDaoImpl.deleteBulletin(bulletinid))
			{
				result="删除成功！";
			}else
			{
				result="删除失败！";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		session.setAttribute("result", result);
		goToAnnouncement(request);
		return "property/showbulletin";
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
			String role=propertyDaoImpl.property_login(username, password2);
			if(role.equals(Users.ROLE_ADMIN)||role.equals(Users.ROLE_SHOP)||role.equals(Users.ROLE_PROPERTY)){
				if(propertyDaoImpl.property_changePwd(Integer.parseInt(session.getAttribute("userid").toString()), userpwd))
				{
					String result="修改成功!";
					
					//System.out.print("1111111111111111111");
					request.setAttribute("result1", result);
					//out.println("<script>window.alert('修改成功！')</script>");
					return "shopusers/changepwd";
					
					
				}
				else
				{
					String result1="修改失败!";
					System.out.print((session.getAttribute("userid").toString())+"3333333333333333333");
					//out.println("<script>window.alert('修改失败！')</script>");
					request.setAttribute("result1", result1);
					return "shopusers/changepwd";
				}
				
			}else{
				String result2="旧密码不正确!";
				
				//System.out.print("1111111111111111111");
				request.setAttribute("result1", result2);
				return "property/changepwd";
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}
	
	@RequestMapping("update")
	public String goToUpdate(HttpServletRequest request){
		HttpSession session =request.getSession();
		try {
			session.setAttribute("property", propertyDaoImpl.selectPropertyInfo(Integer.parseInt(session.getAttribute("userid").toString())));
			String filePath=propertyDaoImpl.selectPropertyInfo(Integer.parseInt(session.getAttribute("userid").toString())).getShopLogo();
			session.setAttribute("photopath", filePath);
			System.out.println("我是propertycontroller");
			  //System.out.print(((Users) session.getAttribute("shopuser")).getSex());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "property/update";
	}
	
	@RequestMapping(value="updatenow",method=RequestMethod.POST)
	public String updateNow(HttpServletRequest request, HttpServletResponse resp){
		HttpSession session =request.getSession();
		//PrintWriter out = null;
		Users user=new Users();
		user.setAddress(request.getParameter("address"));
		//user.setId(((Users) session.getAttribute("shopuser")).getId());
		user.setRealName(request.getParameter("realname"));
		user.setLinkman(request.getParameter("linkman"));
		user.setPhone(request.getParameter("phone"));
		user.setAboutUs(request.getParameter("aboutus"));
		//System.out.print(((Users) session.getAttribute("shopuser")).getId());
		try {
			if(propertyDaoImpl.updatePropertyInfo(user,Integer.valueOf(session.getAttribute("userid").toString())))
			{
				String result="修改成功!";
				
				//System.out.print("1111111111111111111");
				request.setAttribute("result", result);
				//out.println("<script>window.alert('修改成功！')</script>");
				return goToUpdate(request);
				
				
			}
			else
			{
				String result1="修改失败!";
				//System.out.print((session.getAttribute("userid").toString())+"3333333333333333333");
				//out.println("<script>window.alert('修改失败！')</script>");
				request.setAttribute("result", result1);
				return goToUpdate(request);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
		
		
	}
	
	@RequestMapping("start")
	public String start(HttpServletRequest req, HttpServletResponse resp) throws IOException{
		HttpSession session=req.getSession();
		session.invalidate();  //清空session
		return "index";
	}
	
	/////////////////////////////////0627
	@RequestMapping("deletecommentnow")
	public String deleteComments(HttpServletRequest request){
		int commentsid=Integer.parseInt(request.getParameter("commentsid"));
		HttpSession session =request.getSession();
		String result=null;
		try {
			if(propertyDaoImpl.deleteComments(commentsid))
			{
				result="删除成功！";
			}else
			{
				result="删除失败！";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		session.setAttribute("result", result);
		//goToAnnouncement(request);
		//return "property/showbulletin";
		//return "property/ShowComments";
		return goToShowComments(request);
	}

	
}
