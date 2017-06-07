package com.taskmanager.action.info;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.taskmanager.entity.Info;
import com.taskmanager.entity.Page;
import com.taskmanager.service.InfoService;
import com.taskmanager.util.PageUtil;


@SuppressWarnings("serial")
public class SearchInfoAction extends ActionSupport{
	private final int everyPage=10;
	private int currentPage;
	private Page searchInfoPage;
	private Info info;
	private int style;
	private String keyword;
	private String columnName;
	private InfoService infoService;
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	
	public Page getSearchInfoPage() {
		return searchInfoPage;
	}
	public void setSearchInfoPage(Page searchInfoPage) {
		this.searchInfoPage = searchInfoPage;
	}
	public Info getInfo() {
		return info;
	}
	public void setInfo(Info info) {
		this.info = info;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
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
	
	public int getStyle() {
		return style;
	}
	public void setStyle(int style) {
		this.style = style;
	}
	
	public String getColumnName() {
		return columnName;
	}
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}
	public String execute(){
		HttpServletRequest request = ServletActionContext.getRequest();
		int totalCount=this.getInfoService().InfoCounts(columnName, keyword, style);
		setSearchInfoPage(PageUtil.createPage(everyPage, totalCount, 0));
		List<Info> notices=this.getInfoService().searchByName(searchInfoPage, keyword, totalCount);
		request.setAttribute("searchNotices", notices);
		request.setAttribute("searchNoticePage", searchInfoPage);
		return SUCCESS;
	}
}
