package com.nciae.community.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.nciae.community.dao.impl.ForumThreadDaoImpl;
import com.nciae.community.domain.ForumThreads;

@Controller
@RequestMapping("/forumThreads")
public class ForumThreadController {
	private ForumThreadDaoImpl forumThreadDaoImpl;
	
	@RequestMapping(value="add")
	public void addNew(HttpServletRequest request,HttpServletResponse response,PrintWriter pw){
		//创建一个通用的同部分解析器
		String guid=UUID.randomUUID().toString();
		
		ForumThreads ft=new ForumThreads();
		ft.setGuid(guid);
		ft.setCreateDate(new Date());
		ft.setContent(org.apache.commons.codec.binary.Base64.encodeBase64String(request.getParameter("content").getBytes()));
		ft.setTitle(org.apache.commons.codec.binary.Base64.encodeBase64String(request.getParameter("title").getBytes()));
		ft.setPhone(request.getParameter("phone"));
		
		try {
			boolean isAdd=this.forumThreadDaoImpl.addNewForumThread(ft);
			JSONObject json=new JSONObject();
			if(!isAdd){
				json.put("result", "fail");
				pw.write(json.toString());
				return;
			}
			
			ArrayList<String> imgs=this.saveAllImgs(request,guid);
			if(imgs==null){
				json.put("result", "success");
				pw.write(json.toString());
				return;
			}
			//System.out.println("======="+imgs.toString()+"========");
			boolean isSucc=this.forumThreadDaoImpl.addForumImgs(imgs, guid);
			if(isSucc){
				json.put("result", "success");
				pw.write(json.toString());
			}else{
				json.put("result", "fail");
				pw.write(json.toString());
			}
			return;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value="list",method=RequestMethod.POST)
	public String listTo(){
		return "forumThreads/list";
	}
	//保存所有上传的图片并返回所有保存后的图片的url地址数组
	public ArrayList<String> saveAllImgs(HttpServletRequest request,String guid){
		CommonsMultipartResolver resolver=new CommonsMultipartResolver(request.getSession().getServletContext());
		ArrayList<String> imgs=null;
		//判断是否有多文件上传
		if(resolver.isMultipart(request)){
			MultipartHttpServletRequest mutiRequest=(MultipartHttpServletRequest)request;
			HttpSession session=request.getSession();
			Iterator<String> iter=mutiRequest.getFileNames();
			
			imgs=new ArrayList<String>();
			int index=0;
			while(iter.hasNext()){
				
				//System.out.println("=====图片路径："+iter.next()+"=======");
				index++;
				//获取当前文件
				MultipartFile file=mutiRequest.getFile(iter.next());
				//获取远程文件的地址
				String fileOldPath=file.getOriginalFilename();
				
				//获取用户Id
				String id=request.getParameter("uid");
				//设置文件保存地址
				String path="/img/forumThreadsImg";
				path=path+"/"+id+"/"+guid;
				
				//获取文件保存地址
				String fileRealPath=mutiRequest.getSession().getServletContext().getRealPath(path);
				File saveFile=new File(fileRealPath);
				if(!saveFile.exists()){
					saveFile.mkdirs();
				}
				
				//获取文件后缀名
				String extension=fileOldPath.substring(fileOldPath.indexOf(".")+1,fileOldPath.length()).toLowerCase();
				//获取文件名
				String fileName=fileOldPath.substring(0,fileOldPath.indexOf("."));
				
				SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmss");
				//新文件全名
				String fileNewName=sdf.format(new Date())+"_"+index+"."+extension;
				//新文件url
				String fileUrl=fileRealPath+File.separator+fileNewName;
				
				//保存文件
				File localFile=new File(fileUrl);
				try {
					file.transferTo(localFile);
				} catch (IllegalStateException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				imgs.add(path+"/"+fileNewName);
			}
		}
		return imgs;
	}

	public ForumThreadDaoImpl getForumThreadDaoImpl() {
		return forumThreadDaoImpl;
	}

	public void setForumThreadDaoImpl(ForumThreadDaoImpl forumThreadDaoImpl) {
		this.forumThreadDaoImpl = forumThreadDaoImpl;
	};
}
