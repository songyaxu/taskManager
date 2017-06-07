package com.taskmanager.service.impl;

import java.util.List;

import com.taskmanager.dao.CourseDAO;
import com.taskmanager.entity.Course;
import com.taskmanager.entity.Page;
import com.taskmanager.service.CourseService;

public class CourseServiceImpl implements CourseService{
	private CourseDAO courseDao;

	public CourseDAO getCourseDao() {
		return courseDao;
	}

	public void setCourseDao(CourseDAO courseDao) {
		this.courseDao = courseDao;
	}

	@Override
	public Course findById(int id) {
		return this.getCourseDao().findById(id);
	}

	@Override
	public void delete(Course course) {
		this.getCourseDao().delete(course);
	}

	@Override
	public void save(Course course) {
		this.getCourseDao().save(course);
	}

	@Override
	public List<Course> queryByPage(Page page) {
		return this.getCourseDao().queryByPage(page);
	}

	@Override
	public Course findByNo(String no) {
		return this.getCourseDao().findByNo(no);
	}

	@Override
	public int courseCounts() {
		return this.getCourseDao().courseCounts();
	}

	@Override
	public void update(Course course) {
		this.getCourseDao().update(course);
	}

	@Override
	public List<Course> searchByName(Page page, String keyword, int style) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Course findByTeacherId(int id) {
		return this.getCourseDao().findByTeacherId(id);
	}
	
}
