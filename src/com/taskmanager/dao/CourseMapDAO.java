package com.taskmanager.dao;

import java.util.List;

import com.taskmanager.entity.CourseMap;
import com.taskmanager.entity.Page;

public interface CourseMapDAO {
	public CourseMap findByStudentId(int id);
	public CourseMap findByStudentNo(String no);
	public void update(CourseMap courseMap);
	public void delete(CourseMap courseMap);
	public void save(CourseMap courseMap);
	public List<CourseMap> queryByPage(final Page page,int courseId);
	public List<CourseMap> queryByStudentId(int studentId);
	public int courseMapCounts(int courseId);
}
