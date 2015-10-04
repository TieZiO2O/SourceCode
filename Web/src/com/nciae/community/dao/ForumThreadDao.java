package com.nciae.community.dao;


import java.util.ArrayList;
import java.util.List;

import com.nciae.community.domain.ForumThreads;
public interface ForumThreadDao {
	public ForumThreads queryByGuid(String guid);
	public ArrayList<ForumThreads> queryAllByUid(String uid);
	public boolean addNewForumThread(ForumThreads forumThread) throws Exception;
	public boolean addForumImgs(ArrayList<String> imgPaths,String guid) throws Exception;
	public boolean addNewUser_ForumThread(ForumThreads forumThread) throws Exception;
	public ArrayList<ForumThreads> queryAll();
	public boolean query_IsBelong(String uid,String guid);
	public boolean delete_By_Guid(String guid);
	ArrayList<ForumThreads> queryAllByUid_Paging(String uid,int pindex,int psize);
	ArrayList<ForumThreads> queryAll_Paging(int pindex, int psize);
}
