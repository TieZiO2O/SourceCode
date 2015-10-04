package com.nciae.community.controller;

import java.beans.Encoder;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
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

import net.sf.json.JSON;
import net.sf.json.JSONObject;
import net.sf.json.JSONString;

import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggerFactory;
import org.apache.log4j.spi.LoggerRepository;
import org.apache.tomcat.util.buf.Utf8Encoder;
import org.apache.tomcat.util.codec.EncoderException;
import org.apache.tomcat.util.codec.binary.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.core.io.JsonStringEncoder;
import com.nciae.community.dao.impl.ForumThreadDaoImpl;
import com.nciae.community.domain.ForumThreads;
import com.nciae.community.utils.JsonUtil;

/*
 * 帖子功能
 * */
@Controller
@RequestMapping("/forumThreads")
public class ForumThreadController {
	private ForumThreadDaoImpl forumThreadDaoImpl;
	private static final Logger logger=Logger.getLogger(ForumThreadController.class);
	/*
	 * 增加新帖
	 * 需要参数：tile,帖子题目；content,帖子内容；phone,电话号码；uid,当前添加新帖用的id
	 * */
	@RequestMapping(value="add")
	public void addNew(HttpServletRequest request,HttpServletResponse response,PrintWriter pw){
		//创建一个通用的同部分解析器
		String guid=UUID.randomUUID().toString();
		String content=request.getParameter("content");
		String title=request.getParameter("title");
		String uid=request.getParameter("uid");
		
		JSONObject json=new JSONObject();
		org.apache.tomcat.util.codec.binary.Base64 base64=new org.apache.tomcat.util.codec.binary.Base64();
		if(title==""){
			json.put("result", "帖子标题不能为空");
			pw.write(json.toString());
			return;
		}
		
		ForumThreads ft=new ForumThreads();
		
		if(content==""){
			ft.setContent("");
		}else{
			try {
				ft.setContent(new String(base64.encode(content.getBytes("utf-8"))));
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		ft.setUid(uid);
		ft.setGuid(guid);
		ft.setCreateDate(new Date());
		try {
			ft.setTitle(new String(base64.encode(title.getBytes("utf-8"))));
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		ft.setPhone(request.getParameter("phone"));
		
		try {
			boolean isAdd=this.forumThreadDaoImpl.addNewForumThread(ft);
			isAdd=this.forumThreadDaoImpl.addNewUser_ForumThread(ft);
			
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
	
	@RequestMapping(value="query",method=RequestMethod.POST)
	public String toQuery(){
		return "forumThreads/query";
	}
	
	//保存所有上传的图片并返回所有保存后的图片的url地址数组
	public ArrayList<String> saveAllImgs(HttpServletRequest request,String guid){
		CommonsMultipartResolver resolver=new CommonsMultipartResolver(request.getSession().getServletContext());
		ArrayList<String> imgs=null;
		imgs=new ArrayList<String>();
		
		//判断是否有多文件上传
		if(resolver.isMultipart(request)){
			MultipartHttpServletRequest mutiRequest=(MultipartHttpServletRequest)request;
			HttpSession session=request.getSession();
			int index=0;
			
			Iterator<MultipartFile> files=mutiRequest.getFiles("file").iterator();
			//Iterator<MultipartFile> mutiFiles=mutiRequest.getFiles(iter.next()).iterator();
			/*while(files.hasNext()){
				
				//System.out.println("=====图片路径："+iter.next()+"=======");
				index++;
				//获取当前文件
				MultipartFile file=files.next();
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
			}*/
			Iterator<String> iter=mutiRequest.getFileNames();
			
			while(iter.hasNext()){
			
				//System.out.println("=====图片路径："+iter.next()+"=======");
				index++;
				String fileNameOld=iter.next();
				//获取当前文件
				MultipartFile file=mutiRequest.getFile(fileNameOld);
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
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				imgs.add(path+"/"+fileNewName);
			}
		}
		return imgs;
	}

	@RequestMapping(value="getOneByGuid",method=RequestMethod.POST)
	public void getOne(String guid,HttpServletResponse response,PrintWriter pw){
		
		JSONObject json=new JSONObject();
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset:utf-8");
		
		if(guid==""){
			json.put("result", "fail");
			json.put("info", "传递的参数不能为空");
			pw.write(json.toString());
			return;
		}
		
		ForumThreads ft=this.forumThreadDaoImpl.queryByGuid(guid);
		json.put("result", "success");
		json.put("info", ft);
		pw.write(json.toString());
		return;
	}

	/**
	 * 按照uid和guid查询判断该帖子是否从属于该用户
	 * @param uid,用户的Id
	 * @param guid,帖子的guid
	 * @return result, info
	 * */
	@RequestMapping("isthreadbelong")
	public void getOne_By_UidAndGuid(HttpServletRequest request,PrintWriter pw){
		String uid=request.getParameter("uid");
		String guid=request.getParameter("guid");
		JSONObject json=new JSONObject();
		if(uid.equals("")){
			json.put("result", "fail");
			json.put("info", "用户Id不能为空");
			pw.write(json.toString());
			return;
		}
		
		if(guid.equals("")){
			json.put("result", "fail");
			json.put("info", "要查询的帖子的guid不能为空");
			pw.write(new String(json.toString()));
			return;
		}
		
		boolean ft=this.forumThreadDaoImpl.query_IsBelong(uid, guid);
		if(!ft){
			json.put("result", "notexist");
			json.put("info", "该用户不具备对帖子的操作权限");
			pw.write(json.toString());
			return;
		}else{
			json.put("result", "exist");
			json.put("info", "该用户具备对帖子的操作权限");
			pw.write(json.toString());
			return;
		}
	}
	
	@RequestMapping("deletebyguid")
	public void deleteOne_By_Guid(HttpServletRequest request,PrintWriter pw){
		String guid=request.getParameter("guid");
		JSONObject json=new JSONObject();
		
		if(guid.equals("")){
			json.put("result", "fail");
			json.put("info", "要删除的帖子的guid不能为空");
			pw.write(json.toString());
			return;
		}
		
		boolean ft=this.forumThreadDaoImpl.delete_By_Guid(guid);
		if(ft){
			json.put("result", "success");
			json.put("info", "该帖子已被删除");
			pw.write(json.toString());
			return;
		}else{
			json.put("result", "fail");
			json.put("info", "操作失败，请重新操作");
			pw.write(json.toString());
			return;
		}
	}
	
	@RequestMapping("getAllByUid")
	public void getAllByUid(String uid,HttpServletRequest request,HttpServletResponse response,PrintWriter pw){
		JSONObject json=new JSONObject();
		ArrayList<ForumThreads> fts=null;

		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset:utf-8");
		
		if(uid==""){
			json.put("result", "fail");
			json.put("info", "参数用户Id不能为空");
			pw.write(json.toString());
			return;
		}
		
		String pindex=request.getParameter("pindex");
		String psize=request.getParameter("psize");
		if(pindex==null || pindex.equals("")){
			fts=this.forumThreadDaoImpl.queryAllByUid(uid);
		}else{
			int pstart=(Integer.parseInt(pindex)-1)*Integer.parseInt(psize);
			fts=this.forumThreadDaoImpl.queryAllByUid_Paging(uid, pstart, Integer.parseInt(psize));
			int totalCount=this.forumThreadDaoImpl.queryAllByUid_Count(uid);
			int pbooks=totalCount/Integer.parseInt(psize);
			//获取所有行的余数
			int yushu=totalCount%Integer.parseInt(psize);
			if(yushu>0){
				pbooks=pbooks+1;
			}
			//设置页数
			json.put("pbooks", pbooks);
		}
		json.put("result", "success");
		json.put("info", fts);
		pw.write(json.toString());
		System.out.println(fts.get(0).getContent());
		return;
	}
	
	@RequestMapping("getAll")
	public void getAll(HttpServletRequest request,HttpServletResponse response,PrintWriter pw){
		JSONObject json=new JSONObject();
		ArrayList<ForumThreads> fts=null;

		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset:utf-8");
		
		String pindex=request.getParameter("pindex");
		String psize=request.getParameter("psize");
		if(pindex==null || pindex.equals("")){
			fts=this.forumThreadDaoImpl.queryAll();
		}else{
			int pstart=(Integer.parseInt(pindex)-1)*Integer.parseInt(psize);
			fts=this.forumThreadDaoImpl.queryAll_Paging(pstart, Integer.parseInt(psize));
		}
		
		json.put("result", "success");
		json.put("info", fts);
		
		logger.info("==========开始时间："+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()).toString()+"==========");
		logger.info(json.toString());
		logger.info("==========结束时间："+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()).toString()+"==========");
		
		pw.write(json.toString());
		return;
	}

	public ForumThreadDaoImpl getForumThreadDaoImpl() {
		return forumThreadDaoImpl;
	}

	public void setForumThreadDaoImpl(ForumThreadDaoImpl forumThreadDaoImpl) {
		this.forumThreadDaoImpl = forumThreadDaoImpl;
	};
}
