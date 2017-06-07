package com.taskmanager.service.impl;

import java.util.List;

import com.taskmanager.dao.InfoDAO;
import com.taskmanager.entity.Info;
import com.taskmanager.entity.Page;
import com.taskmanager.service.InfoService;

public class InfoServiceImpl implements InfoService{
	
	private InfoDAO infoDao;
	
	public InfoDAO getInfoDao() {
		return infoDao;
	}

	public void setInfoDao(InfoDAO infoDao) {
		this.infoDao = infoDao;
	}

	@Override
	public Info findInfoById(int id) {
		return this.getInfoDao().findInfoById(id);
	}

	@Override
	public void delete(Info info) {
		this.getInfoDao().delete(info);
	}

	@Override
	public void save(Info info) {
		this.getInfoDao().save(info);
	}

	@Override
	public List<Info> queryByPage(Page page,int style) {
		return this.getInfoDao().queryByPage(page,style);
	}

	@Override
	public int InfoCounts(String columnName, String keyword, int style) {
		return this.getInfoDao().InfoCounts(columnName, keyword, style);
	}

	@Override
	public List<Info> searchByName(Page page, String keyword, int style) {
		return this.getInfoDao().searchByName(page, keyword, style);
	}

	
	
}
