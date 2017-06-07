package com.taskmanager.service.impl;

import java.util.List;

import com.taskmanager.dao.StudentDAO;
import com.taskmanager.entity.Page;
import com.taskmanager.entity.Student;
import com.taskmanager.service.StudentService;

public class StudentServiceImpl implements StudentService{

	@Override
	public List<Student> queryByPage(Page page) {
		return this.getStudentDao().queryByPage(page);
	}

	@Override
	public int studentCounts() {
		return this.getStudentDao().studentCounts();
	}

	private StudentDAO studentDao;
	
	public StudentDAO getStudentDao() {
		return studentDao;
	}

	public void setStudentDao(StudentDAO studentDao) {
		this.studentDao = studentDao;
	}

	@Override
	public Student findStudentByNo(String no) {
		return this.getStudentDao().findStudentByNo(no);
	}

	@Override
	public Student findStudentById(int id) {
		return this.getStudentDao().findStudentById(id);
	}

	@Override
	public Student loginStudent(Student student) {
		return this.getStudentDao().loginStudent(student);
	}

	@Override
	public void update(Student student) {
		this.getStudentDao().update(student);
	}

	@Override
	public void delete(Student student) {
		this.getStudentDao().delete(student);
	}

	@Override
	public void save(Student student) {
		this.getStudentDao().save(student);
	}
	
}
