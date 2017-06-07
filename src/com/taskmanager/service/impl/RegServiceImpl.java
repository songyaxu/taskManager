package com.taskmanager.service.impl;

import java.util.List;

import com.taskmanager.dao.RegDAO;
import com.taskmanager.entity.Reg;
import com.taskmanager.service.RegService;

public class RegServiceImpl implements RegService{
	private RegDAO regDao;

	public RegDAO getRegDao() {
		return regDao;
	}

	public void setRegDao(RegDAO regDao) {
		this.regDao = regDao;
	}

	@Override
	public Reg findById(int id) {
		return this.getRegDao().findById(id);
	}

	@Override
	public Reg findByStudentNo(String no) {
		return this.getRegDao().findByStudentNo(no);
	}

	@Override
	public void delete(Reg reg) {
		this.getRegDao().delete(reg);
	}
	
	@Override
	public List<Reg> findByTeacherId(int id) {
		return this.getRegDao().findByTeacherId(id);
	}

	@Override
	public void update(Reg reg) {
		this.getRegDao().update(reg);
	}

	@Override
	public void save(Reg reg) {
		this.getRegDao().save(reg);
	}
	
}
