package com.taskmanager.service;

import java.util.List;

import com.taskmanager.entity.Page;
import com.taskmanager.entity.TaskMap;

public interface TaskMapService {
	public TaskMap findById(int id);
	public TaskMap findByCourseNo(String no);
	public TaskMap findTaskMap(int studentId,int taskId);
	public void delete(TaskMap taskMap);
	public void save(TaskMap taskMap);
	public void update(TaskMap taskMap);
	public List<TaskMap> queryByPage(final Page page,int taskId);
	public int taskMapCounts(int taskId);
}
