package com.taskmanager.dao;

import java.util.List;

import com.taskmanager.entity.Page;
import com.taskmanager.entity.Student;

public interface StudentDAO {
	public Student findStudentByNo(String no);
	public Student findStudentById(int id);
	public Student loginStudent(Student student);
	public void update(Student student);
	public void delete(Student student);
	public void save(Student student);
	public List<Student> queryByPage(final Page page);
	public int studentCounts();
}
