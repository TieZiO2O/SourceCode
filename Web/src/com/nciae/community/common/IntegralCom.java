package com.nciae.community.common;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import com.nciae.community.dao.impl.IntegralDaoImpl;
import com.nciae.community.domain.Integral;
import com.nciae.community.domain.IntegralOperateEnum;
import com.nciae.community.domain.IntegralType;

public class IntegralCom {
	
	private IntegralDaoImpl integralDaoImpl;
	
	public IntegralDaoImpl getIntegralDaoImpl() {
		return integralDaoImpl;
	}

	public void setIntegralDaoImpl(IntegralDaoImpl integralDaoImpl) {
		this.integralDaoImpl = integralDaoImpl;
	}

	public boolean OperateUserIntegral(HttpServletRequest request,PrintWriter pw){
		
		String uid=request.getParameter("userId");
		String otype=request.getParameter("operatetype");
		String cid=request.getParameter("commodityId");
		JSONObject json=new JSONObject();
		if(uid.equals("")){
			json.put("result", "fail");
			json.put("info", "该操作需要userId");
			pw.write(json.toString());
			return false;
		}
		
		if(otype.equals("")){
			json.put("result", "fail");
			json.put("info", "该操作需要传递操作的类型");
			pw.write(json.toString());
			return false;
		}
		
		if(otype.equals("buycommodity")){
			if(cid.equals("")){
				json.put("result", "fail");
				json.put("info", "兑换积分功能需要传递商品的Id");
				pw.write(json.toString());
				return false;
			}
		}

		//查询该用户是否已经添加积分
		Integral inte=this.integralDaoImpl.QueryIntegral(uid);
		//获取该类型用户所需要的积分类型
		IntegralType Itype=this.integralDaoImpl.QueryOneByType(otype);
		Integral integral=new Integral();
		integral.setFraction(Itype.getFraction());
		integral.setUserId(Integer.parseInt(uid));
		integral.setUsed(true);
		
		boolean result=true;
		Integral newInte=null;
		//如果该用户不存在，则执行写入数据操作
		if(inte==null){
			if(Itype.isAddOrdecrease()){
				this.integralDaoImpl.InsertIntegral(integral);
			}else{
				json.put("result", "fail");
				json.put("info", "该用户积分不足");
				pw.write(json.toString());
				return false;
			}
		}else{
			if(Itype.isAddOrdecrease()){
				result=this.integralDaoImpl.AddIntegral(integral);
			}else{
				result=this.integralDaoImpl.SubtractIntegral(integral);
			}
			
			if(result){
				newInte=this.integralDaoImpl.QueryIntegral(uid.toString());
			}
		}
		
		
		if(newInte!=null){
			json.put("result", "success");
			json.put("info", "操作后积分为："+newInte.getFraction());
			pw.write(json.toString());
		}else{
			json.put("result", "fail");
			json.put("info", "操作失败");
			pw.write(json.toString());
			return false;
		}
		
		return true;
	}

}
