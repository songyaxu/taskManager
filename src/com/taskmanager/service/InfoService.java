package com.taskmanager.service;

import java.util.List;

import com.taskmanager.entity.Info;
import com.taskmanager.entity.Page;

public interface InfoService {
	public Info findInfoById(int id);
	public void delete(Info info);
	public void save(Info info);
	public List<Info> queryByPage(final Page page,int style);
	public int InfoCounts(String columnName,String keyword,int style);
	public List<Info> searchByName(final Page page,String keyword,int style);
}
