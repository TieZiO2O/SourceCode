package com.nciae.community.dao;

import com.nciae.community.domain.Integral;
import com.nciae.community.domain.IntegralType;

public interface IntegralDao {
	public boolean AddIntegral(Integral integral);
	public boolean SubtractIntegral(Integral integral);
	public Integral QueryIntegral(String userId);
	public boolean InsertIntegral(Integral integral);
	public IntegralType QueryOneByType(String type);
}
