package com.nciae.community.dao;

import java.util.ArrayList;

import com.nciae.community.domain.Reply;

public interface ReplyDao {
	/**
	 * communityId=0
	 * 软件反馈--0
	 * @return
	 * @throws Exception
	 */
	public ArrayList<Reply> querySoftwareReply() throws Exception;
	/**
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public boolean deleteSoftwareReply(Integer id) throws Exception;
}
