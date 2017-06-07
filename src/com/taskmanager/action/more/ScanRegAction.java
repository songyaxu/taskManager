package com.taskmanager.action.more;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.taskmanager.entity.Reg;
import com.taskmanager.entity.User;
import com.taskmanager.service.RegService;

public class ScanRegAction extends ActionSupport{
	/**
	 * 
	 */
	private static final long serialVersionUID = -163027517201823477L;
	private RegService regService;
	private Reg reg;
	public RegService getRegService() {
		return regService;
	}
	public void setRegService(RegService regService) {
		this.regService = regService;
	}
	public Reg getReg() {
		return reg;
	}
	public void setReg(Reg reg) {
		this.reg = reg;
	}
	public String execute(){
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session=request.getSession();
		User user=(User) session.getAttribute("user");
		List<Reg> regs=this.regService.findByTeacherId(user.getId());
		session.setAttribute("regs", regs);
		return SUCCESS;
	}
}
