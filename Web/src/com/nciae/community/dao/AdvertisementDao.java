package com.nciae.community.dao;

import java.util.ArrayList;

import com.nciae.community.domain.Advertisement;

public interface AdvertisementDao {
	/**
	 * 添加广告
	 * @param advertisement
	 * @return
	 * @throws Exception
	 */
	public boolean add(Advertisement advertisement) throws Exception;
	/**
	 * 删除广告
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public boolean delete(Integer id) throws Exception;
	/**
	 * 查询一条广告
	 * @return
	 * @throws Exception
	 */
	public Advertisement queryOneAdvertisement() throws Exception;
	/**
	 * 查询所有广告
	 * @return
	 * @throws Exception
	 */
	public ArrayList<Advertisement> queryAdvertisements() throws Exception;
	/**
	 * 查询最新三条广告
	 * @return
	 * @throws Exception
	 */
	public ArrayList<Object> queryNewThreeAd() throws Exception;
	/**
	 * 添加广告图片
	 * @param id
	 * @param path
	 * @return
	 * @throws Exception
	 */
	public boolean addAdImg(Integer id, String path) throws Exception;
	/**
	 *0629
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Integer lxQueryShoperIDofAD(Integer id) throws Exception;
	/**
	 * 0629
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public String lxFindAdImgUrl(int id) throws Exception;
}
