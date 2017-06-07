package com.taskmanager.action.info;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.json.annotations.JSON;

import com.opensymphony.xwork2.ActionSupport;
import com.taskmanager.entity.Info;
import com.taskmanager.entity.Teacher;
import com.taskmanager.entity.User;
import com.taskmanager.service.InfoService;
import com.taskmanager.service.TeacherService;

@SuppressWarnings("serial")
public class InfoAction extends ActionSupport{
	private InfoService infoService;
	private TeacherService teacherService;
	private Info info;
	private int stateCode;
	private int id;
	private String message;
	@JSON(serialize=false) 
	public InfoService getInfoService() {
		return infoService;
	}
	public void setInfoService(InfoService infoService) {
		this.infoService = infoService;
	}
	@JSON(serialize=false) 
	public TeacherService getTeacherService() {
		return teacherService;
	}
	public void setTeacherService(TeacherService teacherService) {
		this.teacherService = teacherService;
	}
	public Info getInfo() {
		return info;
	}
	public void setInfo(Info info) {
		this.info = info;
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
	public String add(){
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session=request.getSession();
		User user=(User)session.getAttribute("user");
		if(user==null)
		{
			stateCode=0;
			message="会话超时或者没有登录！";
			return "sessionReject";
		}
		if(getInfo().getStyle()==2&&user.getType()==2)
		{
			Teacher tc=this.getTeacherService().findTeacherById(user.getId());
			if(tc==null)
			{
				stateCode=3;
				message="出现未知错误！";
				return "deleteReject";
			}
			getInfo().setAuthor(user.getName());
			if(tc.getCourseName()!=null&&!tc.getCourseName().equals(""))
			{
				getInfo().setCourseId(tc.getCourseId());
				getInfo().setCourseName(tc.getCourseName());
			}
			getInfo().setAuthor(user.getName());
			getInfo().setTeacherId(tc.getId());
			getInfo().setTeacherName(tc.getName());
			this.getInfoService().save(info);
			stateCode=1;
			message="发布成功！";
			return "AddSuccess";
		}
		getInfo().setAuthor(user.getName());
		this.getInfoService().save(info);
		stateCode=1;
		message="发布成功！";
		return "AddSuccess";
	}
	public String delete(){
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session=request.getSession();
		User user=(User)session.getAttribute("user");
		Info in=this.getInfoService().findInfoById(id);
		if(in==null)
		{
			stateCode=3;
			message="出现未知错误！";
			return "deleteReject";
		}
		if(user==null)
		{
			stateCode=0;
			message="会话超时或者没有登录！";
			return "sessionReject";
		}
		if(user.getType()==2)
		{
			if(user.getId()==in.getTeacherId()||user.getName().equals(in.getAuthor()))
			{
				this.getInfoService().delete(in);
				stateCode=1;
				message="删除成功！";
				return "deleteSuccess";
			}
			else
			{
				System.out.println(user.getId()+">>>>>"+in.getTeacherId());
				stateCode=2;
				message="没有权限！";
				return "deleteReject";
			}
		}else if(user.getType()==3)
		{
			this.getInfoService().delete(in);
			stateCode=1;
			message="删除成功！";
			return "deleteSuccess";
		}
		else
		{
			stateCode=2;
			message="删除失败或没有权限！";
			return "deleteReject";
		}
	}
	public String detail(){
		HttpServletRequest request = ServletActionContext.getRequest();
		Info tempInfo=this.getInfoService().findInfoById(id);
		request.setAttribute("info",tempInfo);
		return "detailSuccess";
	}
}
