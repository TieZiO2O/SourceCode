package com.nciae.community.dao;


import java.util.ArrayList;
import java.util.List;

import com.nciae.community.domain.DailyLives;
import com.nciae.community.domain.DailyLivesType;
import com.nciae.community.domain.ForumThreads;
public interface DailyLivesDao {
	public DailyLives queryByGuid(String guid);
	public ArrayList<DailyLives> queryAll();
	public boolean addNewDailyLives(DailyLives forumThread) throws Exception;
	public boolean addDailyLivesImgs(ArrayList<String> imgPaths,String guid) throws Exception;
	public boolean addNewUser_DailyLives(DailyLives forumThread) throws Exception;
	public ArrayList<DailyLivesType> SelectAllDailyLivesTypes();
	/*public ArrayList<DailyLivesType> queryAllButUsed();*/
	public ArrayList<DailyLives> queryAllByServiceType(int typeId);
	public boolean addNewDailyLivesType(DailyLivesType dlv);
	boolean delete_DailyLivesType_ById(int id);
	boolean delete_DailyLives_ById(String guid);
	ArrayList<DailyLives> query_By_titleorphone(String title, String phone);
}
