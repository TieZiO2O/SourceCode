package com.nciae.community.dao;

import java.util.ArrayList;

import com.nciae.community.domain.City;

public interface CityDao {

	public ArrayList<City> queryAllCity() throws Exception;
	public Integer queryIdByName(String city) throws Exception;
}
