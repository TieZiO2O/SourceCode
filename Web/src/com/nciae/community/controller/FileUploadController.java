package com.nciae.community.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/file")
public class FileUploadController {
 
	@RequestMapping(value="upload_file",method=RequestMethod.POST)
	public void uploadFile(HttpServletRequest request,HttpServletResponse response){
		try {
			response.setContentType("text/plain");
			response.setCharacterEncoding("UTF-8");
			//如果没有对session 进行操作的话 就会抛出异常:
			//java.lang.IllegalStateException: Cannot create a session after the response has been committed
			HttpSession session=request.getSession();
			session.setAttribute("log", "get uploading");
			String fileName=request.getParameter("fileName");
			
			System.out.println("获取客户端错误日志文件-----"+fileName);
			ServletInputStream in = request.getInputStream();
			String filePath=((HttpSession) request).getServletContext().getRealPath("\\")+"community\\img\\";
			File file=new File(filePath);
			
			System.out.println("获取filePath日志文件-----"+filePath);
			if(file.exists()==false){
				file.mkdirs();
			}
			file=new File(file,fileName);
			if(file.exists()==false){
				file.createNewFile();
			}
			FileOutputStream fos=new FileOutputStream(file);
			
			System.out.println(filePath);
			
			int len=0;
			byte[] buf=new byte[1024*8];
			while((len=in.read(buf))!=-1){
				fos.write(buf,0,len);
				fos.flush();
			}
			in.close();
			fos.close();
			PrintWriter out=response.getWriter();
			out.write("success");
			
		} catch ( Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				PrintWriter out=response.getWriter();
				out.write("failed");
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
  
}
