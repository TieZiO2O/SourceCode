package com.nciae.community.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ModelAndView;

import com.nciae.community.dao.impl.UsersDaoImpl;

@Controller
@RequestMapping("/adminusers")
public class UploadController2 {

	private UsersDaoImpl usersDaoImpl;
	
	public UsersDaoImpl getUsersDaoImpl() {
		return usersDaoImpl;
	}

	public void setUsersDaoImpl(UsersDaoImpl usersDaoImpl) {
		this.usersDaoImpl = usersDaoImpl;
	}

	@RequestMapping(value="upload2",method=RequestMethod.POST)
	public String upload2(HttpServletRequest request,HttpServletResponse response) throws Exception{
		System.out.println("UploadController2.upload2()");
		//创建一个通用的多部分解析器.   
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
		 //判断 request 是否有文件上传,即多部分请求...  
		if(multipartResolver.isMultipart(request))
		{
			 //判断 request 是否有文件上传,即多部分请求...  
			MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest)(request);
			//int id=Integer.parseInt(request.getParameter("id"));
			Integer id=Integer.valueOf(request.getParameter("id"));
			System.out.println("id="+id);
			Iterator<String> iter = multiRequest.getFileNames();
			while(iter.hasNext()){
				MultipartFile file = multiRequest.getFile(iter.next());
				String fileName = file.getOriginalFilename();
				//String path ="E:/tempImg/";
				String path ="/shopLogo/tempImg";
				
				String logoRealPathDir = request.getSession().getServletContext().getRealPath(path);
				File logoSaveFile = new File(logoRealPathDir);
				if (!logoSaveFile.exists())
		            logoSaveFile.mkdirs();
				
				String hzm1=fileName.substring(fileName.indexOf(".")+1,fileName.length()).toLowerCase(); //后缀名
				String wjm1=fileName.substring(0,fileName.indexOf(".")); //文件名
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
//				fileName = wjm1 + "_" + sdf.format(new Date())+"."+hzm1;//新文件的全名
				fileName = "LOGO_" + wjm1 + "_" + sdf.format(new Date())+"."+hzm1;//新文件的全名
				String url = logoRealPathDir + File.separator + fileName;//新文件URL
				
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
				
				try {//usersDaoImpl.addShopLogo(id, url)
					
					///////////0629
					//File localFile = new File(url);
					//file.transferTo(localFile);
					/////////
					
					String savePath = path+"/"+fileName;
					if(usersDaoImpl.addShopLogo(id, savePath)){
						request.setAttribute("result", "<font color='green'>上传成功</font>");
					}else{
						request.setAttribute("result", "<font color='red'>上传失败</font>");
					}
				} catch (Exception e) {
					e.printStackTrace();
					request.setAttribute("result", "<font color='red'>上传失败</font>");
				}
				
			}
		}		
		
		return "adminusers/upload2";
	}
	
	@RequestMapping("upload_page")
    public String handleUploadShow() { 
		System.out.println("UploadController2.handleUploadShow()");
        return "adminusers/upload2";  
    }
	
	////////////////////////0614
	/*@ExceptionHandler(Exception.class)
    public String handleException(Exception ex,HttpServletRequest request) {       
         //Map<Object, Object> model = new HashMap<Object, Object>();  
         if (ex instanceof MaxUploadSizeExceededException){  
//                        model.put("result", "文件应不大于 "+  
//                       getFileKB(((MaxUploadSizeExceededException)ex).getMaxUploadSize())); 
        	 			request.setAttribute("result", "<font color='red'>文件应不大于 "+getFileKB(((MaxUploadSizeExceededException)ex).getMaxUploadSize())+"</font>");
                     } else{  
                        //model.put("result", "不知错误: " + ex.getMessage());  
                        request.setAttribute("result", "<font color='red'>未知错误: "+ex.getMessage()+"</font>");
                    }  
         //return new ModelAndView("/common/file/upload", (Map) model);  
         return "adminusers/upload2";         
    }    
      
    private String getFileKB(long byteFile){  
        if(byteFile==0)  
           return "0KB";  
        long kb=1024;  
        return ""+byteFile/kb+"KB";  
    }  
    private String getFileMB(long byteFile){  
        if(byteFile==0)  
           return "0MB";  
        long mb=1024*1024;  
        return ""+byteFile/mb+"MB";  
    }  */
	
	
	//////////////剪辑///////////////

}
