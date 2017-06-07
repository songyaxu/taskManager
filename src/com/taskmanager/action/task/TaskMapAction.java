package com.taskmanager.action.task;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.json.annotations.JSON;

import com.opensymphony.xwork2.ActionSupport;
import com.taskmanager.entity.Task;
import com.taskmanager.entity.TaskMap;
import com.taskmanager.entity.User;
import com.taskmanager.service.TaskMapService;
import com.taskmanager.service.TaskService;
import com.taskmanager.util.TimeUtil;

public class TaskMapAction extends ActionSupport{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1568467287141802992L;
	private TaskMapService taskMapService;
	private TaskService taskService; 
	private int stateCode;
	private String message;
	private Task task;
	private int score;
	private TaskMap taskMap;
	private int flag;
	private int id;
	@JSON(serialize=false)
	public TaskMapService getTaskMapService() {
		return taskMapService;
	}
	public void setTaskMapService(TaskMapService taskMapService) {
		this.taskMapService = taskMapService;
	}
	@JSON(serialize=false)
	public TaskService getTaskService() {
		return taskService;
	}
	public void setTaskService(TaskService taskService) {
		this.taskService = taskService;
	}
	public Task getTask() {
		return task;
	}
	public void setTask(Task task) {
		this.task = task;
	}
	public int getFlag() {
		return flag;
	}
	public void setFlag(int flag) {
		this.flag = flag;
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
	public void setMessage(String message) {
		this.message = message;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public TaskMap getTaskMap() {
		return taskMap;
	}
	public void setTaskMap(TaskMap taskMap) {
		this.taskMap = taskMap;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public String edit(){
		task=this.getTaskService().findById(id);
		return "editSuccess";
	}
	public String add(){
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session=request.getSession();
		User user=(User) session.getAttribute("user");
		if(user==null||user.getType()!=1)
		{
			stateCode=0;
			message="无法提交！";
			return ERROR;
		}
		task=this.getTaskService().findById(taskMap.getTaskId());
		if(TimeUtil.currentTime().getTime()<task.getStartTime().getTime())
		{
			stateCode=0;
			message="作业还没有到提交时间，无法提交！";
			return ERROR;
		}
		if(TimeUtil.currentTime().getTime()>task.getEndTime().getTime())
		{
			stateCode=0;
			message="作业已经过了提交时间！";
			return ERROR;
		}
		TaskMap tm=this.getTaskMapService().findTaskMap(user.getId(),task.getId());
		if(tm!=null)
		{
			tm.setContent(taskMap.getContent());
			tm.setAttach(taskMap.getAttach());
			this.getTaskMapService().update(tm);
			stateCode=1;
			message="修改成功！";
			return SUCCESS;
		}
		taskMap.setTaskName(task.getName());
		taskMap.setStudentId(user.getId());
		taskMap.setStudentName(user.getName());
		taskMap.setTeacherId(task.getTeacherId());
		taskMap.setTeacherName(task.getTeacherName());
		taskMap.setCourseNo(task.getCourseNo());
		taskMap.setCourseName(task.getCourseName());
		taskMap.setState(0);
		taskMap.setTime(TimeUtil.currentTime());
		this.getTaskMapService().save(taskMap);
		stateCode=1;
		message="提交成功！";
		return SUCCESS;
	}
	public String detail(){
		taskMap=this.getTaskMapService().findById(id);
		return "detailSuccess";
	}
	public String score(){
		if(score>100||score<0)
		{
			stateCode=0;
			message="请认真填写分数！";
			return "scoreError";
		}
		taskMap=this.getTaskMapService().findById(id);
		if(taskMap==null)
		{
			stateCode=0;
			message="该同学还没有上传作业！";
			return "scoreError";
		}
		else{
			if(taskMap.getScore()!=0)
			{
				taskMap.setScore(score);
				this.getTaskMapService().update(taskMap);
				stateCode=1;
				message="成绩修改成功！";
				return "score";
			}else{
				taskMap.setScore(score);
				this.getTaskMapService().update(taskMap);
				stateCode=1;
				message="成绩设置成功！";
				return "score";
			}
		}
	}
}
