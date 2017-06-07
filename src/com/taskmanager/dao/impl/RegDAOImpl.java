package com.taskmanager.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.taskmanager.dao.RegDAO;
import com.taskmanager.entity.Reg;

public class RegDAOImpl extends HibernateDaoSupport implements RegDAO{

	@Override
	public Reg findById(int id) {
		return this.getHibernateTemplate().get(Reg.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Reg findByStudentNo(String no) {
		String hql = "from Reg r where r.no='"
				+ no + "'";
		List<Reg> regs = (List<Reg>) this.getHibernateTemplate().find(hql);
		if (regs.size() > 0) {
			return regs.get(0);
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Reg> findByTeacherId(int id) {
		String hql = "from Reg r where r.teacherId="+ id+" and r.state!=1";
		List<Reg> regs = (List<Reg>) this.getHibernateTemplate().find(hql);
		return regs;
	}

	@Override
	public void delete(Reg reg) {
		this.getHibernateTemplate().delete(reg);
	}

	@Override
	public void update(Reg reg) {
		this.getHibernateTemplate().update(reg);
	}

	@Override
	public void save(Reg reg) {
		this.getHibernateTemplate().save(reg);
	}
	
}
