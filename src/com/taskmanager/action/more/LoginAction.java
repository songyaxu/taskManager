package com.taskmanager.action.more;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.taskmanager.util.MD5Util;
import com.taskmanager.util.PageUtil;
import com.taskmanager.entity.Admin;
import com.taskmanager.entity.Info;
import com.taskmanager.entity.Page;
import com.taskmanager.entity.Student;
import com.taskmanager.entity.Teacher;
import com.taskmanager.entity.User;
import com.taskmanager.service.AdminService;
import com.taskmanager.service.InfoService;
import com.taskmanager.service.StudentService;
import com.taskmanager.service.TeacherService;

@SuppressWarnings("serial")
public class LoginAction extends ActionSupport {
	
	private Page indexPage;
	private InfoService infoService;
	private final int everyPage=10;
	
	private String no;
	public Page getIndexPage() {
		return indexPage;
	}

	public void setIndexPage(Page indexPage) {
		this.indexPage = indexPage;
	}

	public InfoService getInfoService() {
		return infoService;
	}

	public void setInfoService(InfoService infoService) {
		this.infoService = infoService;
	}
	private String name;
	private String pwd;
	private String message;
	private AdminService adminService;
	private StudentService studentService;
	private TeacherService teacherService;
	public StudentService getStudentService() {
		return studentService;
	}

	public void setStudentService(StudentService studentService) {
		this.studentService = studentService;
	}

	public TeacherService getTeacherService() {
		return teacherService;
	}

	public void setTeacherService(TeacherService teacherService) {
		this.teacherService = teacherService;
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}
	
	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public AdminService getAdminService() {
		return adminService;
	}

	public void setAdminService(AdminService adminService) {
		this.adminService = adminService;
	}
	
	public String execute(){
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session=request.getSession();
		session.removeAttribute("user");
	    Admin ad = adminService.findAdminByNo(this.getNo());
	    if(ad!=null)
	    {
	    	Admin loginAdmin=new Admin(no,MD5Util.md5(getPwd()));
	    	Admin admin=adminService.loginAdmin(loginAdmin);
	    	if(admin!=null)
	    	{
	    		message="登录成功！";
	    		User user=new User(admin.getId(),admin.getNo(),admin.getName(),admin.getPwd(),3);
	    		session.setAttribute("user", user);
	    		int totalCount=this.infoService.InfoCounts("0", "0", 1);
	    		setIndexPage(PageUtil.createPage(everyPage, totalCount, 0));
	    		List<Info> indexinfos=this.infoService.queryByPage(indexPage,1);
	    		session.setAttribute("indexinfos", indexinfos);
	    		totalCount=this.infoService.InfoCounts("0", "0", 4);
	    		setIndexPage(PageUtil.createPage(5, totalCount, 0));
	    		List<Info> indexres=this.infoService.queryByPage(indexPage,4);
	    		session.setAttribute("indexres", indexres);
	    		totalCount=this.infoService.InfoCounts("0", "0", 2);
	    		setIndexPage(PageUtil.createPage(5, totalCount, 0));
	    		List<Info> indexcw=this.infoService.queryByPage(indexPage,2);
	    		session.setAttribute("indexcw", indexcw);
	    		return SUCCESS;
	    	}
	    	else
	    	{
	    		message="密码不正确！";
	    		return INPUT;
	    	}
	    }
	    else
	    {
	    	Student student=studentService.findStudentByNo(no);
	    	if(student!=null)
	    	{
	    		Student loginSt=new Student(no,MD5Util.md5(getPwd()));
	    		Student st=studentService.loginStudent(loginSt);
	    		if(st!=null)
	    		{
	    			message="登录成功！";
		    		User user=new User(st.getId(),st.getNo(),st.getName(),st.getPwd(),1);
		    		session.setAttribute("user", user);
		    		int totalCount=this.infoService.InfoCounts("0", "0", 1);
		    		setIndexPage(PageUtil.createPage(everyPage, totalCount, 0));
		    		List<Info> indexinfos=this.infoService.queryByPage(indexPage,1);
		    		session.setAttribute("indexinfos", indexinfos);
		    		totalCount=this.infoService.InfoCounts("0", "0", 4);
		    		setIndexPage(PageUtil.createPage(5, totalCount, 0));
		    		List<Info> indexres=this.infoService.queryByPage(indexPage,4);
		    		session.setAttribute("indexres", indexres);
		    		totalCount=this.infoService.InfoCounts("0", "0", 2);
		    		setIndexPage(PageUtil.createPage(5, totalCount, 0));
		    		List<Info> indexcw=this.infoService.queryByPage(indexPage,2);
		    		session.setAttribute("indexcw", indexcw);
		    		return SUCCESS;
	    		}
	    		else
	    		{
	    			message="密码不正确！";
		    		return INPUT;
	    		}
	    	}
	    	else
	    	{
	    		Teacher teacher=teacherService.findTeacherByNo(no);
	    		if(teacher!=null)
	    		{
	    			Teacher loginTeacher=new Teacher(no,MD5Util.md5(getPwd()));
	    			Teacher tc=teacherService.loginTeacher(loginTeacher);
	    			if(tc!=null)
	    			{
	    				message="登录成功！";
			    		User user=new User(tc.getId(),tc.getNo(),tc.getName(),tc.getPwd(),2);
			    		session.setAttribute("user", user);
			    		int totalCount=this.infoService.InfoCounts("0", "0", 1);
			    		setIndexPage(PageUtil.createPage(everyPage, totalCount, 0));
			    		List<Info> indexinfos=this.infoService.queryByPage(indexPage,1);
			    		session.setAttribute("indexinfos", indexinfos);
			    		totalCount=this.infoService.InfoCounts("0", "0", 4);
			    		setIndexPage(PageUtil.createPage(5, totalCount, 0));
			    		List<Info> indexres=this.infoService.queryByPage(indexPage,4);
			    		session.setAttribute("indexres", indexres);
			    		totalCount=this.infoService.InfoCounts("0", "0", 2);
			    		setIndexPage(PageUtil.createPage(5, totalCount, 0));
			    		List<Info> indexcw=this.infoService.queryByPage(indexPage,2);
			    		session.setAttribute("indexcw", indexcw);
			    		return SUCCESS;
	    			}
	    			else
	    			{
	    				message="密码不正确！";
			    		return INPUT;
	    			}
	    		}
	    		else
	    		{	
		    		message="没有此账号，请核对后在登录！";
	    	    	return INPUT;
	    		}
	    	}
	    }
	}
	public String visit()
	{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		session.removeAttribute("user");
		User user=new User(0,"","游客","",0);
		session.setAttribute("user", user);
		int totalCount=this.infoService.InfoCounts("0", "0", 1);
		setIndexPage(PageUtil.createPage(everyPage, totalCount, 0));
		List<Info> indexinfos=this.infoService.queryByPage(indexPage,1);
		session.setAttribute("indexinfos", indexinfos);
		totalCount=this.infoService.InfoCounts("0", "0", 4);
		setIndexPage(PageUtil.createPage(5, totalCount, 0));
		List<Info> indexres=this.infoService.queryByPage(indexPage,4);
		session.setAttribute("indexres", indexres);
		return SUCCESS;
	}
	public String logout()
	{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		session.removeAttribute("user");
		message="用户已登出！";
		return "logout";
	}
}
