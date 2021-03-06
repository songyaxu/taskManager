package com.taskmanager.action.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.json.annotations.JSON;

import com.opensymphony.xwork2.ActionSupport;
import com.taskmanager.entity.Admin;
import com.taskmanager.entity.User;
import com.taskmanager.service.AdminService;
import com.taskmanager.util.MD5Util;

@SuppressWarnings("serial")
public class AdminAction extends ActionSupport{
	private int id;
	private Admin admin;
	private String message;
	private AdminService adminService;
	
	
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
	
	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}
	@JSON(serialize=false)
	public AdminService getAdminService() {
		return adminService;
	}

	public void setAdminService(AdminService adminService) {
		this.adminService = adminService;
	}
	public String logout(){
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		session.removeAttribute("user");
		message="用户已登出！";
		return ERROR;
	}
	public String add(){
		Admin ad=this.getAdminService().findAdminByNo(getAdmin().getNo());
		if(ad!=null)
		{
			message="管理员编号已存在！";
			return "addFailure";
		}
		admin.setPwd(MD5Util.md5(admin.getPwd()));
		this.getAdminService().save(admin);
		message="添加成功！";
		return "addSuccess";
	}
	public String delete(){
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		User user=(User) session.getAttribute("user");
		Admin ad=getAdminService().findAdminById(id);
		if(ad==null)
		{
			message="出现未知问题！";
			return "deleteFailure";
		}else if(ad.getNo().equals(user.getNo()))
		{
			message="不能删除自己！";
			return "deleteFailure";
		}
		this.getAdminService().delete(ad);
		message="删除成功！";
		return "deleteSuccess";
	}
	public String update(){
		Admin ad=this.getAdminService().findAdminById(id);
		if(ad==null)
		{
			message="出现未知问题！";
			return "updateFailure";
		}
		if(admin.getPwd()!=null&&!admin.getPwd().trim().equals(""))
			ad.setPwd(MD5Util.md5(admin.getPwd().trim()));
		if(admin.getName()!=null&&!admin.getName().trim().equals(""))
			ad.setName(admin.getName().trim());
		this.getAdminService().update(ad);
		message="更新成功！";
		return "updateSuccess";
	}
	public String edit(){
		admin=getAdminService().findAdminById(id);
		return "edit";
	}
}
