package com.taskmanager.service;

import java.util.List;

import com.taskmanager.entity.Reg;

public interface RegService {
	public Reg findById(int id);
	public Reg findByStudentNo(String no);
	public List<Reg> findByTeacherId(int id);
	public void delete(Reg reg);
	public void update(Reg reg);
	public void save(Reg reg);
}
