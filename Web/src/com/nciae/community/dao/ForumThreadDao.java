package com.nciae.community.dao;


import java.util.ArrayList;
import java.util.List;

import com.nciae.community.domain.ForumThreads;
public interface ForumThreadDao {
	public ForumThreads queryByGuid(String guid);
	public ArrayList<ForumThreads> queryAllByUid(String uid);
	public boolean addNewForumThread(ForumThreads forumThread) throws Exception;
	public boolean addForumImgs(ArrayList<String> imgPaths,String guid) throws Exception;
	
}