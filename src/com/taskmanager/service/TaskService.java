package com.taskmanager.service;

import java.util.List;

import com.taskmanager.entity.Page;
import com.taskmanager.entity.Task;

public interface TaskService {
	public Task findById(int id);
	public Task findByCourseNo(String no);
	public void delete(Task task);
	public void save(Task task);
	public void update(Task task);
	public List<Task> queryByPage(final Page page,String courseNo);
	public int taskCounts(String courseNo);
	public List<Task> stuqueryByPage(final Page page,int id);
	public int stutaskCounts(int id);
}
