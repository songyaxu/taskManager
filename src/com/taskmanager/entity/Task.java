package com.taskmanager.entity;

import java.sql.Timestamp;

public class Task {
	private int id;
	private String name;
	private int courseId;
	private String courseNo;
	private String courseName;
	private int teacherId;
	private String teacherName;
	private String content;
	private String attach;
	private Timestamp startTime;
	private Timestamp endTime;
	
	/*
	 * 连接查询使用字段
	 * private int studentId;
	private String studentName;
	private String studentNo;
	 */
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getCourseId() {
		return courseId;
	}
	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public int getTeacherId() {
		return teacherId;
	}
	public void setTeacherId(int teacherId) {
		this.teacherId = teacherId;
	}
	public String getTeacherName() {
		return teacherName;
	}
	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getAttach() {
		return attach;
	}
	public void setAttach(String attach) {
		this.attach = attach;
	}
	public String getCourseNo() {
		return courseNo;
	}
	public void setCourseNo(String courseNo) {
		this.courseNo = courseNo;
	}
	public Timestamp getStartTime() {
		return startTime;
	}
	public void setStartTime(Timestamp startTime) {
		this.startTime = startTime;
	}
	public Timestamp getEndTime() {
		return endTime;
	}
	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}
	public Task(){
		
	}
	public Task(int id,String name,String courseName,int courseId,int teacherId,String teacherName){
		this.id=id;
		this.name=name;
		this.courseName=courseName;
		this.courseId=courseId;
		this.teacherId=teacherId;
		this.teacherName=teacherName;
	}
}
