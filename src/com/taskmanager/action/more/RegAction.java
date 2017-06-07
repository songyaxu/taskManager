package com.taskmanager.action.more;


import org.apache.struts2.json.annotations.JSON;

import com.opensymphony.xwork2.ActionSupport;
import com.taskmanager.entity.Course;
import com.taskmanager.entity.Reg;
import com.taskmanager.entity.Student;
import com.taskmanager.service.CourseService;
import com.taskmanager.service.RegService;
import com.taskmanager.service.StudentService;
import com.taskmanager.util.TimeUtil;

public class RegAction extends ActionSupport{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8850895205175143832L;
	private RegService regService;
	private StudentService studentService;
	private CourseService courseService;
	private Reg reg;
	private int stateCode;
	private String message;
	private int id;
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
	public CourseService getCourseService() {
		return courseService;
	}

	public void setCourseService(CourseService courseService) {
		this.courseService = courseService;
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
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public Reg getReg() {
		return reg;
	}

	public void setReg(Reg reg) {
		this.reg = reg;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	public String add(){
		if(this.getReg().getNo().startsWith("a")||this.getReg().getNo().startsWith("t"))
		{
			stateCode=0;
			message="学号不能以a或者t开头！";
			return ERROR;
		}
		Student st=this.getStudentService().findStudentByNo(reg.getNo());
		if(st!=null)
		{
			stateCode=0;
			message="学号已存在！";
			return ERROR;
		}
		Reg regtemp=this.getRegService().findByStudentNo(reg.getNo());
		if(regtemp!=null)
		{
			stateCode=0;
			message="学号已存在或您已经申请过了！";
			return ERROR;
		}
		Course cr=this.getCourseService().findByNo(reg.getCourseNo());
		reg.setCourseId(cr.getId());
		reg.setCourseName(cr.getName());
		reg.setDate(TimeUtil.currentTime());
		reg.setState(0);
		reg.setTeacherId(cr.getTeacherId());
		reg.setTeacherName(cr.getTeacherName());
		this.getRegService().save(reg);
		message="申请成功，等待通知！";
		stateCode=1;
		return SUCCESS;
	}
	public String detail(){
		Reg re=this.getRegService().findById(id);
		setReg(re);
		return "detailSuccess";
	}
	public String delete(){
		Reg re=this.getRegService().findById(id);
		this.getRegService().delete(re);
		stateCode=1;
		message="删除成功！";
		return SUCCESS;
	}
}
