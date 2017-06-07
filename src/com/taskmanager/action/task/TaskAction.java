package com.taskmanager.action.task;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.json.annotations.JSON;

import com.opensymphony.xwork2.ActionSupport;
import com.taskmanager.entity.Course;
import com.taskmanager.entity.Task;
import com.taskmanager.entity.TaskMap;
import com.taskmanager.entity.User;
import com.taskmanager.service.CourseService;
import com.taskmanager.service.TaskMapService;
import com.taskmanager.service.TaskService;
import com.taskmanager.service.TeacherService;
import com.taskmanager.util.TimeUtil;

public class TaskAction extends ActionSupport{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2001804505230884628L;
	private TaskService taskService;
	private TaskMapService taskMapService;
	private CourseService courseService;
	private TeacherService teacherService;
	private int stateCode;
	private String message;
	private Course course;
	private String start;
	private String end;
	private int id;
	private TaskMap taskMap;
	private Task task;
	@JSON(serialize=false)
	public TaskService getTaskService() {
		return taskService;
	}
	public void setTaskService(TaskService taskService) {
		this.taskService = taskService;
	}
	@JSON(serialize=false)
	public CourseService getCourseService() {
		return courseService;
	}
	public void setCourseService(CourseService courseService) {
		this.courseService = courseService;
	}
	@JSON(serialize=false)
	public TeacherService getTeacherService() {
		return teacherService;
	}
	public void setTeacherService(TeacherService teacherService) {
		this.teacherService = teacherService;
	}
	@JSON(serialize=false)
	public TaskMapService getTaskMapService() {
		return taskMapService;
	}
	public void setTaskMapService(TaskMapService taskMapService) {
		this.taskMapService = taskMapService;
	}
	public TaskMap getTaskMap() {
		return taskMap;
	}
	public void setTaskMap(TaskMap taskMap) {
		this.taskMap = taskMap;
	}
	public Course getCourse() {
		return course;
	}
	public void setCourse(Course course) {
		this.course = course;
	}
	public int getStateCode() {
		return stateCode;
	}
	public void setStateCode(int stateCode) {
		this.stateCode = stateCode;
	}
	public String getMessage() {
		return message;
	}
	
	public String getStart() {
		return start;
	}
	public void setStart(String start) {
		this.start = start;
	}
	public String getEnd() {
		return end;
	}
	public void setEnd(String end) {
		this.end = end;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Task getTask() {
		return task;
	}
	public void setTask(Task task) {
		this.task = task;
	}
	public String add(){
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session=request.getSession();
		User user=(User) session.getAttribute("user");
		if(user.getType()!=2)
		{
			stateCode=0;
			message="无法发布作业！";
			return "addError";
		}
		Course cr=this.getCourseService().findByTeacherId(user.getId());
		if(cr==null)
		{
			stateCode=0;
			message="您还没有创建课程,无法发布作业！";
			return "addError";
		}
		getTask().setStartTime(TimeUtil.gettimestampTime(start));
		getTask().setEndTime(TimeUtil.gettimestampTime(end));
		getTask().setCourseId(cr.getId());
		getTask().setCourseName(cr.getName());
		getTask().setCourseNo(cr.getNo());
		getTask().setTeacherId(user.getId());
		getTask().setTeacherName(user.getName());
		this.getTaskService().save(task);
		stateCode=1;
		message="发布成功！";
		return SUCCESS;
	}
	public String edit(){
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session=request.getSession();
		User user=(User) session.getAttribute("user");
		setCourse(this.getCourseService().findByTeacherId(user.getId()));
		return "editSuccess";
	}
	public String delete(){
		Task tk=this.getTaskService().findById(id);
		this.getTaskService().delete(tk);
		stateCode=1;
		message="删除成功！";
		return SUCCESS;
	}
	public String detail(){
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session=request.getSession();
		User user=(User) session.getAttribute("user");
		if(user.getType()==2)
		{
			setTask(this.getTaskService().findById(id));
			return "detailSuccess";
		}
		if(user.getType()==1)
		{
			setTask(this.getTaskService().findById(id));
			setTaskMap(this.getTaskMapService().findTaskMap(user.getId(), getId()));
			return "detailSuccess";
		}
		return ERROR;
	}
}
