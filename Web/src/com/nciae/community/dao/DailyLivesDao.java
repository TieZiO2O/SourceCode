package com.nciae.community.dao;


import java.util.ArrayList;
import java.util.List;

import com.nciae.community.domain.DailyLives;
import com.nciae.community.domain.DailyLivesType;
import com.nciae.community.domain.ForumThreads;
public interface DailyLivesDao {
	public DailyLives queryByGuid(String guid);
	public ArrayList<DailyLives> queryAllByUid(String uid);
	public boolean addNewDailyLives(DailyLives forumThread) throws Exception;
	public boolean addDailyLivesImgs(ArrayList<String> imgPaths,String guid) throws Exception;
	public boolean addNewUser_DailyLives(DailyLives forumThread) throws Exception;
	public ArrayList<DailyLivesType> SelectAllDailyLivesTypes();
	/*public ArrayList<DailyLivesType> queryAllButUsed();*/
	public ArrayList<DailyLives> queryAllByServiceType(int typeId);
	public boolean addNewDailyLivesType(DailyLivesType dlv);
}
