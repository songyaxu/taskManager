package com.taskmanager.service.impl;

import java.util.List;

import com.taskmanager.dao.AdminDAO;
import com.taskmanager.entity.Admin;
import com.taskmanager.entity.Page;
import com.taskmanager.service.AdminService;

public class AdminServiceImpl implements AdminService{
	
	@Override
	public Admin findAdminById(int id) {
		return this.getAdminDao().findAdminById(id);
	}

	@Override
	public List<Admin> queryByPage(Page page) {
		return this.getAdminDao().queryByPage(page);
	}

	@Override
	public int adminCounts() {
		return this.getAdminDao().adminCounts();
	}

	private AdminDAO adminDao;
	
	public AdminDAO getAdminDao() {
		return adminDao;
	}

	public void setAdminDao(AdminDAO adminDao) {
		this.adminDao = adminDao;
	}

	@Override
	public Admin findAdminByNo(String no) {
		return this.adminDao.findAdminByNo(no);
	}

	@Override
	public Admin loginAdmin(Admin admin) {
		return this.adminDao.loginAdmin(admin);
	}

	@Override
	public void update(Admin admin) {
		this.getAdminDao().update(admin);
	}

	@Override
	public void delete(Admin admin) {
		this.getAdminDao().delete(admin);
	}

	@Override
	public void save(Admin admin) {
		this.getAdminDao().save(admin);
	}
	
}
