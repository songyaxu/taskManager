package com.taskmanager.dao;

import java.util.List;

import com.taskmanager.entity.Admin;
import com.taskmanager.entity.Page;

public interface AdminDAO {
	public Admin findAdminById(int id);
	public Admin findAdminByNo(String no);
	public Admin loginAdmin(Admin admin);
	public void update(Admin admin);
	public void delete(Admin admin);
	public void save(Admin admin);
	public List<Admin> queryByPage(final Page page);
	public int adminCounts();
}
