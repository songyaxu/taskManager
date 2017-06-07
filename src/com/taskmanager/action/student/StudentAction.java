package com.taskmanager.action.student;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.json.annotations.JSON;

import com.opensymphony.xwork2.ActionSupport;
import com.taskmanager.entity.CourseMap;
import com.taskmanager.entity.Reg;
import com.taskmanager.entity.Student;
import com.taskmanager.entity.User;
import com.taskmanager.service.CourseMapService;
import com.taskmanager.service.RegService;
import com.taskmanager.service.StudentService;
import com.taskmanager.util.MD5Util;

public class StudentAction extends ActionSupport{
	/**
	 * 
	 */
	private static final long serialVersionUID = 9056665237987325857L;
	private RegService regService;
	private StudentService studentService;
	private CourseMapService courseMapService;
	private int id;
	private int stateCode;
	private String message;
	private Student student;
	@JSON(serialize=false)
	public RegService getRegService() {
		return regService;
	}
	public void setRegService(RegService regService) {
		this.regService = regService;
	}
	@JSON(serialize=false)
	public StudentService getStudentService() {
		return studentService;
	}
	public void setStudentService(StudentService studentService) {
		this.studentService = studentService;
	}
	@JSON(serialize=false)
	public CourseMapService getCourseMapService() {
		return courseMapService;
	}
	public void setCourseMapService(CourseMapService courseMapService) {
		this.courseMapService = courseMapService;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	public String reg(){
		Reg reg=this.getRegService().findById(id);
		Student st=new Student();
		st.setNo(reg.getNo());
		st.setName(reg.getName());
		st.setPwd(MD5Util.md5(reg.getNo()));
		this.getStudentService().save(st);
		reg.setState(1);
		this.getRegService().update(reg);
		CourseMap courseMap=new CourseMap();
		courseMap.setCourseId(reg.getCourseId());
		courseMap.setCourseName(reg.getCourseName());
		courseMap.setTeacherId(reg.getTeacherId());
		courseMap.setTeacherName(reg.getTeacherName());
		courseMap.setStudentId(this.getStudentService().findStudentByNo(reg.getNo()).getId());
		courseMap.setStudentNo(reg.getNo());
		courseMap.setStudentName(reg.getName());
		this.getCourseMapService().save(courseMap);
		stateCode=1;
		message=reg.getName()+"-注册成功！";
		return SUCCESS;
	}
	public String edit(){
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		User user=(User) session.getAttribute("user");
		Student st=this.getStudentService().findStudentById(user.getId());
		setStudent(st);
		return "editSuccess";
	}
	public String update(){
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		User user=(User) session.getAttribute("user");
		Student st=this.getStudentService().findStudentById(id);
		System.out.println(user.getNo()+"ss"+st.getNo());
		if(!user.getNo().equals(st.getNo()))
		{
			stateCode=0;
			message="无法修改！";
			return "updateError";
		}
		if(student.getPwd()!=null&&!student.getPwd().equals(""))
		{
			st.setPwd(MD5Util.md5(student.getPwd()));
			this.getStudentService().update(st);
			stateCode=1;
			message="修改成功！";
			return "update";

		}
		stateCode=0;
		message="无法修改！";
		return "updateError";		
	}
}
