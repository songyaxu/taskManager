package com.taskmanager.action.course;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.json.annotations.JSON;

import com.opensymphony.xwork2.ActionSupport;
import com.taskmanager.entity.Course;
import com.taskmanager.entity.Teacher;
import com.taskmanager.entity.User;
import com.taskmanager.service.CourseService;
import com.taskmanager.service.TeacherService;

public class CourseAction extends ActionSupport{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6395020637929742255L;
	private CourseService courseService;
	private TeacherService teacherService;
	private Course course;
	private int id;
	private String no;
	private String message;
	private int stateCode;
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
	public Course getCourse() {
		return course;
	}
	public void setCourse(Course course) {
		this.course = course;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public int getStateCode() {
		return stateCode;
	}
	public void setStateCode(int stateCode) {
		this.stateCode = stateCode;
	}
	
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	public String add(){
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session=request.getSession();
		User user=(User)session.getAttribute("user");
		if(user.getType()!=2)
		{
			stateCode=0;
			message="无法添加新的课程！";
			return ERROR;
		}
		Course temp=this.getCourseService().findByTeacherId(user.getId());
		if(temp!=null)
		{
			if(!course.getContent().trim().equals("")&&course.getContent()!=null)
				temp.setContent(getCourse().getContent());
			if(!course.getOutline().trim().equals("")&&course.getOutline()!=null)
				temp.setOutline(getCourse().getOutline());
			if(!course.getStyle().trim().equals("")&&course.getStyle()!=null)
				temp.setStyle(getCourse().getStyle());
			this.getCourseService().update(temp);
			stateCode=1;
			message="修改成功！";
			return SUCCESS;
		}
		Teacher tc=this.getTeacherService().findTeacherById(user.getId());
		getCourse().setTeacherId(user.getId());
		getCourse().setTeacherName(user.getName());
		this.getCourseService().save(getCourse());
		tc.setCourseId(this.getCourseService().findByTeacherId(id).getId());
		tc.setCourseName(this.getCourseService().findByTeacherId(id).getName());
		this.getTeacherService().update(tc);
		stateCode=1;
		message="添加成功！";
		return SUCCESS;
	}
	public String detail(){
		Course cs=this.getCourseService().findById(getId());
		if(cs==null)
		{
			stateCode=0;
			message="发生未知错误！";
			return "detailError";
		}
		setCourse(cs);
		stateCode=1;
		message="查询成功！";
		return "detailSuccess";
	}
	public String edit(){
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session=request.getSession();
		User user=(User)session.getAttribute("user");
		Course cs=this.getCourseService().findByTeacherId(user.getId());
		if(cs!=null)
		{
			setCourse(cs);
		}
		return "edit";
	}
	public String queryByNo(){
		Course cs=this.getCourseService().findByNo(no);
		if(cs==null)
		{
			stateCode=0;
			return "queryError";
		}
		setCourse(cs);
		stateCode=1;
		return "querySuccess";
	}
}
