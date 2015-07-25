package com.nciae.community.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.nciae.community.dao.impl.UsersDaoImpl;
import com.nciae.community.domain.ShopImg;

@Controller
@RequestMapping("/user")
public class UploadController {

	private UsersDaoImpl usersDaoImpl;
	
	public UsersDaoImpl getUsersDaoImpl() {
		return usersDaoImpl;
	}
	public void setUsersDaoImpl(UsersDaoImpl usersDaoImpl) {
		this.usersDaoImpl = usersDaoImpl;
	}

	@RequestMapping(value="upload1",method=RequestMethod.POST)
	public String upload2(HttpServletRequest request,HttpServletResponse response) throws NumberFormatException, Exception{
		//创建一个通用的多部分解析器.   
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
		 //判断 request 是否有文件上传,即多部分请求... 
		String result = null;
		if(multipartResolver.isMultipart(request))
		{
			 //判断 request 是否有文件上传,即多部分请求...  
			MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest)(request);
			HttpSession session=request.getSession();
			Iterator<String> iter = multiRequest.getFileNames();
			while(iter.hasNext())
			{
				MultipartFile file = multiRequest.getFile(iter.next());
				String fileName = file.getOriginalFilename();
				String path ="/shopLogo";
				
				///////////////0629-1825
				String id = session.getAttribute("userid").toString();
				int userid = Integer.parseInt(id);
				
				path = path + "/" + userid;
				/////////////////////////////
				
				String logoRealPathDir = request.getSession().getServletContext().getRealPath(path);
				File logoSaveFile = new File(logoRealPathDir);
				if (!logoSaveFile.exists())
		            logoSaveFile.mkdirs();
				
				//////////////////////0629
				String hzm1=fileName.substring(fileName.indexOf(".")+1,fileName.length()).toLowerCase(); //后缀名
				String wjm1=fileName.substring(0,fileName.indexOf(".")); //文件名
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
				//fileName = wjm1 + "_" + sdf.format(new Date())+"."+hzm1;//新文件的全名
				fileName = "LOGO_" + wjm1 + "_" + sdf.format(new Date())+"."+hzm1;//新文件的全名
				String url = logoRealPathDir + File.separator + fileName;//新文件URL
				////////////////////
				
				
				//.........删除同名的旧文件，添加新文件
				/////////////////////////////////////////////0629
				  File file1=new File(logoRealPathDir);
				  File[] tempList = file1.listFiles();
				  System.out.println("该目录下对象个数："+tempList.length);
				  if(tempList.length>0){
					  for (int i = 0; i < tempList.length; i++) {
						   if (tempList[i].isFile()) {
							    String fileName1=tempList[i].getName();//文件名称
//							    String wjm0=fileName1.substring(0,fileName1.indexOf("_"));
//							    if(wjm0.equals(wjm1)){
//							    	String url0 = logoRealPathDir + File.separator + fileName1;//旧文件的URL
//							    	new File(url0).delete();
//							    }
							    String wjm0=fileName1.substring(0,fileName1.indexOf("_"));
							    if(wjm0.equals("LOGO")){
							    	String url0 = logoRealPathDir + File.separator + fileName1;//旧文件的URL
							    	new File(url0).delete();
							    }
						   }
					  }
					  File localFile = new File(url);
					  file.transferTo(localFile);
				  }else{
						File localFile = new File(url);
						file.transferTo(localFile);
				  }
				  
				//........
				
				
				
				
				
				
				///////////////////0629
//				File localFile = new File(url);
//				file.transferTo(localFile);
				
				String photopath = path+"/"+fileName;
				////////
		        /*
		         * 0629屏蔽
		        String filename = logoRealPathDir +"/"+ fileName;
		        String filename1 = path + "/" + fileName;
				File localFile = new File(filename);
				file.transferTo(localFile);
				String photopath=filename1;
				
				*/
				//String username=session.getAttribute("username").toString();
				//int userid=usersDaoImpl.selectShopUserId(username);
				////////////////0629-1825
				//String id = session.getAttribute("userid").toString();
				//int userid = Integer.parseInt(id);
				////////
				//int userid = Integer.parseInt(session.getAttribute("userid").toString());
				if(usersDaoImpl.uploadPhoto(userid, photopath))
				{
					result="图片上传成功";
					request.setAttribute("result", result);
					return "shopusers/upload5";
				}
				else
				{	
					result="图片上传出错";
					request.setAttribute("result", result);
					return "shopusers/upload5";
				}
			}

		}
		else
		{
			result="图片上传出错";
			request.setAttribute("result", result);
			return "shopusers/upload5";	
		}
		return null;
	}
	@RequestMapping(value="addshopimg",method=RequestMethod.POST)
	public String addShopImg(HttpServletRequest request,HttpServletResponse response) throws NumberFormatException, Exception
	{
		//创建一个通用的多部分解析器.   
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
		 //判断 request 是否有文件上传,即多部分请求... 
		String result = null;
		if(multipartResolver.isMultipart(request))
		{
			 //判断 request 是否有文件上传,即多部分请求...  
			MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest)(request);
			HttpSession session=request.getSession();
			Iterator<String> iter = multiRequest.getFileNames();
			while(iter.hasNext())
			{
				MultipartFile file = multiRequest.getFile(iter.next());
				if(file==null)
				{
					result="图片为空";
					request.setAttribute("result", result);
					return "shopusers/upload5";
				}
				else{
				String fileName = file.getOriginalFilename();
				String path ="/shopLogo";
				
				///////////////0629-1825
				String id = session.getAttribute("userid").toString();
				int userid = Integer.parseInt(id);
				
				path = path + "/" + userid;
				/////////////////////////////
				
				String logoRealPathDir = request.getSession().getServletContext().getRealPath(path);
				File logoSaveFile = new File(logoRealPathDir);
				if (!logoSaveFile.exists())
		            logoSaveFile.mkdirs();
				
				
				
			//////////////////////0629
			String hzm1=fileName.substring(fileName.indexOf(".")+1,fileName.length()).toLowerCase(); //后缀名
			String wjm1=fileName.substring(0,fileName.indexOf(".")); //文件名
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
			fileName = wjm1 + "_" + sdf.format(new Date())+"."+hzm1;//新文件的全名
			String url = logoRealPathDir + File.separator + fileName;//新文件URL
			////////////////////
		        //String filename = logoRealPathDir + "/" + fileName;
		        //String filename1 = path + "/" + fileName;
				//File localFile = new File(filename);
				//file.transferTo(localFile);
				File localFile = new File(url);////
				file.transferTo(localFile);
				String photopath=path + "/" + fileName;
				ShopImg shopimg=new ShopImg();
				shopimg.setImgUrl(photopath);
				shopimg.setUserId(Integer.parseInt(session.getAttribute("userid").toString()));
					if(usersDaoImpl.addShopImg(shopimg))
					{
						result="图片上传成功";
						request.setAttribute("result", result);
						ArrayList<ShopImg>  shopimglist =new ArrayList<ShopImg>();
						shopimglist=usersDaoImpl.selectShopImg(Integer.parseInt(session.getAttribute("userid").toString()));
						session.setAttribute("shopimglist", shopimglist);
						return "shopusers/shop_img";
					}
					else
					{	
						result="图片上传出错";
						request.setAttribute("result", result);
						return "shopusers/upload5";
					}
				}
			}

		}
		else
		{
			result="图片上传出错";
			request.setAttribute("result", result);
			return "shopusers/upload5";	
		}
		return null;
	}
	@RequestMapping("upload_page")
    public String handleUploadShow() {  
        return "adminusers/upload5";  
    }

}
