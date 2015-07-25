package com.nciae.community.utils;

import java.util.ArrayList;

import com.nciae.community.domain.Merchants;
import com.nciae.community.domain.Property;
import com.nciae.community.domain.Users;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class JsonUtil {
	public static String toJsonString(ArrayList<Object> objs)
	{
		JSONArray ja=new JSONArray();
		for(Object obj:objs)
		{
			ja.add(obj);
		}
		return ja.toString();
	}
	public static String toJsonStringObject(Object objs){
		JSONArray ja=new JSONArray();
			ja.add(objs);
		return ja.toString();
	}
	public static String toJsonString1(ArrayList<Integer> objs){
		JSONArray ja=new JSONArray();
		for(Object obj:objs){
			ja.add(obj);
		}
		return ja.toString();
	}
	//0601
	public static String toJsonStringUser(ArrayList<Users> objs)
	{
		JSONArray ja=new JSONArray();
		for(Users obj:objs)
		{
			ja.add(obj);
		}
		return ja.toString();
	}
	
	public static String ToJsonStringProperty(Property property){
		JSONObject ja=new JSONObject();
		ja.put("property",property);
	return ja.toString();
	}
	
	public static String ToJsonStringMerchants(Merchants mer){
		JSONObject ja=new JSONObject();
		ja.put("mer",mer);
	return ja.toString();
	}
	
}
