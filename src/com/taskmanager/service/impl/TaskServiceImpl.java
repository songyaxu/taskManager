package com.taskmanager.service.impl;

import java.util.List;

import com.taskmanager.dao.TaskDAO;
import com.taskmanager.entity.Page;
import com.taskmanager.entity.Task;
import com.taskmanager.service.TaskService;

public class TaskServiceImpl implements TaskService{
	private TaskDAO taskDao;
	
	public TaskDAO getTaskDao() {
		return taskDao;
	}

	public void setTaskDao(TaskDAO taskDao) {
		this.taskDao = taskDao;
	}

	@Override
	public Task findById(int id) {
		return this.getTaskDao().findById(id);
	}

	@Override
	public Task findByCourseNo(String no) {
		return this.findByCourseNo(no);
	}

	@Override
	public void delete(Task task) {
		this.getTaskDao().delete(task);
	}

	@Override
	public void save(Task task) {
		this.getTaskDao().save(task);
	}

	@Override
	public void update(Task task) {
		this.getTaskDao().update(task);
	}

	@Override
	public List<Task> queryByPage(Page page, String courseNo) {
		return this.getTaskDao().queryByPage(page, courseNo);
	}

	@Override
	public int taskCounts(String courseNo) {
		return this.getTaskDao().taskCounts(courseNo);
	}

	@Override
	public List<Task> stuqueryByPage(Page page, int id) {
		return this.getTaskDao().stuqueryByPage(page, id);
	}

	@Override
	public int stutaskCounts(int id) {
		return this.getTaskDao().stutaskCounts(id);
	}
	
}
