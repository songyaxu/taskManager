package com.taskmanager.action.task;

import com.opensymphony.xwork2.ActionSupport;
import com.taskmanager.entity.Page;
import com.taskmanager.entity.Task;
import com.taskmanager.service.TaskMapService;
import com.taskmanager.service.TaskService;

public class ScanTaskMapAction extends ActionSupport{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2205604217097964891L;
	private TaskMapService taskMapService;
	private Task task;
	private Page taskMapPage;
	private int currentPage;
	private TaskService taskService;
	private final int everyPage=10;
	public TaskMapService getTaskMapService() {
		return taskMapService;
	}
	public void setTaskMapService(TaskMapService taskMapService) {
		this.taskMapService = taskMapService;
	}
	public Task getTask() {
		return task;
	}
	public void setTask(Task task) {
		this.task = task;
	}
	public Page getTaskMapPage() {
		return taskMapPage;
	}
	public void setTaskMapPage(Page taskMapPage) {
		this.taskMapPage = taskMapPage;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public TaskService getTaskService() {
		return taskService;
	}
	public void setTaskService(TaskService taskService) {
		this.taskService = taskService;
	}
	public int getEveryPage() {
		return everyPage;
	}
	
}
