package com.nciae.community.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.nciae.community.dao.impl.DailyLivesDaoImpl;
import com.nciae.community.domain.DailyLives;
import com.nciae.community.domain.DailyLivesType;
import com.nciae.community.domain.DailyLives_SeperateTypes;

@Controller
@RequestMapping("dailyLives")
public class DailyLivesController {
	
	private DailyLivesDaoImpl dailyLivesDaoImpl;
	
	/**
	 * 增加新的服务
	 * @param title,服务的标题；style,服务所属的服务类型；customerNotice,顾客须知；
	 * */
	@RequestMapping("add")
	public String AddService(HttpServletRequest request,PrintWriter pw){
		
		JSONObject json=new JSONObject();
		boolean result=true;
		if(request.getParameter("title").equals("")){
			json.put("result", "fail");
			json.put("info", "服务标题不能为空");
			return "dailyLives/add";
		}
		
		boolean isMainList=Boolean.valueOf(request.getParameter("isMainList"));

		org.apache.tomcat.util.codec.binary.Base64 base64=new org.apache.tomcat.util.codec.binary.Base64();
		String guid=UUID.randomUUID().toString();
		DailyLives dailyLives=new DailyLives();
		dailyLives.setGuid(guid);
		
		if(!isMainList){
			dailyLives.setDailyLivesId(Integer.parseInt(request.getParameter("style")));
		}else{
			dailyLives.setDailyLivesId(0);
		}
		
		dailyLives.setCustomerNotice(request.getParameter("customerNotice"));
		dailyLives.setMainList(isMainList);
		//获取小区集合
		String communtiys=request.getParameter("hidCom");
		dailyLives.setCommunitys(communtiys);
		
		try {
			dailyLives.setTitle(new String(base64.encode(request.getParameter("title").getBytes("utf-8"))));
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			dailyLives.setContent(new String(base64.encode(request.getParameter("content").getBytes("utf-8"))));
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		HttpSession session=request.getSession();
		String userId=session.getAttribute("userid").toString();
		dailyLives.setPhone(request.getParameter("phone"));
		dailyLives.setAddress(request.getParameter("address"));
		dailyLives.setUid(userId);
		
		
		try {
			result=this.dailyLivesDaoImpl.addNewDailyLives(dailyLives);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(!result){
			json.put("result", "fail");
			json.put("info", "写入失败，请重新操作");
			pw.write(json.toString());
			return "dailyLives/add";
		}
		
		ArrayList<String> imgs=this.AddImages(request, guid);
		result=this.dailyLivesDaoImpl.addDailyLivesImgs(imgs, guid);
		if(!result){
			json.put("result", "fail");
			json.put("info", "图片保存失败，请重新操作");
			pw.write(json.toString());
			return "dailyLives/add";
		}
		return this.GetAllDailtypes(request,pw);
		
	}
	
	public ArrayList<String> AddImages(HttpServletRequest request,String guid){
		ArrayList<String> imgs=null;
		CommonsMultipartResolver resolver=new CommonsMultipartResolver(request.getSession().getServletContext());
		
		//判断是否有多文件上传
		if(resolver.isMultipart(request)){
			MultipartHttpServletRequest mutiRequest=(MultipartHttpServletRequest)request;
			HttpSession session=request.getSession();
			Iterator<MultipartFile> mutiFiles=mutiRequest.getFiles("img").iterator();
			
			int index=0;
			imgs=new ArrayList<String>();
			
			while(mutiFiles.hasNext()){
				index++;
				MultipartFile file=mutiFiles.next();
				//获取远程文件的地址
				String fileOldPath=file.getOriginalFilename();
				
				String path="/img/dailylives";
				path=path+"/"+guid;
				
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
	
	/**
	 * 获取所有的服务类型
	 * @return 所有的服务类型
	 * */
	@RequestMapping("getAllDailyType")
	public void GetAllDailyTypes(HttpServletRequest request,PrintWriter pw){
		
		DailyLives_SeperateTypes dailys=this.dailyLivesDaoImpl.SelectAllDailyLivesTypsBySerperate();
		
		if(dailys!=null){
			request.setAttribute("dailys", dailys);
			JSONObject json=new JSONObject();
			json.put("result", "success");
			json.put("info",dailys);
			pw.write(json.toString());
		}else{
			JSONObject json=new JSONObject();
			json.put("result", "fail");
			json.put("info","查询出错，请联系系统管理员");
			pw.write(json.toString());
		}
		return;
		//return "dailyLives/add";
	}
	
	@RequestMapping("getAllDailtypes")
	private String GetAllDailtypes(HttpServletRequest request,PrintWriter pw){
		ArrayList<DailyLivesType> dailys=this.dailyLivesDaoImpl.SelectAllDailyLivesTypes();
		
		if(dailys!=null){
			request.setAttribute("dailys", dailys);
			JSONObject json=new JSONObject();
			json.put("result", "success");
			json.put("info",dailys);
			pw.write(json.toString());
		}else{
			JSONObject json=new JSONObject();
			json.put("result", "fail");
			json.put("info","查询出错，请联系系统管理员");
			pw.write(json.toString());
		}
		
		return "dailyLives/add";
	}
	
	/**
	 * @param sid,要删除的服务的Id
	 * */
	@RequestMapping("deleteonedailytype")
	public void DeleteOneDailyType(HttpServletRequest request,PrintWriter pw){
		String sid=request.getParameter("sid");
		JSONObject json=new JSONObject();
		
		if(sid.equals("")){
			json.put("result", "fail");
			json.put("info", "操作失败，需要传递对应的服务序列号");
			pw.write(json.toString());
			return;
		}
		
		boolean data=this.dailyLivesDaoImpl.delete_DailyLivesType_ById(Integer.parseInt(sid));
		if(data){
			json.put("result", "success");
			json.put("info", "操作成功");
		}else{
			json.put("result", "fail");
			json.put("info", "操作失败，请重新操作");
		}
		pw.write(json.toString());
		return;
	}
	
	@RequestMapping("getAllServiceTypeBySid")
	public void GetAllServiceTypeByServiceId(HttpServletRequest request,PrintWriter pw){
		String id=request.getParameter("sid");
		JSONObject json=new JSONObject();
		if(id==""){
			json.put("result", "fail");
			json.put("info","需要服务Id来查询列表，但该服务Id并没有上传");
			pw.write(json.toString());
			return;
		}
		
		ArrayList<DailyLives> dls=this.dailyLivesDaoImpl.queryAllByServiceType(Integer.parseInt(id));
		
		json.put("result", "success");
		json.put("info",dls);
		pw.write(json.toString());
		
		return;
	}

	@RequestMapping("getOneStypeByGid")
	public void GetOneServiceTypeByGid(HttpServletRequest request,PrintWriter pw){
		String guid=request.getParameter("guid");
		JSONObject json=new JSONObject();
		if (guid=="") {
			json.put("result", "fail");
			json.put("info", "查询服务的guid不能为空");
			pw.write(json.toString());
			return;
		}
		
		DailyLives dl=this.dailyLivesDaoImpl.queryByGuid(guid);
		
		if(dl==null){
			json.put("result", "fail");
			json.put("info", "查询失败");
			pw.write(json.toString());
			return;
		}
		
		json.put("result", "success");
		json.put("info", dl);
		pw.write(json.toString());
		
		return;
	}
	
	@RequestMapping("addservicetype")
	public String AddServiceType(HttpServletRequest request,PrintWriter pw){
		DailyLivesType dlv=new DailyLivesType();
		JSONObject json=new JSONObject();
		String style=request.getParameter("style");
		if(style.equals("")){
			json.put("result", "fail");
			json.put("info", "要添加的服务类型不能为空，请重新操作");
			return "dailyLives/addservicetype";
		}
		
		CommonsMultipartResolver resolver=new CommonsMultipartResolver(request.getSession().getServletContext());
		
		if(resolver.isMultipart(request)){
			MultipartHttpServletRequest mutireqeust=(MultipartHttpServletRequest)request;
			Iterator<String> fileNames=mutireqeust.getFileNames();
			while(fileNames.hasNext()){
				String fileOldName=fileNames.next();
				MultipartFile file=mutireqeust.getFile(fileOldName);
				
				String fileOldPath=file.getOriginalFilename();
				//设置文件的保存地址
				String path="/img/serviceLogo";
				String fileRealPath=mutireqeust.getSession().getServletContext().getRealPath(path);
				File saveFile=new File(fileRealPath);
				
				if(!saveFile.exists()){
					saveFile.mkdirs();
				}
				//获取文件后缀名
				String extension=fileOldPath.substring(fileOldPath.indexOf(".")+1,fileOldPath.length()).toLowerCase();
				//获取文件名
				String fileName=fileOldPath.substring(0,fileOldPath.indexOf("."));
				SimpleDateFormat date=new SimpleDateFormat("yyyyMMddHHmmss");
				
				String fileNewName=date.format(new Date())+"."+extension;
				fileRealPath=fileRealPath+File.separator+fileNewName;
				
				File newFile=new File(fileRealPath);
				try {
					file.transferTo(newFile);
				} catch (IllegalStateException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				dlv.setLogoPath(path+"/"+fileNewName);
			}
		}else{
			dlv.setLogoPath("");
		}
		
		String serviceType=request.getParameter("servicetype");
		dlv.setStyle(style);
		dlv.setServiceType(serviceType);
		
		boolean result=this.dailyLivesDaoImpl.addNewDailyLivesType(dlv);
		if(result){
			json.put("result", "success");
			json.put("info", "添加成功");
		}else{
			json.put("result", "fail");
			json.put("info", "添加失败");
		}
		pw.write(json.toString());

		ArrayList<DailyLivesType> dailyLives=this.dailyLivesDaoImpl.SelectAllDailyLivesTypes();
		request.setAttribute("dailyLives", dailyLives);

		return "dailyLives/query";
	}
	
	@RequestMapping("query")
	public String query(HttpServletRequest request,PrintWriter pw){

		ArrayList<DailyLivesType> dailyLives=this.dailyLivesDaoImpl.SelectAllDailyLivesTypes();
		request.setAttribute("dailyLives", dailyLives);
		return "dailyLives/query";
	}
	
	@RequestMapping("addservicetypeget")
	public String jumpToadd(){
		return "dailyLives/addservicetype";
	}
	
	@RequestMapping("addservicestyle")
	public String jumpToaddstyle(HttpServletRequest request){
		ArrayList<DailyLivesType> dailys=this.dailyLivesDaoImpl.SelectAllDailyLivesTypes();
		
		if(dailys!=null){
			request.setAttribute("dailys", dailys);
		}
		return "dailyLives/add";
	}
	
	@RequestMapping("servicemanage")
	public String serviceManage(HttpServletRequest request,PrintWriter pw){
		String title=request.getParameter("title")==null ?"":request.getParameter("title");
		String phone=request.getParameter("phone")==null?"":request.getParameter("phone");
		
		ArrayList<DailyLives> lives= this.dailyLivesDaoImpl.query_By_titleorphone(title, phone);
		ArrayList<DailyLivesType> livesType=this.dailyLivesDaoImpl.SelectAllDailyLivesTypes();
		request.setAttribute("dailyLives", lives);
		request.setAttribute("dailyLivesType", livesType);
		return "dailyLives/servicemanage";
	}
	
	@RequestMapping("service")
	public String serviceJump(){
		return "dailyLives/service";
	}
	
	public DailyLivesDaoImpl getDailyLivesDaoImpl() {
		return dailyLivesDaoImpl;
	}

	public void setDailyLivesDaoImpl(DailyLivesDaoImpl dailyLivesDaoImpl) {
		this.dailyLivesDaoImpl = dailyLivesDaoImpl;
	}
	
	@RequestMapping("delete_dailylives_byguid")
	public void DeleteDailyLivesByGuid(HttpServletRequest request,PrintWriter pw){
		String guid=request.getParameter("guid");
		JSONObject json=new JSONObject();
		if(guid.equals("")){
			json.put("result","fail");
			json.put("info","guid，不能为空");
			pw.write(json.toString());
			return;
		}
		
		boolean result=this.dailyLivesDaoImpl.delete_DailyLives_ById(guid);
		if(result){
			json.put("result","success");
			json.put("info","删除成功");
		}else{
			json.put("result","fail");
			json.put("info","删除失败");
		}
		pw.write(json.toString());
		return;
	}
}
