package com.taskmanager.action.task;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.taskmanager.entity.Page;
import com.taskmanager.entity.Task;
import com.taskmanager.entity.User;
import com.taskmanager.service.CourseMapService;
import com.taskmanager.service.CourseService;
import com.taskmanager.service.TaskService;
import com.taskmanager.util.PageUtil;

public class ScanTaskAction extends ActionSupport{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6532732298134979991L;
	private Task task;
	private Page taskPage;
	private int currentPage;
	private TaskService taskService;
	private CourseService courseService;
	private CourseMapService courseMapService;
	private final int everyPage=10;
	public Task getTask() {
		return task;
	}
	public void setTask(Task task) {
		this.task = task;
	}
	public Page getTaskPage() {
		return taskPage;
	}
	public void setTaskPage(Page taskPage) {
		this.taskPage = taskPage;
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
	
	public CourseMapService getCourseMapService() {
		return courseMapService;
	}
	public void setCourseMapService(CourseMapService courseMapService) {
		this.courseMapService = courseMapService;
	}
	public CourseService getCourseService() {
		return courseService;
	}
	public void setCourseService(CourseService courseService) {
		this.courseService = courseService;
	}
	public void setTaskService(TaskService taskService) {
		this.taskService = taskService;
	}
	public int getEveryPage() {
		return everyPage;
	}
	public String execute(){
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session=request.getSession();
		User user=(User) session.getAttribute("user");
		if(user.getType()==2)
		{
			if(this.getCourseService().findByTeacherId(user.getId())==null)
			{
				return SUCCESS;
			}
			String courseNo=this.getCourseService().findByTeacherId(user.getId()).getNo();
			int totalCount=this.taskService.taskCounts(courseNo);
			setTaskPage(PageUtil.createPage(everyPage, totalCount, 0));
			List<Task> tasks=this.taskService.queryByPage(taskPage,courseNo);
			session.setAttribute("tasks", tasks);
			session.setAttribute("taskPage", taskPage);
			return SUCCESS;
		}
		if(user.getType()==1)
		{
			int totalCount=this.taskService.stutaskCounts(user.getId());
			setTaskPage(PageUtil.createPage(everyPage, totalCount, 0));
			List<Task> tasks=this.taskService.stuqueryByPage(taskPage,user.getId());
			session.setAttribute("tasks", tasks);
			session.setAttribute("taskPage", taskPage);
			return "stSuccess";
		}
		else
		{
			return ERROR;
		}
	}
	public String nextPage(){
		//ÏÂÒ»Ò³
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session=request.getSession();
		User user=(User) session.getAttribute("user");
		if(user.getType()==2){
			String courseNo=this.getCourseService().findByTeacherId(user.getId()).getNo();
			int totalCount=this.taskService.taskCounts(courseNo);
			taskPage=PageUtil.createPage(everyPage, totalCount, currentPage+1);
			List<Task> tasks=this.taskService.queryByPage(taskPage,courseNo);
			session.setAttribute("tasks", tasks);
			session.setAttribute("taskPage", taskPage);
			return "NextPage";
		}else
		{
			return ERROR;
		}
		
	}
	public String frontPage(){
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session=request.getSession();
		User user=(User) session.getAttribute("user");
		if(user.getType()==2){
			String courseNo=this.getCourseService().findByTeacherId(user.getId()).getNo();
			int totalCount=this.taskService.taskCounts(courseNo);
			taskPage=PageUtil.createPage(everyPage, totalCount,currentPage-1);
			List<Task> tasks=this.taskService.queryByPage(taskPage,courseNo);
			session.setAttribute("tasks", tasks);
			session.setAttribute("taskPage", taskPage);
			return "frontPage";
		}
		else
		{
			return ERROR;
		}
	} 
}
