package com.nciae.community.dao;

import java.util.ArrayList;

import com.nciae.community.domain.Integral;
import com.nciae.community.domain.IntegralType;

public interface IntegralDao {
	public boolean AddIntegral(Integral integral);
	public boolean SubtractIntegral(Integral integral);
	public boolean InsertIntegral(Integral integral);
	public IntegralType QueryOneByType(String type);
	/*ArrayList<Integral> QueryIntegrals(String userName);*/
	public Integral QueryIntegral(String userId);
}
