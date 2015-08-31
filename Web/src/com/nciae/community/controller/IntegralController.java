package com.nciae.community.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.beanutils.converters.StringConverter;
import org.apache.commons.codec.binary.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.nciae.community.common.IntegralCom;
import com.nciae.community.dao.impl.IntegralDaoImpl;
import com.nciae.community.domain.DailyLives;
import com.nciae.community.domain.DailyLivesType;
import com.nciae.community.domain.Integral;
import com.nciae.community.domain.IntegralCommodity;
import com.nciae.community.domain.IntegralType;

/*
 * 积分功能
 * */
@Controller
@RequestMapping("integral")
public class IntegralController {
	
	private IntegralDaoImpl integralDaoImpl;
	public IntegralDaoImpl getIntegralDaoImpl() {
		return integralDaoImpl;
	}
	public void setIntegralDaoImpl(IntegralDaoImpl integralDaoImpl) {
		this.integralDaoImpl = integralDaoImpl;
	}
	
	private IntegralCom integralCom;
	
	/*
	 *更新积分
	 *Integral中的addOrFract=true，代表添加积分，false代表减去积分 
	 * */
	@RequestMapping("modify")
	public void CalcIntegral(HttpServletRequest request,PrintWriter pw){
		String userName=request.getParameter("name");
		ArrayList<Integral> inte=this.integralDaoImpl.QueryIntegral_By_UserName(userName);
		boolean result=true;
		JSONObject json=new JSONObject();
		
		Integral integral=new Integral();
		integral.setUserName(userName);
		String fract=request.getParameter("fraction");
		integral.setFraction(Integer.parseInt(fract));
		
		String isAdd=request.getParameter("addOrFract");
		boolean IsAdd=true;
		if(isAdd.equals("true")){
			IsAdd=true;
		}else{
			IsAdd=false;
		}
		integral.setAddOrFract(IsAdd);
		
		if(inte.equals(null)){
			result=this.integralDaoImpl.InsertIntegral(integral);
		}else{
			if(integral.isAddOrFract()){
				result=this.integralDaoImpl.AddIntegral(integral);
			}else{
				result=this.integralDaoImpl.SubtractIntegral(integral);
			}
		}
		
		if(!result){
			json.put("result", "failed");
			json.put("info", "操作失败，请重新操作");
		}else{
			json.put("result", "success");
			json.put("info", "操作成功");
		}
		
		pw.write(json.toString());
		
		return;
	}
	
	@RequestMapping("query")
	public String queryByUserName(HttpServletRequest request,PrintWriter pw){
		
		JSONObject json=new JSONObject();
		String uName=request.getParameter("name");
		if(uName.equals("")){
			json.put("result", "failed");
			json.put("info", "用户名不能为空");
			pw.write(json.toString());
			return "integral/query";
			//return "integral/getOne";
		}
			
		ArrayList<Integral> integrals=this.integralDaoImpl.QueryIntegral_By_UserName(uName);
		
		request.setAttribute("integrals", integrals);
		json.put("result", "success");
		json.put("info", integrals);
		pw.write(json.toString());
		
		return "integral/query";
	}
	
	@RequestMapping("integral_page")
	public String integral_Page(){
		return "integral/integral_manage";
	}
	/*public String CalcIntegral(Integral integral,PrintWriter pw){
		Integral inte=this.integralDaoImpl.QueryIntegral(Integer.toString(integral.getUserId()));
		boolean result=true;
		JSONObject json=new JSONObject();
		
		if(inte==null){
			result=this.integralDaoImpl.InsertIntegral(integral);
		}else{
			if(integral.isAddOrFract()){
				result=this.integralDaoImpl.AddIntegral(integral);
			}else{
				result=this.integralDaoImpl.SubtractIntegral(integral);
			}
		}
		
		if(!result){
			json.put("result", "failed");
			json.put("info", "操作失败，请重新操作");
		}else{
			json.put("result", "success");
			json.put("info", "操作成功");
		}
		
		pw.write(json.toString());
		
		return "integral/modify";
	}
	*/
	@RequestMapping("getByUid")
	public void GetIntegralByUid(HttpServletRequest request,PrintWriter pw){
		String uid=request.getParameter("uid");
		JSONObject json=new JSONObject();
		if(uid==""){
			json.put("result", "failed");
			json.put("info", "用户的Id不能为空");
			pw.write(json.toString());
			//return "integral/getOne";
			return;
		}
			
		Integral integral=this.integralDaoImpl.QueryIntegral(uid);
		
		request.setAttribute("integral", integral);
		json.put("result", "success");
		json.put("info", integral);
		pw.write(json.toString());
		return;
		//return "integral/getOne";
	}

	@RequestMapping("modifyoperate")
	public void ModifyOperate(HttpServletRequest request,PrintWriter pw){
		
		String otype=request.getParameter("operatetype");
		JSONObject json=new JSONObject();
		if(otype.equals("")){
			json.put("result", "fail");
			json.put("info", "增加积分出错，请选择操作的类型");
			pw.write(json.toString());
			return;
		}
		int fraction=Integer.parseInt(request.getParameter("fraction"));
		boolean isAdd=Boolean.parseBoolean(request.getParameter("addOrdecrease"));
		IntegralType integralType=new IntegralType();
		integralType.setOperateType(otype);
		integralType.setAddOrdecrease(isAdd);
		integralType.setFraction(fraction);
		
		boolean result=this.integralDaoImpl.Update_Integral_Type(integralType);
		if(!result){
			json.put("result", "fail");
			json.put("info", "更新失败");
		}else{
			json.put("result", "success");
			json.put("info", "更新成功");
		}
		pw.write(json.toString());
		return;
	}
	
	@RequestMapping("updateType")
	public String UpdateType(HttpServletRequest request,PrintWriter pw){
		IntegralType type=this.integralDaoImpl.QueryOneByType("phonecall");
		request.setAttribute("type", type);
		return "integral/updateType";
	}
	
	@RequestMapping("modify_fraction")
	public void ModifyFraction(HttpServletRequest request,PrintWriter pw){
		
		this.integralCom.OperateUserIntegral(request, pw);		
		return;
	}
	
	@RequestMapping("jumpToFraciton")
	public String JumpToFraciton(){
		return "integral/modify_fraction";
	}
	@RequestMapping("jumpToquery")
	public String JumpToQuery(){
		return "integral/query";
	}
	
	@RequestMapping(value="getallcommodity",method=RequestMethod.POST)
	public void GetAllCommodity(HttpServletRequest request,HttpServletResponse response,PrintWriter pw){
		JSONObject json=new JSONObject();
		ArrayList<IntegralCommodity> dls=this.integralDaoImpl.queryAllByCommodity();
		
		json.put("result", "success");
		json.put("info",dls);
		pw.write(json.toString());
		
		//return "integral/getallcommodity";
		return;
	}

	@RequestMapping("getcomdetail")
	public void GetOneServiceTypeByGid(HttpServletRequest request,PrintWriter pw){
		String guid=request.getParameter("guid");
		JSONObject json=new JSONObject();
		if (guid=="") {
			json.put("result", "fail");
			json.put("info", "查询服务的guid不能为空");
			pw.write(json.toString());
			return;
			//return "integral/getcomdetail";
		}
		
		IntegralCommodity dl=this.integralDaoImpl.queryByGuid(guid);
		
		if(dl==null){
			json.put("result", "fail");
			json.put("info", "查询失败");
			pw.write(json.toString());
			return;
			//return "integral/getcomdetail";
		}
		
		json.put("result", "success");
		json.put("info", dl);
		pw.write(json.toString());
		
		return;
		//return "integral/getcomdetail";
	}
	
	/*@RequestMapping("addservicetype")
	public String AddServiceType(HttpServletRequest request,PrintWriter pw){
		DailyLivesType dlv=new DailyLivesType();
		JSONObject json=new JSONObject();
		if(request.getParameter("style").equals("")){
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
				String path="/img/serviceTypeLogo/"+request.getParameter("style")+"/";
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
		
		dlv.setStyle(request.getParameter("style"));
		dlv.setServiceType(request.getParameter("servicetype"));
		
		boolean result=this.dailyLivesDaoImpl.addNewDailyLivesType(dlv);
		if(result){
			json.put("result", "success");
			json.put("info", "添加成功");
		}else{
			json.put("result", "fail");
			json.put("info", "添加失败");
		}
		pw.write(json.toString());
		return "dailyLives/addservicetype";
	}
	*/
	
	
	public IntegralCom getIntegralCom() {
		return integralCom;
	}
	public void setIntegralCom(IntegralCom integralCom) {
		this.integralCom = integralCom;
	}
}
