package com.taskmanager.dao;

import java.util.List;

import com.taskmanager.entity.Course;
import com.taskmanager.entity.Page;

public interface CourseDAO {
	public Course findById(int id);
	public Course findByTeacherId(int id);
	public Course findByNo(String no);
	public void delete(Course course);
	public void save(Course course);
	public void update(Course course);
	public List<Course> queryByPage(final Page page);
	public int courseCounts();
	public List<Course> searchByName(final Page page,String keyword,int style);
}
