package com.taskmanager.dao;

import java.util.List;

import com.taskmanager.entity.Page;
import com.taskmanager.entity.Teacher;

public interface TeacherDAO {
	public Teacher findTeacherByNo(String no);
	public Teacher findTeacherById(int id);
	public Teacher loginTeacher(Teacher teacher);
	public List<Teacher> queryByPage(final Page page);
	public int teacherCounts();
	public void update(Teacher teacher);
	public void delete(Teacher teacher);
	public void save(Teacher teacher);
}
