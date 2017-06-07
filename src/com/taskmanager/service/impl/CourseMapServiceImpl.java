package com.taskmanager.service.impl;

import java.util.List;

import com.taskmanager.dao.CourseMapDAO;
import com.taskmanager.entity.CourseMap;
import com.taskmanager.entity.Page;
import com.taskmanager.service.CourseMapService;

public class CourseMapServiceImpl implements CourseMapService{
	private CourseMapDAO courseMapDao;

	public CourseMapDAO getCourseMapDao() {
		return courseMapDao;
	}

	public void setCourseMapDao(CourseMapDAO courseMapDao) {
		this.courseMapDao = courseMapDao;
	}

	@Override
	public CourseMap findByStudentId(int id) {
		return this.getCourseMapDao().findByStudentId(id);
	}

	@Override
	public CourseMap findByStudentNo(String no) {
		return this.getCourseMapDao().findByStudentNo(no);
	}

	@Override
	public void update(CourseMap courseMap) {
		this.getCourseMapDao().update(courseMap);
	}

	@Override
	public void delete(CourseMap courseMap) {
		this.getCourseMapDao().delete(courseMap);
	}

	@Override
	public void save(CourseMap courseMap) {
		this.getCourseMapDao().save(courseMap);
	}

	@Override
	public List<CourseMap> queryByPage(Page page, int courseId) {
		return this.getCourseMapDao().queryByPage(page, courseId);
	}

	@Override
	public int courseMapCounts(int courseId) {
		return this.getCourseMapDao().courseMapCounts(courseId);
	}

	@Override
	public List<CourseMap> queryByStudentId(int studentId) {
		return this.getCourseMapDao().queryByStudentId(studentId);
	}
	
}
