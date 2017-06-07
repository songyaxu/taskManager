package com.taskmanager.action.course;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.taskmanager.entity.CourseMap;
import com.taskmanager.entity.Page;
import com.taskmanager.entity.TaskMap;
import com.taskmanager.service.CourseMapService;
import com.taskmanager.service.TaskMapService;
import com.taskmanager.util.PageUtil;

public class ScanCourseMapAction extends ActionSupport{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8820145401273443792L;
	private CourseMapService courseMapService;
	private TaskMapService taskMapService;
	private int id;
	private int courseId;
	private int stateCode;
	private String message;
	private Page courseMapPage;
	private int currentPage;
	private final int everyPage=10;
	public CourseMapService getCourseMapService() {
		return courseMapService;
	}
	public void setCourseMapService(CourseMapService courseMapService) {
		this.courseMapService = courseMapService;
	}
	public TaskMapService getTaskMapService() {
		return taskMapService;
	}
	public void setTaskMapService(TaskMapService taskMapService) {
		this.taskMapService = taskMapService;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCourseId() {
		return courseId;
	}
	public void setCourseId(int courseId) {
		this.courseId = courseId;
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
	
	public Page getCourseMapPage() {
		return courseMapPage;
	}
	public void setCourseMapPage(Page courseMapPage) {
		this.courseMapPage = courseMapPage;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getEveryPage() {
		return everyPage;
	}
	public String execute(){
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session=request.getSession();
		int totalCount=this.courseMapService.courseMapCounts(courseId);
		setCourseMapPage(PageUtil.createPage(everyPage, totalCount, 0));
		List<CourseMap> courseMaps=this.courseMapService.queryByPage(courseMapPage, courseId);
		for(int i=0;i<courseMaps.size();i++)
		{
			TaskMap ts=this.getTaskMapService().findTaskMap(courseMaps.get(i).getStudentId(), id);
			if(ts==null)
			{
				courseMaps.get(i).setTaskMapId(0);
			}
			else
			{
				courseMaps.get(i).setTaskMapId(ts.getId());
				courseMaps.get(i).setScore(ts.getScore());
			}
		}
		session.setAttribute("courseMaps", courseMaps);
		session.setAttribute("courseMapPage", courseMapPage);
		return SUCCESS;
	}
	public String nextPage(){
		//ÏÂÒ»Ò³
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session=request.getSession();
		int totalCount=this.courseMapService.courseMapCounts(courseId);
		courseMapPage=PageUtil.createPage(everyPage, totalCount, currentPage+1);
		List<CourseMap> courseMaps=this.courseMapService.queryByPage(courseMapPage,courseId);
		for(int i=0;i<courseMaps.size();i++)
		{
			TaskMap ts=this.getTaskMapService().findTaskMap(courseMaps.get(i).getStudentId(), id);
			if(ts==null)
			{
				courseMaps.get(i).setTaskMapId(0);
			}
			else
			{
				courseMaps.get(i).setTaskMapId(ts.getId());
				courseMaps.get(i).setScore(ts.getScore());
			}
		}
		session.setAttribute("courseMaps", courseMaps);
		session.setAttribute("courseMapPage", courseMapPage);
		return "NextPage";
		
	}
	public String frontPage(){
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session=request.getSession();
		int totalCount=this.courseMapService.courseMapCounts(courseId);
		courseMapPage=PageUtil.createPage(everyPage, totalCount,currentPage-1);
		List<CourseMap> courseMaps=this.courseMapService.queryByPage(courseMapPage,courseId);
		for(int i=0;i<courseMaps.size();i++)
		{
			TaskMap ts=this.getTaskMapService().findTaskMap(courseMaps.get(i).getStudentId(), id);
			if(ts==null)
			{
				courseMaps.get(i).setTaskMapId(0);
			}
			else
			{
				courseMaps.get(i).setTaskMapId(ts.getId());
				courseMaps.get(i).setScore(ts.getScore());
			}
		}
		session.setAttribute("courseMaps", courseMaps);
		session.setAttribute("courseMapPage", courseMapPage);
		return "frontPage";
	}
}
