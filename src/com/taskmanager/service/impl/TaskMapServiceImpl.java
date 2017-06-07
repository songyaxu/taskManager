package com.taskmanager.service.impl;

import java.util.List;

import com.taskmanager.dao.TaskMapDAO;
import com.taskmanager.entity.Page;
import com.taskmanager.entity.TaskMap;
import com.taskmanager.service.TaskMapService;

public class TaskMapServiceImpl implements TaskMapService{
	private TaskMapDAO taskMapDao;

	public TaskMapDAO getTaskMapDao() {
		return taskMapDao;
	}

	public void setTaskMapDao(TaskMapDAO taskMapDao) {
		this.taskMapDao = taskMapDao;
	}

	@Override
	public TaskMap findById(int id) {
		return this.getTaskMapDao().findById(id);
	}

	@Override
	public TaskMap findByCourseNo(String no) {
		return this.getTaskMapDao().findByCourseNo(no);
	}

	@Override
	public void delete(TaskMap taskMap) {
		this.getTaskMapDao().delete(taskMap);
	}

	@Override
	public void save(TaskMap taskMap) {
		this.getTaskMapDao().save(taskMap);
	}

	@Override
	public void update(TaskMap taskMap) {
		this.getTaskMapDao().update(taskMap);
	}

	@Override
	public List<TaskMap> queryByPage(Page page, int taskId) {
		return this.getTaskMapDao().queryByPage(page, taskId);
	}

	@Override
	public int taskMapCounts(int taskId) {
		return this.getTaskMapDao().taskMapCounts(taskId);
	}

	@Override
	public TaskMap findTaskMap(int studentId, int taskId) {
		return this.getTaskMapDao().findTaskMap(studentId, taskId);
	}
	
}
