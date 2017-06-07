package com.taskmanager.action.course;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.taskmanager.entity.Course;
import com.taskmanager.entity.Page;
import com.taskmanager.service.CourseService;
import com.taskmanager.util.PageUtil;

public class ScanCourseAction extends ActionSupport{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4799446174067719812L;
	private Course course;
	private Page coursePage;
	private int currentPage;
	private int style;
	private CourseService courseService;
	private final int everyPage=10;
	public Course getCourse() {
		return course;
	}
	public void setCourse(Course course) {
		this.course = course;
	}
	public Page getCoursePage() {
		return coursePage;
	}
	public void setCoursePage(Page coursePage) {
		this.coursePage = coursePage;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getStyle() {
		return style;
	}
	public void setStyle(int style) {
		this.style = style;
	}
	public CourseService getCourseService() {
		return courseService;
	}
	public void setCourseService(CourseService courseService) {
		this.courseService = courseService;
	}
	public int getEveryPage() {
		return everyPage;
	}
	public String execute(){
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session=request.getSession();
		int totalCount=this.courseService.courseCounts();
		setCoursePage(PageUtil.createPage(everyPage, totalCount, 0));
		List<Course> courses=this.courseService.queryByPage(coursePage);
		session.setAttribute("courses", courses);
		session.setAttribute("coursePage", coursePage);
		return SUCCESS;
	}
	public String nextPage(){
		//ÏÂÒ»Ò³
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session=request.getSession();
		int totalCount=this.courseService.courseCounts();
		coursePage=PageUtil.createPage(everyPage, totalCount, currentPage+1);
		List<Course> courses=this.courseService.queryByPage(coursePage);
		session.setAttribute("courses", courses);
		session.setAttribute("coursePage", coursePage);
		return "NextPage";
		
	}
	public String frontPage(){
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session=request.getSession();
		int totalCount=this.courseService.courseCounts();
		coursePage=PageUtil.createPage(everyPage, totalCount,currentPage-1);
		List<Course> courses=this.courseService.queryByPage(coursePage);
		session.setAttribute("courses", courses);
		session.setAttribute("coursePage", coursePage);
		return "frontPage";
	}
}
