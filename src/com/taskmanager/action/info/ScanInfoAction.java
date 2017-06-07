package com.taskmanager.action.info;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.taskmanager.entity.Info;
import com.taskmanager.entity.Page;
import com.taskmanager.service.InfoService;
import com.taskmanager.util.PageUtil;


@SuppressWarnings("serial")
public class ScanInfoAction extends ActionSupport{
	private Info info;
	private Page infoPage;
	private int currentPage;
	private int style;
	private InfoService infoService;
	private final int everyPage=10;
	
	public Info getInfo() {
		return info;
	}
	public void setInfo(Info info) {
		this.info = info;
	}
	
	public Page getInfoPage() {
		return infoPage;
	}
	public void setInfoPage(Page infoPage) {
		this.infoPage = infoPage;
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
	public InfoService getInfoService() {
		return infoService;
	}
	public void setInfoService(InfoService infoService) {
		this.infoService = infoService;
	}
	public int getEveryPage() {
		return everyPage;
	}
	public String execute(){
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session=request.getSession();
		int totalCount=this.infoService.InfoCounts("0", "0", style);
		setInfoPage(PageUtil.createPage(everyPage, totalCount, 0));
		List<Info> infos=this.infoService.queryByPage(infoPage,style);
		session.setAttribute("infos", infos);
		session.setAttribute("infoPage", infoPage);
		return SUCCESS;
	}
	public String nextPage(){
		//ÏÂÒ»Ò³
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session=request.getSession();
		int totalCount=this.infoService.InfoCounts("0","0",style);
		infoPage=PageUtil.createPage(everyPage, totalCount, currentPage+1);
		List<Info> infos=this.infoService.queryByPage(infoPage,style);
		session.setAttribute("infos", infos);
		session.setAttribute("infoPage", infoPage);
		return "NextPage";
		
	}
	public String frontPage(){
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session=request.getSession();
		int totalCount=this.infoService.InfoCounts("0","0",style);
		infoPage=PageUtil.createPage(everyPage, totalCount,currentPage-1);
		List<Info> infos=this.infoService.queryByPage(infoPage,style);
		session.setAttribute("infos", infos);
		session.setAttribute("infoPage", infoPage);
		return "frontPage";
	}
}
